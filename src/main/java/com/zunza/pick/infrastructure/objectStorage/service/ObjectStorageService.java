package com.zunza.pick.infrastructure.objectStorage.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.zunza.pick.infrastructure.objectStorage.controller.response.UploadImageResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ObjectStorageService {

	private final AmazonS3 amazonS3;

	@Value("${ncp.object-storage.endpoint}")
	private String endPoint;

	@Value("${ncp.object-storage.bucket-name}")
	private String bucketName;

	private static final String FOLDER_PREFIX = "product/";
	private static final String CONTENT_TYPE_PREFIX = "image/";

	public UploadImageResponse uploadFile(List<MultipartFile> files) throws IOException {

		List<String> images = new ArrayList<>();

		for (MultipartFile file : files) {
			try {
				String originalFilename = file.getOriginalFilename();
				String ext = getExt(originalFilename);
				String key = getKey(originalFilename, ext);

				ObjectMetadata metaData = new ObjectMetadata();
				metaData.setContentLength(file.getSize());
				metaData.setContentType(CONTENT_TYPE_PREFIX + ext);

				amazonS3.putObject(
					new PutObjectRequest(bucketName, key, file.getInputStream(), metaData)
						.withCannedAcl(CannedAccessControlList.PublicRead));

				String imageUrl = getImageUrl(key);
				images.add(imageUrl);

			} catch (AmazonS3Exception e) {
				e.printStackTrace();
			} catch(SdkClientException e) {
				e.printStackTrace();
			}
		}

		return new UploadImageResponse(images);
	}

	private String getExt(String originalFilename) {
		return originalFilename.substring(originalFilename.indexOf(".") + 1);
	}

	private String getKey(String filename, String ext) {
		String role = filename.substring(0, filename.indexOf("-") + 1);
		return FOLDER_PREFIX + role + UUID.randomUUID() + "." + ext;
	}

	private String getImageUrl(String key) {
		return endPoint + "/" + bucketName + "/" + key;
	}
}

package com.zunza.pick.infrastructure.objectStorage.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zunza.pick.infrastructure.objectStorage.controller.response.UploadImageResponse;
import com.zunza.pick.infrastructure.objectStorage.service.ObjectStorageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ObjectStorageController {

	private final ObjectStorageService objectStorageService;

	@PostMapping("/api/seller/products/images")
	public ResponseEntity<UploadImageResponse> uploadImage(@RequestPart List<MultipartFile> files) throws
		IOException {
		UploadImageResponse uploadImageResponses = objectStorageService.uploadFile(files);
		return ResponseEntity.ok(uploadImageResponses);
	}
}

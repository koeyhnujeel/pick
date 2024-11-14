package com.zunza.pick.infrastructure.objectStorage.controller.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UploadImageResponse {
	private List<String> imageUrl;
}

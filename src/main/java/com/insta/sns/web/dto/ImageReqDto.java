package com.insta.sns.web.dto;

import org.springframework.web.multipart.MultipartFile;

import com.insta.sns.domain.image.Image;
import com.insta.sns.domain.user.User;

import lombok.Data;

@Data
public class ImageReqDto {
	private MultipartFile file;
	private String caption;
	private String location;
	private String tags;

	public Image toEntity(String imageUrl, User userEntity) {
		return Image.builder()
				.location(location)
				.caption(caption)
				.imageUrl(imageUrl)
				.user(userEntity)
				.build();
	}
}

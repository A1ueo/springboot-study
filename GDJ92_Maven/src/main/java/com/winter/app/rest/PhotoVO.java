package com.winter.app.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class PhotoVO {

	private Long albumId;
	private Long id;
	private String title;
	private String url;
	private String thumbnailUrl;
}

package com.example.infsecondsemsemesterwork.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class CloudinaryHelper {
	@Getter
	private static Cloudinary cloudinary;

	public CloudinaryHelper() {
		if (cloudinary == null) {
			cloudinary = new Cloudinary(ObjectUtils.asMap(
					"cloud_name", "sasha29",
					"api_key", "989221667791394",
					"api_secret", "xmXEZoJhqoZ6uXnrECpAYviIPog"));
		}
	}

}
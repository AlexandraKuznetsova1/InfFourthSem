package com.example.infsecondsemsemesterwork.util;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;

@Component
public class ImageHelper {
	public File makeFile(MultipartFile part) throws IOException {
		String fileName = Paths.get(Objects.requireNonNull(part.getOriginalFilename())).getFileName().toString();
		InputStream content = part.getInputStream();
		File file = new File(fileName);
		FileOutputStream outputStream = new FileOutputStream(file);
		byte[] buffer = new byte[content.available()];
		content.read(buffer);
		outputStream.write(buffer);

		return file;
	}

	public String createUrl(MultipartFile pic) {
		File file;
		String url;
		try {
			file = makeFile(pic);
			Map upload = CloudinaryHelper.getCloudinary().uploader()
					.upload(file, ObjectUtils.asMap("public_id", file.getName()));
			url = (String) upload.get("url");
			file.delete();
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}

		return url;
	}
}

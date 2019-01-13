package com.atguigu.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MyFileUpload {

	public static List<String> upload_image(MultipartFile[] files) {

		String path = MyPropertyUtil.getProperty("myUpload.properties", "windows_path");

		List<String> list_image = new ArrayList<String>();

		for (int i = 0; i < files.length; i++) {

			if (!files[i].isEmpty()) {
				String originalFilename = files[i].getOriginalFilename();

				// UUID randomUUID = UUID.randomUUID();
				String name = System.currentTimeMillis() + originalFilename;
				String upload_name = path + "/" + name;

				try {
					files[i].transferTo(new File(upload_name));
					list_image.add(name);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return list_image;
	}

}

package com.atguigu.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class MyUploadUtil {

	public static List<String> upload_image(MultipartFile[] files) {
		List<String> list_image_name = new ArrayList<String>();
		// 获得上传地址
		String myProperty = MyPropertiesUtil.getMyProperty("img_path.properties", "windows_path");

		for (int i = 0; i < files.length; i++) {

			if (!files[i].isEmpty()) {
				// UUID randomUUID = UUID.randomUUID();

				String originalFilename = System.currentTimeMillis() + files[i].getOriginalFilename();

				String upload_name = myProperty + "/" + originalFilename;

				try {
					files[i].transferTo(new File(upload_name));
					list_image_name.add(originalFilename);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return list_image_name;
	}

}

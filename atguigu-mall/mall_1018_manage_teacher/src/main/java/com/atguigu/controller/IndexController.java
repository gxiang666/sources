package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("goto_sku")
	public String goto_sku() {
		return "sku";
	}

	@RequestMapping("goto_attr")
	public String goto_attr() {
		return "attr";
	}

	@RequestMapping("goto_spu")
	public String goto_spu() {
		return "spu";
	}

	@RequestMapping("index")
	public String index(String url, String title, ModelMap map) {

		map.put("url", url);
		map.put("title", title);
		return "main";
	}

}

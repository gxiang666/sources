package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 展示页面---2017-6-27 08:39:40
 *@author Daniel
 *@date: 2017年6月27日上午8:39:34
 */
@Controller
public class PageController {

	@RequestMapping("/")//请求路径
	public String showIndex(){
		return"index";
	}
	/**
	 * 页面的展示各个页面显示出来。2017-6-27 09:57:12
	 * @param page
	 * @return
	 */
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page){
		return page;
	}	
}

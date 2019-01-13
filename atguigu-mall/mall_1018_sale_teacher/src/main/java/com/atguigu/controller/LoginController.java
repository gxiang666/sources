package com.atguigu.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.server.LoginServerInf;
import com.atguigu.server.TestServerInf;
import com.atguigu.service.CartServiceInf;
import com.atguigu.util.MyJsonUtil;

@Controller
public class LoginController {

	@Autowired
	CartServiceInf cartServiceInf;

	@Autowired
	LoginServerInf loginServerInf;

	@Autowired
	TestServerInf testServerInf;

	@RequestMapping("login")
	public String goto_login(@RequestParam(value = "redirect", required = false) String redirect,
			@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie,
			HttpServletResponse response, HttpSession session, T_MALL_USER_ACCOUNT user, HttpServletRequest request,
			String dataSource_type, ModelMap map) {

		T_MALL_USER_ACCOUNT select_user = new T_MALL_USER_ACCOUNT();// loginMapper.select_user(user);

		// 登陆，远程用户认证接口
		String loginJson = "";
		if (dataSource_type.equals("1")) {
			loginJson = loginServerInf.login(user);
			testServerInf.ping("hello");

		} else if (dataSource_type.equals("2")) {
			loginJson = loginServerInf.login2(user);
		}

		select_user = MyJsonUtil.json_to_object(loginJson, T_MALL_USER_ACCOUNT.class);

		if (select_user == null) {
			return "redirect:/login.do";
		} else {
			session.setAttribute("user", select_user);

			// 在客户端存储用户个性化信息，方便用户下次再访问网站时使用
			try {
				Cookie cookie = new Cookie("yh_mch", URLEncoder.encode(select_user.getYh_mch(), "utf-8"));
				// cookie.setPath("/");
				cookie.setMaxAge(60 * 60 * 24);
				response.addCookie(cookie);

				Cookie cookie2 = new Cookie("yh_nch", URLEncoder.encode("周润发", "utf-8"));
				// cookie.setPath("/");
				cookie2.setMaxAge(60 * 60 * 24);
				response.addCookie(cookie2);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 同步购物车数据
			combine_cart(select_user, response, session, list_cart_cookie);

		}

		if (StringUtils.isBlank(redirect)) {
			return "redirect:/index.do";
		} else {
			return "redirect:/" + redirect;
		}

	}

	private void combine_cart(T_MALL_USER_ACCOUNT user, HttpServletResponse response, HttpSession session,
			String list_cart_cookie) {
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();

		if (StringUtils.isBlank(list_cart_cookie)) {
			//
		} else {
			// 判断db是否为空
			List<T_MALL_SHOPPINGCAR> list_cart_db = cartServiceInf.get_list_cart_by_user(user);
			list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);

			for (int i = 0; i < list_cart.size(); i++) {
				T_MALL_SHOPPINGCAR cart = list_cart.get(i);
				cart.setYh_id(user.getId());
				boolean b = cartServiceInf.if_cart_exists(list_cart.get(i));

				if (b) {
					// 更新
					for (int j = 0; j < list_cart_db.size(); j++) {
						if (cart.getSku_id() == list_cart_db.get(j).getSku_id()) {
							cart.setTjshl(cart.getTjshl() + list_cart_db.get(j).getTjshl());
							cart.setHj(cart.getTjshl() * cart.getSku_jg());
							// 老车，更新
							cartServiceInf.update_cart(cart);
						}
					}
				} else {
					// 添加
					cartServiceInf.add_cart(cart);
				}
			}
		}
		// 同步session，清空cookie
		session.setAttribute("list_cart_session", cartServiceInf.get_list_cart_by_user(user));
		response.addCookie(new Cookie("list_cart_cookie", ""));

		//
		// if (list_cart_db == null || list_cart_db.size() == 0) {
		// for (int i = 0; i < list_cart.size(); i++) {
		// list_cart.get(i).setYh_id(user.getId());
		// cartServiceInf.add_cart(list_cart.get(i));
		// }
		// } else {
		// for (int i = 0; i < list_cart.size(); i++) {
		//
		// boolean b = if_new_cart(list_cart_db, list_cart.get(i));
		//
		// if (b) {
		// list_cart.get(i).setYh_id(user.getId());
		// cartServiceInf.add_cart(list_cart.get(i));
		// } else {
		// for (int j = 0; j < list_cart_db.size(); j++) {
		// if (list_cart.get(j).getSku_id() ==
		// list_cart_db.get(j).getSku_id()) {
		// list_cart.get(j).setTjshl(list_cart.get(j).getTjshl() +
		// list_cart_db.get(j).getTjshl());
		// list_cart.get(j).setHj(list_cart.get(j).getTjshl() *
		// list_cart.get(j).getSku_jg());
		// // 老车，更新
		// cartServiceInf.update_cart(list_cart.get(j));
		// }
		// }
		// }
		// }
		// }
	}

	private boolean if_new_cart(List<T_MALL_SHOPPINGCAR> list_cart, T_MALL_SHOPPINGCAR cart) {
		boolean b = true;
		for (int i = 0; i < list_cart.size(); i++) {
			if (list_cart.get(i).getSku_id() == cart.getSku_id()) {
				b = false;
			}
		}
		return b;
	}

}

package com.atguigu.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.atguigu.bean.OBJECT_T_MALL_FLOW;
import com.atguigu.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_ORDER_INFO;
import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.exception.OverSaleException;
import com.atguigu.server.AddressServerInf;
import com.atguigu.service.CartServiceInf;
import com.atguigu.service.OrderServiceInf;

@Controller
@SessionAttributes("order")
public class OrderController {

	@Autowired
	OrderServiceInf orderServiceInf;
	@Autowired
	CartServiceInf cartServiceInf;

	@Autowired
	AddressServerInf addressServerInf;

	@RequestMapping("goto_checkOrder")
	public String goto_checkOrder(HttpSession session, ModelMap map) {
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();

		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		if (user == null) {
			return "redirect:/goto_login_checkOrder.do";
		} else {
			// session购物车列表
			list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");// 数据库

			OBJECT_T_MALL_ORDER order = new OBJECT_T_MALL_ORDER();// 订单对象
			order.setYh_id(user.getId());
			order.setJdh(1);
			order.setZje(get_sum(list_cart));

			// 结算业务
			// 根据购物车的选中状态，获得库存地址信息
			Set<String> set_kcdz = new HashSet<String>();
			for (int i = 0; i < list_cart.size(); i++) {
				if (list_cart.get(i).getShfxz().equals("1")) {
					// 去重
					set_kcdz.add(list_cart.get(i).getKcdz());
				}
			}

			// 根据库存地址封装送货清单
			List<OBJECT_T_MALL_FLOW> list_flow = new ArrayList<OBJECT_T_MALL_FLOW>();

			Iterator<String> iterator = set_kcdz.iterator();
			while (iterator.hasNext()) {
				String kcdz = iterator.next();
				// 根据库存地址生成送货清单
				OBJECT_T_MALL_FLOW flow = new OBJECT_T_MALL_FLOW();// 送货清单对象
				flow.setMqdd("商品未出库");
				flow.setPsfsh("硅谷快递");
				flow.setYh_id(user.getId());
				List<T_MALL_ORDER_INFO> list_info = new ArrayList<T_MALL_ORDER_INFO>();

				// 循环购物车，将购物车对象转化成订单信息
				for (int i = 0; i < list_cart.size(); i++) {
					if (list_cart.get(i).getShfxz().equals("1") && list_cart.get(i).getKcdz().equals(kcdz)) {

						T_MALL_SHOPPINGCAR cart = list_cart.get(i);
						T_MALL_ORDER_INFO info = new T_MALL_ORDER_INFO();

						// 将购物车转为订单信息
						info.setGwch_id(cart.getId());
						info.setShp_tp(cart.getShp_tp());
						info.setSku_id(cart.getSku_id());
						info.setSku_jg(cart.getSku_jg());
						info.setSku_kcdz(kcdz);
						info.setSku_mch(cart.getSku_mch());
						info.setSku_shl(cart.getTjshl());
						list_info.add(info);
					}
				}
				flow.setList_info(list_info);
				// 将送货清单放入送货清单 集合
				list_flow.add(flow);
			}
			// 送货清单放入主订单
			order.setList_flow(list_flow);// 内存中的对象，游离状态对象
			try {
				List<T_MALL_ADDRESS> list_address = addressServerInf.get_addresses(user);
				map.put("list_address", list_address);
			} catch (Exception e) {
				e.printStackTrace();
				// 处理用户系统调用异常
				return "redirect:/orderErr.do";
			}
			map.put("order", order);
		}

		return "checkOrder";
	}

	@RequestMapping("save_order")
	public String save_order(HttpSession session, @ModelAttribute("order") OBJECT_T_MALL_ORDER order,
			T_MALL_ADDRESS address) {
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");

		// 获取地址信息
		T_MALL_ADDRESS get_address = addressServerInf.get_address(address.getId());

		// 调用保存订单的业务
		orderServiceInf.save_order(get_address, order);

		// 重新同步session
		session.setAttribute("list_cart_session", cartServiceInf.get_list_cart_by_user(user));

		// 重定向到支付服务，传入订单号和交易金额
		return "realPay";
	}

	@RequestMapping("pay_success")
	public String pay_success(@ModelAttribute("order") OBJECT_T_MALL_ORDER order) {
		// 支付成功
		try {
			orderServiceInf.pay_success(order);
		} catch (OverSaleException e) {

			e.printStackTrace();
			return "redirect:/order_fail.do";
		}
		return "redirect:/order_success.do";
	}
	
	@RequestMapping("real_pay_success")
	@ResponseBody
	public String real_pay_success(@ModelAttribute("order") OBJECT_T_MALL_ORDER order) {
		// 支付成功
		try {
			orderServiceInf.pay_success(order);
		} catch (OverSaleException e) {

			e.printStackTrace();
			return "success";
		}
		return "success";
	}


	@RequestMapping("order_success")
	public String order_success() {
		return "orderSuccess";
	}

	@RequestMapping("order_fail")
	public String order_fail() {
		return "orderErr";
	}

	@RequestMapping("goto_pay")
	public String goto_pay() {
		// 伪支付服务
		return "pay";
	}

	private BigDecimal get_sum(List<T_MALL_SHOPPINGCAR> list_cart) {
		BigDecimal sum = new BigDecimal("0");
		for (int i = 0; i < list_cart.size(); i++) {
			if (list_cart.get(i).getShfxz().equals("1")) {
				sum = sum.add(new BigDecimal(list_cart.get(i).getHj() + ""));
			}
		}
		return sum;
	}

}

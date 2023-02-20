/*
날짜 : 2023/02/08~
이름 : 김동근
내용 : Kmarket2 SpringBoot product controller
*/
package kr.co.kmarket2.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.kmarket2.config.SessionConst;
import kr.co.kmarket2.repository.MemberRepo;
import kr.co.kmarket2.service.ProductService;
import kr.co.kmarket2.vo.CartVO;
import kr.co.kmarket2.vo.NavCateVO;
import kr.co.kmarket2.vo.OrderItemVO;
import kr.co.kmarket2.vo.OrderVO;
import kr.co.kmarket2.vo.ProductVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ProductController {
	
	@Autowired
	ProductService service;
	@Autowired
	private MemberRepo repo;
	
	// product/list
	@GetMapping("product/list")
	public String list(Model model, String option) {
		//네비 가져오기 -> cate1, cate2 추가할 것
		NavCateVO navCate = service.selectNavByCate("10", "10");
		model.addAttribute("navCate", navCate);
		//정렬 옵션
		if(option == null) { option = "sold"; }
		model.addAttribute("option", option);
		//상품 가져오기 -> cate1, cate2 추가할 것
		List<ProductVO> products = service.selectProductsByCate("10", "10", option, 0);
		model.addAttribute("products", products);
		
		return "product/list";
	}
	
	
	// product/view
	@GetMapping("product/view")
	public String view(Model model, int no) {
		ProductVO product = service.selectProduct(no);
		service.updateProductHit(no);
		model.addAttribute("product", product);
		
		return "product/view";
	}
	
	@ResponseBody
	@PostMapping("product/addCart")
	public int addCart(Principal principal, CartVO vo) {
		return service.addCart(principal.getName(), vo);
	}
	
	// product/cart
	@GetMapping("product/cart")
	public String cart(Model model, Principal principal) {
		if(principal != null) {
			List<CartVO> carts = service.selectCarts(principal.getName());
			model.addAttribute("carts", carts);
		}else {
			
		}
		return "product/cart";
	}
	
	@ResponseBody
	@PostMapping("product/deleteCart")
	public int deleteCart(Principal principal, @RequestParam(value="deleteList") List<Integer> deleteList) {
		if(principal != null) {
			return service.deleteCart(principal.getName(), deleteList);
		}else {
			return 0;
		}
	}
	
	
	
	// product/order
	@GetMapping("product/order")
	public String order(Principal principal, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<?> list = (List<?>) session.getAttribute(SessionConst.PRODUCT_ORDER);
		List<OrderItemVO> orderList = new ArrayList<>();
		for (Object object : list) {
			orderList.add((OrderItemVO)object);
		}
		model.addAttribute("orders", orderList);
		model.addAttribute("userPoint", repo.findById(principal.getName()).get().getPoint());
		model.addAttribute("userName", repo.findById(principal.getName()).get().getName());
		model.addAttribute("userHp", repo.findById(principal.getName()).get().getHp());
		return "product/order";
	}
	
	@ResponseBody
	@PostMapping("product/order")
	public int order(HttpServletRequest request, @RequestBody List<OrderItemVO> orderList) {
		HttpSession session = request.getSession();
		session.removeAttribute(SessionConst.PRODUCT_ORDER);
		session.setAttribute(SessionConst.PRODUCT_ORDER, orderList);
		return 1;
	}
	
	
	// product/complete
	@GetMapping("product/complete")
	public String complete(Principal principal, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		OrderVO orderInfo = (OrderVO) session.getAttribute(SessionConst.PRODUCT_COMEPLETE);
		service.completeOrder(principal.getName(), orderInfo);
		return "product/complete";
	}
	
	@ResponseBody
	@PostMapping("product/complete")
	public int complete(HttpServletRequest request, @RequestBody OrderVO orderInfo) {
		HttpSession session = request.getSession();
		session.removeAttribute(SessionConst.PRODUCT_COMEPLETE);
		session.setAttribute(SessionConst.PRODUCT_COMEPLETE, orderInfo);
		return 1;
	}
	
	
	
}
 
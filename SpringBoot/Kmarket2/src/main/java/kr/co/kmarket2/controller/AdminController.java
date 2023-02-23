/*
날짜 : 2023/02/15
이름 : 김훈
내용 : Kmarket2 SpringBoot Admin controller
*/
package kr.co.kmarket2.controller;

import java.util.List;

import org.aspectj.weaver.patterns.HasThisTypePatternTriedToSneakInSomeGenericOrParameterizedTypePatternMatchingStuffAnywhereVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.kmarket2.config.Pager;
import kr.co.kmarket2.service.AdminService;
import kr.co.kmarket2.vo.ProductVO;


@Controller
public class AdminController {
	@Autowired
	private AdminService service;
	
	/* 메인페이지 */
	@GetMapping("admin")
	public String index() {
		return "admin/index";
	}

    
    /* product */
    @GetMapping("admin/product/list")
	public String ProductList(Model model, String pg) {
    	
    	//페이징
		Pager pager = null;
		if(pg == null) { pager = new Pager(10, 1, service.selectCountProducts()); }
		else { pager = new Pager(10, Integer.parseInt(pg), service.selectCountProducts()); }
		model.addAttribute("pager", pager);
    	
    	//상품가져오기
		List<ProductVO> products = service.selectProducts(pager.getStart());
    	model.addAttribute("products", products);
    	
    	return "admin/product/list";
	}
    
    /* product - regiater*/
    @GetMapping("admin/product/register")
    public String ProductRegister() {
    	
    	return "admin/product/register";
    }
}

/*
날짜 : 2023/02/15
이름 : 김훈
내용 : Kmarket2 SpringBoot Admin controller
*/
package kr.co.kmarket2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.kmarket2.service.AdminService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService service;
	
	/* 메인페이지 */
	@GetMapping("")
	public String index() {
		return "admin/index";
	}
	
	/* cs - faq */
    @GetMapping("/cs/faq/list")
	public String FaqList() {
		return "admin/cs/faq/list";
	}
    
    @GetMapping("/cs/faq/modify")
    public String FaqModify() {
    	return "admin/cs/faq/modify";
    }
    
    @GetMapping("/cs/faq/view")
    public String FaqView() {
    	return "admin/cs/faq/view";
    }
    
    @GetMapping("/cs/faq/write")
    public String FaqWrite() {
    	return "admin/cs/faq/write";
    }
    
	/* cs - notice */
    @GetMapping("/cs/notice/list")
	public String NoticeList() {
		return "admin/cs/notice/list";
	}
    
    @GetMapping("/cs/notice/modify")
    public String NoticeModify() {
    	return "admin/cs/notice/modify";
    }
    
    @GetMapping("/cs/notice/view")
    public String NoticeView() {
    	return "admin/cs/notice/view";
    }
    
    @GetMapping("/cs/notice/write")
    public String NoticeWrite() {
    	return "admin/cs/notice/write";
    }
    
    /* cs - qna */
    @GetMapping("/cs/qna/list")
	public String QnaList() {
		return "admin/cs/qna/list";
	}
    
    @GetMapping("/cs/qna/view")
    public String QnaView() {
    	return "admin/cs/qna/view";
    }
    
    
    /* product */
    @GetMapping("/product/list")
	public String ProductList() {
		return "admin/product/list";
	}
    
    @GetMapping("/product/register")
    public String ProductRegister() {
    	return "admin/product/register";
    }
}

package kr.co.kmarket2.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.kmarket2.service.MyPageService;
import kr.co.kmarket2.vo.OrderVO;

@Controller
public class MyPageController {
	
	@Autowired
	MyPageService service;
	
	@GetMapping(value = {"my/", "my/home"})
    public String index(Principal principal, Model model){
		
		//최근주문내역
		OrderVO latestOrder = service.selectMyLatest(principal.getName());
		model.addAttribute("latestOrder", latestOrder);
		
        return "my/home";
    }
	
	@GetMapping("my/coupon")
    public String coupon(Model model){
        return "my/coupon";
    }
	
	@GetMapping("my/info")
    public String info(Model model){
        return "my/info";
    }
	
	@GetMapping("my/ordered")
    public String ordered(Model model){
        return "my/ordered";
    }
	
	@GetMapping("my/point")
    public String point(Model model){
        return "my/point";
    }
	
	@GetMapping("my/qna")
    public String qna(Model model){
        return "my/qna";
    }
	
	@GetMapping("my/review")
    public String review(Model model){
        return "my/review";
    }
}

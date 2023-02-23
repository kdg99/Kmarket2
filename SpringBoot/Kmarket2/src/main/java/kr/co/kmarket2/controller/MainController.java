package kr.co.kmarket2.controller;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.kmarket2.service.MainService;
import kr.co.kmarket2.vo.ProductVO;

@MapperScan("kr.co.kmarket2.dao")
@Controller
public class MainController {
	
	@Autowired
	private MainService service;
	
	
	@GetMapping(value = {"/", "index"})
    public String index(Model model){
		List<ProductVO> solds = service.selectProdSold();
		List<ProductVO> hits = service.selectProdHit();
		List<ProductVO> scores = service.selectProdScore();
		List<ProductVO> news = service.selectProdNew();
		List<ProductVO> discounts = service.selectProdDiscount();
		
		model.addAttribute("solds", solds);
		model.addAttribute("hits", hits);
		model.addAttribute("scores", scores);
		model.addAttribute("news", news);
		model.addAttribute("discounts", discounts);
		
        return "index";
    }
	
	
	
	
	
	
	
	
}

package kr.co.kmarket2.controller;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.kmarket2.service.MainService;
import kr.co.kmarket2.service.MemberService;
import kr.co.kmarket2.vo.ProductVO;
import kr.co.kmarket2.vo.TermsVO;

@MapperScan("kr.co.kmarket2.dao")
@Controller
public class MainController {
	
	@Autowired
	private MainService service;
	@Autowired
	private MemberService serviceMember;
	
	
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
	
	
	
	// termsPolicy
	@GetMapping(value = {"termsPolicy/", "termsPolicy/index", "termsPolicy/buyer"})
    public String termsIndex(Model model){
		TermsVO terms = serviceMember.selectTerms();
		model.addAttribute("terms", terms.getTerms());
		return "termsPolicy/buyer";
	}
	@GetMapping("termsPolicy/seller")
    public String termsSeller(Model model){
		TermsVO terms = serviceMember.selectTerms();
		model.addAttribute("terms", terms.getTax());
		return "termsPolicy/seller";
	}
	@GetMapping("termsPolicy/finance")
    public String termsFinance(Model model){
		TermsVO terms = serviceMember.selectTerms();
		model.addAttribute("terms", terms.getFinance());
		return "termsPolicy/finance";
	}
	@GetMapping("termsPolicy/location")
    public String termsLocation(Model model){
		TermsVO terms = serviceMember.selectTerms();
		model.addAttribute("terms", terms.getLocation());
		return "termsPolicy/location";
	}
	@GetMapping("termsPolicy/privacy")
    public String termsPrivacy(Model model){
		TermsVO terms = serviceMember.selectTerms();
		model.addAttribute("terms", terms.getPrivacy());
		return "termsPolicy/privacy";
	}
	
	
	
	
	
	// company
	@GetMapping(value = {"company/", "company/index"})
    public String companyIndex(Model model){
		return "company/index";
	}
	
	@GetMapping("company/introduce")
    public String companyIntroduce(Model model){
		return "company/introduce";
	}
	
	@GetMapping("company/manage")
    public String companyManage(Model model){
		return "company/manage";
	}
	
	@GetMapping("company/notice")
    public String companyNotice(Model model){
		return "company/notice";
	}
	
	@GetMapping("company/promote")
    public String companyPromote(Model model){
		return "company/promote";
	}
	
	
}

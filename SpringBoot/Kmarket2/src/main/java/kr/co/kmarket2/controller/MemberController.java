package kr.co.kmarket2.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.kmarket2.service.MemberService;
import kr.co.kmarket2.vo.MemberVO;
import kr.co.kmarket2.vo.TermsVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@GetMapping("member/login")
	public String login() {
		return "member/login";
	}
	
	@GetMapping("member/join")
	public String join() {
		return "member/join";
	}
	
	@GetMapping("member/register")
	public String register() {
		return "member/register";
	}
	
	@PostMapping("member/register")
	public String register(MemberVO vo, HttpServletRequest req) {
		String regip = req.getRemoteAddr();
		vo.setRegip(regip);
		int result = service.insertMember(vo);
		return "redirect:/user/login?success="+result;
	}
	
	
	@GetMapping("member/registerSeller")
	public String registerSeller() {
		return "member/registerSeller";
	}
	
	

	@GetMapping("member/terms")
	public String terms(Model model) {
		TermsVO vo = service.selectTerms();
		log.info("vo : " + vo);
		model.addAttribute(vo);
		return "member/terms";
	}
	
	@ResponseBody
	@GetMapping("member/checkUid")
	public Map<String, Integer> checkUid(String uid) {
		int result = service.countMember(uid);
		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);
		
		return map;
	}
	
	
	
}

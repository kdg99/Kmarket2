package kr.co.kmarket2.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.kmarket2.repository.MemberRepo;

@Controller
public class MainController {
	
	@Autowired
	private MemberRepo repo;
	
	
	@GetMapping(value = {"/", "index"})
    public String index(Model model){
		
		
		
        return "index";
    }
	
	
	
	
	
	
	
}

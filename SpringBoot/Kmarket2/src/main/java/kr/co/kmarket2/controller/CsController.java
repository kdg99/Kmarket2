/*
날짜 : 2023/02/10
이름 : 최아영
내용 : Kmarket2 STS CsController
*/
package kr.co.kmarket2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.kmarket2.entity.MemberEntity;
import kr.co.kmarket2.security.MyUserDetails;
import kr.co.kmarket2.service.CsService;
import kr.co.kmarket2.vo.Cs_Cate1VO;
import kr.co.kmarket2.vo.Cs_QnaVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CsController {
	
	@Autowired
	private CsService service;
	
	/* index */
    @GetMapping(value = {"cs", "cs/index"})
    public String index(){
        return "cs/index";
    }	
    
    /* Notice */
    @GetMapping(value = {"cs/notice/list"})
    public String NoticeList(){
        return "cs/notice/list";
    }
    
    @GetMapping(value = {"cs/notice/view"})
    public String NoticeView(){
        return "cs/notice/view";
    }
    
    /* Faq */
    @GetMapping(value = {"cs/faq/list"})
    public String FaqList(){
        return "cs/faq/list";
    }
    
    @GetMapping(value = {"cs/faq/view"})
    public String FaqView(){
        return "cs/faq/view";
    }
    
    /* Qna */
    @GetMapping(value = {"cs/qna/list"})
    public String QnaList(Model model, String pg, @RequestParam(value="cate1", defaultValue = "10") String cate1){
    	
    	 pg = (pg == null) ? "1" : pg;

    	 int currentPage = service.getCurrentPage(pg);
         int start = service.getLimitStart(currentPage);
         long total = service.getTotalCount(cate1);
         int lastPage = service.getLastPageNum(total);
         int pageStartNum = service.getPageStartNum(total, start);
         int groups[] = service.getPageGroup(currentPage, lastPage);

         Cs_Cate1VO ccv = service.selectCate1(cate1);
         List<Cs_QnaVO> cate1s = service.selectQnaArticles(start, cate1);
         
         
         /*log.info("currentPage : " + currentPage);
         log.info("lastPage : " + lastPage);
         log.info("pageStartNum : " + pageStartNum);
         log.info("groups : " + groups);
         log.info("ccv : " + ccv);
         log.info("cate1s : " + cate1s);
         log.info("cate1 : " + cate1);
         */
         
         model.addAttribute("currentPage", currentPage);
         model.addAttribute("lastPage", lastPage);
         model.addAttribute("pageStartNum", pageStartNum);
         model.addAttribute("groups", groups);
         model.addAttribute("ccv", ccv);
         model.addAttribute("cate1s", cate1s);
         model.addAttribute("cate1", cate1);
         
        return "cs/qna/list";
    }
    
    @GetMapping(value = {"cs/qna/view"})
    public String QnaView(Model model, int no, String cate1 ){
    	
    	 Cs_Cate1VO ccv = service.selectCate1(cate1);
         Cs_QnaVO vo = service.selectQnaArticle(no);

         model.addAttribute("vo", vo);
         model.addAttribute("cate1", cate1);

         return "cs/qna/view";
    	
    }
    
    @GetMapping(value = {"cs/qna/write"})
    public String write(Model model, @RequestParam("cate1") String cate1){

        model.addAttribute("cate1", cate1);

        return "cs/qna/write";
    }
    
    @PostMapping(value = {"cs/qna/write"})
    public String write(Model model,HttpServletRequest req, @AuthenticationPrincipal MyUserDetails myUser, Cs_QnaVO vo,
    		Integer cate1, Integer cate2){

        MemberEntity member = myUser.getMember();

        vo.setUid(member.getUid());
        vo.setCate1(String.valueOf(cate1));
        vo.setCate2(String.valueOf(cate2));
        vo.setRegip(req.getRemoteAddr());
        service.insertQnaArticle(vo);

        return "redirect:/cs/qna/list?cate1="+cate1;
    }
}

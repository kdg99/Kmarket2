/*
날짜 : 2023/02/10
이름 : 최아영
내용 : Kmarket2 STS CsController
*/
package kr.co.kmarket2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.kmarket2.service.CsService;
import kr.co.kmarket2.vo.Cs_Cate1VO;
import kr.co.kmarket2.vo.Cs_QnaVO;

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
    public String QnaList(Model model, String group, String pg, HttpServletRequest req){
    	
    	 pg = (pg == null) ? "1" : pg;
         group = (group == null) ? "1" : group;

         String cate1 = req.getParameter("cate1");

         int currentPage = service.getCurrentPage(pg);
         int start = service.getLimitStart(currentPage);
         long total = service.getTotalCount(cate1);
         int lastPage = service.getLastPageNum(total);
         int pageStartNum = service.getPageStartNum(total, start);
         int groups[] = service.getPageGroup(currentPage, lastPage);

         Cs_Cate1VO ccv = service.selectCate1(cate1);
         List<Cs_QnaVO> cate1s = service.selectQnaArticles(start, cate1);

         model.addAttribute("group", group);
         model.addAttribute("currentPage", currentPage);
         model.addAttribute("lastPage", lastPage);
         model.addAttribute("pageStartNum", pageStartNum);
         model.addAttribute("groups", groups);
         model.addAttribute("total", total);
         model.addAttribute("start", start);
         
         model.addAttribute("cate1", cate1);
         model.addAttribute("ccv", ccv);
         model.addAttribute("cate1s", cate1s);
         
        return "cs/qna/list";
    }
    
    @GetMapping(value = {"cs/qna/view"})
    public String QnaView(){
        return "cs/qna/view";
    }
    
    @GetMapping(value = {"cs/qna/write"})
    public String QnaWrite(){
        return "cs/qna/write";
    }
}

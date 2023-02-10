/*
날짜 : 2023/02/10
이름 : 최아영
내용 : Kmarket2 STS CsController
*/
package kr.co.kmarket2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CsController {
	
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
    public String QnaList(){
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

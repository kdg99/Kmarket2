/*
날짜 : 2023/02/13
이름 : 최아영
내용 : Kmarket2 STS CsSevice
*/
package kr.co.kmarket2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kmarket2.dao.CsDAO;
import kr.co.kmarket2.vo.Cs_Cate1VO;
import kr.co.kmarket2.vo.Cs_Cate2VO;
import kr.co.kmarket2.vo.Cs_FaqVO;
import kr.co.kmarket2.vo.Cs_QnaVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CsService {
		
		@Autowired
		private CsDAO dao;
		
		
		
		/* index */
				
		/* Notice */
		
		/* Faq */
		public List<Cs_Cate1VO> selectCs_cate1() {
		        return dao.selectCs_cate1();
		}
		public List<Cs_Cate2VO> selectCs_cate2(Integer cate1) {
	        return dao.selectCs_cate2(cate1);
	    }
		
		public List<Cs_FaqVO> selectCsFaqList(Integer cate1, Integer cate2) {
		        return dao.selectCsFaqList(cate1, cate2);
		}
		
		/* Qna */
		public Cs_Cate1VO selectCate1(String cate1){
	        return dao.selectCate1(cate1);
	    }
		
		public int insertQnaArticle(Cs_QnaVO vo){
			
	        int result = dao.insertQnaArticle(vo);
	        return result;
	    }
		
		public Cs_QnaVO selectQnaArticle(int no){
	        return dao.selectQnaArticle(no);
	    }
		
		public List<Cs_QnaVO> selectQnaArticles(int start, String cate1){
	        return dao.selectQnaArticles(start, cate1);
	    }
		
		public Cs_QnaVO selectCsQnaNo(int no){
	        return dao.selectCsQnaNo(no);
	    }

		public int getLimitStart(int currentPage){
	        return (currentPage - 1) * 10;
	    }
		
		public int getCurrentPage(String pg){
		        int currentPage = 1;
		        if (pg != null){
		            currentPage = Integer.parseInt(pg);
		        }
		        return currentPage;
		 }
		
		public long getTotalCount(String cate1){
		        return dao.selectCountTotal(cate1);
		}
		
		public int getLastPageNum(long total){
	        int lastPage = 0;

	        if (total % 10 == 0){
	            lastPage = (int) (total/10);
	        }else{
	            lastPage = (int) ((total/10) + 1);
	        }
	        return lastPage;
	    }
		
		public int getPageStartNum(long total, int start){
		        return (int) (total - start);
		}
		
		public int[] getPageGroup(int currentPage, int lastPage){
	        int groupCurrent = (int) Math.ceil(currentPage/10.0);
	        int groupStart = (groupCurrent - 1) * 10 + 1;
	        int groupEnd = groupCurrent * 10;

	        if (groupEnd > lastPage){
	            groupEnd = lastPage;
	        }

	        int[] groups = {groupStart, groupEnd};

	        return groups;
	    }
		
}

/*
날짜 : 2023/02/13
이름 : 최아영
내용 : Kmarket2 STS CsService
*/
package kr.co.kmarket2.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.co.kmarket2.vo.Cs_Cate1VO;
import kr.co.kmarket2.vo.Cs_QnaVO;

@Repository
@Mapper
public interface CsDAO {

		public Cs_Cate1VO selectCate1(@Param("cate1")String cate1);
		/* index */
		
		/* Notice */
		
		/* Faq */
		
		/* Qna */
		public int insertQnaArticle(Cs_QnaVO vo);
		public Cs_QnaVO selectQnaArticle(@Param("no")int no);
		public List<Cs_QnaVO> selectQnaArticles(@Param("start")int start,@Param("cate1") String cate1);
		public Cs_QnaVO selectCsQnaNo(int no);
		public int selectCountTotal(@Param("cate1")String cate1);
}

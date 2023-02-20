/*
 * 날짜 : 2023/02/16
 * 이름 : 김훈
 * 내용 : Admin service
 * */
package kr.co.kmarket2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.model.Model;
import jakarta.transaction.Transactional;
import kr.co.kmarket2.dao.AdminDAO;
import kr.co.kmarket2.utils.PageHandler;
import kr.co.kmarket2.utils.SearchCondition;
import kr.co.kmarket2.vo.MemberVO;
import kr.co.kmarket2.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminService {
	@Autowired
	private AdminDAO dao;
	
	// 상품 등록
	@Transactional
	public int insertProductAdmin(ProductVO product) {
		// 파일 업로드
		FileUpload(product);
		// 상품 등록
		int result = dao.insertProductAdmin(product);
		return result;
	}

	// 상품 조회
	public void selectProductAdmin(Model m, SearchCondition sc, @AuthenticationPrincipal MemberVO user) {
        int totalCnt = dao.countProductAdmin(sc); // 전체 상품 갯수

        /** 검색 페이지 > 전체 페이수일 경우 실행 **/
        int totalPage = (int)Math.ceil(totalCnt/(double)sc.getPageSize()); // 전체 페이지수

        // 전체 페이지수가 현재 페이지수 보다 크면 전체 페이지수로 값 저장
        if(sc.getPage() > totalPage) sc.setPage(totalPage);

        PageHandler pageHandeler = new PageHandler(totalCnt, sc); // 페이징 처리

        // SearchCondition에 uid, type 담기
        sc.setType(user.getType());
        sc.setUid(user.getUid());
        System.out.println("type : " + user.getType());

        List<ProductVO> list = dao.selectProductAdmin(sc); // 상품 조회
	}
	
	
	private void FileUpload(ProductVO product) {
		
		
	}
}
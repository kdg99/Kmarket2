/*
 * 날짜 : 2023/02/16
 * 이름 : 김훈
 * 내용 : Admin service
 * */
package kr.co.kmarket2.service;

import kr.co.kmarket2.dao.AdminDAO;
import kr.co.kmarket2.vo.ProductVO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AdminService {

	@Autowired
	private AdminDAO dao;
	
	public List<ProductVO> selectProducts(int start){
		return dao.selectProducts(start);
	}
	
	public int selectCountProducts() {
		return dao.selectCountProducts();
	}

	// 제품 등록
	public void regiaterProduct(ProductVO vo) {
		dao.regiaterProduct(vo);
	}
}

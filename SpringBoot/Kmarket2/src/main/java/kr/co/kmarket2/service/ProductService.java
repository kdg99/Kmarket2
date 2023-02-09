/*
날짜 : 2023/02/08
이름 : 김동근
내용 : Kmarket2 SpringBoot product service
*/
package kr.co.kmarket2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kmarket2.dao.ProductDAO;
import kr.co.kmarket2.vo.ProductVO;

@Service
public class ProductService {
	
	@Autowired
	private ProductDAO dao;
	
	public void insertProduct(ProductVO vo) {
		dao.insertProduct(vo);
	}
	public ProductVO selectProduct(String no) {
		return dao.selectProduct(no);
	}
	public List<ProductVO> selectProducts(){
		return dao.selectProducts();
	}
	public void updateProduct(ProductVO vo) {
		dao.updateProduct(vo);
	}
	public void deleteProduct(String no) {
		dao.deleteProduct(no);
	}
	
	// product/list
	public List<ProductVO> selectProductsByCate(String cate1, String cate2, String order, int start){
		return dao.selectProductsByCate(cate1, cate2, order, start);
	}
	
}

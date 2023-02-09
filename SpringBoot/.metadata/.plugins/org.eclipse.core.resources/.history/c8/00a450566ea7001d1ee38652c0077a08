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
	
}

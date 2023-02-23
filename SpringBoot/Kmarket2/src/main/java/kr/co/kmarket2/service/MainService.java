package kr.co.kmarket2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kmarket2.dao.MainDAO;
import kr.co.kmarket2.vo.ProductVO;

@Service
public class MainService {
	
	@Autowired
	private MainDAO dao;
	
	
	public List<ProductVO> selectProdSold(){
		return dao.selectProdSold();
	}
	
	public List<ProductVO> selectProdHit(){
		return dao.selectProdHit();
	}
	
	public List<ProductVO> selectProdScore(){
		return dao.selectProdScore();
	}
	
	public List<ProductVO> selectProdNew(){
		return dao.selectProdNew();
	}
	
	public List<ProductVO> selectProdDiscount(){
		return dao.selectProdDiscount();
	}
	

}

package kr.co.kmarket2.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.kmarket2.vo.ProductVO;

@Mapper
@Repository
public interface MainDAO {
	
	public List<ProductVO> selectProdSold();
	public List<ProductVO> selectProdHit();
	public List<ProductVO> selectProdScore();
	public List<ProductVO> selectProdNew();
	public List<ProductVO> selectProdDiscount();

}

/*
날짜 : 2023/02/16
이름 : 김훈
내용 : Kmarket2 SpringBoot Admin DAO
*/
package kr.co.kmarket2.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.co.kmarket2.vo.ProductVO;

@Repository
@Mapper
public interface AdminDAO {
	// 제품 불러오기
	public List<ProductVO> selectProducts(@Param("start") int start);
	public int selectCountProducts();
	
	// 제품 등록
	public void regiaterProduct(ProductVO vo);
}

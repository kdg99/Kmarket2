/*
날짜 : 2023/02/16
이름 : 김훈
내용 : Kmarket2 SpringBoot Admin DAO
*/
package kr.co.kmarket2.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.kmarket2.utils.SearchCondition;
import kr.co.kmarket2.vo.ProductVO;

@Repository
@Mapper
public interface AdminDAO {
    public int insertProductAdmin(ProductVO product);
    public List<ProductVO> selectProductAdmin(SearchCondition sc);
    public int countProductAdmin(SearchCondition sc);
    public int deleteProduct(String prodNo);
    public int modifyProduct(ProductVO product);
}

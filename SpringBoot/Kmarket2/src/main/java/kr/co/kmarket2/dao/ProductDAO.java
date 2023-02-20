/*
날짜 : 2023/02/08
이름 : 김동근
내용 : Kmarket2 SpringBoot product dao
*/
package kr.co.kmarket2.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.co.kmarket2.vo.CartVO;
import kr.co.kmarket2.vo.NavCateVO;
import kr.co.kmarket2.vo.OrderItemVO;
import kr.co.kmarket2.vo.OrderVO;
import kr.co.kmarket2.vo.ProductVO;

@Mapper
@Repository
public interface ProductDAO {
	public void insertProduct(ProductVO vo);
	public ProductVO selectProduct(int no);
	public List<ProductVO> selectProducts();
	public void updateProduct(ProductVO vo);
	public void deleteProduct(int no);
	
	// product/list
	public NavCateVO selectNavByCate(@Param("cate1") String cate1, @Param("cate2") String cate2);
	public List<ProductVO> selectProductsByCate(@Param("cate1") String cate1, @Param("cate2") String cate2, @Param("order") String order, @Param("start") int start);
	public void updateProductHit(int prodNo);
	
	// product/cart
	public List<CartVO> selectCarts (String uid);
	public CartVO checkCart(@Param("uid") String uid, @Param("prodNo") int prodNo);
	public void insertCart(CartVO vo);
	public void deleteCart(@Param("uid") String uid, @Param("prodNo") int prodNo);
	
	// product/complete
	public void insertOrder(OrderVO vo);
	public void insertOrderItem(OrderItemVO vo);
	public void updateOrder(int ordComplete, int ordNo);
}

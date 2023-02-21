/*
날짜 : 2023/02/08~21
이름 : 김동근
내용 : Kmarket2 SpringBoot product service
*/
package kr.co.kmarket2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kmarket2.dao.ProductDAO;
import kr.co.kmarket2.vo.CartVO;
import kr.co.kmarket2.vo.NavCateVO;
import kr.co.kmarket2.vo.OrderItemVO;
import kr.co.kmarket2.vo.OrderVO;
import kr.co.kmarket2.vo.ProductVO;
import kr.co.kmarket2.vo.ReviewVO;

@Service
public class ProductService {
	
	@Autowired
	private ProductDAO dao;
	
	public void insertProduct(ProductVO vo) {
		dao.insertProduct(vo);
	}
	public ProductVO selectProduct(int no) {
		return dao.selectProduct(no);
	}
	public List<ProductVO> selectProducts(){
		return dao.selectProducts();
	}
	public void updateProduct(ProductVO vo) {
		dao.updateProduct(vo);
	}
	public void deleteProduct(int no) {
		dao.deleteProduct(no);
	}
	
	
	// product/list
	public NavCateVO selectNavByCate(String cate1, String cate2) {
		return dao.selectNavByCate(cate1, cate2);
	}
	
	public List<ProductVO> selectProductsByCate(String cate1, String cate2, String order, int start){
		return dao.selectProductsByCate(cate1, cate2, order, start);
	}
	
	public void updateProductHit(int prodNo) {
		dao.updateProductHit(prodNo);
	}
	public int selectProductCountByCate(String cate1, String cate2) {
		return dao.selectProductCountByCate(cate1, cate2);
	}
	
	
	// product/view
	public List<ReviewVO> selectReviews(int prodNo, int start){
		return dao.selectReviews(prodNo, start);
	}
	public int selectReviewCount(int prodNo) {
		return dao.selectReviewCount(prodNo);
	}
	
	
	
	
	// product/cart
	public List<CartVO> selectCarts (String uid){
		return dao.selectCarts(uid);
	}
	
	public CartVO checkCart(String uid, int prodNo) {
		return dao.checkCart(uid, prodNo);
	}
	
	public int addCart(String uid, CartVO vo) {
		vo.setUid(uid);
		if(checkCart(uid, vo.getProdNo()) == null) {
			dao.insertCart(vo);
			return 1;
		}
		return 0;
	}
	
	public int deleteCart(String uid, List<Integer> prodNo) {
		for(int no : prodNo) {
			dao.deleteCart(uid, no);
		}
		return 1;
	}
	
	
	// product/Complete
	public void completeOrder(String uid, OrderVO orderInfo) {
		orderInfo.setUid(uid);
		//결제 확인후
		//주문 인서트
		dao.insertOrder(orderInfo);
		int ordNo = orderInfo.getOrdNo();
		for (OrderItemVO orderItem : orderInfo.getOrderList()) {
			//주문상품 인서트, 장바구니 삭제, 상품 정보 갱신
			orderItem.setOrdNo(ordNo);
			dao.insertOrderItem(orderItem);
			dao.deleteCart(uid, orderItem.getProdNo());
			dao.updateProductStockSold(orderItem.getCount(), orderItem.getProdNo());
		}
		//포인트, 유저 정보 갱신
		int point = orderInfo.getSavePoint() - orderInfo.getUsedPoint();
		dao.insertPoint(uid, ordNo, point);
		dao.updateMemberPoint(uid, point);
		
	}
	
}

package kr.co.kmarket2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import kr.co.kmarket2.dao.MemberDAO;
import kr.co.kmarket2.dao.ProductDAO;
import kr.co.kmarket2.vo.OrderVO;

@Service
public class MyPageService {

	@Autowired
	private ProductDAO daoProd;
	//@Autowired
	//private MemberDAO daoMem;
	
	// myPage/home
	public OrderVO selectMyLatest(String uid) {
		return daoProd.selectMyLatest(uid);
	}
	
	
}

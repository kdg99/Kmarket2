/*
 * 날짜 : 2023/02/16
 * 이름 : 김훈
 * 내용 : Admin service
 * */
package kr.co.kmarket2.service;

import kr.co.kmarket2.dao.AdminDAO;
import kr.co.kmarket2.utils.PageHandler;
import kr.co.kmarket2.utils.SearchCondition;
import kr.co.kmarket2.vo.ProductVO;
import kr.co.kmarket2.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AdminService {

	@Autowired
	private AdminDAO dao;
	
	public List<ProductVO> selectProducts(int start){
		return dao.selectProducts(start);
	};
	
	public int selectCountProducts() {
		return dao.selectCountProducts();
	}

}

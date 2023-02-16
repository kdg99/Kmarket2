/*
 * 날짜 : 2023/02/16
 * 이름 : 김훈
 * 내용 : Admin service
 * */
package kr.co.kmarket2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kmarket2.dao.AdminDAO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminService {
	@Autowired
	private AdminDAO dao;
	
}
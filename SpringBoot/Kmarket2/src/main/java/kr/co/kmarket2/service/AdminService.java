/*
 * 날짜 : 2023/02/16
 * 이름 : 김훈
 * 내용 : Admin service
 * */
package kr.co.kmarket2.service;

import kr.co.kmarket2.dao.AdminDAO;
import kr.co.kmarket2.vo.MemberVO;
import kr.co.kmarket2.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Slf4j
@Service
public class AdminService {

	@Autowired
	private AdminDAO dao;

	public List<ProductVO> selectProducts(int start) {
		return dao.selectProducts(start);
	}

	public int selectCountProducts() {
		return dao.selectCountProducts();
	}

	// 제품 등록
	public void insertProduct(ProductVO vo, @AuthenticationPrincipal MemberVO member) {
		fileUpload(vo);
		dao.insertProduct(vo);
	}

	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;

	private void fileUpload(ProductVO vo) {
        MultipartFile[] files = vo.getFile();
        List<String> names = new ArrayList<>();
        
        // 파일 저장 경로 설정
        String path = new File(uploadPath + "/" + vo.getCate1() + "/" + vo.getCate2()).getAbsolutePath();

        for (MultipartFile file : files) {
            // 새 파일명 생성
            String oName = file.getOriginalFilename();
            String ext = oName.substring(oName.lastIndexOf("."));
            String nName = UUID.randomUUID().toString()+ext;
            names.add(nName);

            // 파일 저장
            try {
                file.transferTo(new File(path, nName));
            } catch (IllegalStateException e) {
                log.error(e.getMessage());
            } catch (IOException e) {
                log.error(e.getMessage());
            }

        }
        vo.setThumb1(names.get(0));
        vo.setThumb2(names.get(1));
        vo.setThumb3(names.get(2));
        vo.setDetail(names.get(3));
    }
}
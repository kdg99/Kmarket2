package kr.co.kmarket2.security;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler{
	@Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
    					AccessDeniedException accessDeniedException) throws IOException, ServletException {
		//레벨체크
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		int level = Integer.parseInt(auth.getAuthorities().toString().replace("[ROLE_", "").replace("]", ""));
		StringBuilder msg = new StringBuilder();
		
		if(level == 1) {
			msg.append("권한이 없습니다 (level.1)");
		}
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('"+ msg.toString() +"'); location.href='/Kmarket2/';</script>");
		out.flush();
    }

}

package kr.or.ddit.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.dto.MemberVO;

public class LoginCheckFilter implements javax.servlet.Filter {

	private List<String> exURLs = new ArrayList<String>();

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRep = (HttpServletResponse) response;

		// 제외할 url 확인
		String reqUrl = httpReq.getRequestURI().substring(httpReq.getContextPath().length());

		// url Check
		if (excludeCheck(reqUrl)) {
			chain.doFilter(request, response);
			return;
		}

		// login check
		HttpSession session = httpReq.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		// login 확인
		if (loginUser == null) { // 비로그인 상태
			httpRep.setContentType("text/html;charset=utf-8");
			PrintWriter out = httpRep.getWriter();
			out.println("<script>");
			out.println("alert('로그인은 필수 입니다');");
			out.println("if(window.opener){window.close();window.opener.parent.location.href='"
					+ httpReq.getContextPath()
					+ "/';}else{");
			out.println("window.parent.location.href='" + httpReq.getContextPath() + "/';");
			out.println("}");
			out.println("</script>");
			out.close();
		} else {
			chain.doFilter(request, response);
		}

		// System.out.println("requset URI : " + reqUrl);

	}

	public void init(FilterConfig fConfig) throws ServletException {
		String excludeURLNames = fConfig.getInitParameter("exclude");
		System.out.println("excludeURLNames" + excludeURLNames);
		StringTokenizer st = new StringTokenizer(excludeURLNames, ",");
		System.out.println("st : " + st );
		while (st.hasMoreTokens()) {
			exURLs.add(st.nextToken().trim());
		}
		System.out.println(exURLs);
	}

	private boolean excludeCheck(String url) {
		if (url.length() <= 1)
			return true;

		for (String exURL : exURLs) {
			if (url.contains(exURL)) {
				return true;
			}
		}
		return false;
	}

}

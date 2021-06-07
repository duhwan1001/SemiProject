package kr.or.ddit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.handler.Handler;

public class FrontServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestPro(request, response);
	}

	private void requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 사용자 URI 검출
		String command = request.getRequestURI(); // contextPath 포함.
		if (command.indexOf(request.getContextPath()) == 0) { // contextPath 삭제
			command = command.substring(request.getContextPath().length());
		}

		// 로그인 확인
		if (!command.contains("login")) {
			HttpSession session = request.getSession();
			MemberVO member = (MemberVO) session.getAttribute("loginUser");
			if (member == null) { // 로그인 하지 않은 상태
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('로그인은 필수 입니다.')");
				out.println("location.href='/';");
				out.println("</script>");
				return;
			}
		}

		// commandHandler 실행 (HandlerMapper 의뢰 handler 할당)
		Handler handler = null;
		String view = null;

		if (handlerMapper != null) {
			handler = handlerMapper.getHandler(command);
			if (handler != null) { // 올바른 요청
				try {
					view = handler.process(request, response);

					if (view != null)
						ViewResolver.view(request, response, view);

				} catch (Exception e) {
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}

			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		} else {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}

	private HandlerMapper handlerMapper;

	@Override
	public void init(ServletConfig config) throws ServletException {
		String handlerMapperType = config.getInitParameter("handlerMapper");

		try {
			this.handlerMapper = (HandlerMapper) injectionBean(handlerMapperType);
			System.out.println("[FrontServlet] handlerMapper 가 준비되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[FrontServlet] handlerMapper 가 준비되지 않았습니다.");
		}
	}

	private Object injectionBean(String classType) throws Exception {
		Class<?> cls = Class.forName(classType);
		return cls.newInstance();
	}

}

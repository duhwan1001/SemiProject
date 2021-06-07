<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
	<head>
	<title>로그인</title>
	<%@ include file="/WEB-INF/views/include/style.jsp"%>
	</head>
	<body class="">
		<div class="login-box">
			<div class="login-logo">
				<a href="#"><b>관리자 로그인</b></a>
			</div>
			<!-- /.login-logo -->
			<div class="card">
				<div class="card-body login-card-body">
					<p class="login-box-msg">Sign in to start your session</p>
	
					<form action="<%=request.getContextPath()%>/login.do" method="post">
						<div class="form-group has-feedback">
							<input type="text" class="form-control" name="id"
								placeholder="아이디를 입력하세요." value="${pastID }"> <span
								class="glyphicon glyphicon-envelope form-control-feedback"></span>
						</div>
						<div class="form-group has-feedback">
							<input type="password" class="form-control" name="pwd"
								placeholder="패스워드를 입력하세요." value="mimi"> <span
								class="glyphicon glyphicon-lock form-control-feedback"></span>
						</div>
						<div class="row">
							<div class="col-sm-8">
								<div class="checkbox icheck">
									<label> <input type="checkbox" name="rememberMe"
										value="check"> Remember Me
									</label>
								</div>
							</div>
							<!-- /.col -->
							<div class="col-sm-4">
								<button type="submit" class="btn btn-primary btn-block btn-flat">로그인</button>
							</div>
							<!-- /.col -->
						</div>
					</form>
	
					<a href="#" style="font-weight: bold;">아이디/패스워드 찾기</a><br>
	
	
				</div>
				<!-- /.login-box-body -->
			</div>
		</div>
		<!-- /.login-box -->
	
		<%@ include file="/WEB-INF/views/include/js.jsp"%>
	</body>
</html>
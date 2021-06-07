<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">
	<!-- Brand Logo -->
	<a href="index3.html" class="brand-link"> <img
		src="<%=request.getContextPath()%>/resources/bootstrap/dist/img/AdminLTELogo.png"
		alt="AdminLTE Logo" class="brand-image img-circle elevation-3"
		style="opacity: .8"> <span class="brand-text font-weight-light">AdminLTE
			3</span>
	</a>

	<!-- Sidebar -->
	<div class="sidebar">
		<!-- Sidebar user panel (optional) -->
		<div class="user-panel mt-3 pb-3 mb-3 d-flex">
			<div class="image">
				<img src="/member/getPicture.do?picture=${loginUser.picture}" class="img-circle elevation-2" alt="User Image">
			</div>
			<div class="info">
				<div class="row">
					<a href="#" class="d-block">${loginUser.name }</a>
					<button onclick="location.href='<%=request.getContextPath()%>/logout.do'" class="btn btn-xs btn-primary col-xs-3" type="button">LOGOUT</button>
				</div>
				<a href="tel:${loginUser.phone }">tel : ${loginUser.phone }</a><br>
				<a href="mailto:${loginUser.email }">email : ${loginUser.email }</a>
			</div>
		</div>

		<!-- Sidebar Menu -->
		<nav class="mt-2">
			<ul class="nav nav-pills nav-sidebar flex-column subMenuList"
				data-widget="treeview" role="menu" data-accordion="false">
				<!--  sub-menu-list -->

			</ul>
		</nav>
		<!-- /.sidebar-menu -->
	</div>
	<!-- /.sidebar -->
</aside>
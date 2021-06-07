<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav aria-label="member list Navigation">
	<ul class="pagination justify-content-center m-0">
		<li class="page-item">
			<a class="page-link" href="javascript:list_go(1,'${list_url }');"> 
				<i class="fas fa-angle-double-left"></i>
			</a>
		</li>
		<li class="page-item">
			<a class="page-link" href="javascript:list_go(${pageMaker.prev ? pageMaker.startPage-1 : 1 }, '${list_url }');">
				<i class="fas fa-angle-left"></i>
			</a>
		</li>
		<c:forEach var="pageNum" begin="${pageMaker.startPage + 1 }"
			end="${pageMaker.endPage }">
			<li
				class="page-item ${pageMaker.cri.page == pageNum ? 'active' : '' }">
				<a class="page-link" href="javascript:list_go(${pageNum }, '${list_url }');">${pageNum}</a>
			</li>
		</c:forEach>
		<li class="page-item">
			<a class="page-link" href="javascript:list_go(${pageMaker.prev ? pageMaker.startPage+1 : 1 }, '${list_url }');">
				<i class="fas fa-angle-right"></i>
			</a>
		</li>
		<li class="page-item">
			<a class="page-link" href="javascript:list_go(${pageMaker.realEndPage }, '${list_url }');">
				<i class="fas fa-angle-double-right"></i>
			</a>
		</li>
	</ul>
</nav>

<form id="jobForm">
	<input type="hidden" name="page" value="${pageMaker.cri.page }"> 
	<input type="hidden" name="perPageNum" value="${pageMaker.cri.perPageNum }">
	<input type="hidden" name="searchType" value="${pageMaker.cri.searchType }">
	<input type="hidden" name="keyword" value="${pageMaker.cri.keyword }">
</form>
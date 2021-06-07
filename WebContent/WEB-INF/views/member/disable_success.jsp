<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script>
	alert("${id} 정지 완료")
	<c:if test="${empty loginUser}">
		window.opener.parent.location.href="/";
		window.close();
	</c:if>
	
	<c:if test="${!empty loginUser}">
		location.href="detail.do?id=${id }"
	</c:if>
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	alert("${member}님의 정보를 성공적으로 삭제하였습니다.");

	<c:if test="${empty loginUser }">
		window.opener.parent.location.href="/";
	</c:if>
	<c:if test="${!empty loginUser }">
		window.opener.parent.location.reload();
	</c:if>
	
	CloseWindow();
</script>
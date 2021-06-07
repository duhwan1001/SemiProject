<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<body>

	<script>
		alert("회원 등록 성공.\n 회원리스트 페이지로 이동합니다.");
		
		window.onload=function(){
			$.ajax({
				url:"/getMcode.do?mName=회원목록",
				type:"get",
				success:function(menu){
					window.opener.parent.location.href="/index.do?mCode=" + menu.mcode;					
					window.close();
				}
				
			})
		}
		
	</script>
</body>
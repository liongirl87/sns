<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="ml-3 pt-2 d-flex justify-content-between">
		<div>
		<h3>Marondalgram</h3>
		</div>
		<div class="mt-3">
			<c:choose>
				<c:when test="${not empty userId}">
					<span class="mr-3">${name}님 안녕하세요</span>
					<a href="/user/sign_out" class="mr-3">로그아웃</a>
				</c:when>
				<c:when test="${empty userId}">
					<a href="/user/sign_in_view" class="mr-3">로그인</a>
				</c:when>	
			</c:choose>
		</div>
		
</div>
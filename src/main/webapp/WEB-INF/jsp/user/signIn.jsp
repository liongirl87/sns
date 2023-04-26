<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center align-items-center" id="loginViewHeight">
	<form method="post" action="/user/sign_in" id="signInForm">
		<div class="">
			<div class="input-group">
				<div class="input-group-prepend">
					<span class="input-group-text">ID</span>
				</div>
				<input type="text" class="form-control" id="loginId" name="loginId">
			</div>
			<div class="input-group my-3">
				<div class="input-group-prepend">
					<span class="input-group-text">PW</span>
				</div>
				<input type="password" class="form-control" id="password" name="password">
			</div>
			<div>
				<button type="submit" id="loginBtn" class="btn btn-primary w-100">로그인</button>
			</div>
			<div class="my-2">
				<a href="/user/sign_up_view" class="btn btn-secondary w-100">회원가입</a>
				<!-- <button type="button" id="signUpBtn" class="btn btn-secondary w-100">회원가입</button> -->
			</div>
		</div>
	</form>
</div>
<script>
$(document).ready(function(){
	$("#signInForm").on('submit', function(e){
		e.preventDefault();
		//validation
		let loginId = $("#loginId").val().trim();
		let password = $('#password').val();
		
		if (!loginId) {
			alert("아이디를 입력해주세요");
			return false;
		}
		if (!password) {
			alert("비밀번호를 입력해주세요");
			return false;
		}
		let url = $('#signInForm').attr("action");	// "/user/sign_in"
		let params = $('#signInForm').serialize();
		console.log(params);
		
		$.post(url, params)
		.done(function(data){
			if(data.code == 1) {	// 성공
				alert("로그인성공")
				location.href = "/timeline/timeline_view"
			} else {	// 실패
				alert("로그인실패")
				alert(data.errorMessage)
			}
		});
		
		
	});

});

</script>
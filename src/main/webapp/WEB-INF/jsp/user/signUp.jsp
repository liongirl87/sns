<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center mt-3">
	<form id="signUpForm" method="post" action="/user/sign_up" class="col-6">
			<h3 class="font-weight-bold">회원가입</h3>
		<div class="bg-light">
			<div class="ml-3 my-3">
				<div>
					<span>ID</span>
					<div class="d-flex">
						<input type="text" class="form-control col-7" id="loginId" name="loginId" placeholder="ID를 입력해주세요">
						<button type="button" class="btn btn-primary ml-3" id="loginIdCheckBtn">중복확인</button>
					</div>
					<div id="idCheckLength" class="small text-danger d-none">ID를 4자이상 입력해주세요.</div>
					<div id="idCheckDuplicated" class="small text-danger d-none">이미 사용중인 ID입니다.</div>
					<div id="idCheckOk" class="small text-success d-none">사용가능한 ID입니다.</div>
				</div>
				<div class="my-2">
					<span>password</span>
					<input type="password" class="form-control col-5" id="password" name="password" placeholder="비밀번호를 입력하세요">
				</div>
				<div>
					<span>confirm password</span>
					<input type="password" class="form-control col-5" id="checkPassword" placeholder="비밀번호를 입력하세요">
				</div>
				<div class="my-2">
					<span>name</span>
					<input type="text" class="form-control col-7" id="name" name="name" placeholder="이름을 입력하세요">
				</div>
				<div>
					<span>이메일</span>
					<input type="text" class="form-control col-7" id="email" name="email" placeholder="이메일을 입력하세요">
				</div>
				<div class="d-flex justify-content-center">
					<button type="submit" class="btn btn-primary mt-2" id="signUpBtn">가입하기</button>
				</div>
			</div>
		</div>
	</form>
</div>
<script>
$(document).ready(function(){
	$("#loginIdCheckBtn").on('click', function(){
		let loginId = $("#loginId").val().trim();
		// alert(loginId);
		// 경고문고 초기화
		$("#idCheckLength").addClass('d-none');
		$("#idCheckDuplicated").addClass('d-none');
		$("#idCheckOk").addClass('d-none');
		
		// validation		
		// 4글자 이하면 경고문구 출력
		if (loginId.length < 4) {
			$("#idCheckLength").removeClass('d-none');
			return;
		}
		$.ajax({
			//request
			url:"/user/is_duplicated_id"
			,data:{"loginId":loginId}
				
			//response	
			,success:function(data) {
				if(data.result) {
					$("#idCheckDuplicated").removeClass("d-none");
				} else {
					$("#idCheckOk").removeClass("d-none");
				}
			}
			,error:function(request, status, error){	// 아애 에러가 생긴경우
				alert("요청에 실패했습니다. 관리자에게 문의해주세요.");
			}
		});
	});
	$('#signUpForm').on('submit', function(e){
		e.preventDefault(); //서브밋기능 중단
		
		let loginId = $('#loginId').val().trim();
		let password = $('#password').val();
		let checkPassword = $('#checkPassword').val();
		let name = $('#name').val().trim();
		let email = $('#email').val().trim();
		
		// validation
		if (!loginId) {
			alert("아이디를 입력하세요.");
			return false;
		}
		if (!password) {
			alert("비밀번호를 입력하세요.");
			return false;
		}
		if (password != checkPassword) {
			alert("비밀번호가 일치하지않습니다.");
			return false;
		}
		if (!name) {
			alert("이름을 입력해주세요.");
			return false;
		}
		if (!email) {
			alert("이메일을 입력해주세요.");
			return false;
		}
		
		// 아이디 중복 체크 유무 확인 ->가입 가능한 아이디인 경우만 가입가능
		if ($('idCheckOk').hasClass('d-none')) {
			alert("아이디 중복체크를 해주세요");
			return false;
		}
		
		// 서버로 보내기
		let url = $(this).attr("action");
		console.log(url);
		let params = $(this).serialize();
		
		$.post(url, params)	//request
		.done(function(data){
			//response
			if(data.code == 1) {
				alert("가입을 환영합니다")
				location.href = "/user/sign_in_view"	//로그인 화면으로 이동
			} else {
				alert(data.errorMessage);
			}
		});
	});
	
});

</script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="d-flex justify-content-center">
	<div class="contents-box">
		<%-- 글쓰기 영역: 로그인 된 상태에서만 보여짐 --%>
		<c:if test="${not empty userId}">
		<div class="write-box border rounded m-3">
			<textarea id="writeTextArea" placeholder="내용을 입력해주세요" class="w-100 border-0"></textarea>
				
			<%-- 이미지 업로드를 위한 아이콘과 업로드 버튼을 한 행에 멀리 떨어뜨리기 위한 div --%>
			<div class="d-flex justify-content-between">
				<div class="file-upload d-flex">
					<%-- file 태그는 숨겨두고 이미지를 클릭하면 file 태그를 클릭한 것처럼 이벤트를 줄 것이다. --%>
					<input type="file" id="file" class="d-none" accept=".gif, .jpg, .png, .jpeg">
					<%-- 이미지에 마우스 올리면 마우스커서가 링크 커서가 변하도록 a 태그 사용 --%>
					<a href="#" id="fileUploadBtn"><img width="35" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"></a>

					<%-- 업로드 된 임시 파일 이름 저장될 곳 --%>
					<div id="fileName" class="ml-2">
					</div>
				</div>
				<button id="writeBtn" class="btn btn-info">게시</button>
			</div>
		</div>
		</c:if>
		<%--// 글쓰기 영역 끝 --%>
		
		<%-- 타임라인 영역 --%>
		<c:forEach items="${cardList}" var="card">
		<div class="timeline-box my-5">
			<%-- 카드1 --%>
			<div class="card border rounded mt-3">
				<%-- 글쓴이, 더보기(삭제) --%>
				<div class="p-2 d-flex justify-content-between">
					<span class="font-weight-bold">${card.user.loginId}</span>
					
					<%-- 더보기(내가 쓴 글일 때만 노출) --%>
					<c:if test="${card.user.id eq userId}">
					<a href="#" class="more-btn" data-toggle="modal" data-target="#modal" data-post-id="${card.post.id}">
						<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
					</a>
					</c:if>
				</div>
				
				<%-- 카드 이미지 --%>
				<div class="card-img">
					<img src="${card.post.imagePath}" class="w-100" alt="본문 이미지">
				</div>
				
				<%-- 좋아요 --%>
				<div class="card-like m-3">
				<!-- 좋아요가 눌려있지 않을떄 빈 하트 -->
				<c:if test="${card.filledLike eq false}">
					<a href="#" class="likeBtn"  data-like="${card.post.id}" ><img src="https://www.iconninja.com/files/214/518/441/heart-icon.png" width="18px" height="18px" alt="filled heart"></a>
					좋아요 ${card.likeCount}개
				</div>
				</c:if>
				<!-- 좋아요가 눌려졌을떄 채워진 하트 -->
				<c:if test="${card.filledLike}">
					<a href="#" class="likeBtn"  data-like="${card.post.id}" ><img src="https://www.iconninja.com/files/527/809/128/heart-icon.png" width="18px" height="18px" alt="filled heart"></a>
					좋아요 ${card.likeCount}개
				</div>
				</c:if>
				<%-- 글 --%>
				<div class="card-post m-3">
					<span class="font-weight-bold">${card.post.userId}</span>
					<span>${card.post.content}</span>
				</div>
				
				<%-- 댓글 --%>
				<div class="card-comment-desc border-bottom">
					<div class="ml-3 mb-1 font-weight-bold">댓글</div>
				</div>
				
				<%-- 댓글 목록 --%>
				<div class="card-comment-list m-2">
					<c:forEach items="${card.commentList}" var="comment">
								<%-- 댓글 내용 --%>
								<div class="card-comment m-1">
									<span class="font-weight-bold">${comment.user.loginId}:</span>
									<span>${comment.comment.content}</span>
									
									<%-- 댓글 삭제 버튼 --%>
									<a href="/comment/delete?id=${comment.user.loginId}" class="commentDelBtn">
										<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10px" height="10px">
									</a>
								</div>
					</c:forEach>
					<%-- 댓글 쓰기 --%>
					<c:if test="${not empty userId}">
					<div class="comment-write d-flex border-top mt-2">
						<input type="text" class="form-control border-0 mr-2 comment-input" data-comment-text="" placeholder="댓글 달기"/> 
						<button type="button" class="comment-btn btn btn-light commentBtn" data-post-id="${card.post.id}">게시</button>
					</div>
					</c:if>
				</div>
				<%--// 댓글 목록 끝 --%>
			</div>
			<%--// 카드1 끝 --%>
		</div>
		</c:forEach>
		<%--// 타임라인 영역 끝  --%>
	</div>
</div>




<!-- Modal -->
<div class="modal fade" id="modal" >
	<!-- modal-dialog-centered: 모달 창을 수직 가운데 정렬  -->
	<!-- modal-sm: small 모달 -->
 	 <div class="modal-dialog modal-dialog-centered modal-sm">
    	<div class="modal-content text-center">
    		<div class="py-3 border-bottom">
      			<a href="#" id="deletPostBtn">삭제하기</a>
      		</div>
      		<div class="py-3">
      			<!-- data-dismiss="modal" => 모달창 닫힘 -->
      			<a href="#" data-dismiss="modal">취소하기</a>
      		</div>
    	</div>
  	</div>
</div>


<script>
$(document).ready(function(){
	// 파일업로드 이미지 클릭 => 숨겨져 있는 file태그를 동작시킨다
	$("#fileUploadBtn").on('click', function(e){
		e.preventDefault(); // a태그의 스크롤이 올라가는 현상 방지
		$('#file').click() // input file을 클릭한 것과 같은 효과
	});
	// 사용자가 이미지를 선택했을 떄 유효성 확인 및 업로드 된 파일명 노출
	$("#file").on('change', function(e){
		let fileName = e.target.files[0].name; //e.target (this)와 같다
		console.log(fileName);
		// alert("파일 선택");
		
		// 확장자 유효성 확인
		let ext = fileName.split(".").pop().toLowerCase();
		if (ext != "jpg" && ext != "jpeg" && ext != "gif" && ext != "png") {
			alert("이미지 파일만 업로드 할 수 있습니다.");
			$("#file").val(""); // 파일 태그에 파일 제거
			$('#fileName').text("") //파일 이름 비우기
			return;
		}
		
		// 유효성 통과한 이미지는 상자에 업로드 된 파일 이름 노출
		$('#fileName').text(fileName);
	});
	
	// 글쓰기 버튼
	$('#writeBtn').on('click', function(){
		// validation
		
		let writeTextArea = $('#writeTextArea').val();
		let file = $('#file').val()
		if (!writeTextArea) {
			alert("글 내용을 입력해주세요");
			return;
		}

		// 글내용, 이미지, 확장자체크
		let formData = new FormData();
		formData.append("writeTextArea", writeTextArea);
		formData.append("file", $('#file')[0].files[0]);
		
		// AJAX 전송
		$.ajax({
			// request
			type:"post"
			,url:"/post/create"
			,data:formData
			,enctype:"multipart/form-data"
			,processData:false
			,contentType:false
			
			// response
			,success:function(data) {
				if(data.code == 1) {
					// alert("글 작성을 완료하였습니다.");
					location.reload();
				} else {
					alert(data.errorMessage);
				}
			}
			,error:function(request, status, error) {
				alert("글 작성을 실패하였습니다.");
			}
		});
		
		
	});
	$(".commentBtn").on('click', function(){
		
		let postId = $(this).data("post-id");
		
		//1 번째 방식
/* 		let commentInput = $(this).prev().val();
		alert(commentInput); */
		
		//2 번째 방식
		let comment = $(this).siblings('input').val();
		//alert(comment);
		
		//validation
		if (!comment) {
			return;
		}
		
		//ajax 호출
		$.ajax({
			//request
			url:"/comment/create"
			,data:{"postId":postId, "comment":comment}
		
			//response
			,success:function(data){
				if(data.code == 1) {
					location.reload();
				} else {
					alert(data.errorMessage);
				}
			}
			,error:function(request, status, error) {
				alert("요청에 실패했습니다. 관리자에게 문의해주세요.");
			}
		});

		
	});
	
	$(".likeBtn").on('click', function(e){
		e.preventDefault();
		
		let likePostid = "/like/" + $(this).data("like");
		
		$.ajax({
			//request
			url: likePostid
			
			,success:function(data){
				if(data.code == 1) {
					alert("좋아요 성공!")
					location.reload();
				} else {
					alert(data.errorMessage);
					location.href = "/user/sign_in_view";
				}
			}
			, error:function(request, status ,error) {
				alert("요청에 실패했습니다 관리자에게 문의주세요");
			}
			
		});
	});
	
	// 글 삭제(... 더보기 버튼 클릭)
	$('.more-btn').on('click', function(e){
		e.preventDefault();	// a 태그 위로 올라감 방지
		
		let postId = $(this).data('post-id'); //getting
		//alert(postId);
		// 모달 태그에(재활용 되는) data-post-id를 심어줌
		$('#modal').data('post-id', postId); // setting
		
	});
	
	// 모달 안에 있는 삭제하기 버튼 클릭 => 진짜 삭제
	$('#modal #deletPostBtn').on('click', function(e){
		e.preventDefault();
		
		let postId = $('#modal').data('post-id');
		console.log(postId);
		// ajax 글삭제
		//alert(postId);

		$.ajax({
			type:"DELETE"
			, url:"/post/delete"
			, data:{"postId":postId}
		
			, success:function(data) {
				if(data.code == 1) {
					alert(data.result);
					location.reload();
				} else {
					alert(data.errorMessage);
				}
			}
			, error(request, status, error) {
				alert("삭제에 실패하였습니다 관리자에게 문의 바랍니다.")
			}
			
			
		});
		
	});
	
});


</script>
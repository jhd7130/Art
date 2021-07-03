<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세페이지(작품)</title>
<link rel="stylesheet" href="resources/css/HD/user_detail(byBoard).css">
<script type="text/javascript"
		src="resources/script/jquery/jquery-1.12.4.min.js">
</script>
<script type="text/javascript">
$(document).ready(function () {
	$("#insideMiddle2").on("click", function () {
		if($("#insideMiddle2").attr("class") != "insideMiddle1"){
			$("#insideMiddle2").attr("class","insideMiddle1")
			$("#insideMiddle1").attr("class","insideMiddle2")
		}else{
			$("#insideMiddle2").attr("class","insideMiddle1")
			$("#insideMiddle1").attr("class","insideMiddle2")
		}
	});
	$("#insideMiddle1").on("click", function () {
		if($("#insideMiddle1").attr("class") != "insideMiddle1"){
			$("#insideMiddle1").attr("class","insideMiddle1")
			$("#insideMiddle2").attr("class","insideMiddle2")
		}else{
			$("#insideMiddle1").attr("class","insideMiddle1")
			$("#insideMiddle2").attr("class","insideMiddle2")
		}
	});
	
	
});//document ready end

</script>
</head>
<body>
<div class= "background"></div>
<div class ="Pmain">
	<div class ="topBar">
		<div class ="blank"></div>
	</div>
	<div class = "profile">
		<div class ="pBox">
			<img class ="img" alt="프로필사진" src="resources/images/HD/profile.png">
			<div class ="cButtonB"></div>
			<div class ="cButtonc">
				<img class ="cButtonI" alt="취소버튼" src="resources/images/HD/cancel.png">
			</div>
		</div>
		<input class ="pName" type ="text" placeholder="nickname" readonly="readonly">
	</div>
	
	<div class ="writeBox" >
		<div class = "blank1">회원상세정보</div>
			<div class = "smallBox">
				<div class ="MsmallBox">
					<div class="informing">이름</div>
					<div class="content_box"></div>
				</div>
				<div class ="MsmallBox">
					<div class="informing">회원번호</div>
					<div class="content_box"></div>
				</div>
				<div class ="MsmallBox">
					<div class="informing">전화번호</div>
					<div class="content_box"></div>
				</div>
				
			</div>
			<div class = "smallBox">
				<div class ="MsmallBox">
					<div class="informing">성별</div>
					<div class="content_box"></div>
				</div>
				<div class ="MsmallBox">
					<div class="informing">생년월일</div>
					<div class="content_box"></div>
				</div>
				<div class ="MsmallBox">
					<div class="informing">이메일</div>
					<div class="content_box"></div>
				</div>
			</div>
			
			<div class="introduce_box">
				<div class="sogea"><b>소개</b></div>
				<div class="sogea_box">
				소개 입력란입니다.
				</div>
			</div>
	</div>
	
	<div class ="middleSection">
		<div class = "brick"></div>
		<button class ="insideMiddle1" id="insideMiddle1">작품</button>
		<button class ="insideMiddle2" id="insideMiddle2">메모</button>
		<div class = "underLine"></div>
	</div>
	<div class ="boxForB">
		<div class ="topOfBox">
			<div class ="topBar"></div>
			<div class ="searchBox">
				<select>
					<option>선택없음</option>
					<option>이거</option>
					<option>저거</option>
				</select>
				<input type="text" placeholder="검색어를 입력하세요" style="font-size:10pt;">
				<button>검색</button>
				<div class = "blank2"></div>
				<button>등록</button>
				<button>수정</button>
				<a herf ="http://localhost:8090/TestWeb/HTML/cssproject.html/remove.html"></a><button>삭제</button></a>
			</div>
		</div>
		<div class ="boxForBoard">
		<table>
			<tr>
				<th>
				<input class = "check" type="checkbox" id="ex_chk"> 
				</th>
				<th> no</th>
				<th> 제목</th>
				<th> 좋아요 개수</th>
				<th> 댓글개수</th>
				<th> 태그</th>
				<th> 신고</th>
				<th> 설명</th>
			</tr>
			<tr>
				<td>
				<input class = "check" type="checkbox" id="ex_chk"> 
				</td>
				<td> no</td>
				<td> 제목</td>
				<td> 좋아요 개수</td>
				<td> 댓글개수</td>
				<td> 태그</td>
				<td> 신고</td>
				<td> 설명</td>
			</tr>
			<tr>
				<td>
				<input class = "check" type="checkbox" id="ex_chk"> 
				</td>
				<td> no</td>
				<td> 제목</td>
				<td> 좋아요 개수</td>
				<td> 댓글개수</td>
				<td> 태그</td>
				<td> 신고</td>
				<td> 설명</td>
			</tr>
			<tr>
				<td>
				<input class = "check" type="checkbox" id="ex_chk"> 
				</td>
				<td> no</td>
				<td> 제목</td>
				<td> 좋아요 개수</td>
				<td> 댓글개수</td>
				<td> 태그</td>
				<td> 신고</td>
				<td> 설명</td>
			</tr>
			<tr>
				<td>
				<input class = "check" type="checkbox" id="ex_chk"> 
				</td>
				<td> no</td>
				<td> 제목</td>
				<td> 좋아요 개수</td>
				<td> 댓글개수</td>
				<td> 태그</td>
				<td> 신고</td>
				<td> 설명</td>
			</tr>
			<tr>
				<td>
				<input class = "check" type="checkbox" id="ex_chk"> 
				</td>
				<td> no</td>
				<td> 제목</td>
				<td> 좋아요 개수</td>
				<td> 댓글개수</td>
				<td> 태그</td>
				<td> 신고</td>
				<td> 설명</td>
			</tr>
			<tr>
				<td>
				<input class = "check" type="checkbox" id="ex_chk"> 
				</td>
				<td> no</td>
				<td> 제목</td>
				<td> 좋아요 개수</td>
				<td> 댓글개수</td>
				<td> 태그</td>
				<td> 신고</td>
				<td> 설명</td>
			</tr>
			<tr>
				<td>
				<input class = "check" type="checkbox" id="ex_chk"> 
				</td>
				<td> no</td>
				<td> 제목</td>
				<td> 좋아요 개수</td>
				<td> 댓글개수</td>
				<td> 태그</td>
				<td> 신고</td>
				<td> 설명</td>
			</tr>
			<tr>
				<td>
				<input class = "check" type="checkbox" id="ex_chk"> 
				</td>
				<td> no</td>
				<td> 제목</td>
				<td> 좋아요 개수</td>
				<td> 댓글개수</td>
				<td> 태그</td>
				<td> 신고</td>
				<td> 설명</td>
			</tr>
			<tr>
				<td>
				<input class = "check" type="checkbox" id="ex_chk"> 
				</td>
				<td> no</td>
				<td> 제목</td>
				<td> 좋아요 개수</td>
				<td> 댓글개수</td>
				<td> 태그</td>
				<td> 신고</td>
				<td> 설명</td>
			</tr>
			<tr>
				<td>
				<input class = "check" type="checkbox" id="ex_chk"> 
				</td>
				<td> no</td>
				<td> 제목</td>
				<td> 좋아요 개수</td>
				<td> 댓글개수</td>
				<td> 태그</td>
				<td> 신고</td>
				<td> 설명</td>
			</tr>
		</table>
		</div>
	</div>
	
</div>
</body>
</html>
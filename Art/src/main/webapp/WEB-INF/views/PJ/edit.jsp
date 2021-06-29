<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>edit</title>
<link rel="stylesheet" href="resources/css/PJ/edit.css">
<script type="text/javascript" src="resources/script/jquery/jquery-1.12.4.min.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	$('#btnMenu').click(function() {
		if ($('.side_bar').css('display') == 'none') {
			$('.side_bar').slideDown();
		} else {
			$('.side_bar').slideUp();
		}
	})
});
</script>
</head>
<body>
	<body>
	<div class="hdr">
		<img src="resources/images/PJ/menu.png" id="btnMenu" alt="메뉴" width="35px" height="40px">
		<a href="main"><img src="resources/images/PJ/art2.png" id="btnLogo" alt="로고" width="70px" height="40px"></a>
		<img src="resources/images/PJ/look.png" id="btnLook" alt="돋보기" width="40px" height="40px">
	</div>
	<div class="side_bar">
		<div class="pfe">
			<img class="pfe_img" src="resources/images/PJ/짱구1.jpg" alt="짱구1" width="300px"
				height="300px">
		</div>
		<div id="pfeName">짱구</div>
		<a href="writing"><input type="button" id="btnUod"
			value="작품등록"></a>
		<div class="side_bar_menu">
			<span>--------------</span>
			<div id="sideBarMenu1">
				<a href="mygallary">나의 작업실</a>
			</div>
			<br />
			<div id="sideBarMenu2">
				<a href="gallary">작품 보러가기</a>
			</div>
			<br />
			<div id="sideBarMenu3">
				<a href="profile">개인정보 수정</a>
			</div>
			<br />
			<div id="sideBarMenu4">
				<a href="#">공지사항</a>
			</div>
		</div>
		<input type="button" id="btnLot" value="로그아웃">
	</div>
	<div class="wrap">
		<!-- <div id="editPage">작품올리기</div> -->
		<!-- <div id="glySet">작품관 선택</div>
		<br /> -->
		<div id="uploadWrap">
			<input type="button" id="upload" value=""/>
			<div id="uploadTxt">작품을 올려주세요.</div>
		</div>
		<div id="setW">
			<select id="set">
				<option value="0" selected="selected">카테고리</option>
				<option value="1">사진작품관</option>
				<option value="2">그림작품관</option>
				<option value="2">영상작품관</option>
			</select>
		</div>
		<!-- <div id="title">제목</div> -->
		<div id="titleInputW"><input id="titleInput" type="text" value="" placeholder="제목을 입력해주세요."></div>
		<!-- <div id="ctts">작품설명</div> -->
		<div id="cttsInW"><textarea id="cttsIn" name="cttsIn" cols="80" rows="10" placeholder="작품을 뽐내주세요."></textarea></div>
		<!-- <div id="tag">태그</div> -->
		<div id="tagInputW"><input id="tagInput" type="text" value="" placeholder="태그를 입력해주세요.(예 : #구름)"></div>
		<div id="srt">공개 설정</div>
		<div id="plcPve">
			<input name="plc" type="radio" checked="checked" /><label for="plc" id="plc">공개</label>
			<input name="plc" type="radio" /><label for="pve" id="pve">비공개</label>
		</div>
		<br />
		<div class="save_ccl">
			<input id="btnSave" type="button" value="수정하기">
			<input id="btnCcl" type="button" value="취소하기">
		</div>
	</div>
	<div class="ftr">
		<a href="main"><img src="resources/images/PJ/art2_w.png" id="btnLogo2" alt="로고" width="70px" height="50px"></a>
		<div class="ftr_pae">You can be an art writer.</div>
		<div id="ftr1"><a href="#">관리방침 안내</a></div>
		<div id="ftr2"><a href="#">도움말 안내</a></div>
		<div id="ftr3"><a href="#">회원가입 및 글게시 안내</a></div>
		<div id="ftr4"><a href="#">홈페이지 서비스 안내</a></div>
	</div>
</body>
</body>
</html>
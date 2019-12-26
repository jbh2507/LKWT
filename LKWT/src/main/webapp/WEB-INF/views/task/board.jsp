<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>LKWT</title>

  <!-- Custom fonts for this template -->
  <link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">

  <!-- Custom styles for this page -->
  <link href="/resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">
  
  <%@include file="../includes/sidebar.jsp"%>

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Begin Page Content -->
        <div class="container-fluid" style="margin-top: 20px">

          <!-- Page Heading -->
          <h1 class="h3 mb-2 text-gray-800">Task</h1>
          <p class="mb-4">과제 관리 페이지 입니다.</p>

          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">{class name}</h6>
            </div>
            <div class="card-body">
     
              <div style="margin: 10px">
            	  <div class="d-flex flex-row align-item-center justify-content-between">
	              	<form class="row" style="width: 50%" method="get">
	             	  <div class="col-sm-12 col-md-2">
		              <select name="category" class="custom-select custom-select-sm form-control form-control-sm">
		                <option value="T">제목</option>
		                <option value="W">요일</option>
		              </select>
		              </div>
		              <div class="col-sm-12 col-md-8">
	                  	<input type="text" name="keyword" class="form-control form-control-sm" placeholder="제목을 입력해주세요">
	                  </div>
	                  <div class="col-auto">
	                  	<button id="searchBtn">검색</button>
	                  </div>
	                </form>
	                <div>
	                  <a id="registBtn" href="#" class="btn btn-primary">작성</a>
	                </div>
                 </div>
              </div>
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th style="width: 5%">번호</th>
                      <th>제목</th>
                      <th style="width: 10%">날짜</th>
                      <th style="width: 10%">제출율</th>
                    </tr>
                  </thead>
                  <tbody id="listBody">
	                  <c:forEach items="${pageDTO.list}" var="post">
	                    <tr>
	                      <td>${post.filebox.bno}</td>
	                      <td><a href="${post.filebox.bno}"><c:out value="${post.filebox.title}"/></a></td>
	                      <td>${post.filebox.regDate.toString()}</td>
	                      <td>
	                      	<div class="progress m-0">
                    			<div class="progress-bar" role="progressbar" style="width: ${post.responseRate}%" aria-valuenow="${post.responseRate}" aria-valuemin="0" aria-valuemax="100"></div>
                 		  	</div>
	                     </td>
	                    </tr>
	                  </c:forEach>
                  
                  </tbody>
                </table>
              </div>

              <div class="row">
                <div class="col-sm-12 col-md-5">
                  <div class="dataTables_info" id="dataTable_info" role="status" aria-live="polite">Showing ${pageDTO.start} to ${pageDTO.end} of ${pageDTO.lastPage} entries</div>
                </div>
                <div class="col-sm-12 col-md-7">
                   <div class="dataTables_paginate paging_simple_numbers" id="paginate">
                    <ul class="pagination" style="justify-content: flex-end;">
                      <li class="paginate_button page-item previous <c:if test="${!(pageDTO.prev)}">disabled</c:if>" id="dataTable_previous">
                        <a href="${pageDTO.start-1}" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link">Previous</a>
                      </li>
                      
                      <c:forEach var="i" begin="${pageDTO.start}" end="${pageDTO.end}">
	                      <li class="paginate_button page-item <c:if test="${pageDTO.source.page == i}">active</c:if>">
	                        <a href="${i}" aria-controls="dataTable" tabindex="0" class="page-link">${i}</a>
	                      </li>
                      </c:forEach>
                      
                      <li class="paginate_button page-item next <c:if test="${!(pageDTO.next)}">disabled</c:if>" id="dataTable_next">
                        <a href="${pageDTO.end+1}" aria-controls="dataTable" data-dt-idx="2" tabindex="0" class="page-link">Next</a>
                      </li>
                   </ul>
                  </div>
                </div>
              </div>

            </div>
          </div>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->
      

  	<%@include file="../includes/footer.jsp"%>
  	
  	<%@include file="./task.jsp"%>
  	<%@include file="./register.jsp"%>
  	<%@include file="./modify.jsp"%>
	
    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

<script>
$(document).ready(function() {
	var cno = ${pageDTO.source.no};
	var tag = 'T';
	var curBno = null;
	
	var $keyword = $("input[name='keyword']");
	var $pop = $("#pop");
	var $regPop = $("#regPop");
	var $modyPop = $("#modyPop");
	
	var $post = $(".post");
	var $postTitle = $("#postTitle");
	var $postContent = $("#postContent");
	var $files = $("#files");
	var $nonSubmitter = $("#nonSubmitter");
	
	var $modyTitle = $("input[name='title']", "#modyForm");
	var $modyContent = $("textarea[name='content']", "#modyForm");
	
	
	function callfilebox(bno){
		$.ajax({
			url:"/task/"+bno,
			type:"GET",
			dataType:"json",
			success: function (result) {
				console.log(result);
				console.log(result.filebox);
				
				var filebox = result.filebox;
				var files = result.files;
				curBno = filebox.bno;
				
				$postTitle.text(filebox.title);
				$modyTitle.val(filebox.title);
				$postContent.text(filebox.content);
				$modyContent.val(filebox.content);
				
				var filesContent = "";
				for(let i=0; i<files.length; i++){
					filesContent += "<div><a href='/download/?data="+data[i].fno+"_"+data[i].regDate+"_"+data[i].fname+"' class='downloadBtn btn'><i class='fas fa-download'></i></a>"+files[i].fname+"  제출자: "+files[i].userName+"</div>"
				}
				$files.html(filesContent);
			}
		});
	};
	
	function setPopupDiv(bno){
		$post.html("");
		
		callfilebox(bno);		
	}
	
	// 페이지 이동
	$("#paginate").on("click", "ul li a", function(e) {
		e.preventDefault();
	
		var addr = ${pageDTO.source.no}+"?page="+$(this).attr("href");
		
		<c:if test="${pageDTO.source.amount != null}">addr += "&amount="+${pageDTO.source.amount};</c:if>
		<c:if test="${pageDTO.source.category != null}">addr += "&category="+"${pageDTO.source.category}";</c:if>
		<c:if test="${pageDTO.source.keyword != null}">addr += "&keyword="+"${pageDTO.source.keyword}";</c:if>
		
		console.log(addr);
		
		location.href = addr;
	});
	
	// 팝업
	$("#listBody").on("click", "tr td a", function (e) {
		e.preventDefault();
		
		var bno = $(this).attr("href");
		console.log(this);
		console.log(bno);
		setPopupDiv(bno);
		
		$pop.show();
	});
	
	//팝업 닫음
	$("#popDown").on("click", function (e) {
	
		e.preventDefault();
		
		$pop.hide();
	});
	
	// 등록 팝업 열기
	$("#registBtn").on("click", function (e) {
	
		e.preventDefault();
		
		$regPop.show();
	});
	
	// 등록 팝업 닫음
	$("#regPopDown").on("click", function (e) {
	
		e.preventDefault();
		
		$regPop.hide();
	});
	
	// 수정 팝업 열기
	$("#modyPopUp").on("click", function (e) {
	
		e.preventDefault();
		
		$modyPop.show();
	});
	
	// 수정 팝업 닫음
	$("#modyPopDown").on("click", function (e) {
	
		e.preventDefault();
		
		$modyPop.hide();
	});
	
	// 검색 카테고리 전환시 placeholder 변경
	$("select[name='category']").on("change", function () {
		if(this.value === 'T') $keyword.attr("placeholder", "제목을 입력해주세요");
		if(this.value === 'W') $keyword.attr("placeholder", "1~7");
	});
	
	// 과제 등록
	$("#regBtn").on("click", function () {
		var formData = new FormData($("#regForm").get(0));
		formData.append("cno", cno);
		
		console.log(formData);
		
		$.ajax({
			url:"/task",
			type:"POST",
			data: formData,
			enctype: 'multipart/form-data',
			processData: false,
	        contentType: false,
		});
		
		$regPop.hide();
	});
	
	// 과제 수정
	$("#modyBtn").on("click", function () {
		var formData = new FormData($("#modyForm").get(0));
		formData.append("tag", tag);
		formData.append("bno", curBno);
		
		console.log(formData);
		
		$.ajax({
			url:"/task",
			type:"PUT",
			data: formData,
			enctype: 'multipart/form-data',
			processData: false,
	        contentType: false,
	        success: function (result) {
	        	callfilebox(curBno)
			}
		});
		
		$modyPop.hide();
	});

});
</script>

</body>

</html>

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
          <h1 class="h3 mb-2 text-gray-800">ResourceBoard</h1>
          <p class="mb-4">자료실 관리 페이지 입니다.</p>

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
                    </tr>
                  </thead>
                  <tbody id="listBody">
	                  <c:forEach items="${pageDTO.list}" var="post">
	                    <tr>
	                      <td>${post.filebox.bno}</td>
	                      <td><a href="${post.filebox.bno}"><c:out value="${post.filebox.title}"/></a></td>
	                      <td>${post.filebox.regDate.toString()}</td>
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
  	
  	<%@include file="./resource.jsp"%>
  	<%@include file="./register.jsp"%>
  	<%@include file="./modify.jsp"%>
	
    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

<script>
$(document).ready(function() {
	var cno = ${pageDTO.source.no};
	var tag = 'L';
	var curBno = null;
	
	var $keyword = $("input[name='keyword']");
	var $pop = $("#pop");
	var $regPop = $("#regPop");
	var $modyPop = $("#modyPop");
	
	var $post = $(".post");
	var $postTitle = $("#postTitle");
	var $postContent = $("#postContent");
	var $accesslog = $("#accesslog");
	
	var $modyTitle = $("input[name='title']", "#modyForm");
	var $modyContent = $("textarea[name='content']", "#modyForm");
	
	var $files = $("#files");
	var $regFileView = $("#regFileView");
	var $modyFileView = $("#modyFileView");
	
	var $logCurPage = $(".curPage", "#logPaginate");
	var $logNextPage = $(".next", "#logPaginate");
	var $logPrevPage = $(".previous", "#logPaginate");
	
	function viewFile(data, $target) {
		
		console.log(data);
		
		var filesContent = "";
		for(let i=0; i<data.length; i++){
			if(!data[i].fno) {
				filesContent += "<div><i class='far fa-check-circle mr-1'></i>"+data[i].orginName+"</div>";
				filesContent +="<input name='fnames' type='hidden' value='"+data[i].regDate+"_"+data[i].fname+"'>"
			}
			else filesContent += "<div><a href='/download/?data="+data[i].fno+"_"+data[i].regDate+"_"+data[i].fname+"' class='downloadBtn btn'><i class='fas fa-download'></i></a>"+data[i].orginName+"</div>";
			
		}
		
		$target.html(filesContent);
	}   
	
	function callfilebox(bno){
		$.ajax({
			url:"/resource/"+bno,
			type:"GET",
			dataType:"json",
			success: function (result) {
				
				var filebox = result.filebox;
				var files = result.files;
				curBno = filebox.bno;
				
				$postTitle.text(filebox.title);
				$modyTitle.val(filebox.title);
				$postContent.text(filebox.content);
				$modyContent.val(filebox.content);
				
				viewFile(files, $files);
				
				for(let i=0; i<files.length; i++){
					files[i].fno = null;
				}
				viewFile(files, $modyFileView);
				
				callAccessLog(1);
			}
		});
	};
	
	function callAccessLog(page) {
		$.ajax({
			url:"/resource/accesslog/"+curBno+"?page="+page+"&amount=5",
			type:"GET",
			dataType:"json",
			success: function (result) {
				var list = result.list;
				var page = result.source.page;
				var next = page != result.lastPage;
				var prev = page != 1;
				
				if(next) $logNextPage.removeClass('disabled');
				else $logNextPage.addClass('disabled');
				
				if(prev) $logPrevPage.removeClass('disabled');
				else $logPrevPage.addClass('disabled');
				
				$logNextPage.attr( 'href', page+1 );
				$logPrevPage.attr( 'href', page-1 );
				$logCurPage.text(page);
				
				var tmp = "";
				for(let i=0; i<list.length; i++){
					var date = list[i].date;
					tmp += "<div class='row' style='width: 90%'> <div class='col-sm-12 col-md-4'>"+date.year+"/"+date.monthValue+"/"+date.dayOfMonth+" "+date.hour+":"+date.minute+"</div> <div class='col-sm-12 col-md-5'>"+list[i].fname+"</div> <div class='col-sm-12 col-md-3'>"+list[i].userName+"</div> </div>"
				}				
				
				
				$accesslog.html(tmp);
			}
		});
	}
	
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
		
		location.href = addr;
	});
	
	// 팝업
	$("#listBody").on("click", "tr td a", function (e) {
		e.preventDefault();
		
		var bno = $(this).attr("href");
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
	
	// 파일 선택
	$("#modyFileInput").on("change", function () {
		
		var formData = new FormData();
		var files = this.files
		
		for (let i = 0; i < files.length; i++) {
			formData.append("uploadfiles" , files[i]);
		}
		
		$.ajax({
			url : '/upload',
			processData : false,
			contentType : false,
			data : formData,
			type : 'POST',
			success : function(result) {
				
				viewFile(result, $modyFileView);
			} // fnc
		}); // $.ajax
		
	}); // fileInput change
	
	// 파일 선택
	$("#regFileInput").on("change", function () {
		
		var formData = new FormData();
		var files = this.files
		
		for (let i = 0; i < files.length; i++) {
			formData.append("uploadfiles" , files[i]);
		}
		
		$.ajax({
			url : '/upload',
			processData : false,
			contentType : false,
			data : formData,
			type : 'POST',
			success : function(result) {
				
				viewFile(result, $regFileView);
				
			} // fnc
		}); // $.ajax
	}); // fileInput change
	
	// 자료 등록
	$("#regBtn").on("click", function () {
		var formData = new FormData($("#regForm").get(0));
		formData.append("cno", cno);
		
		$.ajax({
			url:"/resource",
			type:"POST",
			data: formData,
			enctype: 'multipart/form-data',
			processData: false,
	        contentType: false,
		});
		
		$regPop.hide();
	});
	
	// 자료 수정
	$("#modyBtn").on("click", function () {
		var formData = new FormData($("#modyForm").get(0));
		formData.append("tag", tag);
		formData.append("bno", curBno);
		
		$.ajax({
			url:"/resource",
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
	
	$logNextPage.on("click", function (e) {
		e.preventDefault();
		
		$this = $(this);
		
		if($this.hasClass('disabled')) return;
		
		callAccessLog($this.attr("href"));
	});
	
	$logPrevPage.on("click", function (e) {
		e.preventDefault();
		
		$this = $(this);
		
		if($this.hasClass('disabled')) return;
		
		callAccessLog($this.attr("href"));
	});

});
</script>

</body>

</html>

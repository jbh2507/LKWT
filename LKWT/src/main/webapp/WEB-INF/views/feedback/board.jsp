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
          <h1 class="h3 mb-2 text-gray-800">Feedback</h1>
          <p class="mb-4">해당 클래스의 피드백 목록입니다.</p>

          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">{class name}</h6>
            </div>
            <div class="card-body">
     
              <div style="margin: 10px">
             	<form action="" class="row">
             	  <div class="col-sm-12 col-md-2">
	              <select name="category" class="custom-select custom-select-sm form-control form-control-sm">
	                <option value="이해도">이해도</option>
	                <option value="진행도">진행도</option>
	              </select>
	              </div>
	              <div class="col-sm-12 col-md-3">
                  <input type="text" name="content" class="form-control form-control-sm" placeholder="내용을 입력해주세요">
                  </div>
                  <div class="col-sm-12 col-md-2">
                  <label><input type="checkbox" name="yesOrNo">Yes or No</label>
                  </div>
                  <div class="col-sm-12 col-md-3">
                  <button>요청</button>
                  </div>
           	  	</form>
              </div>
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th style="width: 5%">번호</th>
                      <th>요청 내용</th>
                      <th style="width: 10%">날짜</th>
                      <th style="width: 10%">응답률</th>
                      <th style="width: 5%">평균</th>
                    </tr>
                  </thead>
                  <tbody id="listBody">
	                  <c:forEach items="${pageDTO.list}" var="post">
	                    <tr>
	                      <td>${post.question.qno}</td>
	                      <td><a href="${post.question.qno}"><c:out value="${post.question.content}"/></a></td>
	                      <td>${post.question.regDate.toString()}</td>
	                      <td>
	                      	<div class="progress mb-4">
                    			<div class="progress-bar" role="progressbar" style="width: ${post.responseRate}%" aria-valuenow="${post.responseRate}" aria-valuemin="0" aria-valuemax="100"></div>
                 		  	</div>
	                      </td>
	                      <td>${post.avgAnswer}%</td>
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
  	
  	<%@include file="./question.jsp"%>
  
	
    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->
  
<script src="https://d3js.org/d3.v5.min.js"></script>

<script src="/resources/js/chart-pie.js"></script>

<script>
$(document).ready(function() {
	var $pop = $("#questionPop");
	var $ans = $("#ans");
	var $pie = $("#pie");
	var data = [];
	
	function callAnswerList(qno){
		$.ajax({
			url:"/feedback/question/"+qno+"/answerList",
			type:"GET",
			dataType:"json",
			success: function (result) {
				var answerForm = "";
				
				data = [{name:0, value:0, color: '#B40404'},
					{name:25, value:0, color: '#FF0000'},
					{name:50, value:0, color: '#FF8000'},
					{name:75, value:0, color: '#FFFF00'},
					{name:100, value:0, color: '#BFFF00'}];
				
				
				for(let i=0; i<result.length; i++){
					let an = result[i];
					
					answerForm +=
						"<div class='row'><div class='col-sm-12 col-md-2 text-center'><img src='/resources/img/usericon.png' style='width: 50%'><p >"
						+an.answer.username
						+"</p></div><div class='col-sm-12 col-md-10'><div class='row bg-gray-200'><div class='col-sm-12 col-md-2'>"
		                +an.answer.indicator
						+"</div><div class='col-sm-12 col-md-10'>";
						
					if(an.ansComment.comment != null) answerForm += an.ansComment.comment;
					
					answerForm += "</div></div></div></div>";
						
					for(let i=0; i<data.length; i++){
						if(data[i].name == an.answer.indicator) data[i].value += 1;
					}
						
				}
				
				console.log(data);
				
				$pie.html("");
				createPie('#pie', data);
				
				$ans.html(answerForm);
				
			}
		});
	};
	var $qContent = $('#qContent');
	var $qContent2 = $('#qContent2');
	var $qRegDate = $('#qRegDate');
	var $qCategory = $('#qCategory');
	function callQuestion(qno){
		$.ajax({
			url:"/feedback/question/"+qno,
			type:"GET",
			dataType:"json",
			success: function (result) {
				 $qContent.text(result.content);
				 $qContent2.text(result.content);
				 $qCategory.text("["+result.category+"]");
				 $qRegDate.text(new Date(result.regDate).toLocaleDateString());
				console.log(result);
			}
		});
	};
	
	// 페이지 이동
	$("#paginate").on("click", "ul li a", function(e) {
		e.preventDefault();
	
		var addr = "board?no="+${pageDTO.source.no}+"&page="+$(this).attr("href");
		
		<c:if test="${pageDTO.source.amount != null}">addr += "&amount="+${pageDTO.source.amount};</c:if>
		<c:if test="${pageDTO.source.category != null}">addr += "&category="+${pageDTO.source.category};</c:if>
		<c:if test="${pageDTO.source.keyword != null}">addr += "&keyword="+${pageDTO.source.keyword};</c:if>
		
		console.log(addr);
		
		location.href = addr;
	});
	
	// 팝업
	$("#listBody").on("click", "tr td a", function (e) {
		
		e.preventDefault();
		
		var qno = $(this).attr("href");
		
		callAnswerList(qno);
		
		callQuestion(qno);
		
		$pop.show();
	});
	
	//팝업 닫음
	$("#popDown").on("click", function (e) {
	
		e.preventDefault();
		
		$pop.hide();
	});
});

</script>

</body>

</html>

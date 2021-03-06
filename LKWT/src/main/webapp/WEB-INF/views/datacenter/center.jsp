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

  <style type="text/css">
	/* 13. Basic Styling with CSS */
	
	/* Style the lines by removing the fill and applying a stroke */
	.line {
	    fill: none;
	    stroke: #ffab00;
	    stroke-width: 3;
	}
	  
	.overlay {
	  fill: none;
	  pointer-events: all;
	}
	
	/* Style the dots by assigning a fill and stroke */
	.dot {
	    fill: #ffab00;
	    stroke: #fff;
	}
	  
	  .focus circle {
	  fill: none;
	  stroke: steelblue;
	}

  </style>
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
          <h1 class="h3 mb-2 text-gray-800">DataCenter</h1>
          <p class="mb-4">피드백 정보를 시각화해 제공합니다</p>

		  <div class="card mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">Select View</h6>
                </div>
                <div class="card-body">
                <div>
                	<form id="dataForm">
                		<div class="form-group row">
	                		<label>groupBy: <select id="formGroupBy"  name="groupBy">
	                			<option value="dayOfYear">일</option>
	                			<option value="weekOfYear">주</option>
	                			<option value="dayOfWeek">요일</option>
	                			<option value="qno">개별</option>
	                		</select></label>
	                		
	                		<div>
	                			<label><input type="radio" name="tag" value="이해도">이해도</label>
	                			<label><input type="radio" name="tag" value="진행도">진행도</label>
	                		</div>
                		
                		</div>
                		
                		<div class="form-group row">
	                		<label>search: <select id="formCategory" class="form-group" name="category">
	                			<option value="">전체</option>
	                			<option value="sno">과목</option>
	                			<option value="cno">차시</option>
	                			<option value="user">학생ID</option>
	                		</select>
	                		
	                		<input id="formKeyword" type="text" name="keyword"></label>
                		</div>
                		
                		<div class="form-group row">
	                		<label>range: <input id="formStartDate" type="date" name="startDate"> ~
	                		<input id="formEndDate" type="date" name="endDate"></label>
                		</div>
                		
                		<button id="formBtn">조회</button>
                	</form>
                </div>
                <div id="chartAreaOne" style="height: 30em">
        
                </div>
              </div>
             </div>
	
          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">바로보기 차트</h6>
            </div>
            <div class="card-body" style="height: 30em">
     

            </div><!-- card body -->
          </div><!-- card end -->

          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">비교차트</h6>
            </div>
            <div class="card-body">
     

            </div><!-- card body -->
          </div><!-- card end -->

          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">검색</h6>
            </div>
            <div class="card-body">
     

            </div><!-- card body -->
          </div><!-- card end -->
          
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

<script src="/resources/js/createChart.js"></script>

<script>
$(document).ready(function() {
	const $pop = $("#questionPop");
	const $ans = $("#ans");
	const $pie = $("#pie");
	
	const $qContent = $('#qContent');
	const $qContent2 = $('#qContent2');
	const $qRegDate = $('#qRegDate');
	const $qCategory = $('#qCategory');
	
	const $dataForm = $("#dataForm")
	const $groupByForm = $("#formGroupBy")
	const $categoryForm = $("#formCategory")
	const $keywordForm = $("#formKeyword")
	const $startDateForm = $("#formStartDate")
	const $endDateForm = $("#formEndDate")
	
	
	function addAnswer(an){
		var answerForm =
			"<div class='row'><div class='col-sm-12 col-md-2 text-center'><img src='/resources/img/usericon.png' style='width: 50%'><p >"
			+an.answer.username
			+"</p></div><div class='col-sm-12 col-md-10'><div class='row bg-gray-200'><div class='col-sm-12 col-md-2'>"
            +an.answer.indicator
			+"</div><div class='col-sm-12 col-md-10'>";
		
		if(an.ansComment != null && an.ansComment.tag != null) answerForm += "["+an.ansComment.tag+"] ";
		if(an.ansComment != null && an.ansComment.comment != null) answerForm += an.ansComment.comment;
		
		answerForm += "</div></div></div></div>";
			
		for(let i=0; i<data.length; i++){
			if(data[i].name == an.answer.indicator) data[i].value += 1;
		}
		
		$ans.prepend(answerForm);
		
		$pie.html("");
		createPie('#pie', data);
	}
	
	function callAnswerList(qno){
		$.ajax({
			url:"/feedback/question/"+qno+"/answerList",
			type:"GET",
			dataType:"json",
			success: function (result) {
				data = [{name:0, value:0, color: '#B40404'},
					{name:25, value:0, color: '#FF0000'},
					{name:50, value:0, color: '#FF8000'},
					{name:75, value:0, color: '#FFFF00'},
					{name:100, value:0, color: '#BFFF00'}];
				
				
				for(let i=0; i<result.length; i++){
					let an = result[i];
					
					addAnswer(an);
				}
				
				console.log(data);
			}
		});
	};
	
	function callData(selector, groupBy, startDate, endDate, tag, category, keyword){
		
		
		
		$.ajax({
			url: url,
			type:"GET",
			data: data,
			dataType:"json",
			success: function (result) {
				createChart(selector, result);
			}
		});
	};
	
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
	
	function setPopupDiv(qno){
		$ans.html("");
		$pie.html("");
		
		callAnswerList(qno);
		callQuestion(qno);
		
		$pop.show();
	}
	
	$("#formBtn").on("click", function(e){
		e.preventDefault();
		
		var selector = "#chartAreaOne"
		
		var data = "?groupBy="+$groupByForm.val()+"&tag="+$("input[name='tag']:checked", "#dataForm").val()
				
		var category = $categoryForm.val()
		var keyword = $keywordForm.val()
		if(category.length != 0) data += "&category="+category+"&keyword="+keyword
		
		var startDate = $startDateForm.val()
		var endDate = $endDateForm.val()
		
		if(endDate - startDate > 0) data += "&startDate="+startDate+"&endDate="+endDate
		
		var isQno = $groupByForm.val() == 'qno';

		$.ajax({
			url: "/datacenter/getData"+data,
			type:"GET",
			dataType:"json",
			success: function (result) {
				createChart(selector, result, isQno);
			}
		});
	});
	
	// 팝업
	$("#listBody").on("click", "tr td a", function (e) {
		e.preventDefault();
		
		var qno = $(this).attr("href");
		
		setPopupDiv(qno);
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
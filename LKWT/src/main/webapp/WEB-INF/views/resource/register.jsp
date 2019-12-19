<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="regPop" class="position-fixed" style="background-color: rgba(0, 0, 0, 0.5); top:0; left:0; display: none; width: 100vw; height: 100vh;">
    <div class="card position-absolute" style="transform:translate(-50%, -50%); top:50%; left:50%; width: 75vw;">
        <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
           
            <h6 class="m-0 font-weight-bold text-primary post">{className} 과제 부여</h6>
        
        </div>


        <!-- BODY -->
        <div class="card-body m-0">
        
            	<form id="regForm" action="/task" method="post" class="board">
					<div>
						<input type="text" class="form-control form-control-user post"
							id="title" name="title" placeholder="title">
					</div>
	
					<div class="form-group">
						<textarea id="content" class="form-control form-control-user post" name="content"
							placeholder="Content"></textarea>
					</div>
					
					<hr>
					
					<div id="regFileView" class="post">
					
					</div>
				</form>
				<div>
					<input id="regFileInput" type="file" multiple="multiple">
				</div>
				<hr>
				<button id="regBtn" class="btn btn-success btn-icon-split">
					<span class="icon text-white-50"> <i class="fas fa-check"></i>
					</span> <span class="text">Registe</span>
				</button>

				<a id="regPopDown" href="/board/list" class="btn btn-danger btn-icon-split"> <span
					class="icon text-white-50"> <i class="fas fa-trash"></i>
				</span> <span class="text">Cancel</span>
				</a>
        </div>
    </div>
</div>
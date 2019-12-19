<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="pop" class="position-fixed" style="background-color: rgba(0, 0, 0, 0.5); top:0; left:0; display: none; width: 100vw; height: 100vh;">
    <div class="card position-absolute" style="transform:translate(-50%, -50%); top:50%; left:50%; width: 75vw;">
        <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
           
            <h6 id="postTitle" class="m-0 font-weight-bold text-primary post">제목</h6>
        
        </div>

        <!-- BODY -->
        <div class="card-body m-0" style="height: 70vh; overflow: hidden;">
	        <div style="height: 100%">
		        <div style="height: 30%">
		            <div class="row" style="height: 90%">
		            
		                <div id="postContent" class="col-sm-12 col-md-7 post" style="height: 100%; overflow: auto;">
		                
		                </div>
		                
		                <div id="files" class="col-sm-12 col-md-5" style="height: 100%; overflow: auto;">
		                	
		                </div>
		                
	            	</div>
		            <hr>
	            </div>
	            	<div style="height: 50%; ">
	            	<h6>AccessLog</h6>
	            	<div id="accesslog" class="post" style="height: 75%; overflow: auto;"></div>
	                <div style="height: 25%;">
	                
						<div class="dataTables_paginate paging_simple_numbers" id="logPaginate">
						
							<ul class="pagination" style="justify-content: flex-end;">									
								<li class="paginate_button page-item previous">
									<a href="#" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link">Previous</a>
								</li>
								  
								
									<li class="paginate_button page-item">
										<div class="page-link curPage">#</div>
									</li>
							
								
								<li class="paginate_button page-item next">
									<a href="#" aria-controls="dataTable" data-dt-idx="2" tabindex="0" class="page-link">Next</a>
								</li>
							</ul>
							
						</div>
					</div>
						
	               </div>
	            <div class="h-auto">
	            	<hr>
		            <button id="modyPopUp" class="btn btn-warning btn-icon-split">
						<span class="icon text-white-50"> <i class="fas fa-edit"></i>
						</span> <span class="text">Modify</span>
					</button>
		
					<a id="popDown" href="/board/list" class="btn btn-danger btn-icon-split"> <span
						class="icon text-white-50"> <i class="fas fa-times-circle"></i>
					</span> <span class="text">Close</span>
					</a>
				</div>
			</div>
        </div>
    </div>
</div>
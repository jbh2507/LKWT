<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="pop" class="position-fixed" style="background-color: rgba(0, 0, 0, 0.5); top:0; left:0; display: none; width: 100vw; height: 100vh;">
    <div class="card position-absolute" style="transform:translate(-50%, -50%); top:50%; left:50%; width: 75vw;">
        <div class="card-header py-3">
            <div class="row">
                <div class="col-sm-12 col-md-10">
                    <h6 id="qContent" class="m-0 font-weight-bold text-primary">qcontent</h6>
                </div>
                <div class="col-sm-12 col-md-2" style="justify-content: flex-end;">
                    <button id="popDown">close</button>
                </div>
            </div>
        </div>

        <div class="card-body" style="margin: 0px; height: 100%;">
            <div class="row">
            
                <div class="col-sm-12 col-md-4 row">
	                <div class="col-sm-12 col-md-12 align-self-center">
	                	<div id=pie class="m-3"></div>
	                	<div id=qinfo class="m-3">
	                		<p id=qCategory></p>
	                		<p id=qContent2></p>
	                		<p id=qRegDate></p>
	                	</div>
	                </div>
                </div>
                
                <div id="ans" class="col-sm-12 col-md-8" style="height: 50vh; overflow: auto;">
                
                </div>
                
            </div>
        </div>
    </div>
</div>
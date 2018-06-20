<!doctype html>
<html>
<head>
</head>
<body>
	<form id="ajaxForm" method="post" data-toggle="validation"  data-widget='{"onkeyup": false}' 
		action="${request.contextPath}/manager/api/app/save" theme="simple" class="form-horizontal ">
    	<input type="hidden" id="f_appId" name="appId" value="${appDto.appId?default("")}"/>
		<div class="panel-body">
			<div class="row">
		        <div class="col-sm-12">
		            <div class="form-group">
		                <i class="fa fa-user icon-left" aria-hidden="true"></i>
		                <input type="text" id="f_appName" name="appName" class="form-control"  value="${appDto.appName?default("")}" placeholder="应用名称" style="padding-left:32px;"  data-rules='{"required":true}'>
		            </div>
		        </div>
		    </div>
		    <div class="row" style="margin-top:15px;">
		        <textarea id="f_appDesc" placeholder="描述信息" name="appDesc" rows="4" class="form-control">${appDto.appDesc?default("")}</textarea>
		    </div>
		</div>
	</form>
</body>
</html>
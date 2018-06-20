<!doctype html>
<html>
<head>
	
</head>
<body>
	<form id="ajaxForm" method="post" action="${request.contextPath}/authz/user/savePassword" data-toggle="validation"  role="form" class="form-horizontal sl_all_form" >
		<div class="row">
			 <div class="col-md-12 col-sm-12">
		        <div class="form-group">
		          <label for="" class="col-sm-3 control-label">
		          	<span style="color:red;">*</span>原密码
	          	</label>
		          <div class="col-sm-8">
		            	 <input type="password"  name="ymm" id="ymm" class="form-control input-sm"  validate="{required:true}" />
		          </div>
		        </div>
		      </div>
		      <div class="col-md-12 col-sm-12">
				<div class="form-group">
					<label for="" class="col-sm-3 control-label">&nbsp;</label>
					<div class="col-sm-8" id="strengthID"></div>
				</div>
			 </div>
			<div class="col-md-12 col-sm-12">
				<div class="form-group">
					<label for="" class="col-sm-3 control-label">
						<span class="red">*</span>新密码
					</label>
					<div class="col-sm-8" >
						<input type="password" maxlength="16" name="mm" id="mm" class="form-control input-sm" data-rules='{"required":true,"strength":true}'/>
					</div>
				</div>
			</div>
			<div class="col-md-12 col-sm-12">
				<div class="form-group">
					<label for="" class="col-sm-3 control-label">
						<span class="red">*</span>重复新密码
					</label>
					<div class="col-sm-8">
						<input type="password" maxlength="16" name="nmm" id="nmm" class="form-control input-sm" data-rules='{"required":true,"equalTo":"#mm"}'/>
					</div>
				</div>
			</div>
		</div>
   </form>
 </body>
 <script type="text/javascript">
 	$(function(){
 		$("#mm").strength({"target":"#strengthID"});
 		$.extend($.validator.messages, {
			equalTo: "两次密码输入不一致"
		});
 	});
 </script>
</html>

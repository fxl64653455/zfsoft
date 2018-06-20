<!doctype html>
<html>
<head>
</head>
<body>
    <form id="ajaxForm" method="post" data-widget='{"onkeyup": false}'  action="${request.contextPath}/manager/api/database/new/form" theme="simple" class="form-horizontal ">
    	
    	<div class="row">
	        <div class="col-sm-12">
	    		<div class="form-group">
		            <label for="db-type" class="col-sm-2 control-label">数据库类型</label>
		            <div class="col-sm-10">
		            	<select id="db-type" name="dbtype" class="form-control form-select chosen-select"  data-rules='{"required":true}'>
				    	  	<option value="">--请选择--</option>
				    	  	[#list driverList as driver] 
						    <option value="${driver.key}" db-url="${driver.url}">${driver.desc}</option>
						    [/#list]
				    	</select>
		                <script type="text/javascript">
		                	$("#db-type").trigger("chosen");
		                </script>
		            </div>
		        </div>
	        </div>
	        <div class="col-sm-12">
	    		<div class="form-group">
		            <label for="db-name" class="col-sm-2 control-label">数据库名称</label>
		            <div class="col-sm-10">
		                <input placeholder="请输入数据库名称." class="form-control" id="db-name" name="name" type="text" data-rules='{"required":true}' />
		            </div>
		        </div>
	        </div>
	        <div class="col-sm-12">
	    		<div class="form-group">
		            <label for="db-cnname" class="col-sm-2 control-label">数据库中文名称</label>
		            <div class="col-sm-10">
		                <input placeholder="请输入数据库中文名称.如：xxx数据库" class="form-control" id="db-cnname" name="cnname" type="text" data-rules='{"required":true}' />
		            </div>
		        </div>
	        </div>
	        <div class="col-sm-12">
	    		<div class="form-group">
		            <label for="db-url" class="col-sm-2 control-label">数据库连接地址</label>
		            <div class="col-sm-10">
		                <input placeholder="请输入数据库连接地址." class="form-control" id="db-url" name="url" type="text" data-rules='{"required":true}' />
		            </div>
		        </div>
	        </div>
	        <div class="col-sm-6">
	    		<div class="form-group">
		            <label for="db-username" class="col-sm-4 control-label">数据库账号</label>
		            <div class="col-sm-8">
		                <input placeholder="请输入数据库账号." class="form-control" id="db-username" name="username" type="text" data-rules='{"required":true}' />
		            </div>
		        </div>
	        </div>
	        <div class="col-sm-6">
	    		<div class="form-group">
		            <label for="db-password" class="col-sm-4 control-label">数据库密码</label>
		            <div class="col-sm-8">
		                <input placeholder="请输入数据库密码." class="form-control" id="db-password" name="password" type="password" data-rules='{"required":true}' />
		            </div>
		        </div>
	        </div>
	        <div class="col-sm-12">
	    		<div class="form-group">
		            <label for="db-password" class="col-sm-2 control-label">数据库描述</label>
		            <div class="col-sm-10">
		            	<textarea placeholder="请输入该数据库相关简述." class="form-control" id="db-desc" name="desc" data-rules='{"required":true,"stringMaxLength":200}'
		                cols="60" rows="2"/>
		            </div>
		        </div>
	        </div>
	        
        </div>
    </form>
    <script type="text/javascript" src="${request.contextPath}/assets/js/api/database/db.js?ver=${versionUtil()}"></script>
</body>
</html>
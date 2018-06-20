[#if authType == 'app' && !apply ??]
<div class="panel">
	<div class="panel-heading">
		<div class="searchbox">
			<div class="input-group custom-search-form">
				<input id="text_search" type="text" class="form-control" placeholder="请输入应用名称...">
				<span class="input-group-btn">
	            	<button id="btn_search" class="text-muted" type="button"><i class="demo-pli-magnifi-glass"></i></button>
	        	</span>
			</div>
		</div>
	</div>
	<div class="panel-body p-1">
		<table id="tabGrid"></table>
	</div>
</div>
[/#if]
<div class="row">
	<div class="col-sm-12">
       	<div class="form-group">
            <label for="input-invokeIp" class="col-sm-2 control-label" style="padding-left:0px;">调用频率</label>
            <div class="col-sm-10">
                <div class="col-sm-3">
		            <input class="default-radio primary-radio" type="radio" name="frequency" value="10000" id="10000" 
		            [#if apply ?? && apply.invokeFrequency == 10000]checked="checked"[/#if] />
		            <label for="10000">1万次/天</label>
		        </div>
		        <div class="col-sm-3">
		            <input class="default-radio primary-radio" type="radio" name="frequency" value="50000" id="50000" 
		            [#if apply ?? && apply.invokeFrequency == 50000]checked="checked"[/#if] />
		            <label for="50000">5万次/天</label>
		        </div>
		        <div class="col-sm-3">
		            <input class="default-radio primary-radio" type="radio" name="frequency" value="100000" id="100000" 
		            [#if apply ?? && apply.invokeFrequency == 100000]checked="checked"[/#if] />
		            <label for="100000">10万次/天</label>
		        </div>
		        <div class="col-sm-3">
		            <input class="default-radio primary-radio" type="radio" name="frequency" value="500000" id="500000" 
		            [#if apply ?? && apply.invokeFrequency == 500000]checked="checked"[/#if] />
		            <label for="500000">50万次/天</label>
		        </div>
            </div>
        </div>
    </div>
</div>
<form id="applyForm">
	[#if authType == 'app']<input id="input-appIds" type="hidden" name="appIds" />[/#if]
	[#if apply ??]<input id="input-applyId" type="hidden" name="applyId" value="${apply.applyId}" />[/#if]
	<input id="input-deployId" type="hidden" name="deployId" value="${deployId}" />
	<input id="input-frequency" type="hidden" name="frequency" />
	<div class="row" style="padding-top:5px;">
		<div class="col-sm-12">
	       	<div class="form-group">
	            <label for="input-invokeIp" class="col-sm-2 control-label" style="padding-left:0px;">调用服务IP</label>
	            <div class="col-sm-10">
	                <input placeholder="请输入调用服务IP，例：127.0.0.1,10.71.33.45" class="form-control" id="input-invokeIp" name="invokeIp" type="text" data-rules='{"required":true}'
	                [#if apply?? ] value="${apply.invokeIp}"[/#if] />
	            </div>
	        </div>
	    </div>
	</div>
    <div class="row" style="padding-top:5px;">
		<div class="col-sm-12">
	       	<div class="form-group">
	            <label for="input-applyFile" class="col-sm-2 control-label" style="padding-left:0px;">申请材料</label>
	            <div class="col-sm-10">
	                <input class="form-control" id="input-applyFile" name="applyFile" type="file" accept="application/msword" data-rules='{"required":true}' />
	            </div>
	        </div>
	    </div>
    </div>
</form>
[#include "/head/zftal-ui-bsTable.ftl" /]
<script type="text/javascript">
	var deployId='${deployId}';
	var authType = '${authType}';
	[#if apply??]
	var submitPath = '${request.contextPath}/manager/api/apply/change/save';
	[#else]
	var submitPath = '${request.contextPath}/manager/api/apply/save';
	[/#if]
</script>
<script type="text/javascript" src="${request.contextPath}/assets/js/api/apply/new-apply.js?ver=${versionUtil()}"></script>
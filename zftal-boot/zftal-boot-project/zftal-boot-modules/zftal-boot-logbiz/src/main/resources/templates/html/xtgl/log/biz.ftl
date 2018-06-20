<!DOCTYPE html>
<html lang="zh_CN">
<head>
	<style>
	.fixed-table-body{
		height : auto;
	}
	</style>
</head>
<body>
<div id="page-content">
	<div class="panel">
		<div class="panel-body">
			<div class="form-inline col-md-12">
				<div class="form-group" style="margin-top:6px;">
	                <label for="query-log-czmc" class="control-label" style="float:left;width:70px;">关键字：</label>
	                <input id="query-log-czmc" type="text" class="form-control" style="width:240px;height:30px;float:left;">
	            </div>
				&nbsp;
				<div class="form-group" style="margin-top:6px;">
	                <label for="query-log-czr" class="control-label" style="float:left;width:70px;">操作人：</label>
	                <input id="query-log-czr" type="text" class="form-control" style="width:240px;height:30px;float:left;">
	            </div>
	            &nbsp;
	            <div class="form-group" style="margin-top:6px;">
	                <label for="query-log-czmk" class="control-label" style="float:left;width:70px;">操作模块：</label>
	                <input id="query-log-czmk" type="text" class="form-control" style="width:240px;height:30px;float:left;">
	            </div>
	            &nbsp;
	            <div class="form-group" style="margin-top:6px;">
	                <label for="query-log-ywmc" class="control-label" style="float:left;width:70px;">业务名称：</label>
	                <input id="query-log-ywmc" type="text" class="form-control" style="width:240px;height:30px;float:left;">
	            </div>
				&nbsp;
				<div class="form-group" style="margin-top:6px;">
                	<label class="control-label" for="query-time-czsj" style="float:left;width:70px;">操作时间:</label>
					<div id="query-time-czsj" style="float:left;">
						<div class="input-group">
						    <input type="text" id="query-time-type" style="width:350px;height:30px;float:left;" class="form-control date-picker" readonly="readonly" />
						    <span class="input-group-addon">
						       <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
						    </span>
						    <input type="hidden" name="beginTime" id="beginTime" value="" />
						    <input type="hidden" name="endTime" id="endTime" value="" />
						</div>
					</div>
				</div>
				&nbsp;
	            <div id="btn_search" class="btn zf-btn btn-primary btn-light-primary" style="height:30px;margin-top:6px;"> 查 询 </div>
			</div>
		</div>
		<div class="panel-body">
			<table id="tabGrid"></table>
	    </div>
    </div>
</div>
[#include "/head/zftal-ui-daterangepicker.ftl" /]
[#include "/head/zftal-ui-bsTable.ftl" /]
<script type="text/javascript" src="${request.contextPath}/assets/js/xtgl/log/biz.js?ver=${versionUtil()}"></script>
</body>  
</html>
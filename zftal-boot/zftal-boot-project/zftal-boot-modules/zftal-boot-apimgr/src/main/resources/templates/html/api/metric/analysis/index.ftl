<!DOCTYPE html>
<html lang="zh_CN">
<head>
	[#include "/head/zftal-ui-echarts.ftl" /]
	[#include "/head/zftal-ui-sockjs.ftl" /]
	
	<style type="text/css">
	.input-group-addon {
	    background-color: #FFF;
	    border-left: 0px;
	}
	
	.table > tbody > tr > td, 
	.table > tbody > tr > th, 
	.table > tfoot > tr > td, 
	.table > tfoot > tr > th, 
	.table > thead > tr > td, 
	.table > thead > tr > th {
		padding: 2px;
		border: 0px solid transparent !important;
	}
	
	.table > thead > tr > th {
	    background-color: #9da0a5 !important;
	    color: #fff !important;
	}

	.td-left{width:120px;}
	.td-left-val{width: 40%;}
	</style>
</head>
<body class="" style="">
	<div id="page-content">
		<div class="row">
			 <div class="col-sm-4">
	
	            <!--Tile-->
	            <!--===================================================-->
	            <div class="panel media middle">
	                <div class="media-left bg-mint pad-all">
	                    <i class="demo-pli-speech-bubble-7 icon-3x"></i>
	                </div>
	                <div class="media-body pad-all">
	                    <p class="text-2x mar-no text-semibold">34</p>
	                    <p class="text-muted mar-no">Comments</p>
	                </div>
	            </div>
	            <!--===================================================-->
			</div>
            <div class="col-sm-4">
	
	            <!--Tile-->
	            <!--===================================================-->
	            <div class="panel media middle">
	                <div class="media-left bg-mint pad-all">
	                    <i class="demo-pli-speech-bubble-7 icon-3x"></i>
	                </div>
	                <div class="media-body pad-all">
	                    <p class="text-2x mar-no text-semibold">34</p>
	                    <p class="text-muted mar-no">Comments</p>
	                </div>
	            </div>
	            <!--===================================================-->
			</div>
            <div class="col-sm-4">
	
	            <!--Tile-->
	            <!--===================================================-->
	            <div class="panel media pad-all bg-info">
	                <div class="media-left">
	                    <span class="icon-wra icon-wap-sm bg-ifo">
	                    <i class="demo-pli-mail icon-3x"></i>
	                    </span>
	                </div>
	                <div class="media-body">
	                    <p class="text-2x mar-no text-semibold">543</p>
	                    <p class="mar-no">New Mail</p>
	                </div>
	            </div>
	            <!--===================================================-->
	
	        </div>
            
			<div class="col-sm-12 col-md-12">
				<div class="panel text-left">
					<div class="panel-body">
						<div class="form-horizontal col-md-12">
		                	<div class="form-group col-sm-3">
								<label class="col-sm-4 control-label" for="query-app-type">应用名称:</label>
                                <div class="col-sm-8">
                                    <select id="query-app-type" name="app" class="form-control form-select chosen-select">
							    	  	<option value="">--请选择--</option>
							    	  	[#list appList as app] 
									    <option value="${app.APPKEY}">${app.APPNAME}</option>
									    [/#list]
							    	</select>
							    	<script type="text/javascript">
					                	$("#query-app-type").trigger("chosen");
					                </script>
                                </div>
		                    </div>
		                    <div class="form-group col-sm-3">
								<label class="col-sm-4 control-label" for="query-app-type">业务名称:</label>
                                <div class="col-sm-8">
                                    <select id="query-biz-type" name="biz" class="form-control form-select chosen-select">
							    	  	<option value="">--请选择--</option>
							    	  	[#list bizList as biz] 
									    <option value="${biz.BIZNAME}">${biz.BIZNAME}</option>
									    [/#list]
							    	</select>
							    	<script type="text/javascript">
					                	$("#query-biz-type").trigger("chosen");
					                </script>
                                </div>
		                    </div>
		                    <div class="form-group col-sm-3">
                                 <div class="col-sm-6 col-sm-offset-4 check-box">
                                    <input class="default-checkbox primary-checkbox" name="refresh" id="refresh-background" checked="checked" type="checkbox">
                                    <label for="refresh-background">后台刷新(1次/分钟)</label>
                                </div>
                                 <div id="btn_search" class="col-sm-2 btn zf-btn btn-primary btn-light-primary">查询</div>
                            </div>
		                </div>
					</div>
				</div>
				<div class="panel text-left">
					<div class="tab-base tab-drop-tline">
						<ul class="nav nav-tabs" id="nav-tabs">
							<li role="presentation" class="active in">
								<a href="#tab-pane-app" role="tab" data-toggle="tab">应用访问占比、分布趋势图</a>
							</li>
						</ul>
						<div class="tab-content">
							<div id="tab-pane-app" class="tab-pane active in">
								<div class="col-sm-12">
					                <div class="real-panel panel">
					                    <div class="panel-body p-1">
					                    	<div id="appRatio_pie" style="height: 400px;"></div>
					                    </div>
					                </div>
					            </div>
								<div class="col-sm-8">
					                <div class="real-panel panel">
					                    <div class="panel-body p-1">
					                    	<div id="appRatio_line" style="height: 300px;"></div>
					                    </div>
					                </div>
					            </div>
					            <div class="col-sm-4">
					                <div class="real-panel panel">
					                    <div class="panel-body p-1">
											<table class="table table-condensed table-striped m-b-0">
										      	<thead>
											        <tr style="height: 20px;">
											        	<th width="26px;">#</th>
											          	<th width="85%">应用名称</th>
											          	<th width="15%">访问量</th>
											        </tr>
										      	</thead>
										      	<tbody id="table-app-tbody" data-colspan="3">
										         	<td colspan="3" class="text-center p-10">暂无数据!</td>
										      	</tbody>
										    </table>
										 </div>
					                </div>
					            </div>
							</div>
						</div>
					</div>
					<div class="tab-base tab-drop-tline">
						<ul class="nav nav-tabs" id="nav-tabs">
							<li role="presentation" class="active in">
								<a href="#tab-pane-biz" role="tab" data-toggle="tab">业务访问占比、分布趋势图</a>
							</li>
						</ul>
						<div class="tab-content">
							<div id="tab-pane-biz" class="tab-pane active in">
								<div class="col-sm-12">
					                <div class="real-panel panel">
					                    <div class="panel-body p-1">
					                    	<div id="bizRatio_pie" style="height: 400px;"></div>
					                    </div>
					                </div>
					            </div>
					         	<div class="col-sm-8">
					                <div class="real-panel panel">
					                    <div class="panel-body p-1">
					                    	<div id="bizRatio_line" style="height: 300px;"></div>
					                    </div>
					                </div>
					            </div>
					            <div class="col-sm-4">
					                <div class="real-panel panel">
					                    <div class="panel-body p-1">
											<table class="table table-condensed table-striped m-b-0">
										      	<thead>
											        <tr style="height: 20px;">
											        	<th width="26px;">#</th>
											          	<th width="85%">业务名称</th>
											          	<th width="15%">访问量</th>
											        </tr>
										      	</thead>
										      	<tbody id="table-biz-tbody" data-colspan="3">
										         	<td colspan="3" class="text-center p-10">暂无数据!</td>
										      	</tbody>
										    </table>
										 </div>
					                </div>
					            </div>
							</div>
						</div>
					</div>
					<div class="tab-base tab-drop-tline">
						<ul class="nav nav-tabs" id="nav-tabs">
							<li role="presentation" class="active in">
								<a href="#tab-pane-device" role="tab" data-toggle="tab">设备类型占比、分布趋势图</a>
							</li>
						</ul>
						<div class="tab-content">
							<div id="tab-pane-device" class="tab-pane active in">
								<div class="col-sm-12">
					                <div class="real-panel panel">
					                    <div class="panel-body p-1">
					                    	<div id="deviceRatio_pie" style="height: 400px;"></div>
					                    </div>
					                </div>
					            </div>
					         	<div class="col-sm-8">
					                <div class="real-panel panel">
					                    <div class="panel-body p-1">
					                    	<div id="deviceRatio_line" style="height: 300px;"></div>
					                    </div>
					                </div>
					            </div>
					            <div class="col-sm-4">
					                <div class="real-panel panel">
					                    <div class="panel-body p-1">
											<table class="table table-condensed table-striped m-b-0">
										      	<thead>
											        <tr style="height: 20px;">
											        	<th width="26px;">#</th>
											          	<th width="85%">设备名称</th>
											          	<th width="15%">访问量</th>
											        </tr>
										      	</thead>
										      	<tbody id="table-device-tbody" data-colspan="3">
										         	<td colspan="3" class="text-center p-10">暂无数据!</td>
										      	</tbody>
										    </table>
										 </div>
					                </div>
					            </div>
							</div>
						</div>
					</div>
					<div class="tab-base tab-drop-tline">
						<ul class="nav nav-tabs" id="nav-tabs">
							<li role="presentation" class="active in">
								<a href="#tab-pane-os" role="tab" data-toggle="tab">操作系统占比、分布趋势图</a>
							</li>
						</ul>
						<div class="tab-content">
							<div id="tab-pane-os" class="tab-pane active in">
								<div class="col-sm-12">
					                <div class="real-panel panel">
					                    <div class="panel-body p-1">
					                    	<div id="osRatio_pie" style="height: 400px;"></div>
					                    </div>
					                </div>
					            </div>
					         	<div class="col-sm-8">
					                <div class="real-panel panel">
					                    <div class="panel-body p-1">
					                    	<div id="osRatio_line" style="height: 300px;"></div>
					                    </div>
					                </div>
					            </div>
					            <div class="col-sm-4">
					                <div class="real-panel panel">
					                    <div class="panel-body p-1">
											<table class="table table-condensed table-striped m-b-0">
										      	<thead>
											        <tr style="height: 20px;">
											        	<th width="26px;">#</th>
											          	<th width="25%">操作系统</th>
											          	<th width="20%">版本</th>
											          	<th width="40%">开发商</th>
											          	<th width="15%">访问量</th>
											        </tr>
										      	</thead>
										      	<tbody id="table-os-tbody" data-colspan="5">
										         	<td colspan="5" class="text-center p-10">暂无数据!</td>
										      	</tbody>
										    </table>
										 </div>
					                </div>
					            </div>
							</div>
						</div>
					</div>
					<div class="tab-base tab-drop-tline">
						<ul class="nav nav-tabs" id="nav-tabs">
							<li role="presentation" class="active in">
								<a href="#tab-pane-browser" role="tab" data-toggle="tab">浏览器类型占比、分布趋势图</a>
							</li>
						</ul>
						<div class="tab-content">
							<div id="tab-pane-browser" class="tab-pane active in">
					           	<div class="col-sm-12">
					                <div class="real-panel panel">
					                    <div class="panel-body p-1">
					                    	<div id="browserRatio_pie" style="height: 400px;"></div>
					                    </div>
					                </div>
					            </div>
					         	<div class="col-sm-8">
					                <div class="real-panel panel">
					                    <div class="panel-body p-1">
					                    	<div id="browserRatio_line" style="height: 300px;"></div>
					                    </div>
					                </div>
					            </div>
					            <div class="col-sm-4">
					                <div class="real-panel panel">
					                    <div class="panel-body p-1">
											<table class="table table-condensed table-striped m-b-0">
										      	<thead>
											        <tr style="height: 20px;">
											        	<th width="26px;">#</th>
											          	<th width="25%">浏览器</th>
											          	<th width="20%">版本</th>
											          	<th width="20%">类型</th>
											          	<th width="20%">内核</th>
											          	<th width="15%">访问量</th>
											        </tr>
										      	</thead>
										      	<tbody id="table-browser-tbody" data-colspan="6">
										         	<td colspan="6" class="text-center p-10">暂无数据!</td>
										      	</tbody>
										    </table>
										 </div>
					                </div>
					            </div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${request.contextPath}/assets/js/api/metric/analysis/index.js?ver=${versionUtil()}"></script>
</body>


</html>

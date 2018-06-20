<!doctype html>
<html>
	<head>
	   <style>
	     .form-inline label{
	       margin-top:5px;
	     }
	   </style>
	</head>
	<body>
		<div id="page-content">
			<div class="row">
				<div class="col-lg-12">
					<div class="panel  text-left" style="min-height:600px;">
						<div class="tab-base tab-drop-tline">
							<ul class="nav nav-tabs" id="nav-tabs">
								<li class="active">
									<a href="#demo-lft-tab-1">数据接口信息</a>
								</li>
								<li>
									<a href="#demo-lft-tab-2">接口发布列表</a>
								</li>
							</ul>
							<div class="tab-content">
								<div id="demo-lft-tab-1" class="tab-pane active in">
									<div class="panel-body demo-nifty-btn">
										<!-- tool bar-start  -->
										<div id="but_ancd" class="btn-toolbar" role="toolbar" style="float:right;">
											<div class="btn-group" >
												<button type="button" href="javascript:void(0);" class="btn zf-btn btn-default btn-light-default" id="btn_zj">
													<i class="fa fa-plus"></i> 新增
												</button>
												<button type="button"  href="javascript:void(0);" class="btn zf-btn btn-default btn-light-default" id="btn_xg">
													<i class="fa fa-pencil-square-o"></i> 编辑
												</button>
												<button type="button"  href="javascript:void(0);" class="btn zf-btn btn-default btn-light-default" id="btn_sc">
													<i class="fa fa-minus"></i> 删除
												</button>
												<button type="button"  href="javascript:void(0);" class="btn zf-btn btn-default btn-light-default" id="btn_detail">
													<i class="fa fa-info"></i> 详情
												</button>
												<button type="button"  href="javascript:void(0);" class="btn zf-btn btn-default btn-light-default" id="btn_debug">
													<i class="fa fa-pencil-square-o"></i> 调式
												</button>
												<button type="button"  href="javascript:void(0);" class="btn zf-btn btn-default btn-light-default" id="btn_config">
													<i class="fa fa-pencil-square-o"></i> 配置
												</button>
												<button type="button"  href="javascript:void(0);" class="btn zf-btn btn-default btn-light-default" id="btn_fb">
													<i class="fa fa-pencil-square-o"></i> 发布
												</button>
											</div>
									    </div>
									    <!-- tool bar-end  -->
								    </div>
									<div class="panel-body ">
			                            <div class="form-inline">
				                            <div class="form-group col-xs-4 col-sm-4 col-md-3 ">
				                                <label class="col-sm-3 control-label" for="query-api-name">接口名称:</label>
				                                <div class="col-sm-9">
				                                     <input id="query-api-name" class="form-control" placeholder="接口名称"/>
				                                </div>
				                            </div>
			                              	<div id="btn_search" class="btn zf-btn btn-primary btn-light-primary"> 查 询 </div>  
			                            </div>
			                        </div>
									<div class="panel-body demo-nifty-btn">
										<!-- table-start  -->
										<table id="tabGrid"></table>
										<!-- table-end  -->
								    </div>
								</div>
								<div id="demo-lft-tab-2" class="tab-pane">
				                        <div class="form-inline">
				                            <div class="form-group col-xs-4 col-sm-4 col-md-3">
				                                <label class="col-sm-4 control-label" for="query-deploy-type">发布接口类型:</label>
				                                <div class="col-sm-8">
				                                    <select id="query-deploy-type" name="type" class="form-control form-select chosen-select">
											    	  	<option value="">--请选择--</option>
											    	  	[#list typeList as type] 
													    <option value="${type.key}">${type.desc}</option>
													    [/#list]
											    	</select>
											    	<script type="text/javascript">
									                	$("#query-deploy-type").trigger("chosen");
									                </script>
				                                </div>
				                            </div>
				                              <div id="btn_search_deploy" class="btn zf-btn btn-primary btn-light-primary"> 查 询 </div>   
				                        </div>
				                        <div class="demo-nifty-btn" style="margin-top: 15px;">
											<!-- table-start  -->
											<table id="tabGrid-deploy"></table>
											<!-- table-end  -->
									    </div>
								</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		[#include "/head/zftal-ui-bsTable.ftl" /]
		<script type="text/javascript" src="${request.contextPath}/assets/js/api/info/data/index.js?ver=${versionUtil()}"></script>
		<script type="text/javascript" src="${request.contextPath}/assets/js/api/deploy/index.js?ver=${versionUtil()}"></script>
		[#include "/head/zftal-ui-kindeditor.ftl" /]
	</body>

</html>
<!doctype html>
<html>
<head>

</head>
<body>
	<div id="page-content">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel text-left" style="min-height:600px;">
					 <!--浅色按钮组-->
					<div class="panel-body demo-nifty-btn">
						<!-- tool bar-start  -->
						<div id="btn_db" class="btn-toolbar" role="toolbar" style="float:right;">
							<div class="btn-group" >
								<button type="button" href="javascript:void(0);" class="btn zf-btn btn-default btn-light-default" id="btn_zj_db">
									<i class="fa fa-plus"></i> 新增
								</button>
								<button type="button"  href="javascript:void(0);" class="btn zf-btn btn-default btn-light-default" id="btn_xg_db">
									<i class="fa fa-pencil-square-o"></i> 编辑
								</button>
								<button type="button"  href="javascript:void(0);" class="btn zf-btn btn-default btn-light-default" id="btn_sc_db">
									<i class="fa fa-pencil-square-o"></i> 删除
								</button>
							</div>
						</div>
						<!-- tool bar-end  -->
					</div>
                    <div class="panel-body ">
                        <div class="form-inline">
                        	<div class="form-group col-xs-4 col-sm-4 col-md-3">
                        		<label for="query-db-type" class="col-sm-4 control-label">数据库类型</label>
                                <div class="col-sm-8">
                                    <select id="query-db-type" name="dbtype" class="form-control form-select chosen-select"  data-rules='{"required":true}'>
							    	  	<option value="">--请选择--</option>
							    	  	[#list driverList as driver] 
									    <option value="${driver.key}" db-url="${driver.url}">${driver.desc}</option>
									    [/#list]
							    	</select>
					                <script type="text/javascript">
					                	$("#query-db-type").trigger("chosen");
					                </script>
                                </div>
                            </div>
                            <div id="db_btn_search" class="btn zf-btn btn-primary btn-light-primary"> 查 询 </div>
                         </div>
                    </div>
					<div class="panel-body demo-nifty-btn">
						<!-- table-start  -->
						<table id="tabGrid"></table>
						<!-- table-end  -->
					</div>
				</div>
			</div>
		</div>
	</div>
	
	[#include "/head/zftal-ui-bsTable.ftl" /]
	<script type="text/javascript" src="${request.contextPath}/assets/js/api/database/index.js?ver=${versionUtil()}"></script>
</body>
</html>
<!doctype html>
<html>
<head>
</head>
<body>
	<div id="page-content">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel  text-left" style="min-height:600px;">
					<div class="panel-heading">
						<div class="panel-body">
							<div class="form-inline col-md-8">
			                	<div class="form-group">
			                        <label for="text_search" class="control-label" style="float:left;width:70px;">应用名称：</label>
			                        <input id="text_search" type="text" class="form-control" placeholder="请输入应用名称...">
			                    </div>
			                    &nbsp;
			                    <div id="btn_search" class="btn zf-btn btn-primary btn-light-primary" style="height:30px;border-radius:3px;">查询</div>
			                </div>
			                <div class="col-md-4 text-right">
								<button type="button" class="btn zf-btn btn-default btn-light-default" id="btn_add">
									<i class="fa fa-plus"></i> 新增
								</button>
							</div>
						</div>
					</div>
					<div class="panel-body"><table id="tabGrid"></table></div>
				</div>
			</div>
		</div>
	</div>
	[#include "/head/zftal-ui-bsTable.ftl" /]
	<script type="text/javascript" src="${request.contextPath}/assets/js/api/app/index.js?ver=${versionUtil()}"></script>
</body>
</html>
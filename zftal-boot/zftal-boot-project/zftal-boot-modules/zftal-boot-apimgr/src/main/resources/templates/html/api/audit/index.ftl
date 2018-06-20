<!doctype html>
<html>
<head>
<style>
.popover {
	word-break: break-all;
}
</style>
</head>
<body>
	<div id="page-content">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel  text-left" style="min-height:600px;">
				
					<div class="tab-base tab-drop-top">
						<ul class="nav nav-tabs">
							<li class="active">
								<a data-toggle="tab" href="#demo-drolf-tab-1" aria-expanded="true">接口申请审核</a>
							</li>
							<li class="">
								<a data-toggle="tab" href="#demo-drolf-tab-2" aria-expanded="false">变更审核</a>
							</li>
						</ul>
						<div class="tab-content" style="min-height:600px;">
							<div id="demo-drolf-tab-1" class="tab-pane fade active in">
								<div class="panel-body">
									<div class="form-inline col-md-12">
										[#if authType == 'app']
					                	<div class="form-group" style="padding-top:8px;">
					                        <label for="s_appName" class="control-label" style="float:left;width:70px;">应用名称：</label>
					                		<div style="width:240px;float:left;">
						                		<select id="s_appName" class="chosen-select">
													<option value="">请选择...</option>
												</select>
											</div>
					                    </div>
					                    &nbsp;
					                    [/#if]
					                	<div class="form-group" style="padding-top:8px;">
					                        <label for="text_search" class="control-label" style="float:left;width:70px;">接口名称：</label>
					                        <input id="text_search" type="text" class="form-control" placeholder="请输入接口名称...">
					                    </div>
					                    &nbsp;
					                    <div id="btn_search" class="btn zf-btn btn-primary btn-light-primary">查询</div>
					                </div>
								</div>
								<div class="col-md-12"><table id="applyGrid"></table></div>
							</div>
							<div id="demo-drolf-tab-2" class="tab-pane fade">
								<div class="panel-body">
									<div class="form-inline col-md-8">
										[#if authType == 'app']
										<div class="form-group" style="padding-top:8px;">
											<label for="c_appName" class="control-label" style="float:left;width:70px;">应用名称：</label>
											<div style="width:240px;float:left;">
												<select id="c_appName" class="chosen-select">
													<option value="">请选择...</option>
												</select>
											</div>
										</div>
										&nbsp;
										[/#if]
										<div class="form-group" style="padding-top:8px;">
											<label for="c_text_search" class="control-label" style="float:left;width:70px;">接口名称：</label>
											<input id="c_text_search" type="text" class="form-control" placeholder="请输入接口名称...">
										</div>
										&nbsp;
										<div id="c_btn_search" class="btn zf-btn btn-primary btn-light-primary">查询</div>
									</div>
								</div>
								<div class="col-md-12"><table id="changeGrid"></table></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	[#include "/head/zftal-ui-bsTable.ftl" /]
	[#include "/head/zftal-ui-chosen.ftl" /]
	<script type="text/javascript">
		var authType = '${authType}';
	</script>
	<script type="text/javascript" src="${request.contextPath}/assets/js/api/audit/index.js?ver=${versionUtil()}"></script>
	<script type="text/javascript" src="${request.contextPath}/assets/js/api/audit/change.js?ver=${versionUtil()}"></script>
</body>
</html>
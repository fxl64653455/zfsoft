[#if deployDto.desc != null && deployDto.desc != '']
	[#assign desc=deployDto.desc?eval /]
[/#if]
<link rel="stylesheet" type="text/css" href="${request.contextPath}/assets/css/survey.css"/>
<div class="api-detail">
	<div class="white-shadow api-apply">
		<div class="api-apply-top">
			<h4>${deployDto.apiName}</h4>
			[#if deployDto.tags?exists]  
	        	[#list deployDto.tags as tag]
					<span class="color-span [#if tag_index % 2 == 0] cyan-span[/#if] [#if tag_index % 3 == 0] green-span[/#if]">${tag}</span>
				[/#list]  
        	[#else]
        		<span class="color-span">API数据</span>
        	[/#if]
        	[#if oauthEnable]
				<a href="javascript:void(0)" class="btn btn-primary btn-apply" id="btn-apply" data-id="${deployDto.id}">接口申请</a>
			[/#if]
		</div>
		<div class="api-apply-bot">
			<p class="text-indent-2">${deployDto.apiInfo}</p>
		</div>
	</div>
	<div class="white-shadow api-tab-box">
		<div class="tab-base tab-drop-tline">
				
			<ul class="nav nav-tabs nav-tab-api">
				<li class="active">
					<a data-toggle="tab" href="#demo-lft-tab-1">基本信息</a>
				</li>
				<li>
					<a data-toggle="tab" href="#demo-lft-tab-2">详细介绍</a>
				</li>
				<li>
					<a data-toggle="tab" href="#demo-lft-tab-3">错误码参照</a>
				</li>
				<li>
					<a data-toggle="tab" href="#demo-lft-tab-4">SDK</a>
				</li>
			</ul>

			<div class="tab-content">
				<div id="demo-lft-tab-1" class="tab-pane active p-10">
					<p class="text-primary p-5"><b>
						接口地址：
						[#if deployDto.type == 'Rest']
							${baseUrl}${deployDto.addr?substring(1)}
						[#else]
							${baseUrl}ws${deployDto.addr}
						[/#if]
					</b></p>
					<p class="text-primary p-5"><b>参数列表：</b></p>
					<table class="table table-condensed table-hover table-striped m-b-0">
				      	<thead>
					        <tr>
					          <th width="10%">编码</th>
					          <th width="10%">名称</th>
					          <th width="10%">类型</th>
					          <th width="10%">是否必填</th>
					          <th width="60%">描述</th>
					        </tr>
				      	</thead>
				      	<tbody id="table-error-tbody">
			      			[#if deployDto.paramList?exists && deployDto.paramList?size > 0]  
				        	[#list deployDto.paramList as par]
				        	<tr>
				        		<td>${par.key?default('')}</td>
								<td>${par.name?default('')}</td>
								<td>${par.type?default('')}</td>
				        		<td>[#if par.required != null && par.required == '1']是[#else]否[/#if]</td>
								<td>${par.desc?default('')}</td>
					        </tr>
							[/#list]  
				        	[#else]
				        	<tr>
					        	<td class="text-center p-5" colspan="3">暂无数据!</td>
					        </tr>
				        	[/#if]
				      	</tbody>
			    	</table>
			    	[#if deployDto.outCase?? && deployDto.outCase?length > 0]  
					<p class="text-primary p-5"><b>返回示例：</b></p>
					<pre class="p-5">${deployDto.outCase?default('')}</pre>
					[/#if]
				</div>
				<div id="demo-lft-tab-2" class="tab-pane fade in">
					<p>${deployDto.apiDetail}</p>
				</div>
				<div id="demo-lft-tab-3" class="tab-pane fade p-20">
					<p class="text-danger"><b>接口平台错误码:</b></p>
					<table class="table table-condensed table-hover table-striped m-b-0">
				      	<thead>
					        <tr>
					          <th width="26px;">#</th>
					          <th width="15%">错误编码</th>
					          <th width="75%">错误描述（占位符{0}表示具体的错误信息）</th>
					        </tr>
				      	</thead>
				      	<tbody id="table-error-tbody">
			      			[#if errorList?exists && errorList?size != 0]  
				        	[#list errorList as error]
				        	<tr>
				        		<td>${error_index + 1}</td>
				        		<td>${error.key?default('')}</td>
								<td>${error.desc?default('')}</td>
					        </tr>
							[/#list]  
				        	[#else]
				        	<tr>
					        	<td class="text-center p-10" colspan="3">暂无数据!</td>
					        </tr>
				        	[/#if]
				      	</tbody>
			    	</table>
			    	<br/>
			    	[#if deployDto.errorList?exists && deployDto.errorList?size != 0]  
			 		<p class="text-danger"><b>第三方接口错误码:</b></p>
					<table class="table table-condensed table-hover table-striped m-b-0">
				      	<thead>
					        <tr>
					          <th width="26px;">#</th>
					          <th width="15%">错误编码</th>
					          <th width="75%">错误描述（占位符{0}表示具体的错误信息）</th>
					        </tr>
				      	</thead>
				      	<tbody id="table-error-tbody">
				        	[#list deployDto.errorList as error]
				        	<tr>
				        		<td>${error_index + 1}</td>
				        		<td>${error.key?default('')}</td>
								<td>${error.desc?default('')}</td>
					        </tr>
							[/#list]  
				      	</tbody>
			    	</table> 
			    	[/#if]
				</div>
				<div id="demo-lft-tab-4" class="tab-pane fade">
					<div class="api-tab-detail">
						<div class="span-box">
							[#if deployDto.language?exists]  
				        	[#list deployDto.language as lang]
				        	<span class="color-span-m on">${lang}</span>
							[/#list]
				        	[#else]
				        	<span class="color-span-m on">Java</span>
				        	[/#if]
						</div>
						<div class="api-tab-text">
							<h6>描述</h6>
							<p>API接口平台提供了多种类型接口调用SDK，通过SDK可以便捷地调取所需数据服务，快速开发实现业务功能。<br />
							请&nbsp;<a  href="${request.contextPath}/manager/api/sdk/download" target="_blank" class="zf-btn btn-primary btn-detail" style="color:#FFF;">点击下载</a>&nbsp;Beta版SDK，如有任何使用问题请联系我们。<br />
							注意：该版本SDK暂不支持含有Image参数的数据调用。</p>
							<h6>调用示例</h6>
							[#include "/html/api/index/language/Java.ftl" /]
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</div>
<script type="text/javascript" src="${request.contextPath}/assets/js/api/index/detail.js?ver=${versionUtil()}"></script>
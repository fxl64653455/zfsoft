<!doctype html>
<html>
<head>

	[#include "/head/zftal-ui-tagsinput.ftl" /]
	[#include "/head/zftal-ui-filestyle.ftl" /]
	
	<style type="text/css">
		.default-checkbox{
			position: relative;
		    cursor: pointer;
		    vertical-align: middle;
		    opacity: 60;
		    top: 0;
		    left: 0;
		    display: inline-block;
		    width: 16px;
		    height: 16px;
		    content: '';
		    border: 1px solid #c0c0c0;
		    border-radius: 3px;
		}
		
		.labelClass{
			border-radius:0;
			border:1px solid #ccc;
			text-align:left!important;
			height:34px;
		}
	</style>
</head>
<body>
    <form id="ajaxForm" method="post" data-widget='{"onkeyup": false}'  class="form-horizontal "
    	action="${request.contextPath}/manager/api/deploy/new-deploy" theme="simple" enctype="multipart/form-data">
    	<input type="hidden" id="apiId" name="apiId" value="${infoDto.id}"/>
    	<div class="row">
            <div class="col-sm-12">
               	<div class="form-group">
		            <label for="deploy-name" class="col-sm-2 control-label" style="padding-left:0px;">接口发布名称</label>
		            <div class="col-sm-10">
		                <label class="control-label labelClass col-sm-12" >${infoDto.name}</label>
		            </div>
		        </div>
            </div>
            <div class="col-sm-6">
               	<div class="form-group">
		            <label for="deploy-type" class="col-sm-4 control-label">接口发布类型</label>
		            <div class="col-sm-8">
		            	<select id="deploy-type" name="type" class="form-control form-select chosen-select"  data-rules='{"required":true}'>
				    	  	[#list typeList as type] 
						    <option value="${type.key}" data-prefix="${type.prefix}" [#if 'Rest' == type.key]selected="selected"[/#if]>${type.desc}</option>
						    [/#list]
				    	</select>
		                <script type="text/javascript">
		                	$("#deploy-type").trigger("chosen");
		                </script>
		            </div>
		        </div>
            </div>
            <div class="col-sm-6">
               	<div class="form-group">
		            <label for="deploy-request" class="col-sm-4 control-label">接口请求类型</label>
		            <div class="col-sm-8">
		            	<select id="deploy-method" name="method" class="form-control form-select chosen-select"  data-rules='{"methodCheck":true}'>
				    	  	<option value="">--请选择--</option>
				    	  	[#list methodList as method] 
						    <option value="${method.key}">${method.desc}</option>
						    [/#list]
				    	</select>
		                <script type="text/javascript">
		                	$("#deploy-method").trigger("chosen");
		                </script>
		            </div>
		        </div>
            </div>
            <div class="col-sm-12">
                <div class="form-group">
		            <label for="deploy-namespace" class="col-sm-2 control-label" style="padding-left:0px;">接口命名空间</label>
		            <div class="col-sm-10">
		            	<div class="input-group mar-btm">
		                    <span class="input-group-addon">http(s)://ws.</span>
		                    <input placeholder="WS接口需要指定命名空间,通常指您的接口所在域名的倒叙，如：zfsot.com" class="form-control" id="deploy-namespace" name="namespace" type="text"  
			                data-rules='{"namespace":true}'/>
		                </div>
		            	 
		            </div>
	         	</div>
            </div>
            
            [#if infoDto.apiType == 'data' && infoDto.exchType == '3']
            <div class="col-sm-6">
               	<div class="form-group">
		            <label for="deploy-pageable" class="col-sm-4 control-label">接口数据分页</label>
		            <div class="col-sm-8">
		            	<select id="deploy-pageable" name="pageable" class="form-control form-select chosen-select"  data-rules='{"pageableCheck":true}'>
				    	  	<!-- <option value="">--请选择--</option> -->
				    	  	<option value="0">不分页</option>
						    <option value="1">分页</option>
				    	</select>
		                <script type="text/javascript">
		                	$("#deploy-pageable").trigger("chosen");
		                </script>
		            </div>
		        </div>
            </div>
            [/#if]
            <div class="col-sm-12">
                <div class="form-group">
	            <label for="deploy-addr" class="col-sm-2 control-label" style="padding-left:0px;">接口发布地址</label>
	            <div class="col-sm-10">
		            <div class="input-group mar-btm">
	                    <span class="input-group-addon" id="deploy-addr-addon">/api/</span>
	                    <input placeholder="请输入接口发布地址，建议命名有意义的名称，如：用 /info/getUser 表示查询用户信息" class="form-control" id="deploy-addr" name="addr" type="text" 
		                data-rules='{"required":true, "addr": true}' />
	                </div>
	            </div>
	          </div>
            </div>
            <div class="col-sm-12">
                <div class="form-group">
	            <label for="deploy-tags" class="col-sm-2 control-label" style="padding-left:0px;">接口个性标签</label>
	            <div class="col-sm-10">
	            	<input class="form-control" id="deploy-tags" name="tags" type="text"  data-rules='{"required":true, "tags": "10"}'/>
	            </div>
	          </div>
            </div>
	        <div class="col-sm-12">
               	<div class="form-group">
		            <label for="deploy-iconbyte" class="col-sm-2 control-label">接口发布图标</label>
		            <div class="col-sm-6">
	                    <input id="deploy-iconbyte" name="iconbyte" type="file" data-message="0" style="display:none;" data-rules='{"checkIcon":true}'/>
                        <img id="iconbyte" data-src="holder.js/140x140" class=" col-sm-offset-1 img-thumbnail img-circle" alt="140x140" style="width: 140px; height: 140px;" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMTQwIiBoZWlnaHQ9IjE0MCIgdmlld0JveD0iMCAwIDE0MCAxNDAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjwhLS0KU291cmNlIFVSTDogaG9sZGVyLmpzLzE0MHgxNDAKQ3JlYXRlZCB3aXRoIEhvbGRlci5qcyAyLjYuMC4KTGVhcm4gbW9yZSBhdCBodHRwOi8vaG9sZGVyanMuY29tCihjKSAyMDEyLTIwMTUgSXZhbiBNYWxvcGluc2t5IC0gaHR0cDovL2ltc2t5LmNvCi0tPjxkZWZzPjxzdHlsZSB0eXBlPSJ0ZXh0L2NzcyI+PCFbQ0RBVEFbI2hvbGRlcl8xNjBiYjQ5OGQyYyB0ZXh0IHsgZmlsbDojQUFBQUFBO2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1mYW1pbHk6QXJpYWwsIEhlbHZldGljYSwgT3BlbiBTYW5zLCBzYW5zLXNlcmlmLCBtb25vc3BhY2U7Zm9udC1zaXplOjEwcHQgfSBdXT48L3N0eWxlPjwvZGVmcz48ZyBpZD0iaG9sZGVyXzE2MGJiNDk4ZDJjIj48cmVjdCB3aWR0aD0iMTQwIiBoZWlnaHQ9IjE0MCIgZmlsbD0iI0VFRUVFRSIvPjxnPjx0ZXh0IHg9IjQzLjUiIHk9Ijc0LjgiPjE0MHgxNDA8L3RleHQ+PC9nPjwvZz48L3N2Zz4=" data-holder-rendered="true"/>
                        <p class="dz-text" style="font-size:14px;color:#AAB2BD">点击选择图片；格式如：*.gif;*.png;*.jpg;*.jpeg;*.bmp</p>
		            </div>
		        </div>
            </div>
            <div class="col-sm-12">
               	<div class="form-group">
		            <label for="deploy-request" class="col-sm-2 control-label">接口示例语言</label>
		            <div class="col-sm-10">
		            	[#list languageList as lang]
		            	<div class="checkbox">
						  	<label>
						    	<input type="checkbox" id="language_${lang_index}" name="language" value="${lang.key}" [#if lang.def == '1']checked="checked"[/#if]  data-rules='{"required":true}'/>
						    	${lang.desc}
						</div>
					    [/#list]
		            </div>
		        </div>
            </div>
        </div> 
    </form>
    <script type="text/javascript" src="${request.contextPath}/assets/js/api/deploy/new-deploy.js?ver=${versionUtil()}"></script>
</body>
</html>
<!doctype html>
<html>
<head>
</head>
<body>
    <form id="ajaxForm" method="post" data-widget='{"onkeyup": false}'  class="form-horizontal "
    	action="${request.contextPath}/manager/api/deploy/new-deploy" theme="simple" enctype="multipart/form-data">
    	<input type="hidden" id="apiid" name="apiid" value="${infoDto.id}"/>
    	<div class="row">
            <div class="col-sm-12">
               	<div class="form-group">
		            <label for="deploy-request" class="col-sm-2 control-label">接口示例语言</label>
		            <div class="col-sm-10">
		            	[#list languageList as lang]
		            	<div class="checkbox">
						  	<label>
						    	<input type="checkbox" id="language_${lang_index}" name="language" value="${lang.key}" [#if lang.def == '1']checked="checked"[/#if]  data-rules='{"required":true}'/>
						    	${lang.desc}
						  	</label>
						</div>
					    [/#list]
		            </div>
		        </div>
            </div>
        </div> 
    </form>
</body>
</html>
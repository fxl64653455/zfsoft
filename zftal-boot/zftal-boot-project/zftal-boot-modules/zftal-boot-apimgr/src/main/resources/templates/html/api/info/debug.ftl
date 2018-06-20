<form id="ajaxForm" method="post" data-widget='{"onkeyup": false}'  class="form-horizontal " action="${request.contextPath}/manager/api/info/debug/exec" theme="simple">
<input type="hidden" name="apiId" value="${apiInfo.id}">
[#list desc.input.params as par]
	<div class="row">
		<div class="col-sm-12">
	       	<div class="form-group">
	            <label for="input-${par.key}" class="col-sm-2 control-label" style="padding-left:0px;">${par.name}</label>
	            <div class="col-sm-10">
	                <input placeholder="请输入${par.name}" class="form-control" id="input-${par.key}" name="${par.key}" type="text" [#if par.required == "1"]data-rules='{"required":true}'[/#if] />
	            </div>
	        </div>
	    </div>
	</div>
[/#list]
[#if apiInfo.type == "data"]
	<br />
	[#list desc.input.updates as par]
		<div class="row">
			<div class="col-sm-12">
		       	<div class="form-group">
		            <label for="input-${par.key}" class="col-sm-2 control-label" style="padding-left:0px;">${par.name}</label>
		            <div class="col-sm-10">
		                <input placeholder="请输入${par.name}" class="form-control" id="input-${par.key}" name="${par.key}" type="text" [#if par.required == "1"]data-rules='{"required":true}'[/#if] />
		            </div>
		        </div>
		    </div>
		</div>
	[/#list]
	<div class="row">
		<div class="col-sm-12">
            <div class="col-sm-2" style="padding-left:0px;">&nbsp;</div>
            <div class="col-sm-10">
            	<input id="input-pageable" class="default-checkbox" type="checkbox" name="pageable">
            	<label for="input-pageable">是否分页</label>
            </div>
        </div>
	</div>
	<div class="row">
		<div class="col-sm-12">
	       	<div class="form-group">
	            <label for="input-offset" class="col-sm-2 control-label" style="padding-left:0px;">偏移量</label>
	            <div class="col-sm-10">
	                <input placeholder="请输入偏移量" class="form-control" id="input-offset" name="offset" type="number" value="0" />
	            </div>
	        </div>
	    </div>
	</div>
	<div class="row">
		<div class="col-sm-12">
	       	<div class="form-group">
	            <label for="input-limit" class="col-sm-2 control-label" style="padding-left:0px;">每页条数</label>
	            <div class="col-sm-10">
	                <input placeholder="请输入每页条数" class="form-control" id="input-limit" name="limit" type="number" value="15" />
	            </div>
	        </div>
	    </div>
	</div>
	<script type="text/javascript">
		$('#input-offset').disabled();$('#input-limit').disabled();
		$('#input-pageable').change(function(){
			if($(this).is(':checked')){
				$('#input-offset').enabled();$('#input-limit').enabled();
			}else{
				$('#input-offset').disabled();$('#input-limit').disabled();
			}
		});
	</script>
[/#if]
</form>
<div class="row">
	<div class="col-sm-12">
       	<div class="form-group">
            <label for="input-res" class="col-sm-2 control-label text-right" style="padding-left:0px;">返回结果</label>
            <div class="col-sm-10">
                <textarea id="input-res" class="form-control" rows="6" readonly="readonly"></textarea>
            </div>
        </div>
    </div>
</div>
<div class="row">
	<div class="col-sm-11 text-right" style="padding-top:5px;">
		<div id="btn_exec" class="btn zf-btn btn-primary btn-light-primary">执 行</div>
	</div>
</div>
<script type="text/javascript" src="${request.contextPath}/assets/js/api/info/formUtil.js?ver=${versionUtil()}"></script>
<script type="text/javascript" src="${request.contextPath}/assets/js/api/info/debug.js?ver=${versionUtil()}"></script>
<div class="row">
	<div class="col-sm-12">
		<div class="col-sm-6">
			<label class="col-sm-3">申请人：</label>
			<label class="col-sm-9">${apply.applyUser}</label>
		</div>
		<div class="col-sm-6">
			<label class="col-sm-3">申请时间：</label>
			<label class="col-sm-9">${apply.applyTime}</label>
		</div>
	</div>
	<div class="col-sm-12">
		<div class="col-sm-6">
			<label class="col-sm-3">调用服务IP：</label>
			<label class="col-sm-9">${apply.invokeIp}</label>
		</div>
		<div class="col-sm-6">
			<label class="col-sm-3">调用频率：</label>
			<label class="col-sm-9">${apply.invokeFrequency}万次/天</label>
		</div>
	</div>
	<div class="form-group check-box col-sm-4">
       <div class="col-sm-6">
            <input class="default-radio primary-radio" type="radio" name="agree" value="yes" id="yes" checked="" />
            <label for="yes">同意</label>
        </div>
        <div class="col-sm-6">
            <input class="default-radio primary-radio" type="radio" name="agree" value="no" id="no" />
            <label for="no">不同意</label>
        </div>
    </div>
	<div class="col-sm-12">
    	<textarea placeholder="请填写审核意见...." rows="5" class="form-control" id="auditOpinion"/>
    </div>
</div>
<script type="text/javascript">
	/**确定事件*/
	$('#btn_success').on('click',function(){
		var agree = $("input[name='agree']:checked").val();
		$.ajax({
			type : 'POST',
			url : _path + '/manager/api/audit/save',
			data : {
				auditFor : '${applyId}',appId : '${appId}',deployId : '${deployId}',auditStatus : agree,
				auditOpinion : $('#auditOpinion').val()
			},
			success : function(obj) {
				$('#btn_cancel').click();
				$('#applyGrid').bootstrapTable('refresh');
			}
		},'json');
	});
</script>
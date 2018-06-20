<div class="row">
	<div class="col-sm-12">
		<div class="col-sm-6">
			<label class="col-sm-3">申请人：</label>
			<label class="col-sm-9">${change.changeUser}</label>
		</div>
		<div class="col-sm-6">
			<label class="col-sm-3">申请时间：</label>
			<label class="col-sm-9">${change.changeTime}</label>
		</div>
	</div>
	<div class="col-sm-12">
			<label class="col-sm-3">变更前：</label>
	</div>
	<div class="col-sm-12">
		<div class="col-sm-6">
			<label class="col-sm-3">调用服务IP：</label>
			<label class="col-sm-9">${change.srcInvokeIp}</label>
		</div>
		<div class="col-sm-6">
			<label class="col-sm-3">调用频率：</label>
			<label class="col-sm-9">${change.srcInvokeFrequency}万次/天</label>
		</div>
	</div>
	<div class="col-sm-12">
			<label class="col-sm-3">变更后：</label>
	</div>
	<div class="col-sm-12">
		<div class="col-sm-6">
			<label class="col-sm-3">调用服务IP：</label>
			<label class="col-sm-9">${change.targetInvokeIp}</label>
		</div>
		<div class="col-sm-6">
			<label class="col-sm-3">调用频率：</label>
			<label class="col-sm-9">${change.targetInvokeFrequency}万次/天</label>
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
			url : _path + '/manager/api/apply/change/audit/save',
			data : {
				auditFor : '${change.applyChangeId}',auditStatus : agree,
				auditOpinion : $('#auditOpinion').val()
			},
			success : function(obj) {
				$('#btn_cancel').click();
				$('#changeGrid').bootstrapTable('refresh');
			}
		},'json');
	});
</script>
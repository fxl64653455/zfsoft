jQuery(function($){
	
	$("#select-apiInfo").change(function(a,b){
		var pars = [];var desc = {};
		if(b.selected != ''){
			pars = jQuery.parseJSON($(this).find('option:selected').attr('pars'));
			desc = jQuery.parseJSON($(this).find('option:selected').attr('desc'));
		}
		for(var x = 0;x < pars.length;x ++){
			var html = ['<option value="">--请选择--</option>'];
			for(var i = 0;i < desc.input.params.length;i ++){
				var str = '<option value="'+desc.input.params[i].key+'" pkey="'+pars[x].key+'">'+desc.input.params[i].name+'</option>';
				if(desc.input.params[i].key == pars[x].key){
					str = '<option selected="selected" value="'+desc.input.params[i].key+'" pkey="'+pars[x].key+'">'+desc.input.params[i].name+'</option>';
					$("#input-" + pars[x].key).val(desc.input.params[i].name);
				}
				html.push(str);
			}
			$("#select-" + pars[x].key).enable();
			$("#select-" + pars[x].key).empty().html(html.join("")).trigger("chosen:updated");
			$("#select-" + pars[x].key).change(function(m,n){
				$("#input-" + $(this).find('option:selected').attr('pkey')).val($(this).find('option:selected').text());
			});
		}
	});
	
	$('#btn_success').on('click',function(){
		var requestData = getFormJson($('#configForm'));
		$.post(_path + '/manager/api/info/config/save',requestData,function(data){
    		$('#btn_cancel').click();
    		$('#tabGrid').bootstrapTable('refresh');
    	});
	});
	
})
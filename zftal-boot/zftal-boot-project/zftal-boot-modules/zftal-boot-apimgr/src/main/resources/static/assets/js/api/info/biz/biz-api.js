jQuery(function($){
	
	$.validator.addMethod("extensionCheck",function(value,element,params){
		if($("#input-plugin").founded() && !$.founded(value)){
			return false;
		}
		return true;
	},"您已选择为当前接口绑的一个认证插件，请选择一个该插件下的认证实现.");
	
	function initFormByType(type){
		if(type == 'Http' || type == 'Https'){
			$('#input-method').enabled().trigger("chosen:updated");
			$('#input-operName').disabled();
			$('#input-namespace').disabled();
		} else if(type == 'Axis' || type == 'Axis2' || type == 'CXF'){
			$('#input-method').disabled().trigger("chosen:updated");
			$('#input-operName').enabled();
			$('#input-namespace').enabled();
		}
	}
	
	initFormByType($("#input-type").val());
	
	$("#input-type").change(function(){
		var type = $(this).val();
		initFormByType(type);
	});
	
	$("#input-plugin").change(function(){
		var pluginId = $(this).val();
		if($.founded(pluginId)){
			$.post(_path + "/manager/api/plugin/authzExtensions/" + pluginId ,function(data){
	    		if($.founded(data)){
	    			var html = ['<option value="">--请选择--</option>'];
	    			$.each(data,function(i,item){
	    				html.push('<option value="'+item["key"]+'">'+item["value"]+'</option>');
	    			});
	    			$("#input-extension").empty().append(html.join(""));
	    		}
	    	}).always(function(){
				$("#input-extension").trigger("chosen:updated");
	    	});
		}
    });
	
	/**消息主题相关*/
	$("#input-topic").change(function(a,b){
		var tags = [];
		if(b.selected != ''){
			tags = jQuery.parseJSON($(this).find('option:selected').attr('tags'));
		}
		var html = ['<option value="">--请选择--</option>'];
		for(var i = 0;i < tags.length;i ++){
			html.push('<option value="'+tags[i]["key"]+'">'+tags[i]["desc"]+'</option>');
		}
		$("#input-tags").empty().html(html.join("")).trigger("chosen:updated");
	});
	
	// 关闭过滤模式，保留所有标签
	KindEditor.options.filterMode = false;
	var ke_container = $("#ke_control").find(".ke-container");
	KindEditor.remove('#input-detail');
	var editor = KindEditor.create('#input-detail', $.extend(true,{},textOption,{
		afterCreate: function () {
		
			this.sync();
			this.readonly(false);
			this.focus();
			
			//清除内存
			$("#input-detail").on("kineditor:sync", function(){
				editor.html(""); 
				editor.sync();
			});
			
			ke_container = $("#ke_control").find(".ke-container");
			$('#ajaxForm').validateForm( {
				ignore				: ".ignore,.ke-edit-textarea",
				//显示提交进度的状态条元素
		        progressElement     : "#bootboxStatus",
				//提交前的回调函数
				beforeSubmit : function(formData, jqForm, options) {

					editor.sync();
				
					var fbnr = editor.text();
					if (!$.founded(fbnr)) {
						$(ke_container).errorClass("接口详细介绍必须填写!");
						return false;
					} else {
						$(ke_container).successClass();
						return true;
					}
				}	
			});
	    },
	    afterBlur: function () {

	    	this.sync();
	    	 
			if (!$.founded(this.text())) {
				$(ke_container).errorClass("接口详细介绍必须填写!");
			} else {
				$(ke_container).successClass();
			}
	       
	    } 
	}));
	
	window.setTimeout(function(){
		editor.readonly(false);
	}, 1000);
	
	$.fn.resetIndex = function (callback){
		return $(this).each(function () {
			$(this).find("tr.trclass").each(function(i,tr){
				$(this).find(":text,select,:hidden,textarea").each(function(){
					var name = $(this).attr("name");
					if($.founded(name)){
						$(this).attr("name",name.replace(/\[\d+\]/, "[" + i + "]"));
					}
				});
				$(this).find("th[scope='row']").each(function(){
					$(this).text(i + 1);
				});
				if($.isFunction(callback)){
					callback.call($this,i,tr);
				}
			});
		});
	}
	
	
	$("#input-param").click(function(event) {
		/*var notHasRow = $("#table-columns-tbody input[name^='paramList']").size() == 0;
		if(notHasRow){
			$("#table-columns-tbody").empty();
		}*/
        var index = $("#table-columns-tbody>tr.trclass").size();
		var html = [];
			html.push('<tr class="trclass">');
				html.push('<th scope="row">'+( index + 1 )+'</th>');
				html.push('<td><input type="text" name="paramList['+index+'].key" class="form-control input-sm" placeholder="参数编码,如:name"/></td>');
				html.push('<td><input type="text" name="paramList['+index+'].name" class="form-control input-sm" placeholder="参数名称,如:名称"/></td>');
				html.push('<td><select name="paramList['+index+'].type" class="form-control input-sm">');
		  		html.push('<option value="">--请选择--</option>');
		  		html.push('<option value="String" selected>字符串</option>');
		  		html.push('<option value="Number">数字</option>');
			  	html.push('<option value="Boolean">布尔值</option>');
				html.push('</select></td>');
	    		html.push('<td><select name="paramList['+index+'].required" class="form-control input-sm">');
	      		html.push('<option value="">--请选择--</option>');
	    	  	html.push('<option value="1" selected>是</option>');
	    	  	html.push('<option value="0">否</option>');
	    		html.push('</select></td>');
				html.push('<td><input type="text" class="form-control input-sm" name="paramList['+index+'].desc" placeholder="参数描述"/></td>');
				html.push('<td><a href="javascript:void(0);" class="btn btn-del btn-primary icon-minus">-</a></td>');
			html.push('</tr>');
		if(index == 0){
			$("#table-columns-tbody").empty().append(html.join(""));
		}else {
			$("#table-columns-tbody").append(html.join(""));
		}
		$("#table-columns-tbody").resetIndex();
	});
	
	$("#output-field").click(function(event) {
		var index = $("#table-fields-tbody>tr.trclass").size();
		var html = [];
			html.push('<tr class="trclass">');
				html.push('<th scope="row">'+( index + 1 )+'</th>');
				html.push('<td><input type="text" name="fieldList['+index+'].key" class="form-control input-sm" placeholder="字段编码,如:name"/></td>');
				html.push('<td><input type="text" name="fieldList['+index+'].name" class="form-control input-sm" placeholder="字段名称,如:名称"/></td>');
				html.push('<td><select name="fieldList['+index+'].type" class="form-control input-sm">');
		  		html.push('<option value="">--请选择--</option>');
		  		html.push('<option value="String" selected>字符串</option>');
		  		html.push('<option value="Number">数字</option>');
			  	html.push('<option value="Boolean">布尔值</option>');
				html.push('</select></td>');
				html.push('<td><input type="text" class="form-control input-sm" name="fieldList['+index+'].desc" placeholder="字段描述"/></td>');
				html.push('<td><a href="javascript:void(0);" class="btn btn-del btn-primary icon-minus">-</a></td>');
			html.push('</tr>');
		if($("#table-fields-tbody>tr.trclass").size() == 0){
			$("#table-fields-tbody").empty().append(html.join(""));
		}else {
			$("#table-fields-tbody").append(html.join(""));
		}
		$("#table-fields-tbody").resetIndex();
	});
	
	$("#error-field").click(function(event) {
		var index = $("#table-error-tbody>tr.trclass").size();
		var html = [];
			html.push('<tr class="trclass">');
				html.push('<th scope="row">'+(index+1)+'</th>');
				html.push('<td><input type="text" name="errorList['+index+'].key" class="form-control input-sm" placeholder="错误编码,如:1001"/></td>');
				html.push('<td><input type="text" class="form-control input-sm" name="errorList['+index+'].desc" placeholder="错误描述"/></td>');
				html.push('<td><a href="javascript:void(0);" class="btn btn-del btn-primary icon-minus">-</a></td>');
			html.push('</tr>');
		if($("#table-error-tbody>tr.trclass").size() == 0){
			$("#table-error-tbody").empty().append(html.join(""));
		}else {
			$("#table-error-tbody").append(html.join(""));
		}
		$("#table-error-tbody").resetIndex();
	});
	
	$(document).off("click","a.btn-del").on("click","a.btn-del",function(event){
		$(this).parent().parent().children("input").val("");
		$(this).parent().parent().remove();
		$("#table-columns-tbody,#table-fields-tbody,#table-error-tbody").resetIndex();
	});

});
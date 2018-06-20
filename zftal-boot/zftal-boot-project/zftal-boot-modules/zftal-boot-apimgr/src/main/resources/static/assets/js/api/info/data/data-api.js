jQuery(function($){
	
	$.validator.addMethod("tableCheck",function(value,element,params){
		// 数据交换方式：0: 数据表 、1： 自定义SQL语句
		var method = $("#input-exchMethod").val();
		if(method == '0' && !$.founded(value)){
			return false;
		}
		return true;
	},"数据表必选.");
	
	$.validator.addMethod("sqlCheck",function(value,element,params){
		// 数据交换方式：0: 数据表 、1： 自定义SQL语句
		var method = $("#input-exchMethod").val();
		if(method == '1' && !$.founded(value)){
			return false;
		}
		return true;
	},"SQL语句必填.");
	
	$.validator.addMethod("outTypeCheck",function(value,element,params){
		// 数据交换类型：0: 新增数据 、1： 删除数据、2： 更新数据、3：查询数据
		var exchType = $("#input-exchType").val();
		if(exchType == '3' && !$.founded(value)){
			return false;
		}
		return true;
	},"返回类型必选.");
	
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
		
		// 数据交换类型：0: 新增数据 、1： 删除数据、2： 更新数据、3：查询数据
		var exchType = $("#input-exchType").val();
		if(exchType == '2'){
			$('#nav-tabs-new a[href="#update-lft-tab"]').tab('show');
    	} else if(exchType == '3'){
    		// 数据查询类型下，需将解析的字段结果放置在返回条件区域
			$('#nav-tabs-new a[href="#output-lft-tab"]').tab('show');
    	} else {
    		$('#nav-tabs-new a[href="#input-lft-tab"]').tab('show');
    	}
		// 数据交换方式：0: 数据表 、1： 自定义SQL语句
		var method = $("#input-exchMethod").val();
		if(method == '0'){
    		$("#form-group-table").show();
    		$("#form-group-sql").hide();
    		$("#form-group-sql-error").hide();
    		loadTables();
    	}else {
    		$("#form-group-table").hide();
    		$("#input-table option:gt(0)").remove();
    		$("#input-table").trigger("chosen:updated");
    		$("#form-group-sql").show();
    		$("#form-group-sql-error").show();
    	}
		
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
	
	
	function loadTables(){
		var dbname = $("#input-db").find("option:selected").attr("data-name");
		if($.founded(dbname)){
			$.post(_path + "/manager/api/database/table/names",{"dbname" : dbname},function(data){
	    		if(data){
	    			var html = ['<option value="">--请选择--</option>'];
	    			$.each(data,function(i,item){
	    				var tableName = item["TABLE_NAME"];var comments = item["COMMENTS"];
	    				if(tableName == undefined){tableName = item["table_name"];}
	    				if(comments == undefined){comments = item["comments"];}
	    				if(comments == '' || comments == undefined){comments = tableName;}
	    				html.push('<option value="'+tableName+'">'+comments+'</option>');
	    			});
	    			$("#input-table").empty().append(html.join(""));
	    		}
	    	}).done(function(){
	    		var pre = $("#input-table").data("pre");
	    		if($.founded(pre)){
	    			$("#input-table").val(pre);
	    		}
				$("#input-table").trigger("chosen:updated");
	    	});
		}
	}
	
	$("#input-db").change(function(){
    	if($("#input-exchMethod").val() == '0'){
    		loadTables();
    	}
    });
	
	$("#input-exchMethod").change(function(e){
		// 数据交换方式：0: 数据表 、1： 自定义SQL语句
		if($(this).val() == '0'){
    		$("#form-group-table").show();
    		$("#form-group-sql").hide();
    		$("#form-group-sql-error").hide();
    		loadTables();
    	}else {
    		$("#form-group-table").hide();
    		$("#input-table option:gt(0)").remove();
    		$("#input-table").trigger("chosen:updated");
    		$("#form-group-sql").show();
    		$("#form-group-sql-error").show();
    	}
	});
	$("#input-exchMethod").trigger("change");
	
	function newInputRow(i,item){
		var columnName = item["COLUMN_NAME"];
		if(columnName == undefined){columnName = item['column_name'] || '';}
		var comments = item["COMMENTS"] ||"";
		if(comments == undefined){comments = item['comments'] || '';}
		var name = comments;
		if(comments == ''){name = columnName;}
		var desc = "无";
		$.each(["【","，","（","：","[","(",":"],function(j,str){
			name = comments.substring(0, comments.indexOf(str));
			if($.founded(name)){
				desc = comments.substring(comments.indexOf(str));
				return false;
			}
		});
		name = $.founded(name) ? name : comments;
		
		var html = [];

		html.push('<tr class="trclass">');
			html.push('<th scope="row">'+( i + 1 )+'</th>');
			html.push('<td><input type="text" name="paramList['+i+'].key" value="'+ columnName +'" class="form-control input-sm" placeholder="参数编码,如:name"/></td>');
			html.push('<td><input type="text" name="paramList['+i+'].name"value="'+ name +'" class="form-control input-sm" placeholder="参数名称,如:名称"/></td>');
			html.push('<td><select name="paramList['+i+'].type" class="form-control input-sm">');
			html.push('<option value="">--请选择--</option>');
			html.push('<option value="String" selected="selected">字符串</option>');
			html.push('<option value="Number">数字</option>');
			html.push('<option value="Boolean">布尔值</option>');
			html.push('</select></td>');
			html.push('<td><select name="paramList['+i+'].required" class="form-control input-sm">');
			html.push('<option value="">--请选择--</option>');
			html.push('<option value="1" selected="selected">是</option>');
			html.push('<option value="0">否</option>');
			html.push('</select></td>');
			html.push('<td><input type="text" class="form-control input-sm" name="paramList['+i+'].desc" value="'+ desc +'"placeholder="参数描述"/></td>');
			html.push('<td><a href="javascript:void(0);" class="btn btn-del btn-primary icon-minus">-</a></td>');
		html.push('</tr>');
		return html.join("");
	}
	
	function newUpdateRow(i,item){
		var columnName = item["COLUMN_NAME"];
		if(columnName == undefined){columnName = item['column_name'] || '';}
		var comments = item["COMMENTS"] ||"";
		if(comments == undefined){comments = item['comments'] || '';}
		var name = comments;
		if(comments == ''){name = columnName;}
		var desc = "无";
		$.each(["【","，","（","：","[","(",":"],function(j,str){
			name = comments.substring(0, comments.indexOf(str));
			if($.founded(name)){
				desc = comments.substring(comments.indexOf(str));
				return false;
			}
		});
		name = $.founded(name) ? name : comments;
		
		var html = [];
		html.push('<tr class="trclass">');
			html.push('<th scope="row">'+( i + 1 )+'</th>');
			html.push('<td><input type="text" name="updateList['+i+'].key" class="form-control input-sm" value="'+columnName+'" placeholder="字段编码,如:name"/></td>');
			html.push('<td><input type="text" name="updateList['+i+'].name" class="form-control input-sm" value="'+name+'" placeholder="字段名称,如:名称"/></td>');
			html.push('<td><select name="updateList['+i+'].type" class="form-control input-sm">');
	  		html.push('<option value="">--请选择--</option>');
	  		html.push('<option value="String" selected="selected">字符串</option>');
	  		html.push('<option value="Number">数字</option>');
		  	html.push('<option value="Boolean">布尔值</option>');
			html.push('</select></td>');
			html.push('<td><input type="text" class="form-control input-sm" name="updateList['+i+'].desc" value="' + desc + '" placeholder="字段描述"/></td>');
			html.push('<td><a href="javascript:void(0);" class="btn btn-del btn-primary icon-minus">-</a></td>');
		html.push('</tr>');
		return html.join("");
	}
	
	function newOutputRow(i,item, name, desc){
		var columnName = item["COLUMN_NAME"];
		if(columnName == undefined){columnName = item['column_name'] || '';}
		var html = [];
		html.push('<tr class="trclass">');
			html.push('<th scope="row">'+( i + 1 )+'</th>');
			html.push('<td><input type="hidden" name="fieldList['+i+'].key" class="form-control input-sm" value="'+columnName+'"  placeholder="字段编码,如:name"/>'+columnName+'</td>');
			html.push('<td><input type="hidden" name="fieldList['+i+'].name" class="form-control input-sm" value="'+name+'"  placeholder="字段名称,如:名称"/>'+name+'</td>');
			html.push('<td><input type="hidden" name="fieldList['+i+'].type" class="form-control input-sm" value="String" placeholder="字段类型"/>String</td>');
			html.push('<td><input type="text" class="form-control input-sm" name="fieldList['+i+'].alias" value="" placeholder="字段别名"/></td>');
			html.push('<td><input type="text" class="form-control input-sm" name="fieldList['+i+'].desc" value="' + desc + '" placeholder="字段描述"/></td>');
			html.push('<td><a href="javascript:void(0);" class="btn btn-del btn-primary icon-minus">-</a></td>');
		html.push('</tr>');
		return html.join("");
	}
	
	$("#input-exchType,#input-exchMethod,#input-table").change(function(e){
		// 数据交换方式：0: 数据表 、1： 自定义SQL语句
		var method = $("#input-exchMethod").val();
		if(method == '1'){
			$("#input-sql").trigger("input");
			return;
		}
		// 数据交换类型：0: 新增数据 、1： 删除数据、2： 更新数据、3：查询数据
		var exchType = $("#input-exchType").val();
		// 获取选择的数据库和数据表
		var dbname = $("#input-db").find("option:selected").attr("data-name");
		var table  = $("#input-table").val();
		if($.founded(dbname) && $.founded(table) && $.founded(exchType)){
			// 清空列表，并设置默认显示信息
			$("#output-case").val("");
			$("#table-columns-tbody").empty().html('<tr><td class="text-center p-10" colspan="7">暂无数据,可添加输出字段!</td></tr>');
			$("#table-update-tbody").empty().html('<tr><td colspan="6" class="text-center p-10">暂无数据,可添加变更字段!</td></tr>');
			$("#table-fields-tbody").empty().html('<tr><td colspan="7" class="text-center p-10">暂无数据,可添加输入参数!</td></tr>');
			// 根据数据库和数据表查询对应的列信息
			$.post(_path + "/manager/api/database/table/columns",{"dbname" : dbname, "table": table},function(data){
	    		if($.founded(data)){
	    			var html_input = [];
	    			var html_update = [];
	    			var html_output = [];
	    			var html_outcase = [];
	    			$.each(data,function(i,item){
	    				
	    				// 输入参数
//	    				html_input.push(newInputRow(i,item));
	    				// 变更字段
	    				if(exchType == '2'){
	    					html_update.push(newUpdateRow(i,item));
		    	    	} 
	    				// 输出参数
		    			else if(exchType == '3'){
		    				var columnName = item["COLUMN_NAME"];
		    				if(columnName == undefined){columnName = item['column_name'] || '';}
		    				var comments = item["COMMENTS"] ||"";
		    				if(comments == undefined){comments = item['comments'] || '';}
		    				var name = comments;
		    				if(comments == ''){name = columnName;}
		    				var desc = "无";
		    				$.each(["【","，","（","：","[","(",":"],function(j,str){
		    					name = comments.substring(0, comments.indexOf(str));
		    					if($.founded(name)){
		    						desc = comments.substring(comments.indexOf(str));
		    						return false;
		    					}
		    				});
		    				name = $.founded(name) ? name : comments;
		    				html_output.push(newOutputRow(i,item,name,desc));
		    				html_outcase.push("\r\n	" + columnName + ':"' + name + '"');
	    				}	
	    			});
	    			
	    			// 输入参数
	    			$("#table-columns-tbody").empty().append(html_input.join(""));
	    			// 变更字段
	    			if(exchType == '2'){
	    				$("#table-update-tbody").empty().append(html_update.join(""));
						$("#update-tab").removeClass("hidden");
						$("#output-tab").removeClass("hidden").addClass("hidden");
						$('#nav-tabs-new a[href="#update-lft-tab"]').tab('show');
			    	} 
	    			// 输出参数
	    			else if(exchType == '3'){
	    				$("#table-fields-tbody").empty().append(html_output.join(""));
		    			$("#output-case").val('{' + html_outcase.join(",") + '\r\n}');
			    		// 数据查询类型下，需将解析的字段结果放置在返回条件区域
			    		$("#update-tab").removeClass("hidden").addClass("hidden");
						$("#output-tab").removeClass("hidden");
						$('#nav-tabs-new a[href="#output-lft-tab"]').tab('show');
			    	} else {
			    		$("#update-tab").addClass("hidden");
						$("#output-tab").addClass("hidden");
			    		$('#nav-tabs-new a[href="#input-lft-tab"]').tab('show');
			    	}
	    		}
	    	}).done(function(){
	    		$("#table-columns-tbody,#table-fields-tbody,#table-update-tbody").resetIndex();
	    	});
			
		}
		
    });
	
	$("#input-sql").input(function(e){
		// 获取选择的数据库和SQL语句
		var dbname = $("#input-db").find("option:selected").attr("data-name");
		var sql    = $(this).val();
		if($.founded(dbname) && $.founded(sql)){
			$('#form-group-sql-error-content').text('');
			// 清空列表，并设置默认显示信息
			$("#output-case").val("");
			$("#table-columns-tbody").empty().html('<tr><td class="text-center p-10" colspan="7">暂无数据,可添加输出字段!</td></tr>');
			$("#table-update-tbody").empty().html('<tr><td colspan="6" class="text-center p-10">暂无数据,可添加变更字段!</td></tr>');
			$("#table-fields-tbody").empty().html('<tr><td colspan="7" class="text-center p-10">暂无数据,可添加输入参数!</td></tr>');
			// 解析SQL
			$.post(_path + "/manager/api/database/sql/columns",{"dbname" : dbname, "sql": sql, "exchType" : $("#input-exchType").val()},function(data){
	    		if($.founded(data)){
	    			if(data["status"] == 'error'){
	    				if(data["message"].length > 200){
	    					$('#form-group-sql-error-content').text(data["message"].substring(0,200) + '...');
	    				}else{
	    					$('#form-group-sql-error-content').text(data["message"]);
	    				}
//	    				$.error(data["message"]);
	    				return;
	    			}
	    			// 数据交换类型：0: 新增数据 、1： 删除数据、2： 更新数据、3：查询数据
	    			var exchType = data["exchType"];
	    			$("#input-exchType").val(exchType).trigger("chosen:updated");
	    			var html_input = [];
	    			$.each(data["input"] || [],function(i,item){
	    				// 输入参数
	    				html_input.push(newInputRow(i,item));
	    			});
	    			// 输入参数
	    			$("#table-columns-tbody").empty().append(html_input.join(""));
	    			// 变更字段
	    			if(exchType == '2'){
	    				var html_update = [];
	    				$.each(data["update"] || [],function(i,item){
		    				// 变更字段
	    					html_update.push(newUpdateRow(i,item));
		    			});
	    				$("#table-update-tbody").empty().append(html_update.join(""));
	    	    	}
	    			// 输出参数
	    			else if(exchType == '3'){
	    				var html_output = [];
		    			var html_outcase = [];
	    				$.each(data["output"] || [],function(i,item){
	    					
	    					var comments = item["COMMENTS"] ||"";
	    					var name = comments;
	    					var desc = "无";
	    					$.each(["【","，","（","：","[","(",":"],function(j,str){
	    						name = comments.substring(0, comments.indexOf(str));
	    						if($.founded(name)){
	    							desc = comments.substring(comments.indexOf(str));
	    							return false;
	    						}
	    					});
	    					name = $.founded(name) ? name : comments;
		    				html_output.push(newOutputRow(i, item, name, desc));
		    				html_outcase.push("\r\n	" + item["COLUMN_NAME"] + ':"' + name + '"');
		    			});
	    				$("#table-fields-tbody").empty().append(html_output.join(""));
		    			$("#output-case").val('{' + html_outcase.join(",") + '\r\n}');
	    	    	}
	    			
	    			if(exchType == '2'){
						$("#update-tab").removeClass("hidden");
						$("#output-tab").removeClass("hidden").addClass("hidden");
						$('#nav-tabs-new a[href="#update-lft-tab"]').tab('show');
			    	} else if(exchType == '3'){
			    		// 数据查询类型下，需将解析的字段结果放置在返回条件区域
			    		$("#update-tab").removeClass("hidden").addClass("hidden");
						$("#output-tab").removeClass("hidden");
						$('#nav-tabs-new a[href="#output-lft-tab"]').tab('show');
			    	} else {
			    		$("#update-tab").addClass("hidden");
						$("#output-tab").addClass("hidden");
			    		$('#nav-tabs-new a[href="#input-lft-tab"]').tab('show');
			    	}
	    		}
			}).done(function(){
				$("#table-columns-tbody,#table-fields-tbody,#table-update-tbody").resetIndex();
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
	
	$("#input-param").click(function(event) {
        var index = $("#table-columns-tbody>tr.trclass").size();
		var html = [];
			html.push('<tr class="trclass">');
				html.push('<th scope="row">'+( index + 1 )+'</th>');
				html.push('<td><input type="text" name="paramList['+index+'].key" class="form-control input-sm" placeholder="参数编码,如:name"/></td>');
				html.push('<td><input type="text" name="paramList['+index+'].name" class="form-control input-sm" placeholder="参数名称,如:名称"/></td>');
				html.push('<td><select name="paramList['+index+'].type" class="form-control input-sm">');
		  		html.push('<option value="">--请选择--</option>');
		  		html.push('<option value="String" selected="selected">字符串</option>');
		  		html.push('<option value="Number">数字</option>');
			  	html.push('<option value="Boolean">布尔值</option>');
				html.push('</select></td>');
	    		html.push('<td><select name="paramList['+index+'].required" class="form-control input-sm">');
	      		html.push('<option value="">--请选择--</option>');
	    	  	html.push('<option value="1" selected="selected">是</option>');
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
	
	$("#input-update").click(function(event) {
        var index = $("#table-update-tbody>tr.trclass").size();
		var html = [];
			html.push('<tr class="trclass">');
				html.push('<th scope="row">'+( index + 1 )+'</th>');
				html.push('<td><input type="text" name="updateList['+index+'].key" class="form-control input-sm" placeholder="字段编码,如:name"/></td>');
				html.push('<td><input type="text" name="updateList['+index+'].name" class="form-control input-sm" placeholder="字段名称,如:名称"/></td>');
				html.push('<td><select name="updateList['+index+'].type" class="form-control input-sm">');
		  		html.push('<option value="">--请选择--</option>');
		  		html.push('<option value="String" selected="selected">字符串</option>');
		  		html.push('<option value="Number">数字</option>');
			  	html.push('<option value="Boolean">布尔值</option>');
				html.push('</select></td>');
				html.push('<td><input type="text" class="form-control input-sm" name="updateList['+index+'].desc" placeholder="字段描述"/></td>');
				html.push('<td><a href="javascript:void(0);" class="btn btn-del btn-primary icon-minus">-</a></td>');
			html.push('</tr>');
		
		if(index == 0){
			$("#table-update-tbody").empty().append(html.join(""));
		}else {
			$("#table-update-tbody").append(html.join(""));
		}
		$("#table-update-tbody").resetIndex();
	});
	
	
	$(document).off("click","a.btn-del").on("click","a.btn-del",function(event){
		$(this).parent().parent().children("input").val("");
		$(this).parent().parent().remove();
		$("#table-columns-tbody,#table-fields-tbody,#table-update-tbody").resetIndex();
	});

});
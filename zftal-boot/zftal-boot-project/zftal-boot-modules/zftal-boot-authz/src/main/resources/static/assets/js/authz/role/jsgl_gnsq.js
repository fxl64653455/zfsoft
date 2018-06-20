$(function(){
	//给每个tab多选框添加事件
	$('#nav_tabs li').each(function(){
		var li_item = this;
		var tab_pane = $(""+$(this).find("a:eq(0)").attr("href"));
		$(this).find(":checkbox").unbind().click(function(event){
			event.stopPropagation();   
			// 切换模块
			$(li_item).find("a:eq(0)").tab('show');
			$(tab_pane).find(":checkbox").prop("checked",$(this).prop("checked"));
		});
	});
	
	$(":hidden[name=userButton]").each(function(i,n){
		var button = $(":checkbox[name=gnczid][value="+$(n).val()+"]");
		button.attr("checked",true);
		var menu = $(":checkbox[name=gnmkdm][value="+button.attr("gnmkdm")+"]");
		menu.attr("checked",true);
		$(":checkbox[name=gnmkdm][value="+menu.attr("gnmkdm")+"]").attr("checked",true);
		$(":checkbox[name=gnmkdm][value="+menu.attr("topGndm")+"]").attr("checked",true);
	});
	
	//子模块勾选事件
	$(document).off('click', 'input[name="gnmkdm"]').on('click', 'input[name="gnmkdm"]', function (e) {
		var input = this;
		var tab_left = $(input).parent().parent();
		var tab_right = $(tab_left).next();
		//右侧子节点跟随该选择状态变化
		$(tab_right).find(":checkbox").prop("checked",$(input).prop("checked"));
		//大于两级节点时：
		var tab_parent = tab_left.parent().parent();
		if(!tab_parent.hasClass("tab-pane")){
			var tab_parent_left_checkbox = $(tab_parent).prev().find(":checkbox");
			$(tab_parent_left_checkbox).data("child-trigger",1);
			if($(tab_parent).find(":checkbox").size()!=0 &&  $(tab_parent).find(":checkbox").size() == $(tab_parent).find("input:checked").size()){
				$(tab_parent_left_checkbox).prop("checked",true);
			}else{
				$(tab_parent_left_checkbox).prop("checked",false);
			}
		}
		
		//当前tab卡片选项input同步选择逻辑
		var tab_pane = $("#tabContent").find("div.active");
		//当前tab的顶级功能模块
		var gnmkdm = $(tab_pane).data("gnmkdm");
		//如果全了则选中tab中的checkbox
		if($(tab_pane).find('input[name="gnmkdm"]').size()!=0 &&  $(tab_pane).find('input[name="gnmkdm"]').size() == $(tab_pane).find('input[name="gnmkdm"]:checked').size()){
			$("li[data-gnmkdm='"+gnmkdm+"']").find(":checkbox").prop("checked",true);
		}else{
			$("li[data-gnmkdm='"+gnmkdm+"']").find(":checkbox").prop("checked",false);
		}
	})
    //操作按钮勾选事件
	.off('click', 'input[name="gnczid"]').on('click', 'input[name="gnczid"]', function (event) {
		var input = this;
		//递归逐级向上查找改变选择状态
		(function(tab_right){
			while ($(tab_right).size() > 0 && !$(tab_right).hasClass("tab-pane")) {
				var tab_left = $(tab_right).prev();
				if($(tab_right).find("input:checked").size() > 0){
					$(tab_left).find(":checkbox").prop("checked",true);
				}else{
					$(tab_left).find(":checkbox").prop("checked",false);
				}
				//父级作为新的右侧div	
				tab_right = $(tab_left).parent().parent();
			}
		})($(input).closest(".col-child"));
		
		//当前tab卡片选项input同步选择逻辑
		var tab_pane = $("#tabContent").find("div.active");
		//当前tab的顶级功能模块
		var gnmkdm = $(tab_pane).data("gnmkdm");
		//如果全了则选中tab中的checkbox
		if($(tab_pane).find(":checkbox").size()!=0 &&  $(tab_pane).find(":checkbox").size() == $(tab_pane).find("input:checked").size()){
			$("li[data-gnmkdm='"+gnmkdm+"']").find(":checkbox").prop("checked",true);
		}else{
			$("li[data-gnmkdm='"+gnmkdm+"']").find(":checkbox").prop("checked",false);
		}
	})
	 //反选事件
	.off('click', 'label.label_gnfx').on('click', 'label.label_gnfx', function (event) {
		var checkboxs = $(this).parent().next().find(":checkbox");
		$(checkboxs).each(function(){
			if($(this).prop("checked")){
				$(this).prop("checked",false);
			}else{
				$(this).prop("checked",true);
			}
		});
	});
	
	$("#btn_qx").click(function(){
		$(":checkbox[name=gnmkdm],[name=gnczid]").each(function(index,checkbox){
			if($(checkbox).prop("disabled") == false){
				$(checkbox).prop("checked",true);
			}
		});
	});
	
	$("#btn_cz").click(function(){
		$(":checkbox[name=gnmkdm],[name=gnczid]").each(function(index,checkbox){
			if($(checkbox).prop("disabled") == false){
				$(checkbox).prop("checked",false);
			}
		});	
	});
});
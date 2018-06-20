jQuery(function($) {
	
	//计算tabs高度
	var pageHeight = $(window).height() - $('#tabs').outerHeight(true) - $('#footer').outerHeight(true) - $('#navbar').outerHeight(true);
	var boxHeight = $(window).height() - $('#footer').outerHeight(true);
	$('#boxed').height(boxHeight);
	
	$(document).on('resize',function(){
		var pageHeight = $(window).height() - $('#tabs').outerHeight(true) - $('#footer').outerHeight(true) - $('#navbar').outerHeight(true);
		var boxHeight = $(window).height() - $('#footer').outerHeight(true);
		$('#boxed').height(boxHeight);
	});
	
	$(document).off('click','.top-nav .navbar-nav>li').on('click','.top-nav .navbar-nav>li',function(){
		if($('.left-nav-root').hasClass('active')){
			$('.left-nav-root').removeClass('active')
		}
	}).off('click','#tabs .nav-tabs>li').on('click','#tabs .nav-tabs>li',function(){
		$(window).off('resize.bootstrap-table');
	});
	
	function buildURL(requestURL,data){
		data = data || {};
		requestURL = _path +  requestURL;
		//在url上追加
		if(requestURL.indexOf("?") > -1){
			requestURL = requestURL + "&th=" + pageHeight + "&gnmkdm=" + data["gnmkdm"];
		}else{
			requestURL = requestURL + "?th=" + pageHeight + "&gnmkdm=" + data["gnmkdm"];
		}
		//alert("requestURL:" + requestURL);
		return requestURL;
	};
	
	// 初始化一级菜单
	function initTop(){
		
		$.post(_path + "/func/nav/topMenuList", {
			
		}, function(data) {
			if($.founded(data)){
				var html = [];
				$.each(data || [], function(i, item){
					html.push('<li class="tgl-menu-btn" data-gnmkdm="' + item["gnmkdm"] + '">');
						html.push('<a href="javascript:void(0);">');
							html.push('<i class="' + (item["tblj"]  || "demo-pli-layout-grid") + '"></i>');
							html.push('<span>' + item["gnmkmc"] + '</span>');
						html.push('</a>');
					html.push('</li>');
				});
				// 一级菜单较多的系统，不将一级菜单放置在
				$('.top-nav').empty().html(html.join(""));
				$('.top-nav li:first').click();
			}
		}).done(function() { 
			$(document).trigger("menuReady");
		});
	}
	
	function initAllLeft(){
		//一级菜单在顶部
		$.post(_path + "/func/nav/treeMenuList", { }, function(data) {
			
			if($.founded(data)){
				
				var html = [];
				
				$.each(data || [], function(i, top){
					
					if(top["sfgnym"] == '0'){
						
						html.push('<!--Category name-->');
						html.push('<li class="list-header">' + top["gnmkmc"] + '</li>');
						

						//存在二级菜单
						if($.founded(top["children"])){
							// 二级菜单
							$.each(top["children"] || [], function(id, item){
								
								html.push('<!--Menu list item-->');
								html.push('<li>');
									
								if(item["sfgnym"] == '0'){
									
									html.push('<a href="javascript:void(0);">');
										html.push('<i class="' + (item["tblj"]  || "fa fa-th-list") + '"></i>');
										html.push('<span class="menu-title">' + item["gnmkmc"] + '</span><i class="arrow"></i>');
									html.push('</a>');
									
									//存在三级菜单
									if($.founded(item["children"])){
										html.push('<!--Submenu-->');
										html.push('<ul class="collapse">');
										// 三级菜单
										$.each(item["children"] || [], function(id, menu){
											html.push('<li><a href="javascript:void(0);" title="'+menu["gnmkmc"]+'" data-addtab="' + menu["gnmkdm"] + '"  data-src="' + buildURL(menu["dyym"], menu) + '"  data-tab-layout="default"  data-blank-layout="default-tab" >' + menu["gnmkmc"] + '</a></li>');
										});
										html.push('</ul>');
									}													
									
								} else {
																																	
									html.push('<a href="javascript:void(0);" title="'+item["gnmkmc"]+'" data-addtab="' + item["gnmkdm"] + '"  data-src="' + buildURL(item["dyym"], item) + '"  data-tab-layout="default"  data-blank-layout="default-tab" >');
										html.push('<i class="' + (item["tblj"]  || "fa fa-th-list") + '"></i>');
										html.push('<span class="menu-title">' + item["gnmkmc"] + '</span>');
									html.push('</a>');
									
								}
								html.push('</li>');
							});
							
						}
							
					} else {
		                
		                html.push('<!--Top menu-->');
						html.push('<li class="active-link">');
							html.push('<a href="javascript:void(0);" title="'+top["gnmkmc"]+'" data-addtab="' + top["gnmkdm"] + '"  data-src="' + buildURL(top["dyym"], top) + '" data-tab-layout="default"  data-blank-layout="default-tab">');
								html.push('<i class="demo-psi-home"></i><span class="menu-title"> <strong>' + top["gnmkmc"] + '</strong> </span>');
							html.push('</a>');
						html.push('</li>');
						
					}
					
				});
				
				try {
					$("#mainnav-menu").empty().html(html.join(""));
					$('#mainnav-menu').metisMenu({ toggle: true });
					//绑定导航相关事件
					$.niftyNav('bind');
					$.niftyAside('bind');
					
		        }catch(err) {
		            console.error(err.message);
		        }
		        //class="active"
			}
		}).done(function() { 
			$(document).trigger("menuReady");
		});
	}
	
	// 菜单类型：1：上面一级菜单，左侧一级一下全部菜单，2：左侧全部菜单
	if($('body').hasClass('nav-type-1')) {
		//一级菜单在顶部
		initTop();
	} else if($('body').hasClass('nav-type-2')) {
		//左侧全部菜单
		initAllLeft();
	}
	
	//测试菜单样式切换
	$('.test').click(function() {
		
		//清空所有菜单
		if($('#mainnav-menu li').size() > 0) {
			$('#mainnav-menu').metisMenu('dispose');
		}
		$('.top-nav').empty();
		$('#mainnav-menu').empty();
		
		if($('body').hasClass('nav-type-1')) {
			$('body').removeClass('nav-type-1');
			$('body').addClass('nav-type-2');
									
			//左侧全部菜单
			initAllLeft();
			
		} else {
			$('body').removeClass('nav-type-2');
			$('body').addClass('nav-type-1');
			//一级菜单在顶部
			initTop();
		}
	});
	
	
	//根据点击的一级菜单显示二级
	$(document).off('click', '.top-nav li').on('click', '.top-nav li', function() {
		$('.top-nav li').removeClass('current');
		$(this).addClass('current');
		var gnmkdm = $(this).attr('data-gnmkdm');
		console.log(gnmkdm);
		$.post(_path + "/func/nav/childMenuTreeList", {
			parent : gnmkdm
		}, function(data) {
			if($.founded(data)){
				var html = [];
				for(var i = 0; i < data.length; i++) {
					var item = data[i];
					var children = item["children"] || [];
					if($.founded(children)){
						html.push('<li class="first">');
							html.push('<a href="javascript:void(0);">');
							html.push('<i class="'+(item["tblj"]  || "fa fa-th-list")+'"></i>');
							html.push('<span class="menu-title">' + item["gnmkmc"] + '</span>');
							html.push('<i class="arrow"></i>');
							html.push('</a>');
							html.push('<ul class="collapse">');
							for(var j = 0; j < children.length; j++) {
								var child = children[j];
								html.push('<li><a href="javascript:void(0);" title="'+child["gnmkmc"]+'" data-addtab=' + child["gnmkdm"] + '  data-src=' + buildURL(child["dyym"], child) + ' data-tab-layout="default"  data-blank-layout="default-tab">' + child["gnmkmc"] + '</a></li>');
							}
							html.push('</ul>');
						html.push('</li>');
						
					} else {
						html.push('<li class="first">');
							html.push('<a href="javascript:void(0);" title="'+item["gnmkmc"]+'" data-addtab="' + item["gnmkdm"] + '"  data-src="' + buildURL(item["dyym"], item) + '" data-tab-layout="default"  data-blank-layout="default-tab">');
							html.push('<i class="' + (item["tblj"]  || "fa fa-th-list") + '"></i>');
							html.push('<span class="menu-title">' + item["gnmkmc"] + '</span>');
							html.push('</a>');
						html.push('</li>');
					}

				}
				
				try {
					if($('#mainnav-menu li').size() > 0) {
						$('#mainnav-menu').metisMenu('dispose');
					}
					$("#mainnav-menu").empty().html(html.join(""));
					$('#mainnav-menu').metisMenu({ toggle: true });
					
					//绑定导航相关事件
					$.niftyNav('bind');
					$.niftyAside('bind');
					
		        } catch(err) {
		            console.error(err.message);
		        }
			}
		});
		
	});
	
	$('#tabs').tabs({
		sortable: true,
		monitor: '#mainnav-container',
	});

});
jQuery(function($){
	
	// 对Date的扩展，将 Date 转化为指定格式的String
	// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
	// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
	// 例子： 
	// (new Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 706-07-02 08:09:04.423 
	// (new Date()).format("yyyy-M-d h:m:s.S")      ==> 706-7-2 8:9:4.18 
	Date.prototype.format = function (fmt) { //author: meizz 
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	}
	
	// <!--Step:1 为ECharts准备一个具备大小（宽高）的Dom-->
	// Step:3 echarts & zrender as a Global Interface by the echarts-plain.js.
	// Step:3 echarts和zrender被echarts-plain.js写入为全局接口
	// onloadurl();
	
	//===========================初始化图表对象==============================
	
	var appRatio_pie = echarts.init(document.getElementById('appRatio_pie'));
	var appRatio_line = echarts.init(document.getElementById('appRatio_line'));
	
	var bizRatio_pie = echarts.init(document.getElementById('bizRatio_pie'));
	var bizRatio_line = echarts.init(document.getElementById('bizRatio_line'));
	
	var deviceRatio_pie = echarts.init(document.getElementById('deviceRatio_pie'));
	var deviceRatio_line = echarts.init(document.getElementById('deviceRatio_line'));
	
	var osRatio_pie = echarts.init(document.getElementById('osRatio_pie'));
	var osRatio_line = echarts.init(document.getElementById('osRatio_line'));
	
	var browserRatio_pie = echarts.init(document.getElementById('browserRatio_pie'));
	var browserRatio_line = echarts.init(document.getElementById('browserRatio_line'));
	
	//批量初始化
	$.each([appRatio_pie, appRatio_line, bizRatio_pie, bizRatio_line, deviceRatio_pie,deviceRatio_line,osRatio_pie,osRatio_line,browserRatio_pie,browserRatio_line],function(i,item){
		item.showLoading({
			text : "图表数据正在努力加载...",
			effect : 'whirling',
			//'spin' | 'bar' | 'ring' | 'whirling' | 'dynamicLine' | 'bubble' 				
			textStyle : {
				fontSize : 16
			}
		});
	});

	var appPie_data = [];
	var appLegend_data = [];
	var appLine_data = { "names" : [], "data" : {} };
	var bizPie_data = [];
	var bizLegend_data = [];
	var bizLine_data = { "names" : [], "data" : {} };
	var osPie_data = [];
	var osLegend_data = [];
	var osLine_data = { "names" : [], "data" : {} };
	var devicePie_data = [];
	var deviceLegend_data = [];
	var deviceLine_data = { "names" : [], "data" : {} };
	var browserPie_data = [];
	var browserLegend_data = [];
	var browserLine_data = { "names" : [], "data" : {} };
	
	function initPieCharts(data){
		
		appPie_data = [];
		appLegend_data = [];
		bizPie_data = [];
		bizLegend_data = [];
		osPie_data = [];
		osLegend_data = [];
		devicePie_data = [];
		deviceLegend_data = [];
		browserPie_data = [];
		browserLegend_data = [];
		
		//===========================图表数据==============================
		
		// 应用访问占比
		
		$.each(data["appRatio"]||[],function(i, item ){

			var appName = item["APPNAME"] || item["APPKEY"] || "Unkonw";
			//动态构建饼（圆环）图使用数据
			appLegend_data.push(appName);
			appPie_data.push({"value" : (item["APPCOUNT"] || 0) , "name" : appName});
			
		});
		
		// 业务占比
		
		$.each(data["bizRatio"]||[],function(i, item ){

			var bizName = item["BIZNAME"] || "Unkonw";
			//动态构建饼（圆环）图使用数据
			bizLegend_data.push(bizName);
			bizPie_data.push({"value" : (item["BIZCOUNT"] || 0) , "name" : bizName });
			
		});
		
		// 设备占比
		
		$.each(data["deviceRatio"]||[],function(i, item ){

			var deviceName = item["DEVICENAME"] || "Unkonw";
			//动态构建饼（圆环）图使用数据
			deviceLegend_data.push(deviceName);
			devicePie_data.push({"value" : (item["DEVICECOUNT"] || 0) , "name" : deviceName});
			
		});
		
		// 操作系统占比
		
		$.each(data["osRatio"]||[],function(i, item ){

			var osName = item["OSNAME"] || "Unkonw";
			//动态构建饼（圆环）图使用数据
			osLegend_data.push(osName);
			osPie_data.push({"value" : (item["OSCOUNT"] || 0) , "name" : osName});
			
		});
		
		// 浏览器占比
		
		$.each(data["browserRatio"]||[],function(i, item ){

			var browserName = item["BROWSERNAME"] || "Unkonw";
			//动态构建饼（圆环）图使用数据
			browserLegend_data.push(item["BROWSERNAME"] || "Unkonw");
			browserPie_data.push({"value" : (item["BROWSERCOUNT"] || 0) , "name" : browserName });
			
		});
		
		//===========================占比（饼（圆环）图）==============================
		
		var pie_option = {
		    title : {
		        text	: '父标题',
		        /* subtext: '子标题',*/
		        x		: 'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient : 'vertical',
		        x : 'left',
		        data:[]
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            magicType : {
		                show: true, 
		                type: ['pie', 'funnel'],
		                option: {
		                    funnel: {
		                        x: '25%',
		                        width: '50%',
		                        funnelAlign: 'left',
		                        max: 1548
		                    }
		                }
		            },
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true/*,
		    series : [
		        {
		            name:'访问来源',
		            type:'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:[
		                {value:335, name:'直接访问'},
		                {value:310, name:'邮件营销'},
		                {value:234, name:'联盟广告'},
		                {value:135, name:'视频广告'},
		                {value:178, name:'搜索引擎'}
		            ]
		        }
		    ]*/
		};
		
		var app_pie_option = $.extend(true, {}, pie_option,{
			title : {
		        text: '应用分类'
		    },
			legend: {
		        data: appLegend_data
		    },
		    series : [
		        {
		            name	: '访问来源',
		            type	: 'pie',
		            radius 	: '55%',
		            center	: ['50%', '60%'],
		            data: appPie_data
		        }
		    ]
		});
		
		var biz_pie_option = $.extend(true, {}, pie_option,{
			title : {
		        text: '业务分类'
		    },
			legend: {
		        data: bizLegend_data
		    },
		    series : [
		        {
		            name	: '访问业务',
		            type	: 'pie',
		            radius 	: '55%',
		            center	: ['50%', '60%'],
		            data: bizPie_data
		        }
		    ]
		});
		
		var os_pie_option = $.extend(true, {}, pie_option,{
			title : {
				text: '操作系统类型'
		    },
			legend: {
		        data: osLegend_data
		    },
		    series : [
		        {
		            name	: '操作系统',
		            type	: 'pie',
		            radius 	: '55%',
		            center	: ['50%', '60%'],
		            data: osPie_data
		        }
		    ]
		});
		
		var device_pie_option = $.extend(true, {}, pie_option,{
			title : {
				text: '终端设备类型'
		    },
			legend: {
		        data: deviceLegend_data
		    },
		    series : [
		        {
		            name	: '客户端设备',
		            type	: 'pie',
		            radius 	: '55%',
		            center	: ['50%', '60%'],
		            data: devicePie_data
		        }
		    ]
		});
		
		var browser_pie_option = $.extend(true, {}, pie_option,{
			title : {
				 text: '浏览器'
		    },
			legend: {
		        data: browserLegend_data
		    },
		    series : [
		        {
		            name	: '客户端浏览器',
		            type	: 'pie',
		            radius 	: '55%',
		            center	: ['50%', '60%'],
		            data: browserPie_data
		        }
		    ]
		});
		
		appRatio_pie.setOption(app_pie_option);
		bizRatio_pie.setOption(biz_pie_option);
		osRatio_pie.setOption(os_pie_option);
		deviceRatio_pie.setOption(device_pie_option);
		browserRatio_pie.setOption(browser_pie_option);
		
		//历史数据初始化完成，清除加载状态
		$.each([appRatio_pie, bizRatio_pie, osRatio_pie, deviceRatio_pie, browserRatio_pie],function(i,item){
			item.hideLoading();
		});

		
	}
	
	function initLineCharts(data){
		
		appLine_data = { "names" : [], "data" : {} };
		bizLine_data = { "names" : [], "data" : {} };
		osLine_data = { "names" : [], "data" : {} };
		deviceLine_data = { "names" : [], "data" : {} };
		browserLine_data = { "names" : [], "data" : {} };
		
		//===========================占比曲线变化（折线图）==============================
		
		// 应用访问前10条
		
		$.each(data["appDaily"]||[],function(i, item ){
			
			var appName = item["APPNAME"] || item["APPKEY"] || "Unkonw";
			
			//动态构建折线图使用率的数据
			if(!appLine_data["names"].contains(appName)){
				appLine_data["names"].push(appName);
			}
			if(!$.defined(appLine_data["data"][appName])){
				appLine_data["data"][appName] = [];
			}
			appLine_data["data"][appName].push((item["COUNT"] || 0));
			
		});

		// 业务访问前10条

		$.each(data["bizDaily"]||[],function(i, item ){
			
			var bizName = item["BIZNAME"] || "Unkonw";
			
			//动态构建折线图使用率的数据
			if(!bizLine_data["names"].contains(bizName)){
				bizLine_data["names"].push(bizName);
			}
			if(!$.defined(bizLine_data["data"][bizName])){
				bizLine_data["data"][bizName] = [];
			}
			bizLine_data["data"][bizName].push((item["COUNT"] || 0));
		});
		
		// 设备访问前10条
		
		$.each(data["deviceDaily"]||[],function(i, item ){
			
			var deviceName = item["DEVICENAME"] || "Unkonw";
			
			//动态构建折线图使用率的数据
			if(!deviceLine_data["names"].contains(deviceName)){
				deviceLine_data["names"].push(deviceName);
			}
			if(!$.defined(deviceLine_data["data"][deviceName])){
				deviceLine_data["data"][deviceName] = [];
			}
			deviceLine_data["data"][deviceName].push((item["COUNT"] || 0));
			
		});
		
		// 操作系统访问前10条
		
		$.each(data["osDaily"]||[],function(i, item ){
			
			var osName = item["OSNAME"] || "Unkonw";
			
			//动态构建折线图使用率的数据
			if(!osLine_data["names"].contains(osName)){
				osLine_data["names"].push(osName);
			}
			if(!$.defined(osLine_data["data"][osName])){
				osLine_data["data"][osName] = [];
			}
			osLine_data["data"][osName].push((item["COUNT"] || 0));
		});
		
		// 浏览器访问前10条

		$.each(data["browserDaily"]||[],function(i, item ){
			
			var browserName = item["BROWSERNAME"] || "Unkonw";
			
			//动态构建折线图使用率的数据
			if(!browserLine_data["names"].contains(browserName)){
				browserLine_data["names"].push(browserName);
			}
			if(!$.defined(browserLine_data["data"][browserName])){
				browserLine_data["data"][browserName] = [];
			}
			browserLine_data["data"][browserName].push((item["COUNT"] || 0));
			
		});
		
		var now = new Date();
		var psdn_xAxis = [];
		var len = 7;
		while (len--) {
			//var time = now.toLocaleTimeString().replace(/^\D*/, '');
			//time = time.substr(time.indexOf(":") + 1);
			psdn_xAxis.unshift(now.format("yyyy-MM-dd"));
			now = new Date(now - 1000 * 60 * 60 * 24);
		}
		
		
		
		var line_option  = {
		    title : {
		        text: '父标题',/*
		        subtext: '子标题'*/
	        	x		: 'center'
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		    	x: 'left',
		    	y: 'bottom',
		    	orient : 'horizontal'
		       /* data: ['意向','预购','成交']*/
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
		            saveAsImage : {show: true}
		        }
		    },
		    grid : {
				x : 40,
				y : 40,
				x2 : 40,
				y2 : 80,
				borderWidth : 0,
				borderColor : "#FFFFFF",
				shadowColor : '#FFFFFF', //默认透明
			},
			calculable : true,
			//X坐标轴信息
			xAxis : [ {
				type : 'category',// 坐标轴类型，横轴默认为类目型'category'，纵轴默认为数值型'value'
				boundaryGap : false,
				data : psdn_xAxis
			} ],
			//Y坐标轴信息
			yAxis : [ {
				 type : 'value'
			}],
		    series : []
		};
		
		var appLine_series = [];
		$.each(appLine_data["names"]||[],function(i,name){
			
			//数据补全；防止后台数据不能填充完整图表
			var dataArr = appLine_data["data"][name];
			for (var int = 0; int < (7 - dataArr.length); int++) {
				dataArr.unshift(0);
			}
			
			appLine_series.push({
	            "name" 		: name,
	            "type"		: 'line',
	            "smooth"	: true,
	            "itemStyle"	: {normal: {areaStyle: {type: 'default'}}},
	            "data"		: dataArr
	        });
		});
		var app_line_option = $.extend(true, {}, line_option,{
			title : {
		        text: '应用分布趋势（最近7天）'
		    },
			legend: {
		        data: appLegend_data
		    },
		    series : appLine_series
		});
		
		var bizLine_series = [];
		$.each(bizLine_data["names"]||[],function(i,name){
			
			//数据补全；防止后台数据不能填充完整图表
			var dataArr = bizLine_data["data"][name];
			for (var int = 0; int < (7 - dataArr.length); int++) {
				dataArr.unshift(0);
			}
			
			bizLine_series.push({
	            "name" 		: name,
	            "type"		: 'line',
	            "smooth"	: true,
	            "itemStyle"	: {normal: {areaStyle: {type: 'default'}}},
	            "data"		: dataArr
	        });
		});
		var biz_line_option = $.extend(true, {}, line_option,{
			title : {
		        text: '业务分布趋势（最近7天）'
		    },
			legend: {
		        data: bizLegend_data
		    },
		    series : bizLine_series
		});
		
		var osLine_series = [];
		$.each(osLine_data["names"]||[],function(i,name){
			
			//数据补全；防止后台数据不能填充完整图表
			var dataArr = osLine_data["data"][name];
			for (var int = 0; int < (7 - dataArr.length); int++) {
				dataArr.unshift(0);
			}
			
			osLine_series.push({
	            "name" 		: name,
	            "type"		: 'line',
	            "smooth"	: true,
	            "itemStyle"	: {normal: {areaStyle: {type: 'default'}}},
	            "data"		: dataArr
	        });
		});
		var os_line_option = $.extend(true, {}, line_option,{
			title : {
		        text: '操作系统分布趋势（最近7天）'
		    },
			legend: {
		        data: osLegend_data
		    },
		    series : osLine_series
		});
		
		var deviceLine_series = [];
		$.each(deviceLine_data["names"]||[],function(i,name){
			
			//数据补全；防止后台数据不能填充完整图表
			var dataArr = deviceLine_data["data"][name];
			for (var int = 0; int < (7 - dataArr.length); int++) {
				dataArr.unshift(0);
			}
			
			deviceLine_series.push({
	            "name" 		: name,
	            "type"		: 'line',
	            "smooth"	: true,
	            "itemStyle"	: {normal: {areaStyle: {type: 'default'}}},
	            "data"		: dataArr
	        });
		});
		var device_line_option = $.extend(true, {}, line_option,{
			title : {
		        text: '设备分布趋势（最近7天）'
		    },
			legend: {
		        data: deviceLegend_data
		    },
		    series : deviceLine_series
		});
		
		var browserLine_series = [];
		$.each(browserLine_data["names"]||[],function(i,name){

			//数据补全；防止后台数据不能填充完整图表
			var dataArr = browserLine_data["data"][name];
			for (var int = 0; int < (7 - dataArr.length); int++) {
				dataArr.unshift(0);
			}
			
			browserLine_series.push({
	            "name" 		: name,
	            "type"		: 'line',
	            "smooth"	: true,
	            "itemStyle"	: {normal: {areaStyle: {type: 'default'}}},
	            "data"		: dataArr
	        });
		});
		var browser_line_option = $.extend(true, {}, line_option,{
			title : {
		        text: '浏览器分布趋势（最近7天）'
		    },
			legend: {
		        data: browserLegend_data
		    },
		    series : browserLine_series
		});
		
		
		appRatio_line.setOption(app_line_option);
		bizRatio_line.setOption(biz_line_option);
		osRatio_line.setOption(os_line_option);
		deviceRatio_line.setOption(device_line_option);
		browserRatio_line.setOption(browser_line_option);
		
		//历史数据初始化完成，清除加载状态
		$.each([appRatio_line, bizRatio_line, osRatio_line, deviceRatio_line, browserRatio_line],function(i,item){
			item.hideLoading();
		});
		
	}
	
	function resetLineCharts(data){
		
		var axisData = (new Date()).toLocaleTimeString().replace(/^\D*/, '');
			axisData = axisData.substr(axisData.indexOf(":") + 1);
		
		// 应用访问前10条
		var appTop10 = data["appDaily"]||[];
		if($.founded(appTop10)){
			var dataTmp = [];
			$.each(appTop10,function(i, item ){
				dataTmp.push([ i, // 系列索引
				     (item["COUNT"] || 0), // 新增数据
				     false, // 新增数据是否从队列头部插入
				     false, // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				     axisData
				   ]);
			});
			appRatio_line.addData(dataTmp);
		}

		// 业务访问前10条
		var bizTop10 = data["bizDaily"]||[];
		if($.founded(bizTop10)){
			var dataTmp = [];
			$.each(bizTop10,function(i, item ){
				dataTmp.push([ i, // 系列索引
				     (item["COUNT"] || 0), // 新增数据
				     false, // 新增数据是否从队列头部插入
				     false, // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				     axisData
				   ]);
			});
			bizRatio_line.addData(dataTmp);
		}
		
		// 设备访问前10条
		var deviceTop10 = data["deviceDaily"]||[];
		if($.founded(deviceTop10)){
			var dataTmp = [];
			$.each(deviceTop10,function(i, item ){
				dataTmp.push([ i, // 系列索引
				     (item["COUNT"] || 0), // 新增数据
				     false, // 新增数据是否从队列头部插入
				     false, // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				     axisData
				   ]);
			});
			deviceRatio_line.addData(dataTmp);
		}
		 
		// 操作系统访问前10条
		var osTop10 = data["osDaily"]||[];
		if($.founded(osTop10)){
			var dataTmp = [];
			$.each(osTop10,function(i, item ){
				dataTmp.push([ i, // 系列索引
				     (item["COUNT"] || 0), // 新增数据
				     false, // 新增数据是否从队列头部插入
				     false, // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				     axisData
				   ]);
			});
			osRatio_line.addData(dataTmp);
		}
		
		// 浏览器访问前10条
		var browserTop10 = data["browserDaily"]||[];
		if($.founded(browserTop10)){
			var dataTmp = [];
			$.each(browserTop10,function(i, item ){
				dataTmp.push([ i, // 系列索引
				     (item["COUNT"] || 0), // 新增数据
				     false, // 新增数据是否从队列头部插入
				     false, // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				     axisData
				   ]);
			});
			browserRatio_line.addData(dataTmp);
		}
		
	}
	
	
	function initTopList(data,reset){
		
		//===========================Top10列表==============================
		
		// 应用访问前10条
		if($("#table-app-tbody>tr.trclass").size() == 0 || reset){
			$("#table-app-tbody").empty();
		}
		$.each(data["appTop10"]||[],function(i, item ){
			var appName = item["APPNAME"] || item["APPKEY"] || "Unkonw";
			var html = [];
				html.push('<tr class="trclass" style="height: 24px;">');
					html.push('<th scope="row">'+( i + 1 )+'</th>');
		    		html.push('<td>'+appName+ '</td>');
		    		html.push('<td>'+item["COUNT"]+'</td>');
				html.push('</tr>');
			$("#table-app-tbody").append(html.join(""));
		});

		// 业务访问前10条
		if($("#table-biz-tbody>tr.trclass").size() == 0 || reset){
			$("#table-biz-tbody").empty();
		}
		$.each(data["bizTop10"]||[],function(i, item ){
			var bizName = item["BIZNAME"] || "Unkonw";
			var html = [];
				html.push('<tr class="trclass" style="height: 24px;">');
					html.push('<th scope="row">'+( i + 1 )+'</th>');
		    		html.push('<td>'+ bizName +'</td>');
		    		html.push('<td>'+item["COUNT"]+'</td>');
				html.push('</tr>');
			$("#table-biz-tbody").append(html.join(""));
		});
		
		// 设备访问前10条
		if($("#table-device-tbody>tr.trclass").size() == 0 || reset){
			$("#table-device-tbody").empty();
		}
		$.each(data["deviceTop10"]||[],function(i, item ){
			
			var deviceName = item["DEVICENAME"] || "Unkonw";
			var html = [];
				html.push('<tr class="trclass" style="height: 24px;">');
					html.push('<th scope="row">'+( i + 1 )+'</th>');
		    		html.push('<td>'+deviceName+'</td>');
		    		html.push('<td>'+item["COUNT"]+'</td>');
				html.push('</tr>');
			$("#table-device-tbody").append(html.join(""));
		});
		
		// 操作系统访问前10条
		if($("#table-os-tbody>tr.trclass").size() == 0 || reset){
			$("#table-os-tbody").empty();
		}
		$.each(data["osTop10"]||[],function(i, item ){
			
			var osName = item["OSNAME"] || "Unkonw";
			var html = [];
				html.push('<tr class="trclass" style="height: 24px;">');
					html.push('<th scope="row">'+( i + 1 )+'</th>');
		    		html.push('<td>'+osName+'</td>');
		    		html.push('<td>'+item["OSVER"]+'</td>');
		    		html.push('<td>'+item["OSMFR"]+'</td>');
		    		html.push('<td>'+item["COUNT"]+'</td>');
				html.push('</tr>');
			$("#table-os-tbody").append(html.join(""));
		});
		
		// 浏览器访问前10条
		if($("#table-browser-tbody>tr.trclass").size() == 0 || reset){
			$("#table-browser-tbody").empty();
		}
		$.each(data["browserTop10"]||[],function(i, item ){
			
			var browserName = item["BROWSERNAME"] || "Unkonw";
			var html = [];
				html.push('<tr class="trclass" style="height: 24px;">');
					html.push('<th scope="row">'+( i + 1 )+'</th>');
		    		html.push('<td>'+browserName+'</td>');
		    		html.push('<td>'+item["BROWSERVER"]+'</td>');
		    		html.push('<td>'+item["BROWSERTYPE"]+'</td>');
		    		html.push('<td>'+item["BROWSERKERNEL"]+'</td>');
		    		html.push('<td>'+item["COUNT"]+'</td>');
				html.push('</tr>');
			$("#table-browser-tbody").append(html.join(""));
		});
		
	}
	
	function initRefresh(){
		
		//===========================定时数据刷新==============================
		
		// 创建 SockJS 连接；SockJS 可以接收相对url；    
		var socket = new SockJS(_path +'/apiMetrics');
		var timeSend = null;
		socket.onopen = function () {
			console.log('Info: WebSocket connection opened.');
			if($("#refresh-background").prop("checked")){
				timeSend = setInterval(function() {
					socket.send(JSON.stringify({
						"appKey" 		: $("#query-app-type").val(),
						"bizName" 		: $("#query-biz-type").val()
					}));
					
				}, 60 * 1000);
			}
		};
		socket.onmessage = function (event) {
			var data = JSON.parse(event.data);
			initPieCharts(data);
			initLineCharts(data);
			initTopList(data,true);
			console.log('Received: ' + event.data);
		};
		socket.onclose = function () {
			console.log('Info: WebSocket connection closed.');
			clearInterval(timeSend);
		};

		
		$("#refresh-background").off("change").on("change", function(e){
			if($(this).prop("checked")){
				initRefresh();
			} else{
				socket.close();
			}
		});
	}
	
	
	
	/**查询按钮事件*/
	$('#btn_search').on('click',function(){
		
		$("#table-app-tbody,#table-biz-tbody,#table-os-tbody,#table-device-tbody,#table-browser-tbody").each(function(){
			$(this).empty().append('<td colspan="' + $(this).data("colspan") + '" class="text-center p-10">暂无数据!</td>');
		});
		
		//访问历史数据
		$.post(_path + '/manager/api/analysis/metircs', {
			"appKey" 		: $("#query-app-type").val(),
			"bizName" 		: $("#query-biz-type").val()
		},function(data){
			initPieCharts(data);
			initLineCharts(data);
			initTopList(data);
			initRefresh();
		});
	});
	
	//访问历史数据
	$.post(_path + '/manager/api/analysis/metircs', function(data){
		initPieCharts(data);
		initLineCharts(data);
		initTopList(data);
		initRefresh();
	}, "json");
	
})
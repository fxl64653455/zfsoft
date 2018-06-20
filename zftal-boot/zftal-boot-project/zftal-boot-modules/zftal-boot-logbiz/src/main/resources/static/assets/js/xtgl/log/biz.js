jQuery(function($){

	//时间插件  
    $('#query-time-type').val(moment().subtract(29, 'days').format('YYYY-MM-DD HH:mm:ss') + ' 至 ' + moment().format('YYYY-MM-DD HH:mm:ss'));  
    $('#query-time-type').daterangepicker({  
        buttonClasses 	: 'btn-sm btn-default',  
        applyClass 		: 'btn-sm btn-primary ',
        cancelClass 	: 'btn-sm btn-default', 
        // 语言包设置
        locale 			: {  
            applyLabel 	: '确定',  
            cancelLabel : '取消',  
            fromLabel 	: '起始时间',  
            toLabel 	: '结束时间',  
            customRangeLabel : '自定义',  
            daysOfWeek 	: [ '日', '一', '二', '三', '四', '五', '六' ],  
            monthNames 	: [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ],  
            firstDay 	: 1
        },
        ranges 			: {
            '最近1小时': [moment().subtract(1,'hours'), moment()],
            '今日': [moment().startOf('day'), moment()],
            '昨日': [moment().subtract(1,'days').startOf('day'), moment().subtract(1,'days').endOf('day')],
            '最近7日': [moment().subtract(6,'days'), moment()],
            '最近30日': [moment().subtract(29,'days'), moment()],
            '本月': [moment().startOf("month"),moment().endOf("month")],
            '上个月': [moment().subtract(1,"month").startOf("month"),moment().subtract(1,"month").endOf("month")]
        },
        opens 			: 'right', 							// 日期选择框的弹出位置  
        format 			: 'YYYY-MM-DD HH:mm:ss', 			//控件中from和to 显示的日期格式  
        separator 		: ' 至 ',
        //startDate	: moment().startOf('day'),  
        //endDate		: moment(),  
        //minDate		: '01/01/2012',    					//最小时间  
        maxDate 		: moment().add(1, 'hours'),	//最大时间   
        dateLimit 		: {  
            days : 30  
        }, //起止时间的最大间隔  
        autoUpdateInput			: false,//input框不设置初始值
        alwaysShowCalendars		: true,
        showCustomRangeLabel	: false,
        showDropdowns 			: true,  	// 是否显示日历上方的年份和月份选择框跳转到特定的月份和年份
        showWeekNumbers 		: true,  	// 是否显示第几周
        timePicker 				: true,  	// 是否允许使用时间选择日期，而不仅仅是日期 
        timePickerIncrement 	: 10, 	// 时间的增量，单位为分钟  
        timePicker24Hour 		: true, // 是否使用24小时而不是12小时，删除AM / PM选择
        //timePicker12Hour 		: false, // 是否使用12小时制来显示时间
        timePickerSeconds		: true,  // 是否在timePicker中显示秒数
        autoApply 				: false
    }, function(start, end, label) {//格式化日期显示框  
         //$('#query-time-type').val(start.format('YYYY-MM-DD HH:mm:ss') + ' 至 ' + end.format('YYYY-MM-DD HH:mm:ss'));
         $('#beginTime').val(start.format('YYYY-MM-DD HH:mm:ss'));
		 $('#endTime').val(end.format('YYYY-MM-DD HH:mm:ss'));
    });  
    $('#query-time-type').on('apply.daterangepicker', function(ev, picker) {
		$(this).val(picker.startDate.format('YYYY-MM-DD HH:mm:ss') + ' 至 ' + picker.endDate.format('YYYY-MM-DD HH:mm:ss'));
	}).on('cancel.daterangepicker', function(ev, picker) {
		$(this).val('');
	});
    
    $('#tabGrid').loadGrid({
		 url		: _path + '/xtgl/log/list',
		 uniqueId	: 'logId',
		 classes	: 'table table-striped table-hover table-condensed table-api text-center',
		 showToggle:false,showRefresh:false,showColumns:false,pageList : [5,10,15],escape : true,
		 columns: [{
			 title : '序号',formatter : function(value,row,index){return (index + 1);}
		 },{
			 field: 'logId', title: '日志ID',visible:false
		 },{
			 field: 'logMsg', title: '操作名称',formatter : function(value,row,index){return value.czms;}
		 },{
			 field: 'logMsg', title: '操作日期',formatter : function(value,row,index){return value.czrq;}
		 },{
			 field: 'logMsg',title: 'host',formatter : function(value,row,index){return value.host;}
		 },{
			 field: 'logMsg', title: '操作人',formatter : function(value,row,index){return value.czr;}
		 },{
			 field: 'logMsg', title: '操作模块',formatter : function(value,row,index){return value.czmk;}
		 },{
			 field: 'logMsg', title: '业务名称',formatter : function(value,row,index){return value.ywmc;}
		 },{
			 field: 'logMsg', title: 'remote',formatter : function(value,row,index){return value.remote;}
		 },{
			 field: 'logMsg', title: '操作类型',formatter : function(value,row,index){return value.czlx;}
		 }],
	    searchParams:function(){
	    	return {
	    		"czms" : $("#query-log-czmc").val(),
	    		"czr" 		: $("#query-log-czr").val(),
				"mkmc" 		: $("#query-log-czmk").val(),
				"ywmc" 	: $("#query-log-ywmc").val(),
				"beginTime" 	: $("#beginTime").val(),
				"endTime" 		: $("#endTime").val()
	    	};
	    }
	});
	
	/**查询按钮事件*/
	$('#btn_search').on('click',function(){
		$('#tabGrid').refreshGrid();
	});
	
	/**查询框回车事件*/
	$('#query-log-czmc').on('keypress',function(e){
		if(e.keyCode == 13){
			$('#tabGrid').refreshGrid();
		}
	});
	$('#query-log-czr').on('keypress',function(e){
		if(e.keyCode == 13){
			$('#tabGrid').refreshGrid();
		}
	});
	$('#query-log-czmk').on('keypress',function(e){
		if(e.keyCode == 13){
			$('#tabGrid').refreshGrid();
		}
	});
	$('#query-log-ywmc').on('keypress',function(e){
		if(e.keyCode == 13){
			$('#tabGrid').refreshGrid();
		}
	});
});
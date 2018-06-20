<!doctype html>
<html>
<head>
	[#include "/head/zftal-ui-daterangepicker.ftl" /]
</head>
<body>
	<div id="page-content">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-primary text-left" style="min-height:600px;">
					<div class="panel-body ">
                        <div class="form-horizontal col-md-12">
	                	<div class="form-group col-sm-3">
							<label class="col-sm-4 control-label" for="query-app-type">应用名称:</label>
                            <div class="col-sm-8">
                                <select id="query-app-type" name="app" class="form-control form-select chosen-select">
						    	  	<option value="">--请选择--</option>
						    	  	[#list appList as app] 
								    <option value="${app.APPKEY}">${app.APPNAME}</option>
								    [/#list]
						    	</select>
						    	<script type="text/javascript">
				                	$("#query-app-type").trigger("chosen");
				                </script>
                            </div>
	                    </div>
	                    <div class="form-group col-sm-3">
							<label class="col-sm-4 control-label" for="query-app-type">业务名称:</label>
                            <div class="col-sm-8">
                                <select id="query-biz-type" name="biz" class="form-control form-select chosen-select">
						    	  	<option value="">--请选择--</option>
						    	  	[#list bizList as biz] 
								    <option value="${biz.BIZID}">${biz.BIZNAME}</option>
								    [/#list]
						    	</select>
						    	<script type="text/javascript">
				                	$("#query-biz-type").trigger("chosen");
				                </script>
                            </div>
	                    </div>
	                    <div class="form-group col-sm-3">
							<label class="col-sm-4 control-label" for="query-app-type">终端设备:</label>
                            <div class="col-sm-8">
                                <select id="query-device-type" name="device" class="form-control form-select chosen-select">
						    	  	<option value="">--请选择--</option>
						    	  	[#list deviceList as device] 
								    <option value="${device.DEVICENAME}">${device.DEVICENAME}</option>
								    [/#list]
						    	</select>
						    	<script type="text/javascript">
				                	$("#query-device-type").trigger("chosen");
				                </script>
                            </div>
	                    </div>
	                    <div class="form-group col-sm-3">
							<label class="col-sm-4 control-label" for="query-app-type">操作系统:</label>
                            <div class="col-sm-8">
                                <select id="query-os-type" name="os" class="form-control form-select chosen-select">
						    	  	<option value="">--请选择--</option>
						    	  	[#list osList as os] 
								    <option value="${os.OSNAME}">${os.OSNAME}</option>
								    [/#list]
						    	</select>
						    	<script type="text/javascript">
				                	$("#query-os-type").trigger("chosen");
				                </script>
                            </div>
	                    </div>
	                    <div class="form-group col-sm-3">
							<label class="col-sm-4 control-label" for="query-browser-type">浏览器:</label>
                            <div class="col-sm-8">
                                <select id="query-browser-type" name="browser" class="form-control form-select chosen-select">
						    	  	<option value="">--请选择--</option>
						    	  	[#list browserList as browser] 
								    <option value="${browser.BROWSERNAME}">${browser.BROWSERNAME}</option>
								    [/#list]
						    	</select>
						    	<script type="text/javascript">
				                	$("#query-browser-type").trigger("chosen");
				                </script>
                            </div>
	                    </div>
	                    <div class="form-group col-sm-6">
	                    	<label class="col-sm-2 control-label" for="query-browser-type">访问时间:</label>
							<div class="col-sm-10">
								<div class="input-group" style=" margin-left: -5px;">
								    <input type="text" class="form-control date-picker" id="query-time-type" readonly="readonly" value="" placeholder="YYYY-MM-DD HH:mm:ss - YYYY-MM-DD HH:mm:ss" />
								    <span class="input-group-addon">
								       <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
								    </span>
								    <input type="hidden" name="beginTime" id="beginTime" value="" />
								    <input type="hidden" name="endTime" id="endTime" value="" />
								</div>
							    <script type="text/javascript">
								jQuery(function($) {
									
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
								});
								</script>
							</div>
						</div>
	                    <div class="form-group col-sm-3">
                             <div id="btn_search" class="col-sm-2 btn zf-btn btn-primary btn-light-primary">查询</div>
                        </div>
	                </div>
                    </div>
					<div class="panel-body demo-nifty-btn">
						<!-- table-start  -->
						<table id="tabGrid"></table>
						<!-- table-end  -->
					</div>
				</div>
			</div>
		</div>
	</div>
	
	[#include "/head/zftal-ui-bsTable.ftl" /]
	<script type="text/javascript" src="${request.contextPath}/assets/js/api/metric/index.js?ver=${versionUtil()}"></script>
</body>

</html>
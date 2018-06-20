[#assign q=JspTaglibs["/zftal-search-tags"] /]
<!doctype html>
<html>
	<head>
	</head>
	<body>
		<!-- tool bar-start  -->
		[#include "/buttons.ftl" /]
		[@q.panel theme="default"] 
		     [@q.input list="#(dm:代码,mc:名称)"/] 
		     [@q.multi name="lx" text="类型" list="lxdmList" listKey="lxdm" listValue="lxmc"  /]
		     [@q.radio name="xtjb" text="级别" list="#(yw:业务,xt:系统)"  /]
		     [@q.radio name="zt" text="启用状态" list="#(1:启用,0:停用)" /]
		[/@q.panel]
		<!-- table-start  -->
		<table id="tabGrid"></table>
		<!-- <div id="pager"></div> -->
		<!-- table-end  -->

		[#include "/head/zftal-ui-bsTable.ftl" /]
		<script type="text/javascript" src="${request.contextPath}/assets/js/xtgl/jcsj.js"></script>
	</body>
</html>

[#assign q=JspTaglibs["/zftal-search-tags"] /]
<!doctype html>
<html>
	<head>
	
	</head>
	<body>
		<!-- tool bar-start  -->
		[#include "/buttons.ftl" /]
		<!-- tool bar-end  -->
		[@q.panel theme="default"] 
		     [@q.input list="#(jsmc:角色名称,jssm:角色说明)"/] 
		     [@q.radio name="jszt" text="启用状态" list="#(1:启用,0:停用)" listKey="key" listValue="value" /]
		[/@q.panel]
		<!-- table-start  -->
			<table id="tabGrid"></table>
			<!--<div id="pager"></div>  -->
		<!-- table-end  -->
		
		[#include "/head/zftal-ui-bsTable.ftl" /]
		<script type="text/javascript" src="${request.contextPath}/assets/js/authz/role/jsgl.js?ver=${messageUtil("zftal.jsVersion")}"></script>
	</body>
</html>
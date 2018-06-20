[#assign q=JspTaglibs["/zftal-search-tags"] /]
<!DOCTYPE html>
<html>
	<head>
	</head>
	<body>
		<!-- tool bar-start  -->
		[#include "/buttons.ftl" /]
		<!-- tool bar-end  -->
		<!--查询条件  开始 -->
		[@q.panel theme="default"] 
		     [@q.input list="#(yhm:用户名,xm:姓名,sjhm:联系电话,dzyx:电子邮箱)"/] 
		     [@q.radio name="yhzt" text="用户状态" list="#(0:停用,1:正常,2:锁定)" listKey="key" listValue="value" /]
		[/@q.panel]
		<!--查询条件  结束  -->
		
		<!--JQGrid 开始 -->
		<div class="formbox">
			<table id="tabGrid"></table>
			<!--<div id="pager"></div>-->
		</div>
		<!--JQGrid 结束  -->
		[#include "/head/zftal-ui-bsTable.ftl" /]
		[#include "/head/zftal-ui-strength.ftl" /]
		<script type="text/javascript" src="${request.contextPath}/assets/js/authz/user/yhgl.js?ver=${messageUtil("zftal.jsVersion")}"></script>
	</body>
</html>

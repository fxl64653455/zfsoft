[#assign q=JspTaglibs["/zftal-search-tags"] /]
<!doctype html>
<html>

	<head>
		[#include "/head/zftal-ui-laydate.ftl" /]
	</head>

	<body>
		<!-- tool bar-start  -->
		[#include "/comm/buttons.ftl" /]
		<!-- tool bar-end  -->
		[@q.panel theme="default"] 
		     [@q.input list="#(xwbt:新闻标题,xm:发布人)"/] 
		     [@q.radio name="sffb" text="是否发布" list="sfList" listKey="key" listValue="value" /]
		     [@q.radio name="sfzd" text="是否置顶" list="sfList" listKey="key" listValue="value" /]
		     [@q.date name="fbsj" text="发布时间" format="YYYY-MM-DD hh:mm:ss"/]
		[/@q.panel]
		<!-- table-start  -->
		<table id="tabGrid"></table>
		<!-- <div id="pager"></div> -->
		<!-- table-end  -->
		[#include "/head/zftal-ui-bsTable.ftl" /]
		<script type="text/javascript" src="${base}/js/comp/xtgl/xwgl.js?ver=${messageUtil(" zftal.jsVersion ")}"></script>
		[#include "/head/zftal-ui-wdate.ftl" /]
		[#include "/head/zftal-ui-kindeditor.ftl" /]
	</body>

</html>
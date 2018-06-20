<!doctype html>
<html lang="zh-CN">
<head>  
    <title></title>
	<!-- 默认的全局引用 -->
	[#include "/head/zftal-ui-meta.ftl" /]
	[#include "/head/zftal-ui-head.ftl" /]
	[#include "/head/zftal-ui-validation.ftl" /]
	<link href="${request.contextPath}/assets/css/survey.css?ver=${versionUtil()}" rel="stylesheet" type="text/css" />
	<sitemesh:write property='head'/>
</head> 
<body>
 
	<div style="width: 100%; padding: 0px; margin: 0px;" id="bodyContainer">
		<div class="container-fluid container-func" style="width:100%;padding: 0px; margin: 0px;padding-right: 15px;">
	   		<!-- page content start -->
	    	<sitemesh:write property='body'/>
	   		<!-- page content end -->  
	   </div>
	</div>
	<!-- 自定义函数 -->
	<script type="text/javascript" src="${request.contextPath}/assets/js/page.js?ver=${versionUtil()}"></script>
</body>  
</html>
<script type="text/javascript">
	//全局变量
	var _path = "${request.contextPath}";
</script>	

[#include "/head/zftal-ui-jquery.ftl" /]
[#include "/head/zftal-ui-bs.ftl" /]
[#include "/head/zftal-ui-tabs.ftl" /]
[#include "/head/zftal-ui-v5.ftl" /]

<!--基于Nifty样式库的新版界面前端依赖-->
<script src="${request.contextPath}/webjars/zftal-ui-v5/plugins/nifty/js/nifty.js?ver=${versionUtil()}"></script>
<link href="${request.contextPath}/webjars/zftal-ui-v5/plugins/nifty/css/nifty.min.css?ver=${versionUtil()}" rel="stylesheet" type="text/css"/>
<!--皮肤+字体-->
<link href="${request.contextPath}/webjars/zftal-ui-v5/css/theme/zftal-ui-nifty-icons.css?ver=${versionUtil()}" rel="stylesheet" type="text/css"/>
<link href="${request.contextPath}/webjars/zftal-ui-v5/css/theme/theme-ocean.min.css?ver=${versionUtil()}" rel="stylesheet" type="text/css"/>
<link href="${request.contextPath}/webjars/zftal-ui-v5/css/zftal-ui-base.css?ver=${versionUtil()}" rel="stylesheet" type="text/css"/>
<link href="${request.contextPath}/webjars/zftal-ui-v5/css/zftal-ui-index.css?ver=${versionUtil()}" rel="stylesheet" type="text/css"/>
<!-- 应用系统自定义函数 -->
<script type="text/javascript" src="${request.contextPath}/assets/js/zftal-ui-index.js?ver=${versionUtil()}"></script>
<script type="text/javascript" src="${request.contextPath}/assets/js/zftal-ui-app.js?ver=${versionUtil()}"></script>
<!-- 应用系统自定义样式 -->
<link href="${request.contextPath}/assets/css/zftal-ui-app.css?ver=${versionUtil()}" rel="stylesheet" type="text/css" />
	
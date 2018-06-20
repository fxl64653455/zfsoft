<script type="text/javascript">
	var _path = "${request.contextPath}";
</script>	

[#include "/head/zftal-ui-jquery.ftl" /]
[#include "/head/zftal-ui-bs.ftl" /]
[#include "/head/zftal-ui-v5.ftl" /]
[#include "/head/zftal-ui-bootbox.ftl" /]
[#include "/head/zftal-ui-chosen.ftl" /]
[#include "/head/zftal-ui-ie8-fix.ftl" /]

<link href="${request.contextPath}/webjars/zftal-ui-v5/plugins/nifty/css/nifty.min.css?ver=${versionUtil()}" rel="stylesheet">
<!--蓝色皮肤css-->
<link id="theme" href="${request.contextPath}/webjars/zftal-ui-v5/css/theme/zftal-ui-nifty-icons.css?ver=${versionUtil()}" rel="stylesheet">
<link id="theme" href="${request.contextPath}/webjars/zftal-ui-v5/css/theme/theme-ocean.min.css?ver=${versionUtil()}" rel="stylesheet">
<!--zftal-ui-base基础css-->
<link href="${request.contextPath}/webjars/zftal-ui-v5/css/zftal-ui-base.css?ver=${versionUtil()}" rel="stylesheet">
<!-- 应用系统自定义样式 -->
<link rel="stylesheet" type="text/css" href="${request.contextPath}/assets/css/zftal-ui-app.css?ver=${versionUtil()}" />
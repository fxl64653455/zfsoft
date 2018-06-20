<!doctype html>
<html>
<head>
</head>
<body>
	<div class="survey">
		<div class="surver-header">
			<div class="survey-search">
				<input type="text" id="input_search" class="survey-input" />
				<input type="button" id="btn_search" value="" class="survey-submit"/>
			</div>
		</div>
		
		<div id="rows-content"></div>
		
	</div>
	
	<div id="page-content">
		
		<div id="page-bar" class="text-center">
			<ul class="pagination pagination-primary">
	            <li id="page-prev" class="disabled"><a href="javascript:prev()"><span class="glyphicon glyphicon-menu-left"></span></a></li>
	            <li id="page-less" class="hidden"><a href="javascript:less()"><span>...</span></a></li>
	            <li id="page-1" class="active"><a href="javascript:current(1)">1</a></li>
	            <li id="page-2"><a href="javascript:current(2)">2</a></li>
	            <li id="page-3"><a href="javascript:current(3)">3</a></li>
	            <li id="page-4"><a href="javascript:current(4)">4</a></li>
	            <li id="page-5"><a href="javascript:current(5)">5</a></li>
	            <li id="page-6"><a href="javascript:current(6)">6</a></li>
	            <li id="page-more"><a href="javascript:more()"><span>...</span></a></li>
	            <li id="page-next"><a href="javascript:next()"><span class="glyphicon glyphicon-menu-right"></span></a></li>
	        </ul>
		</div>
		
	</div>
	
	<link rel="stylesheet" type="text/css" href="${request.contextPath}/assets/css/survey.css"/>
	<script type="text/javascript" src="${request.contextPath}/assets/js/api/api/list.js?ver=${versionUtil()}"></script>
</body>
</html>
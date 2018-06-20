/**分页相关*/
var pageNo = 1;var lastPage = 6;var limit = 10;var offset = 0;var pageCount = 6;

/**根据返回数据创建列表*/
function createRows(rows){
	for(var i = 0; i < rows.length; i ++){
		var row = '<div class="survey-box"><img class="survey-log img-circle" src="'+ _path + '/manager/api/index/icon/'+rows[i].id+'"/>'+
		'<div class="survey-box-text"><div class="survey-box-text1"><h4>'+rows[i].apiName+'</h4>'+
		'<span class="api-tag">'+rows[i].type+'</span><span class="api-tag api-tag-dark-blue">'+rows[i].ver+'</span>';
		if(rows[i].type == 'Rest'){
			row += '<span class="api-tag api-tag-purple">'+rows[i].method+'</span>';
		}
		row += '</div><p>'+rows[i].apiInfo+'</p>'+
		'<div class="survey-visit"><span class="survey-num">今日访问量：'+rows[i].dayValue+'</span>'+
		'<span>最近一月访问量：'+rows[i].monthValue+'</span></div></div><a href="'+ _path + '/manager/api/index/detail/'+rows[i].id+'" class="survey-btn" target="_blank">查看详情</a></div>';
		$('#rows-content').append(row);
	}
}

/**点当前页*/
function current(n){
	if(pageCount <= 6){
		for(var i = 6; i >= 1; i --){
			$('#page-' + i).remove();
			$('#page-less').after('<li id="page-'+i+'"><a href="javascript:current('+i+')">'+i+'</a></li>');
		}
	}
	pageNo = n;
	$('ul .active').removeClass('active');
	$('#page-' + n).addClass('active');
	offset = n * limit - limit;
	$('#btn_search').click();
}

/**点上一页*/
function prev(){
	var n = parseInt(pageNo) - 1;
	if(n >= 1){
		if(n < lastPage - 5){
			less();
		}else{
			current(n);
		}
	}
}

/**点下一页*/
function next(){
	var n = parseInt(pageNo) + 1;
	if(n <= pageCount){
		if(n > lastPage){
			more();
		}else{
			current(n);
		}
	}
}

/**前六页*/
function less(){
	for(var i = lastPage; i >= lastPage - 5; i --){
		$('#page-' + i).remove();
	}
	lastPage = lastPage - 6;
	for(var i = lastPage; i >= lastPage - 5; i --){
		$('#page-less').after('<li id="page-'+i+'"><a href="javascript:current('+i+')">'+i+'</a></li>');
	}
	if(lastPage <= 6){
		$('#page-less').addClass('hidden');
	}
	current(lastPage);
}

/**后六页*/
function more(){
	for(var i = lastPage; i >= lastPage - 5; i --){
		$('#page-' + i).remove();
	}
	lastPage = lastPage + 6;
	for(var i = lastPage - 5; i <= lastPage; i ++){
		$('#page-more').before('<li id="page-'+i+'"><a href="javascript:current('+i+')">'+i+'</a></li>');
	}
	if(lastPage > 6){
		$('#page-less').removeClass('hidden');
	}
	current(lastPage - 5);
	resetPage();
}

function resetPage(){
	for(var i = lastPage; i > pageCount; i --){
		$('#page-' + i).remove();
	}
	if(pageCount > 1){
		$('#page-content').removeClass('hidden');
	}else{
		$('#page-content').addClass('hidden');
	}
	if(pageNo == pageCount){
		$('#page-next').addClass('disabled');
	}else{
		$('#page-next').removeClass('disabled');
	}
	if(pageNo <= 1){
		$('#page-prev').addClass('disabled');
	}else{
		$('#page-prev').removeClass('disabled');
	}
	if(pageCount > lastPage){
		$('#page-more').removeClass('hidden');
	}else{
		$('#page-more').addClass('hidden');
	}
}

jQuery(function($){
	
	/**查询按钮事件*/
	$('#btn_search').on('click',function(){
		$.ajax({
			type : 'POST',
			url : _path + '/manager/api/index/search',
			data : {
				apiName : $('#input_search').val(),limit : limit,offset : offset
			},
			success : function(d) {
				if(d.items.length > 0){
					$('#rows-content').html('');
					createRows(d.items);
					var totalCount = parseInt(d.totalCount);
					pageCount = Math.ceil(totalCount / limit);
					resetPage();
				}else{
					$('#page-content').addClass('hidden');
					$('#rows-content').html('<div class="text-center"><h1>没有返回数据!<h1></div>');
				}
			}
		},'json');
	});
	
	/**查询框回车事件*/
	$('#input_search').on('keypress',function(e){
		if(e.keyCode == 13){
//			$('#btn_search').click();
			current(1);
		}
	});

	/**查询*/
//	$('#btn_search').click();
	current(1);
	
});
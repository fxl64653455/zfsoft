jQuery(function($){
	  
	 $.validator.addMethod("checkIcon",function(value,element,params){
//		var message = $(element).data("message");
//		if("0" == message){
//			$(".dz-text").css("color","#a94442").html("点击选择图片；格式如：*.gif;*.png;*.jpg;*.jpeg;*.bmp");
//			return false;
//		}
//		if($.founded(message)){
//			$(".dz-text").css("color","#a94442").html(message);
//			return false;
//		}
		return true;
	},"");
	 
	$.validator.addMethod("namespace",function(value,element,params){
		if($("#deploy-type").val() == "WS" && !$.founded(value)){
			return false;
		}
		return true;
	},"您已选择发布成Ws接口，请指定命名空间.");
	
	$.validator.addMethod("methodCheck",function(value,element,params){
		if($("#deploy-type").val() != "WS" && !$.founded(value)){
			return false;
		}
		return true;
	},"您已选择发布成Rest接口，请指定接口请求类型.");
	
	$.validator.addMethod("pageableCheck",function(value,element,params){
		if($("#deploy-type").val() != "WS" && !$.founded(value)){
			return false;
		}
		return true;
	},"您已选择发布成Rest接口，请指定接口数据是否分页.");
	
	$.validator.addMethod("addr",function(value,element,params){
		var stat = false;
		$.ajaxSetup({async:false});
		$.post(_path + "/manager/api/deploy/addr/check" ,{ "type": $("#deploy-type").val(), "addr": $("#deploy-addr").val() },function(value){
			stat = Boolean(value);	
		},'json');
		$.ajaxSetup({async:true});
		return stat;
	},"接口地址已被占用,请重新输入");
	
	$.validator.addMethod("tags",function(value,element,params){
		if($.founded(value)){
			return value.split(",").length <= Number(params || "2").toFixed(0);
		}
		return true;
	}, $.validator.format("接口标签超出{0}个限制."));
	
	$('#deploy-namespace').disabled();
	$("#deploy-type").change(function(e,f){
		if(f.selected == 'Rest'){
			$('#deploy-namespace').disabled();
			$('#deploy-method').enabled().trigger("chosen:updated");
		}else if(f.selected == 'WS'){
			$('#deploy-namespace').enabled();
			$('#deploy-method').disabled().trigger("chosen:updated");
		}
		var prefix = $(this).find("option:selected").data("prefix");
		if($.founded(prefix)){
			$("#deploy-addr-addon").html(prefix);
		}
    });
	
	$("#deploy-tags").tagsinput({
		trimValue: true,
		maxTags  : 12, 	//允许输入多少个标签
		maxChars : 20	//每个标签的最大字符，如果不设置或者为0，就是无限大
	});
	$("#deploy-tags").on('itemAdded', function(event) {
		// event.item: contains the item
		$("#deploy-tags").valid()
	});
	

	function getObjectURL(file){    
	   var url=null;    
	   if(window.createObjectURL!=undefined){ // basic    
	       url=window.createObjectURL(file);   
	   }else if(window.URL!=undefined){ // mozilla(firefox)    
	       url=window.URL.createObjectURL(file); 
	   } else if(window.webkitURL!=undefined){ // webkit or chrome    
	       url=window.webkitURL.createObjectURL(file);
	   }
	   return url;
	}
	
	$("#deploy-iconbyte").change(function(e) {
		var $this = this;
		var files = $(e.target).getFiles() || e.dataTransfer.files;
		$.filehandle(files,{
			// 可选择文件类型，默认全部类型；多个用;分割，如：*.gif;*.png;*.jpg;*.jpeg;*.bmp
			fileType 		: '*.gif;*.png;*.jpg;*.jpeg;*.bmp;*.JPG;*.GIF;*.PNG;*.BMP;*.JPEG',
			// 单个文件最大:默认10M
			maxSize 		: "2MB",
			// 所有文件总共大小:默认100MB；换算关系： 1GB=1024MB; 1MB=1024KB; 1KB=1024BB（字节）; 1B = 8Byte
			maxTotal 		: "10MB",
			// 文件总个数超出总限制回调
			handleOverMaxCount	: function(message){
				$("#deploy-iconbyte").attr("data-message", message);
			},
			// 单个文件不匹配要求文件类型回调
			handleInvalidType	: function(file,message){
				$("#deploy-iconbyte").attr("data-message", message);
			},
			// 单个文件大小超出限制回调：该函数需要返回true|false，决定是否继续之后的逻辑
			handleOverSize 		: function(file,message){
				$("#deploy-iconbyte").attr("data-message", message);
			},
			// 文件大小超出总限制回调
			handleOverTotal 	: function(message){
				$("#deploy-iconbyte").attr("data-message", message);
			},
			// 每个文件的回调函数
			handleFile			: function(file){
				$("#deploy-iconbyte").attr("data-message", "");
				var objUrl = getObjectURL(file);
				if(objUrl){
	              $("#iconbyte").attr({"src": objUrl});
	              $(".dz-text").css("color","").html("点击选择图片；格式如：*.gif;*.png;*.jpg;*.jpeg;*.bmp");
	            }
			}
		});
/*		
	    var size=this.files[0].size;
	    $("#files").val(size);
		var imgname = file.name;
		var imgsuffix = imgname.substr(imgname.lastIndexOf(".")+1);
		var suffixarr = ".jpg .gif .png .bmp .jpeg .JPG .GIF .PNG .BMP .JPEG";
		if(suffixarr.indexOf(imgsuffix)<0){
			$(".dz-text").css("color","#a94442").html("图片格式不正确，请上传png/jpg/jpeg/bmp/gif格式的图片");
		}else{
			
		}
*/	});
	
	$("#iconbyte").on('click', function(event) {
		 $("#deploy-iconbyte").click();
	});
	
	
	$('#ajaxForm').validateForm({
		//显示提交进度的状态条元素
		progressElement: "#bootboxStatus"
	});
	
	
});

 	
	
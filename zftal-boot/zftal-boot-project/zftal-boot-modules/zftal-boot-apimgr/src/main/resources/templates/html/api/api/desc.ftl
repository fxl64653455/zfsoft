[#if apiDeploy.desc != null && apiDeploy.desc != '']
	[#assign desc=apiDeploy.desc?eval /]
[/#if]

<div class="api-detail">
	<div class="white-shadow api-apply">
		<div class="api-apply-top">
			<h4>${apiDeploy.apiName}</h4>
			<span class="color-span">API数据</span>
			<span class="color-span cyan-span">移动通讯</span>
			<span class="color-span green-span">自主研发</span>
			<a href="javascript:apply()" class="btn btn-primary btn-apply">接口申请</a>
		</div>
		<div class="api-apply-bot">
			<p>
				专注短信行业8年运营与技术积累，自主研发系统，后台稳健。结合三大运营商优质通道，支持自定义签名及内容。5秒极速送达。支持最新移动、电信、联通虚拟号段。专注短信行业8年运营与技术积累，自主研发系统，后台稳健。结合三大运营商优质通道
				，支持自定义签名及内容。5秒极速送达。支持最新移动、电信、联通虚拟号段。专注短信行业8年运营与技术积累，自主研发系统，后台稳健。结合三大运营商优质通道，支持自定义签名及内容。5秒极速送达。支持最新移动、电信、联通虚拟号段。
			</p>
		</div>
	</div>
	<div class="white-shadow api-tab-box">
		<div class="tab-base tab-base-top">
				
			<ul class="nav nav-tabs nav-tab-api">
				<li>
					<a data-toggle="tab" href="#demo-lft-tab-1">API详情</a>
				</li>
				<li>
					<a data-toggle="tab" href="#demo-lft-tab-2">详细介绍</a>
				</li>
				<li>
					<a data-toggle="tab" href="#demo-lft-tab-3">错误码参照</a>
				</li>
				<li class="active">
					<a data-toggle="tab" href="#demo-lft-tab-4">SDK   beta</a>
				</li>
			</ul>

			<div class="tab-content">
				<div id="demo-lft-tab-1" class="tab-pane fade">
					API详情
				</div>
				<div id="demo-lft-tab-2" class="tab-pane fade">
					详细介绍
				</div>
				<div id="demo-lft-tab-3" class="tab-pane fade">
					错误码参照
				</div>
				<div id="demo-lft-tab-4" class="tab-pane fade active in">
					<div class="api-tab-detail">
						<div class="span-box">
							<span class="color-span-m">java</span>
							<span class="color-span-m">android</span>
							<span class="color-span-m">PHP</span>
							<span class="color-span-m">C#</span>
							<span class="color-span-m">python</span>
						</div>
						<div class="api-tab-text">
							<h6>描述</h6>
							<p>京东万象数据服务商城封装提供了多种语言版本SDK，使用SDK可以便捷地调取所需数据服务，快速开发实现业务功能。<br />
							请点击下载Beta版SDK，如有任何使用问题请联系我们。<br />
							注意：该版本SDK暂不支持含有Image参数的数据调用。</p>
							<h6>调用示例</h6>
							<p class="api-code">import wxlink.*;<br />
								import api.DefaultApi;<br />
								import java.math.BigDecimal;<br />
								import java.io.File;<br />
								import java.util.*;<br />
								public class DefaultApiExample {<br />
								 
								    　public static void main(String[] args) {<br />
								 
								        　　DefaultApi apiInstance = new DefaultApi();<br />
								        
								       　　 String mobile = "13568813957"; // String | 发送目标号码，多个号码之间用半角英文标点逗号隔开<br />
								        
								        　　String content = "【成都创信信息】验证码为：5873,欢迎注册平台！"; // String | 短信内容，需要UTF-8编码<br />
								        
								        　　String appkey = "appkey_example"; // String | 万象平台提供的appkey<br />
								        
								        　try {<br />
								           　　 String result = apiInstance.dxjk(mobile,content,appkey);<br />
								           　　 System.out.println(result);<br />
								        　} catch (ApiException e) {<br />
								            　　System.err.println("Exception when calling DefaultApi#dxjk");<br />
								            　　e.printStackTrace();<br />
								        　}<br />
								    }<br />
								}<br />
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</div>

<link rel="stylesheet" type="text/css" href="${request.contextPath}/assets/css/survey.css"/>
<script type="text/javascript">
	function apply(){
		$.showDialog(_path + '/manager/api/api/apply?deployId=${apiDeploy.id}','选择应用',$.extend({},addConfig,{
			customScrollbar : false,btnlock : true
		}));
	}
</script>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
	[#include "/head/zftal-ui-meta.ftl" /]
	<!--首页专用的额外引用-->
	[#include "/head/zftal-ui-index.ftl" /]
</head>
<body class="body-api left-nav [#if web.menuType == 'top']nav-type-1[#else]nav-type-2[/#if]  "> 
    <div id="container" class="effect aside-float aside-bright mainnav-lg mainnav-fixed navbar-fixed">

        <header id="navbar">
            <div id="navbar-container" class="boxed">
                <!--LOGO位置-->
                <div class="navbar-header">
                    <a href="index.html" class="navbar-brand">
                        <img src="${request.contextPath}/assets/images/logo.png" alt="ZF Logo" class="brand-icon">
                        <div class="brand-title">
                            <span class="brand-text">${home.title?default("管理平台")} </span>
                        </div>
                    </a>
                </div>
                <!--顶部导航-->
                <div class="navbar-content clearfix">
                    <ul class="nav navbar-top-links pull-left">

                        <li class="tgl-menu-btn">
                            <a class="mainnav-toggle" href="javascript:void(0);">
                                <i class="demo-pli-view-list"></i>
                            </a>
                        </li>
						<!--
                        <li class="dropdown">
                            <a href="javascript:void(0);" data-toggle="dropdown" class="dropdown-toggle">
                                <i class="demo-pli-bell"></i>
                                <span class="badge badge-header badge-danger"></span>
                            </a>


                            <div class="dropdown-menu dropdown-menu-md">
                                <div class="pad-all bord-btm">
                                    <p class="text-semibold text-main mar-no">你有9条提醒</p>
                                </div>
                                <div class="nano scrollable">
                                    <div class="nano-content">
                                        <ul class="head-list">


                                            <li>
                                                <a href="javascript:void(0);">
                                                    <div class="clearfix">
                                                        <p class="pull-left">总工作进度</p>
                                                        <p class="pull-right">70%</p>
                                                    </div>
                                                    <div class="progress progress-sm">
                                                        <div style="width: 70%;" class="progress-bar">
                                                            <span class="sr-only">70% Complete</span>
                                                        </div>
                                                    </div>
                                                </a>
                                            </li>


                                            <li>
                                                <a href="javascript:void(0);">
                                                    <div class="clearfix">
                                                        <p class="pull-left">完成任务量</p>
                                                        <p class="pull-right">10%</p>
                                                    </div>
                                                    <div class="progress progress-sm">
                                                        <div style="width: 10%;" class="progress-bar progress-bar-warning">
                                                            <span class="sr-only">10% Complete</span>
                                                        </div>
                                                    </div>
                                                </a>
                                            </li>

                                            <li>
                                                <a class="media" href="javascript:void(0);">
                                            <span class="badge badge-success pull-right">90%</span>
                                                    <div class="media-left">
                                                        <i class="demo-pli-data-settings icon-2x"></i>
                                                    </div>
                                                    <div class="media-body">
                                                        <div class="text-nowrap">本人磁盘量</div>
                                                        <small class="text-muted">50 分钟前</small>
                                                    </div>
                                                </a>
                                            </li>


                                            <li>
                                                <a class="media" href="javascript:void(0);">
                                                    <div class="media-left">
                                                        <i class="demo-pli-file-edit icon-2x"></i>
                                                    </div>
                                                    <div class="media-body">
                                                        <div class="text-nowrap">请假申请</div>
                                                        <small class="text-muted">8 小时前</small>
                                                    </div>
                                                </a>
                                            </li>

                                            <li>
                                                <a class="media" href="javascript:void(0);">
                                            <span class="label label-danger pull-right">New</span>
                                                    <div class="media-left">
                                                        <i class="demo-pli-speech-bubble-7 icon-2x"></i>
                                                    </div>
                                                    <div class="media-body">
                                                        <div class="text-nowrap">会议</div>
                                                        <small class="text-muted">9 小时前</small>
                                                    </div>
                                                </a>
                                            </li>


                                            <li>
                                                <a class="media" href="javascript:void(0);">
                                                    <div class="media-left">
                                                        <i class="demo-pli-add-user-plus-star icon-2x"></i>
                                                    </div>
                                                    <div class="media-body">
                                                        <div class="text-nowrap">新增任务</div>
                                                        <small class="text-muted">1 天前</small>
                                                    </div>
                                                </a>
                                            </li>

                                            <li class="bg-gray">
                                                <a class="media" href="javascript:void(0);">
                                                    <div class="media-left">
                                                        <img class="img-circle img-sm" alt="Profile Picture" src="${request.contextPath}/assets/images/profile-photos/9.png">
                                                    </div>
                                                    <div class="media-body">
                                                        <div class="text-nowrap">收到部门领导信息</div>
                                                        <small class="text-muted">30 分钟前</small>
                                                    </div>
                                                </a>
                                            </li>


                                            <li class="bg-gray">
                                                <a class="media" href="javascript:void(0);">
                                                    <div class="media-left">
                                                        <img class="img-circle img-sm" alt="Profile Picture" src="${request.contextPath}/assets/images/profile-photos/3.png">
                                                    </div>
                                                    <div class="media-body">
                                                        <div class="text-nowrap">张三抖了你一下</div>
                                                        <small class="text-muted">40 分钟前</small>
                                                    </div>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>


                                <div class="pad-all bord-top">
                                    <a href="javascript:void(0);" class="btn-link text-dark box-block">
                                        <i class="fa fa-angle-right fa-lg pull-right"></i>展开所有提醒
                                    </a>
                                </div>
                            </div>
                        </li>
	                    
	                    <li class="mega-dropdown">
							<a href="javascript:void(0);" class="mega-dropdown-toggle">
								<i class="demo-pli-layout-grid"></i>
							</a>
							<div class="dropdown-menu mega-dropdown-menu">
								<div class="row">
									<ul class="list-unstyled">
										<li class="col-md-3">
											<a href="http://122.224.218.35:8025/zfca?yhlx=teacher&amp;login=122579031373493682&amp;url=main.action" target="_blank" class="media mar-btm">
												<div class="media-left">
													<i class="demo-pli-data-settings icon-2x"></i>
												</div>
												<div class="media-body">
													<p class="text-semibold text-dark mar-no">智慧办公平台</p>
													<small class="text-muted"></small>
												</div>
											</a>
										</li>
										<li class="col-md-3">
											<a href="http://122.224.218.35:8025/zfca?yhlx=department&amp;login=122579031373493679&amp;url=teaPage.jsp" target="_blank" class="media mar-btm">
												<div class="media-left">
													<i class="demo-pli-support icon-2x"></i>
												</div>
												<div class="media-body">
													<p class="text-semibold text-dark mar-no">学生工作管理服务平台</p>
													<small class="text-muted"></small>
												</div>
											</a>
										</li>
										<li class="col-md-3">
											<a href="http://122.224.218.35:8025/zfca?yhlx=teacher&amp;login=0122579031373493691&amp;url=systemrole%252findex_initMenu.html" target="_blank" class="media mar-btm">
												<div class="media-left">
													<i class="demo-pli-computer-secure icon-2x"></i>
												</div>
												<div class="media-body">
													<p class="text-semibold text-dark mar-no">教师科研管理服务平台</p>
													<small class="text-muted"></small>
												</div>
											</a>
										</li>
										<li class="col-md-3">
											<a href="http://122.224.218.35:8025/zfca?yhlx=teacher&amp;login=0122579031373493708&amp;url=xtgl%252findex_initMenu.html" target="_blank" class="media mar-btm">
												<div class="media-left">
													<i class="demo-pli-map-2 icon-2x"></i>
												</div>
												<div class="media-body">
													<p class="text-semibold text-dark mar-no">教学管理服务平台</p>
													<small class="text-muted"></small>
												</div>
											</a>
										</li>
										<li class="col-md-3">
											<a href="http://122.224.218.35:8025/zfca/login?yhlx=department&amp;login=0122579031373493687&amp;url=desktop%2fdesktop_initDesktop.html" target="_blank" class="media mar-btm">
												<div class="media-left">
													<i class="demo-pli-data-settings icon-2x"></i>
												</div>
												<div class="media-body">
													<p class="text-semibold text-dark mar-no">人力资源管理服务平台</p>
													<small class="text-muted"></small>
												</div>
											</a>
										</li>
										<li class="col-md-3">
											<a href="http://122.224.218.35:8025/zfca?yhlx=teacher&amp;login=0122579031373493684&amp;url=mainJs.do" target="_blank" class="media mar-btm">
												<div class="media-left">
													<i class="demo-pli-data-settings icon-2x"></i>
												</div>
												<div class="media-body">
													<p class="text-semibold text-dark mar-no">招生管理服务平台</p>
													<small class="text-muted"></small>
												</div>
											</a>
										</li>
										<li class="col-md-3">
											<a href="http://122.224.218.35:8025/zfca?yhlx=teacher&amp;login=0122579031373493689&amp;url=xtwh%2Flogin_index.do" target="_blank" class="media mar-btm">
												<div class="media-left">
													<i class="demo-pli-support icon-2x"></i>
												</div>
												<div class="media-body">
													<p class="text-semibold text-dark mar-no">迎新管理服务平台</p>
													<small class="text-muted"></small>
												</div>
											</a>
										</li>
										<li class="col-md-3">
											<a href="http://122.224.218.35:8025/zfca?yhlx=department&amp;login=0122579031373493728&amp;url=xtgl%2Findex_initMenu.html" target="_blank" class="media mar-btm">
												<div class="media-left">
													<i class="demo-pli-computer-secure icon-2x"></i>
												</div>
												<div class="media-body">
													<p class="text-semibold text-dark mar-no">离校管理服务平台</p>
													<small class="text-muted"></small>
												</div>
											</a>
										</li>
										<li class="col-md-3">
											<a href="http://122.224.218.35:8025/zfca?yhlx=department&amp;login=0122579031373493729&amp;url=xtgl%2Findex_initMenu.html" target="_blank" class="media mar-btm">
												<div class="media-left">
													<i class="demo-pli-map-2 icon-2x"></i>
												</div>
												<div class="media-body">
													<p class="text-semibold text-dark mar-no">就业管理服务平台</p>
													<small class="text-muted"></small>
												</div>
											</a>
										</li>
										<li class="col-md-3">
											<a href="http://122.224.218.35:8025/zfca?yhlx=department&amp;login=0122579031373493707&amp;url=xtgl%2Findex_initMenu.html" target="_blank" class="media mar-btm">
												<div class="media-left">
													<i class="demo-pli-data-settings icon-2x"></i>
												</div>
												<div class="media-body">
													<p class="text-semibold text-dark mar-no">事业发展服务平台</p>
													<small class="text-muted"></small>
												</div>
											</a>
										</li>
										<li class="col-md-3">
											<a href="http://122.224.218.35:8025/zfca?yhlx=department&amp;login=0122579031373493722&amp;url=xtgl%2Findex_initMenu.html%3Fgnmkdm%3DN02" target="_blank" class="media mar-btm">
												<div class="media-left">
													<i class="demo-pli-map-2 icon-2x"></i>
												</div>
												<div class="media-body">
													<p class="text-semibold text-dark mar-no">数字档案</p>
													<small class="text-muted"></small>
												</div>
											</a>
										</li>
										<li class="col-md-3">
											<a href="http://122.224.218.35:8025/zfca?yhlx=department&amp;login=0122579031373493723&amp;url=zhxqLogin.do" target="_blank" class="media mar-btm">
												<div class="media-left">
													<i class="demo-pli-support icon-2x"></i>
												</div>
												<div class="media-body">
													<p class="text-semibold text-dark mar-no">综合校情服务</p>
													<small class="text-muted"></small>
												</div>
											</a>
										</li>
										<li class="col-md-3">
											<a href="http://122.224.218.35:8025/zfca?yhlx=deparment&amp;login=0122579031373493726&amp;url=xtgl%2Findex_initMenu.html" target="_blank" class="media mar-btm">
												<div class="media-left">
													<i class="demo-pli-data-settings icon-2x"></i>
												</div>
												<div class="media-body">
													<p class="text-semibold text-dark mar-no">高基报表系统</p>
													<small class="text-muted"></small>
												</div>
											</a>
										</li>
										<li class="col-md-3">
											<a href="http://122.224.218.35:8025/zfca?yhlx=teacher&amp;login=0122579031373493727&amp;url=xtgl%2Findex_initMenu.html" target="_blank" class="media mar-btm">
												<div class="media-left">
													<i class="demo-pli-data-settings icon-2x"></i>
												</div>
												<div class="media-body">
													<p class="text-semibold text-dark mar-no">学生收费管理服务平台</p>
													<small class="text-muted"></small>
												</div>
											</a>
										</li>
										<li class="col-md-3">
											<a href="http://122.224.218.35:8025/zfca?yhlx=deparment&amp;login=0122579031373493713&amp;url=index.do" target="_blank" class="media mar-btm">
												<div class="media-left">
													<i class="demo-pli-data-settings icon-2x"></i>
												</div>
												<div class="media-body">
													<p class="text-semibold text-dark mar-no">网络教学平台</p>
													<small class="text-muted"></small>
												</div>
											</a>
										</li>
									</ul>
								</div>
							</div>
						</li>-->
					</ul>
					<ul class="nav pull-left top-nav clearfix">
                    </ul>         
                    <ul class="nav navbar-top-links pull-right">


                        <li id="dropdown-user" class="dropdown">
                           	<a href="javascript:void(0);" data-toggle="dropdown" class="dropdown-toggle text-right">
								<span class="pull-right">
                                <img class="img-circle img-user media-object" src="${request.contextPath}/assets/images/profile-photos/1.png" alt="Profile Picture">
                                <!--<i class="demo-pli-male ic-user"></i>-->
                            </span>
								<div class="username hidden-xs">欢迎你，${user.xm?default("管理员")}</div>
							</a>

                            <div class="dropdown-menu dropdown-menu-md dropdown-menu-right panel-default">
								
								<!-- Dropdown heading  -->
								<!--
								<div class="pad-all bord-btm">
									<p class="text-main mar-btm"><span class="text-bold">750GB</span> of 1,000GB Used</p>
									<div class="progress progress-sm">
										<div class="progress-bar" style="width: 70%;">
											<span class="sr-only">70%</span>
										</div>
									</div>
								</div>
								-->
								
                                <!-- User dropdown menu -->
								<ul class="head-list bord-btm">
									<!--
									<li>
										<a href="javascript:void(0);">
											<i class="demo-pli-male icon-lg icon-fw"></i> [@spring.message code="home.profile"/]
										</a>
									</li>
									<li>
										<a href="javascript:void(0);">
											<span class="badge badge-danger pull-right">9</span>
											<i class="demo-pli-mail icon-lg icon-fw"></i> [@spring.message code="home.messages"/]
										</a>
									</li>
									<li>
										<a href="javascript:void(0);">
											<span class="label label-success pull-right">New</span>
											<i class="demo-pli-gear icon-lg icon-fw"></i> [@spring.message code="home.settings"/]
										</a>
									</li>
									-->
									<li>
										<a href="javascript:home_help();">
											<i class="demo-pli-information icon-lg icon-fw"></i> [@spring.message code="home.help"/]
										</a>
									</li>
								</ul>

								<!-- Dropdown footer -->
                                <div class="pad-all text-right">
                                    <a href="${request.contextPath}/logout" class="btn btn-primary">
                                        <i class="demo-pli-unlock"></i> [@spring.message code="home.logout"/]
                                    </a>
                                </div>
                            </div>
                        </li>
						<!--
                        <li>
                            <a href="javascript:void(0);" class="aside-toggle navbar-aside-icon">
                                <i class="pci-ver-dots"></i>
                            </a>
                        </li>
                        -->
                    </ul>
                </div>
            </div>
        </header>

        <div class="boxed" id="boxed">
            <!--中间内容-->
              <div id="content-container" class="content-container-api">
				<div class="zf-content">
					<div class="page-content">
						<div id="tab-general">
							<div id="tabs">
								<div class="nav-container tabs-below">
									<ul class="nav nav-tabs" role="tablist">
										<li role="presentation" class="active no-sort" id="tab_tab_home">
											<a href="#iframe_home" aria-controls="home" role="tab" data-toggle="tab"> 主页
											</a>
										</li>
									</ul>
								</div>
							</div>
							<div class="tab-content">
								<div role="tabpanel" class="tab-pane active embed-responsive embed-responsive-16by9" id="tab_home">
									<iframe id="iframe_home" class="iframeClass embed-responsive-item" style="height:400px" height="400"></iframe>
									
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
            <!--右侧侧边栏-->
            <aside id="aside-container">
                <div id="aside">
                    <div class="nano">
                        <div class="nano-content">

                            <!--Nav tabs-->
							<!--================================-->
							<ul class="nav nav-tabs nav-justified">
								<li class="active">
									<a href="#demo-asd-tab-1" data-toggle="tab">
										<i class="demo-pli-speech-bubble-7"></i> [@spring.message code="home.visit"/]  
									</a>
								</li>
								<!--<li>
									<a href="#demo-asd-tab-2" data-toggle="tab">
										<i class="demo-pli-information icon-fw"></i> Report
									</a>
								</li>-->
								<li>
									<a href="#demo-asd-tab-3" data-toggle="tab">
										<i class="demo-pli-wrench icon-fw"></i> [@spring.message code="home.settings"/]   
									</a>
								</li>
							</ul>
							<!--================================-->
							<!--End nav tabs-->

                            <!-- Tabs Content -->
                            <!--================================-->
                            <div class="tab-content">

                                <!--First tab (Contact list)-->
                                <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
                                <div class="tab-pane fade in active" id="demo-asd-tab-1">
                                    <p class="pad-hor mar-top text-semibold text-main">
                                        <span class="pull-right badge badge-warning">3</span> [@spring.message code="home.visit.lastest"/]
                                    </p>

                                    <!--Family-->
                                    <div class="list-group bg-trans">
                                        <a href="javascript:void(0);" class="list-group-item">
                                            <div class="media-left pos-rel">
                                                <img class="img-circle img-xs" src="${request.contextPath}/assets/images/profile-photos/2.png" alt="Profile Picture">
                                                <i class="badge badge-success badge-stat badge-icon pull-left"></i>
                                            </div>
                                            <div class="media-body">
                                                <p class="mar-no">Stephen Tran</p>
                                                <small class="text-muted">Availabe</small>
                                            </div>
                                        </a>
                                        <a href="javascript:void(0);" class="list-group-item">
                                            <div class="media-left pos-rel">
                                                <img class="img-circle img-xs" src="${request.contextPath}/assets/images/profile-photos/7.png" alt="Profile Picture">
                                            </div>
                                            <div class="media-body">
                                                <p class="mar-no">Brittany Meyer</p>
                                                <small class="text-muted">I think so</small>
                                            </div>
                                        </a>
                                        <a href="javascript:void(0);" class="list-group-item">
                                            <div class="media-left pos-rel">
                                                <img class="img-circle img-xs" src="${request.contextPath}/assets/images/profile-photos/1.png" alt="Profile Picture">
                                                <i class="badge badge-info badge-stat badge-icon pull-left"></i>
                                            </div>
                                            <div class="media-body">
                                                <p class="mar-no">Jack George</p>
                                                <small class="text-muted">Last Seen 2 hours ago</small>
                                            </div>
                                        </a>
                                        <a href="javascript:void(0);" class="list-group-item">
                                            <div class="media-left pos-rel">
                                                <img class="img-circle img-xs" src="${request.contextPath}/assets/images/profile-photos/4.png" alt="Profile Picture">
                                            </div>
                                            <div class="media-body">
                                                <p class="mar-no">Donald Brown</p>
                                                <small class="text-muted">Lorem ipsum dolor sit amet.</small>
                                            </div>
                                        </a>
                                        <a href="javascript:void(0);" class="list-group-item">
                                            <div class="media-left pos-rel">
                                                <img class="img-circle img-xs" src="${request.contextPath}/assets/images/profile-photos/8.png" alt="Profile Picture">
                                                <i class="badge badge-warning badge-stat badge-icon pull-left"></i>
                                            </div>
                                            <div class="media-body">
                                                <p class="mar-no">Betty Murphy</p>
                                                <small class="text-muted">Idle</small>
                                            </div>
                                        </a>
                                        <a href="javascript:void(0);" class="list-group-item">
                                            <div class="media-left pos-rel">
                                                <img class="img-circle img-xs" src="${request.contextPath}/assets/images/profile-photos/9.png" alt="Profile Picture">
                                                <i class="badge badge-danger badge-stat badge-icon pull-left"></i>
                                            </div>
                                            <div class="media-body">
                                                <p class="mar-no">Samantha Reid</p>
                                                <small class="text-muted">Offline</small>
                                            </div>
                                        </a>
                                    </div>

                                    <hr>
                                    <p class="pad-hor text-semibold text-main">
                                        <span class="pull-right badge badge-success">23</span> [@spring.message code="home.visit.most"/]
                                    </p>

                                    <!--Works-->
                                    <div class="list-group bg-trans">
                                        <a href="javascript:void(0);" class="list-group-item">
                                            <span class="badge badge-purple badge-icon badge-fw pull-left"></span> Joey K. Greyson
                                        </a>
                                        <a href="javascript:void(0);" class="list-group-item">
                                            <span class="badge badge-info badge-icon badge-fw pull-left"></span> Andrea Branden
                                        </a>
                                        <a href="javascript:void(0);" class="list-group-item">
                                            <span class="badge badge-success badge-icon badge-fw pull-left"></span> Johny Juan
                                        </a>
                                        <a href="javascript:void(0);" class="list-group-item">
                                            <span class="badge badge-danger badge-icon badge-fw pull-left"></span> Susan Sun
                                        </a>
                                    </div>


                                    <hr>
                                    <p class="pad-hor mar-top text-semibold text-main">News</p>

                                    <div class="pad-hor">
                                        <p class="text-muted">Lorem ipsum dolor sit amet, consectetuer
                                            <a data-title="45%" class="add-tooltip text-semibold" href="javascript:void(0);">adipiscing elit</a>,
                                            sed diam nonummy nibh. Lorem ipsum dolor sit amet.
                                        </p>
                                        <small class="text-muted"><em>Last Update : Des 12, 2014</em></small>
                                    </div>


                                </div>
                                <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
                                <!--End first tab (Contact list)-->


                                <!--Second tab (Custom layout)-->
                                <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
                                <div class="tab-pane fade" id="demo-asd-tab-2">

                                    <!--Monthly billing-->
                                    <div class="pad-all">
                                        <p class="text-semibold text-main">Billing &amp; reports</p>
                                        <p class="text-muted">Get <strong>$5.00</strong> off your next bill by making sure your full payment reaches
                                            us before August 5, 2016.</p>
                                    </div>
                                    <hr class="new-section-xs">
                                    <div class="pad-all">
                                        <span class="text-semibold text-main">Amount Due On</span>
                                        <p class="text-muted text-sm">August 17, 2016</p>
                                        <p class="text-2x text-thin text-main">$83.09</p>
                                        <button class="btn btn-block btn-success mar-top">Pay Now</button>
                                    </div>


                                    <hr>

                                    <p class="pad-hor text-semibold text-main">Additional Actions</p>

                                    <!--Simple Menu-->
                                    <div class="list-group bg-trans">
                                        <a href="javascript:void(0);" class="list-group-item"><i class="demo-pli-information icon-lg icon-fw"></i> Service Information</a>
                                        <a href="javascript:void(0);" class="list-group-item"><i class="demo-pli-mine icon-lg icon-fw"></i> Usage Profile</a>
                                        <a href="javascript:void(0);" class="list-group-item"><span class="label label-info pull-right">New</span><i class="demo-pli-credit-card-2 icon-lg icon-fw"></i> Payment Options</a>
                                        <a href="javascript:void(0);" class="list-group-item"><i class="demo-pli-support icon-lg icon-fw"></i> Message Center</a>
                                    </div>


                                    <hr>

                                    <div class="text-center">
                                        <div><i class="demo-pli-old-telephone icon-3x"></i></div>
                                        Questions?
                                        <p class="text-lg text-semibold text-main"> (415) 234-53454 </p>
                                        <small><em>We are here 24/7</em></small>
                                    </div>
                                </div>
                                <!--End second tab (Custom layout)-->
                                <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->


                                <!--Third tab (Settings)-->
                                <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
                                <div class="tab-pane fade" id="demo-asd-tab-3">
                                    <ul class="list-group bg-trans">
                                        <li class="pad-top list-header">
                                            <p class="text-semibold text-main mar-no">[@spring.message code="home.settings.account"/]</p>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="pull-right">
                                                <input class="toggle-switch" id="demo-switch-1" type="checkbox" checked>
                                                <label for="demo-switch-1"></label>
                                            </div>
                                            <p class="mar-no">Show my personal status</p>
                                            <small class="text-muted">Lorem ipsum dolor sit amet, consectetuer adipiscing elit.</small>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="pull-right">
                                                <input class="toggle-switch" id="demo-switch-2" type="checkbox" checked>
                                                <label for="demo-switch-2"></label>
                                            </div>
                                            <p class="mar-no">Show offline contact</p>
                                            <small class="text-muted">Aenean commodo ligula eget dolor. Aenean massa.</small>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="pull-right">
                                                <input class="toggle-switch" id="demo-switch-3" type="checkbox">
                                                <label for="demo-switch-3"></label>
                                            </div>
                                            <p class="mar-no">Invisible mode </p>
                                            <small class="text-muted">Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. </small>
                                        </li>
                                    </ul>


                                    <hr>

                                    <ul class="list-group pad-btm bg-trans">
                                        <li class="list-header">
                                            <p class="text-semibold text-main mar-no">[@spring.message code="home.settings.public"/]</p>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="pull-right">
                                                <input class="toggle-switch" id="demo-switch-4" type="checkbox" checked>
                                                <label for="demo-switch-4"></label>
                                            </div>
                                            Online status
                                        </li>
                                        <li class="list-group-item">
                                            <div class="pull-right">
                                                <input class="toggle-switch" id="demo-switch-5" type="checkbox" checked>
                                                <label for="demo-switch-5"></label>
                                            </div>
                                            Show offline contact
                                        </li>
                                        <li class="list-group-item">
                                            <div class="pull-right">
                                                <input class="toggle-switch" id="demo-switch-6" type="checkbox" checked>
                                                <label for="demo-switch-6"></label>
                                            </div>
                                            Show my device icon
                                        </li>
                                    </ul>



                                    <hr>

                                    <p class="pad-hor text-semibold text-main mar-no">Task Progress</p>
                                    <div class="pad-all">
                                        <p>Upgrade Progress</p>
                                        <div class="progress progress-sm">
                                            <div class="progress-bar progress-bar-success" style="width: 15%;"><span class="sr-only">15%</span></div>
                                        </div>
                                        <small class="text-muted">15% Completed</small>
                                    </div>
                                    <div class="pad-hor">
                                        <p>Database</p>
                                        <div class="progress progress-sm">
                                            <div class="progress-bar progress-bar-danger" style="width: 75%;"><span class="sr-only">75%</span></div>
                                        </div>
                                        <small class="text-muted">17/23 Database</small>
                                    </div>

                                </div>
                                <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
                                <!--Third tab (Settings)-->

                            </div>
                        </div>
                    </div>
                </div>
            </aside>
            <!--左侧侧边栏-->
            <nav id="mainnav-container">
                <div id="mainnav">

                    <div id="mainnav-menu-wrap">
                        <div class="nano">
                            <div class="nano-content">
                                <!--导航正式内容-->
                                <ul id="mainnav-menu" class="list-group">
									[#include "/html/left-menu.ftl" /]
                                </ul>
                            </div>
                        </div>
                    </div>

                </div>
            </nav>

        </div>
        <!--底部版权-->
        [#include "/bottom.ftl" /]
        <!--置顶icon
        <button class="scroll-top btn"><i class="pci-chevron chevron-up"></i></button>-->

    </div>
    [#include "/head/zftal-ui-validation.ftl" /]
</body>

</html>
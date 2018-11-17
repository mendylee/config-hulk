<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>向日葵配置中心</title>
    <meta name="renderer" content="webkit"> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">   
    <meta name="apple-mobile-web-app-status-bar-style" content="black"> 
    <meta name="apple-mobile-web-app-capable" content="yes">    
    <meta name="format-detection" content="telephone=no">   
    <!-- load css -->
    <link rel="stylesheet" type="text/css" href="/common/layui/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="/common/global.css" media="all">
    <link rel="stylesheet" type="text/css" href="/css/adminstyle.css" media="all">
</head>
<body>
<div class="layui-layout layui-layout-admin" id="layui_layout">
    <!-- 顶部区域 -->
    <div class="layui-header header header-demo">
        <div class="layui-main">
            <!-- logo区域 -->
            <div class="admin-logo-box">
                <a class="logo" href="http://www.kuxuebao.net" title="logo"><img src="/images/config_logo.png" alt=""></a>
                <div class="larry-side-menu">
                    <i class="fa fa-bars" aria-hidden="true"></i>
                </div>
            </div>
            <!-- 右侧导航 -->
            <ul class="layui-nav larry-header-item">
                    <li class="layui-nav-item">
                    </li>
                    <li class="layui-nav-item">
                        <a href="login.html">
                        <i class="iconfont icon-exit"></i>
                        退出</a>
                    </li>
            </ul>
        </div>
    </div>
    <!-- 左侧侧边导航开始 -->
    <div class="layui-side layui-side-bg layui-larry-side" id="larry-side">
        <div class="layui-side-scroll" id="larry-nav-side" lay-filter="side">
        <!-- 左侧菜单 -->
        <ul class="layui-nav layui-nav-tree">
            <li class="layui-nav-item layui-this">
                
                <a href="javascript:;" data-url="/admin/main">
                    <i class="iconfont icon-home1" data-icon='icon-home1'></i>
                    <span>首页</span>
                </a>
            </li>
            <!-- 个人信息 -->
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <i class="iconfont icon-jiaoseguanli" ></i>
                    <span>我的面板</span>
                    <em class="layui-nav-more"></em>
                </a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="javascript:;" data-url="/admin/main">
                            <i class="iconfont icon-geren1" data-icon='icon-geren1'></i>
                            <span>我的项目</span>
                        </a>
                    </dd>
                    <dd>
                        <a href="javascript:;" data-url="/wait">
                            <i class="iconfont icon-iconfuzhi01" data-icon='icon-iconfuzhi01'></i>
                            <span>修改密码</span>
                        </a>
                    </dd>
                    <dd>
                        <a href="javascript:;" data-url="/wait">
                            <i class="iconfont icon-piliangicon" data-icon='icon-piliangicon'></i>
                            <span>日志信息</span>
                        </a>
                    </dd>
                </dl>
            </li>
            <!-- 配置管理 -->
            <li class="layui-nav-item">
                    <a href="javascript:;">
                       <i class="iconfont icon-wenzhang1" ></i>
                       <span>配置管理</span>
                       <em class="layui-nav-more"></em>
                    </a>
                       <dl class="layui-nav-child">
                           <dd>
                                <a href="javascript:;" data-url="/admin/toAddConfig">
                                   <i class="iconfont icon-lanmuguanli" data-icon='icon-lanmuguanli'></i>
                                   <span>添加配置</span>
                                </a>
                            </dd>
                            <dd>
                                <a href="javascript:;" data-url="/admin/configlist">
                                   <i class="iconfont icon-wenzhang2" data-icon='icon-wenzhang2'></i>
                                   <span>配置综合查询</span>
                                </a>
                            </dd>
                            <dd>
                                <a href="javascript:;" data-url="/admin/applist">
                                   <i class="iconfont icon-icon1" data-icon='icon-icon1'></i>
                                   <span>应用管理</span>
                                </a>
                            </dd>
                            
                       </dl>
               </li>
            
             <!-- 用户管理 
            <li class="layui-nav-item">
                    <a href="javascript:;">
                       <i class="iconfont icon-jiaoseguanli2" ></i>
                       <span>用户管理</span>
                       <em class="layui-nav-more"></em>
                    </a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" data-url="userlist.html">
                                   <i class="iconfont icon-yonghu1" data-icon='icon-yonghu1'></i>
                                   <span>用户列表</span>
                                </a>
                            </dd>
                            <dd>
                                <a href="javascript:;">
                                   <i class="iconfont icon-jiaoseguanli4" data-icon='icon-jiaoseguanli4'></i>
                                   <span>角色列表</span>
                                </a>
                            </dd>
                            <dd>
                                <a href="javascript:;">
                                   <i class="iconfont icon-quanxian2" data-icon='icon-quanxian2'></i>
                                   <span>菜单管理</span>
                                </a>
                            </dd>
                        </dl>
                </li>    
                -->
            <!-- 系统设置 -->
            <li class="layui-nav-item">
                    <a href="javascript:;">
                       <i class="iconfont icon-xitong" ></i>
                       <span>系统设置</span>
                       <em class="layui-nav-more"></em>
                    </a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" data-url="/admin/toSyn">
                                   <i class="iconfont icon-zhandianpeizhi" data-icon='icon-zhandianpeizhi'></i>
                                   <span>zookeeper配置同步</span>
                                </a>
                            </dd>
                            <dd>
                                <a href="javascript:;" data-url="/admin/envlist">
                                   <i class="iconfont icon-zhandianguanli1" data-icon='icon-zhandianguanli1'></i>
                                   <span>环境管理</span>
                                </a>
                            </dd>
                            <dd>
                                <a href="javascript:;" data-url="/wait">
                                   <i class="iconfont icon-sms" data-icon='icon-sms'></i>
                                   <span>邮箱接口设置</span>
                                </a>
                            </dd>
                        </dl>
                </li>
               
        </ul>
        </div>
    </div>

    <!-- 左侧侧边导航结束 -->
    <!-- 右侧主体内容 -->
    <div class="layui-body" id="larry-body" style="bottom: 0;border-left: solid 2px #1AA094;">
        <div class="layui-tab layui-tab-card larry-tab-box" id="larry-tab" lay-filter="demo" lay-allowclose="true">
            
            <ul class="layui-tab-title">
                <li class="layui-this" id="admin-home"><i class="iconfont icon-diannao1"></i><em>首页</em></li>
            </ul>
            
            <div class="layui-tab-content" style="min-height: 150px; ">
                <div class="layui-tab-item layui-show">
                    <iframe class="larry-iframe" data-id='0' src="/admin/main"></iframe>
                </div>
            </div>
        </div>

        
    </div>
    <!-- 底部区域 -->
    <div class="layui-footer layui-larry-foot" id="larry-footer">
        <div class="layui-mian">
            <div class="larry-footer-left">
                                          向日葵科技技术有限公司：
                       
            </div>
            
        </div>
    </div>
</div>
<!-- 加载js文件-->
    <script type="text/javascript" src="/common/layui/layui.js"></script> 
    <script type="text/javascript" src="/js/larry.js"></script>
    <script type="text/javascript" src="/js/index.js"></script>
<!-- 锁屏 -->
<div class="lock-screen" style="display: none;">
    <div id="locker" class="lock-wrapper">
        <div id="time"></div>
        <div class="lock-box center">
            <img src="/images/userimg.jpg" alt="">
            <h1>admin</h1>
            <duv class="form-group col-lg-12">
                <input type="password" placeholder='锁屏状态，请输入密码解锁' id="lock_password" class="form-control lock-input" autofocus name="lock_password">
                <button id="unlock" class="btn btn-lock">解锁</button>
            </duv>
        </div>
    </div>
</div>
<!-- 菜单控件 -->
<!-- <div class="larry-tab-menu">
    <span class="layui-btn larry-test">刷新</span>
</div> -->
<!-- iframe框架刷新操作 -->
<!-- <div id="refresh_iframe" class="layui-btn refresh_iframe">刷新</div> -->
</body>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>LarryBlogCMS-Home</title>
  <meta name="renderer" content="webkit"> 
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"> 
  <meta name="apple-mobile-web-app-status-bar-style" content="black"> 
  <meta name="apple-mobile-web-app-capable" content="yes">  
  <meta name="format-detection" content="telephone=no"> 
    <link rel="stylesheet" type="text/css" href="/common/layui/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="/common/bootstrap/css/bootstrap.css" media="all">
    <link rel="stylesheet" type="text/css" href="/common/global.css">
    <link rel="stylesheet" type="text/css" href="/css/main.css" media="all">
</head>
<body>
<section class="larry-wrapper">
    <!-- overview -->
    <div class="row state-overview">
        <div class="col-lg-3 col-sm-6">
            <section class="panel">
                <div class="symbol userblue"> <i class="iconpx-app"></i>
                </div>
                <div class="value">
                    <a href="#">
                        <h1 id="count1">${appSize}</h1>
                    </a>
                    <p>接入应用总数</p>
                </div>
            </section>
        </div>
        <div class="col-lg-3 col-sm-6">
            <section class="panel">
                <div class="symbol commred"> <i class="iconpx-check-circle"></i>
                </div>
                <div class="value">
                    <a href="#">
                        <h1 id="count2">${configSize}</h1>
                    </a>
                    <p>管理配置总数</p>
                </div>
            </section>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-md-6">
            <section class="panel">
                <header class="panel-heading bm0">
                    <span class='span-title'>系统概览</span>
                    <span class="tools pull-right"><a href="javascript:;" class="iconpx-chevron-down"></a></span>
                </header>
                <div class="panel-body" >
                    <table class="table table-hover personal-task">
                         <table class="layui-table">
                            <tbody>
                                <tr>
                                    <td>
                                        <strong>软件名称</strong>：

                                    </td>
                                    <td>
                                        <a href="http://jqadmin.jqcool.net">向日葵配置中心</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <strong>当前版本</strong>：

                                    </td>
                                    <td>
                                        V1.2.0
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <strong>技术栈</strong>：
                                    </td>
                                    <td>
                                        Java、SpringBoot、Zookeeper、SpringDataJPA、Mysql
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <strong>服务器环境</strong>：
                                    </td>
                                    <td>linux</td>
                                </tr>
                            </tbody>
                        </table>
                </div>
            </section>
        </div>
        
        
        <div class="col-xs-12 col-md-6">
          <section class="panel">
                 <header class="panel-heading bm0">
                        <span class='span-title'>系统公告</span>
                        <span class="tools pull-right"><a href="javascript:;" class="iconpx-chevron-down"></a></span>
                  </header>
                  <div class="panel-body">
                        
                        <table class="table table-hover personal-task">
                            <tbody><tr>
                              <td>
                                <p class="larry_github">
                               向日葵配置管理中心能够集中化管理应用不同环境、
不同版本的配置，配置修改后能够实时推送到应用端<br>
在线动态修改配置信息生效后，实时推送新配置信息至项目中，不需要编译打包和重启线上服务器,欢迎各项目组应用接入配置中心<br>
接入方案：<a href="https://confluence.xiangrikui.com:1443/pages/viewpage.action?pageId=11734066" class="githublink" target="_blank">https://confluence.xiangrikui.com:1443/pages/viewpage.action?pageId=11734066</a><br>
联系QQ：<span>393499544</span><br>
邮箱：<span>zhubin@xiangrikui.com</span><br>

                                </p>
                              </td>
                            </tr>
                        </tbody></table>
                  </div>
             </section>
        
        
        </div>
        
        
        </div>
        
    </div>

<script type="text/javascript" src="../common/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['jquery','layer','element'],function(){
        window.jQuery = window.$ = layui.jquery;
        window.layer = layui.layer;
        window.element = layui.element();

       $('.panel .tools .iconpx-chevron-down').click(function(){
           var el = $(this).parents(".panel").children(".panel-body");
           if($(this).hasClass("iconpx-chevron-down")){
               $(this).removeClass("iconpx-chevron-down").addClass("iconpx-chevron-up");
               el.slideUp(200);
           }else{
               $(this).removeClass("iconpx-chevron-up").addClass("iconpx-chevron-down");
               el.slideDown(200);
           }
       })

    });
</script>
<script type="text/javascript" src="/jsplug/echarts.min.js"></script>
<script type="text/javascript" src="/js/main.js"></script>
</body>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>添加应用</title>
    <meta name="renderer" content="webkit"> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">   
    <meta name="apple-mobile-web-app-status-bar-style" content="black"> 
    <meta name="apple-mobile-web-app-capable" content="yes">    
    <meta name="format-detection" content="telephone=no">   
    <link rel="stylesheet" type="text/css" href="/common/layui/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="/common/bootstrap/css/bootstrap.css" media="all">
    <link rel="stylesheet" type="text/css" href="/common/global.css" media="all">
    <link rel="stylesheet" type="text/css" href="/css/personal.css" media="all">
</head>
<body>
<section class="layui-larry-box">
    <div class="larry-personal">
        <header class="larry-personal-tit">
            <span>新增应用</span>
        </header><!-- /header -->
        <div class="larry-personal-body clearfix">
            <form class="layui-form col-lg-5" action="/app/add" method="post">
                <div class="layui-form-item">
                    <label class="layui-form-label">应用名称</label>
                    <div class="layui-input-block">  
                        <input type="text" name="appName"  autocomplete="off" lay-verify="required" class="layui-input" placeholder="输入应用名称">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">应用描述</label>
                    <div class="layui-input-block">
                        <input type="text" name="description"  autocomplete="off" lay-verify="required" class="layui-input" placeholder="输入应用描述">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">邮箱列表</label>
                    <div class="layui-input-block">
                        <input type="text" name="emails"  autocomplete="off" lay-verify="required|email" class="layui-input" placeholder="输入应用负责人邮箱列表多个邮箱用,隔开">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="addApp">立即提交</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>
<script type="text/javascript" src="/common/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['form','upload'],function(){
         var form = layui.form();
    });
</script>
</body>
</html>
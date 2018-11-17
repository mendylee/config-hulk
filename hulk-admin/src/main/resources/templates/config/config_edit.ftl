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
            <span>编辑配置</span>
             
        </header><!-- /header -->
      
        <div class="larry-personal-body clearfix">
                <form class="layui-form col-lg-5" action="/config/edit" method="post">
                <input type="hidden" name="configId" value="${config.configId}" >
                <div class="layui-form-item">
                    <label class="layui-form-label">应用信息</label>
                    <div class="layui-input-block">
                            <input type="text" name="info" value="${config.app.appName}_${config.env.envName}_${config.version}" autocomplete="off"  class="layui-input" readonly="true">
                    </div>
                </div>
                <div class="batchUpdate">
                    <div class="layui-form-item">
                        <label class="layui-form-label">配置项名称</label>
                        <div class="layui-input-block">
                            <input type="text" name="key" value="${config.name}" autocomplete="off"  class="layui-input" readonly="true" >
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">配置值</label>
                        <div class="layui-input-block">
                            <input type="text" name="value" value="${config.value}" autocomplete="off"  class="layui-input" >
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button id="test" class="layui-btn" lay-submit lay-filter="editConfig">保存</button>
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
         var $ = layui.jquery;
         var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
         $('#test').on('click', function(){
                //parent.location.reload();//刷新父窗口
                parent.layer.close(index); //执行关闭
               
            });          
        });
         
</script>
</body>
</html>
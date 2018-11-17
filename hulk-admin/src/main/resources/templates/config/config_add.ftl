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
            <span>新增配置</span>
        </header><!-- /header -->
        <div class="larry-personal-body clearfix">
            <form class="layui-form col-lg-5" action="/config/add" method="post">
                <div class="layui-form-item">
                    <label class="layui-form-label">应用名称</label>
                    <div class="layui-input-block">  
                        <select name="appId" lay-verify="required" lay-search="">
                          <option value="">直接选择或搜索选择</option>
                          <#list appList as appItem>
                          <option value=${appItem.appId}>${appItem.appName}(${appItem.description})</option>
                          </#list>  
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">应用环境</label>
                    <div class="layui-input-block">  
                        <select name="envId" lay-verify="required" lay-search="">
                          <option value="">直接选择或搜索选择</option>
                          <#list envList as envItem>
                          <option value=${envItem.envId}>${envItem.envName}(${envItem.description})</option>
                          </#list>  
                     </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">版本号</label>
                    <div class="layui-input-block">
                        <input type="text" name="version" lay-verify="required" autocomplete="off" class="layui-input" placeholder="输入版本号">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">单项添加</label>
                    <div class="layui-input-block">
                        <input type="radio" lay-filter="batchUpdate" name="mode" value="是" title="是" checked=""><div class="layui-unselect layui-form-radio layui-form-radioed"><i class="layui-anim layui-icon"></i><span>是</span></div>
                        <input type="radio" lay-filter="batchUpdate" name="mode" value="否" title="否"><div class="layui-unselect layui-form-radio"><i class="layui-anim layui-icon"></i><span>否</span></div>
                    </div>
                </div> 
                <div class="isBatchUpdate" style="display:none">
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">文本模式</label>
                        <div class="layui-input-block">
                            <textarea placeholder="批量添加多段配置时,每行配置进行换行"  name="content" value="" class="layui-textarea"></textarea>
                        </div>
                    </div>
                </div>
                <div class="batchUpdate">
                    <div class="layui-form-item">
                        <label class="layui-form-label">配置项名称</label>
                        <div class="layui-input-block">
                            <input type="text" name="key"  autocomplete="off"  class="layui-input" placeholder="输入配置项的key名称">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">配置值</label>
                        <div class="layui-input-block">
                            <input type="text" name="value"  autocomplete="off"  class="layui-input" placeholder="输入配置项的值">
                        </div>
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
         var $ = layui.jquery;
         form.on('radio(batchUpdate)', function(data){
            if(data.value == "是"){
                $(".batchUpdate").show();
                $(".isBatchUpdate").hide();
            } else {
                $(".batchUpdate").hide();
                $(".isBatchUpdate").show();
            }
        });
         
    });
</script>
</body>
</html>
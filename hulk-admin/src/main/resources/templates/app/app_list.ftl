<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>配置管理</title>
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
        <div class="layui-tab">
        <blockquote class="layui-elem-quote">
                        <form class="layui-form layui-form-pane">
                             <div class="layui-form-item" style="margin:0;">
                                <label class="layui-form-label">应用名称:</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="appName" placeholder="请输入应用名称" autocomplete="off" class="layui-input">
                                </div>
                                <button class="layui-btn" lay-submit lay-filter="formDemo">查询</button>
                                <a class="layui-btn layui-btn-normal" href="/admin/toAddApp"> <i class="layui-icon">&#xe608;</i>添加应用</a>
                             </div>
                        </form>
            </blockquote>
            <div class="larry-separate"></div>
   <div class="layui-tab-content larry-personal-body clearfix mylog-info-box">
                 <!-- 操作日志 -->
                <div class="layui-tab-item  layui-show">
                     <table class="layui-table">
                         <colgroup>
                          <col width="100">
                          <col width="150">
                          <col width="150">
                          <col width="200">
                          <col width="200">
                          <col>
                        </colgroup>
                          <thead>
                              <tr>
                                  <th>应用名称</th>
                                  <th>描述</th>
                                  <th>创建时间</th>
                                  <th>负责人邮箱</th>
                              </tr>
                          </thead>
                          <!--内容容器-->
                            <tbody id="con">
                            </tbody>
                     </table>
                     <div class="larry-table-page clearfix">
                          <div id="paged" class="page"></div>
                     </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!--模板-->
        <script type="text/html" id="conTemp">
            {{# layui.each(d.result, function(index, item){ }}
            <tr>
                <td>{{ item.appName }}</td>
                <td>{{ item.description }}</td>
                <td>{{ item.createdAt }}</td>
                <td>{{ item.emails }}</td>
            </tr>
            {{# }); }}
        </script>
<script type="text/javascript" src="/common/layui/layui.js"></script>
<script>
            layui.config({
                base: '/js/'
            }).use(['paging', 'code','form'], function() {
                layui.code();
                var $ = layui.jquery,
                layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
                paging = layui.paging();
                form = layui.form();
                init();
                //监听搜索表单的提交事件
                form.on('submit(formDemo)', function (data) {
                    var appName = data.field.appName;
                    //调用get方法获取数据
                    paging.get({
                        appName: appName //获取输入的关键字。
                    });
                    return false;
                });
                
                function init(){
                    paging.init({
                        openWait: false,
                        url: '/app/list', //地址
                        elem: '#con', //内容容器
                        type:'post',
                        params: { //发送到服务端的参数
                            pageIndex: 1
                        },
                        type:'GET',
                        tempElem: '#conTemp', //模块容器
                        pageConfig: { //分页参数配置
                            elem: '#paged', //分页容器
                            pageSize: 15 //分页大小
                        },
                        success: function() { //渲染成功的回调
                            //alert('渲染成功');
                        },
                        fail: function(msg) { //获取数据失败的回调
                            //alert('获取数据失败')
                        },
                        complate: function(res) { //完成的回调
                            //alert('处理完成');
                            console.log(res);
                        },
                    });
                }
                
                
                 
            });
            
          
        </script>
</body>
</html>
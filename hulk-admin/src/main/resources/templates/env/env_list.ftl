<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>环境管理</title>
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
                                <label class="layui-form-label">环境名称:</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="env_name" placeholder="请输入环境名称" autocomplete="off" class="layui-input">
                                </div>
                                <button class="layui-btn" lay-submit lay-filter="formDemo">查询</button>
                                <a class="layui-btn layui-btn-normal" href="addOrEdit.html"> <i class="layui-icon">&#xe608;</i>添加环境</a>
                             </div>
                        </form>
            </blockquote>
            <div class="larry-separate"></div>
   <div class="layui-tab-content larry-personal-body clearfix mylog-info-box">
                 <!-- 操作日志 -->
                <div class="layui-tab-item  layui-show">
                     <table class="layui-table">
                         <colgroup>
                          <col width="30">
                          <col width="150">
                          <col width="150">
                          <col width="200">
                          <col width="200">
                          <col>
                        </colgroup>
                          <thead>
                              <tr>
                                  <th>ID</th>
                                  <th>环境名称</th>
                                  <th>描述</th>
                                  <th>创建时间</th>
                                  <th>操作</th>
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
                <td>{{ item.envId }}</td>
                <td>{{ item.envName }}</td>
                <td>{{ item.description }}</td>
                <td>{{ item.createdAt }}</td>
                <td>
                    <a href="/manage/article_edit_1" class="layui-btn layui-btn-mini">编辑</a>
                    <a href="javascript:;" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
                </td>
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
                    paging = layui.paging();
                    form = layui.form();
                paging.init({
                    openWait: false,
                    url: '/env/list', //地址
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
                
                //监听搜索表单的提交事件
                form.on('submit(formDemo)', function (data) {
                    var envName = data.field.env_name;
                    //调用get方法获取数据
                    paging.get({
                        envName: envName //获取输入的关键字。
                    });
                    return false;
                });
            });
            
            
        </script>
</body>
</html>
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
                                <label class="layui-form-label">应用名称</label>
                                <div class="layui-input-inline">
                                    <select name="app" id="app"  lay-search="">
                                      <option value="">直接选择或搜索选择</option>
                                      <#list appList as appItem>
                                      <option value=${appItem.appId}>${appItem.appName}</option>
                                      </#list>  
                                    </select>
                                </div>
                                <div class="layui-input-inline">
                                    <input type="text" name="version" id="version" placeholder="请输入版本号" autocomplete="off" class="layui-input">
                                </div>
                                <label class="layui-form-label">配置key</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="key" id="key" placeholder="请输入配置项名称" autocomplete="off" class="layui-input">
                                </div>
                                <button class="layui-btn" lay-submit lay-filter="formDemo">查询</button>
                             </div>
                        </form>
            </blockquote>
            <div class="larry-separate"></div>
   <div class="layui-tab-content larry-personal-body clearfix mylog-info-box">
  <div class="layui-tab-content" style="height: 1
                 <!-- 操作日志 -->
                <div class="layui-tab-item  layui-show">
                     <table class="layui-table">
                         <colgroup>
                          <col width="200">
                          <col width="100">
                          <col width="100">
                          <col width="150">
                          <col width="300">
                          <col width="200">
                          <col width="50">
                          <col>
                        </colgroup>
                          <thead>
                              <tr>
                                  <th>应用名称</th>
                                  <th>环境</th>
                                  <th>版本号</th>
                                  <th>配置项</th>
                                  <th>配置内容</th>
                                  <th>修改时间</th>
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
                <td>{{ item.app.appName }}</td>
                <td>{{ item.env.envName }}</td>
                <td>{{ item.version }}</td>
                <td>{{ item.name }}</td>
                <td style="table-layout:fixed;word-break:break-all;">{{ item.value }}</td>
                <td>{{ item.updatedAt }}</td>
                <td class="last-td">
                    <input type="button" value="编辑" data-action="edit" data-id="{{ item.configId }}" class="btn-del layui-btn layui-btn-mini" />
                    
                </td>
            </tr>
            {{# }); }}
        </script>
<script type="text/javascript" src="/common/layui/layui.js"></script>
<script>
            layui.config({
                base: '/js/'
            }).use(['paging', 'element','code','form'], function() {
                layui.code();
                var $ = layui.jquery,
                layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
                paging = layui.paging();
                form = layui.form();
                var element = layui.element();
                //分页初始化
                init();
                
                //监听搜索表单的提交事件
                form.on('submit(formDemo)', function (data) {
                    var appId = data.field.app;
                    var key = data.field.key;
                    var version = data.field.version;
                    //调用get方法获取数据
                    paging.get({
                        appId: appId, //获取输入的关键字。
                        key: key,
                        version:version
                    });
                    return false;
                });
                
                function init(){
                    paging.init({
                        openWait: false,
                        url: '/config/list', //地址
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
                
                $('body').on('click', '.btn-del', function(){
                        var $that = $(this);     
                        var id = $that.data('id');
                         //在这里面输入任何合法的js语句
                        var index = layer.open({
                          type: 2 //Page层类型
                          ,area: ['600px', '400px']
                          ,title: ''
                          ,shade: [0.5, '#000', false]
                          ,maxmin: true //允许全屏最小化
                          ,anim: 1 //0-6的动画形式，-1不开启
                          ,end : function () {
                             //调用get方法获取数据
                            var appId = $('#app').val();
                            paging.get({
                               appId: appId //获取输入的关键字。
                            });
                          }
                          ,content: '/config/toEdit?configId='+id
                        }); 
                });
                 
            });
            
          
        </script>
</body>
</html>
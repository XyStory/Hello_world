<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2021/8/31
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <link href="${ctx}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${ctx}/js/jquery-3.2.1.min.js"></script>
    <script src="${ctx}/js/bootstrap.min.js"></script>
    <script type="text/javascript">

        /*
        * 此方法就是构建标签页
        * */
        function addTabs(options) {
            console.log($("#" + options.id));
            console.log($("#" + options.id).length);
            //判断是否已存在指定ID的tab
            if ($("#" + options.id).length > 0) {
                $($("#" + options.id + "-li").children("a").get(0)).tab('show');
                $("#" + options.id).siblings().removeClass("active");
                $("#" + options.id).addClass("tab-pane fade in active");

                //重新加载窗体内容
                var iframeObj=$("#" + options.id).children("iframe");
                var src=iframeObj.attr("src");
                $("#" + options.id).children("iframe").attr("src",src);
            }else
            {
                //构建li元素
                var li = $("<li />", {
                    "id": options.id + "-li",
                });

                //构建a元素
                var a = $("<a />", {
                    "href": "#" + options.id,
                    "text": options.title,
                    "click": function () {
                        console.log($(this));
                        $(this).tab("show");
                    }
                });

                //构建span元素
                var aspan = $("<span />", {
                    "text": 'X',
                    "click": function () {
                        console.log($(this).parent().parent().prev().children("a").get(0));
                        $($(this).parent().parent().prev().children("a").get(0)).tab('show');
                        $(this).parent().parent().remove();
                        $("#" + options.id).prev().addClass("tab-pane fade in active");
                        $("#" + options.id).remove();
                    }
                });

                a.append(aspan);

                //合并li和a元素
                li.append(a);


                var ul = $("ul.nav-tabs");
                //合并ul和li元素
                ul.append(li);

                //添加完成显示当前li
                $(li).tab("show");

                //构建div内容元素
                var div = $("<div />", {
                    "id": options.id,
                    "class": "tab-pane fade in active",
                });

                var iframecontent="<iframe src=\""+options.url+"\" id=\"mainFrame\" name=\"mainFrame\" frameborder=\"0\" width=\"100%\"  height=\"650px\" frameBorder=\"0\"></iframe>";
                //兼容纯文本和html片段
                div.html(iframecontent);
                var container = $(".tab-content");
                container.append(div);
                //添加完成后显示div
                $(div).siblings().removeClass("active");
            }
        }


        /*
        * 标签页的名称
        * 标签页的id
        * 标签页内容的url地址
        * */
        function addtab(title,tabId,url) {

            addTabs({
                "id": tabId,
                "title": title,
                "url": url
            });
        }

        function exit() {
            location.href = "/logout";
        }
    </script>
</head>
<body>
<nav class="navbar navbar-inverse" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#example-navbar-collapse">
                <span class="sr-only">切换导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">
                <img src="images/a.jpg" height="100%" />
            </a>
        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a class="icon-bar" href="#">网站设置</a>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a>欢迎您,${user.username}</a>
                </li>
                <li><a href="#" onclick="exit()">安全退出</a>
                </li>
            </ul>
        </div>
    </div>
</nav>


<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2">
            <a data-toggle="collapse" data-parent="#accordion"
               href="#collapseOne" class="list-group-item">
                <!-- 小图标样式设置 -->
                <span class="glyphicon glyphicon-search"></span>
                部门维护
            </a>
            <div id="collapseOne" class="panel-collapse collapse">
                <div style="padding-left: 15px">
                    <a href="#" class="list-group-item" onclick="addtab('部门添加','adddept_tab','${ctx}/saveforward');">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                        部门添加
                    </a>
                    <a href="#" class="list-group-item" onclick="addtab('部门列表','deptlist_tab','${ctx}/show-dept');">
                        <span class="glyphicon glyphicon-align-left" aria-hidden="true"></span>
                        部门列表
                    </a>
                </div>
            </div>

            <a data-toggle="collapse" data-parent="#accordion"
               href="#collapseOne2" class="list-group-item">
                <span class="glyphicon glyphicon-search"></span>
                用户管理
            </a>
            <div id="collapseOne2" class="panel-collapse collapse">
                <div style="padding-left: 15px">
                    <a href="#"  class="list-group-item" onclick="addtab('用户查询','userlist_tab','${ctx}/show-user');">
                        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                        用户查询
                    </a>
                    <a href="#" class="list-group-item" onclick="addtab('添加用户','adduser_tab','${ctx}/saveuser-forward');">
                        <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                        添加用户
                    </a>
                </div>
            </div>

            <a data-toggle="collapse" data-parent="#accordion"
               href="#collapseOne3" class="list-group-item">
                <span class="glyphicon glyphicon-search"></span>
                职位管理
            </a>
            <div id="collapseOne3" class="panel-collapse collapse">
                <div style="padding-left: 15px">
                    <a href="#"  class="list-group-item" onclick="addtab('职位查询','joblist_tab','${ctx}/show-job');">
                        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                        职位查询
                    </a>
                    <a href="#" class="list-group-item" onclick="addtab('添加职位','addjob_tab','${ctx}/savejob-forward');">
                        <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                        添加职位
                    </a>
                </div>
            </div>

            <a data-toggle="collapse" data-parent="#accordion"
               href="#collapseOne4" class="list-group-item">
                <span class="glyphicon glyphicon-user"></span>
                员工管理
            </a>
            <div id="collapseOne4" class="panel-collapse collapse">
                <div style="padding-left: 15px">
                    <a href="#"  class="list-group-item" onclick="addtab('员工列表','employeelist_tab','${ctx}/show-employee');">
                        <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
                        员工列表
                    </a>
                    <a href="#" class="list-group-item" onclick="addtab('添加员工','addemployee_tab','${ctx}/saveforward-employee');">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                        添加员工
                    </a>
                </div>
            </div>

            <a data-toggle="collapse" data-parent="#accordion"
               href="#collapseOne5" class="list-group-item">
                <span class="glyphicon glyphicon-sort"></span>
                下载中心
            </a>
            <div id="collapseOne5" class="panel-collapse collapse">
                <div style="padding-left: 15px">
                    <a href="#"  class="list-group-item" onclick="addtab('文件查询/下载','documentlist_tab','${ctx}/show-document');">
                        <span class="glyphicon glyphicon-save" aria-hidden="true"></span>
                        文件查询/下载
                    </a>
                    <a href="#" class="list-group-item" onclick="addtab('上传文件','adddocument_tab','${ctx}/upload-forward');">
                        <span class="glyphicon glyphicon-open" aria-hidden="true"></span>
                        上传文件
                    </a>
                </div>
            </div>

        </div>
        <!--右边标签页及中央内容显示-->
        <div class="col-sm-10">
            <ul id="myTab" class="nav nav-tabs">
            </ul>
            <div id="myTabContent" class="tab-content">
            </div>
        </div>
    </div>
</div>
<!-- 底部页脚部分 -->
<div class="footer">
    <p class="text-center">
        2021 &copy; gec.
    </p>
</div>

</body>
</html>

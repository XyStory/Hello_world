<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <link href="${ctx}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${ctx}/js/jquery-3.2.1.min.js"></script>
    <script src="${ctx}/js/bootstrap.min.js"></script>
    <script>
        
        function save() {

            var loginName=$("input[name=loginName]").val();
            var pwd=$("input[name=pwd]").val();
            var status=$("select[name=status]").val();
            var username=$("input[name=username]").val();

            var jsondata={"loginName":loginName,"pwd":pwd,"status":status,"username":username};

            $.ajax({
                url:"${ctx}/save-user",
                type: "post",
                data: jsondata,
                dataType:"json",
                success:function (data) {

                    console.log(data);

                    if(data=='1') {
                        alert("添加成功");
                        window.location.href = "${ctx}/show-user";
                    }else {
                        alert("添加失败");
                    }
                }
            })
        }

    </script>
</head>
<body>
<div class="panel panel-default"></div>
<form class="form-horizontal">
    <div class="form-group">
        <label for="inputLoginName" class="col-sm-2 control-label">登录名：</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" id="inputLoginName" placeholder="login name" name="loginName">
        </div>
    </div>
    <div class="form-group">
        <label for="inputPwd" class="col-sm-2 control-label">密码：</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" name="pwd" id="inputPwd" placeholder="password">
        </div>
    </div>
    <div class="form-group">
        <label for="inputStatus" class="col-sm-2 control-label">状态：</label>
        <div class="col-sm-5">
            <select id="inputStatus" name="status" class="form-control">
                <option value="1" selected>启用</option>
                <option value="0">禁用</option>
            </select>
        </div>
    </div><div class="form-group">
    <label for="inputUsername" class="col-sm-2 control-label">用户名：</label>
    <div class="col-sm-5">
        <input type="text" class="form-control" id="inputUsername" placeholder="username" name="username">
    </div>
</div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="button" class="btn btn-default" onclick="save()">保存</button>
        </div>
    </div>
</form>
</body>
</html>

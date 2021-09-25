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
        
        function editSave() {

            var userId = $("input[name=userId]").val();
            var loginName=$("input[name=loginName]").val();
            var password=$("input[name=password]").val();
            var status=$("select[name=status]").val();
            var username = $("input[name=username]").val();


            var jsondata={"userId":userId,"loginName":loginName,"password":password,"status":status,"username":username};

            console.log(jsondata);

            $.ajax({
                url:"${ctx}/update-user",
                type: "post",
                data: jsondata,
                dataType:"json",
                success:function (data) {

                    console.log(data);

                    if(data=='1')
                    {
                        alert("修改成功");
                        window.location.href = "${ctx}/show-user";
                    }else
                    {
                        alert("修改失败");
                    }
                }
            })
        }

    </script>
</head>
<body>
<div class="panel panel-default"></div>
<form class="form-horizontal">
    <input type="hidden" name="userId" value="${userEdit.userId}"/>
    <div class="form-group">
        <label for="inputName" class="col-sm-1 control-label">登录名：</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="inputName" placeholder="name" name="loginName" value="${userEdit.loginName}" disabled>
        </div>
        <label for="inputPwd" class="col-sm-1 control-label">密码：</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="inputPwd" placeholder="password" name="password" value="${userEdit.pwd}" >
        </div>
    </div>
    <div class="form-group">
        <label for="inputName" class="col-sm-1 control-label">状态：</label>
        <div class="col-sm-4">
            <select id="selectStatus" name="status" class="form-control">
                <option ${userEdit.status == 0 ? 'selected' : ''} value="0">禁用</option>
                <option ${userEdit.status == 1 ? 'selected' : ''} value="1">启用</option>
                <option ${userEdit.status == 2 ? 'selected' : ''} value="2">超级管理员</option>
            </select>
        </div>
        <label for="inputUsername" class="col-sm-1 control-label">用户名：</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="inputUsername" placeholder="username" name="password" value="${userEdit.username}" >
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="button" class="btn btn-default" onclick="editSave()">保存</button>
        </div>
    </div>
</form>
</body>
</html>

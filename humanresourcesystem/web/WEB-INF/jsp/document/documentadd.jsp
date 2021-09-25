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

            var name=$("input[name=title]").val();
            var remark=$("textarea[name=remark]").val();

            var formData=new FormData();
            formData.append("file", $("#inputFile")[0].files[0]);
            formData.append("title", name);
            formData.append("remark", remark);

            $.ajax({
                url:"${ctx}/upload",
                type: "post",
                cache: false,
                data: formData,
                processData: false,
                contentType: false,
                success:function (data) {

                    console.log(data);

                    if(data=='1') {
                        alert("添加成功");
                        window.location.href = "${ctx}/show-document";
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
<form class="form-horizontal" enctype="multipart/form-data">
    <div class="form-group">
        <label for="inputName" class="col-sm-2 control-label">标题：</label>
        <div class="col-sm-9">
            <input type="text" class="form-control" id="inputName" placeholder="name" name="title" required>
        </div>
    </div>
    <div class="form-group">
        <label for="inputRemark" class="col-sm-2 control-label">正文：</label>
        <div class="col-sm-9">
            <textarea class="form-control" rows="3" name="remark" id="inputRemark"></textarea>
        </div>
    </div>
    <div class="form-group">
        <label for="inputFile" class="col-sm-2 control-label">选择文件：</label>
        <div class="col-sm-10">
            <input type="file" name="file" id="inputFile"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="button" class="btn btn-default" onclick="save()">保存</button>
            <button type="reset" class="btn btn-default">重置</button>
        </div>
    </div>
</form>
</body>
</html>

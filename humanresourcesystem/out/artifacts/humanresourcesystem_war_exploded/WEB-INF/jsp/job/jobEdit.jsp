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

            var name=$("input[name=jobName]").val();
            var remark=$("textarea[name=jobRemark]").val();
            var id=$("input[name=jobId]").val();


            var jsondata={"jobId":id,"jobName":name,"jobRemark":remark};

            console.log(jsondata);

            $.ajax({
                url:"${ctx}/update-job",
                type: "post",
                data: jsondata,
                dataType:"json",
                success:function (data) {

                    console.log(data);

                    if(data=='1')
                    {
                        alert("修改成功");
                        window.location.href = "${ctx}/show-job";
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
    <input type="hidden" name="jobId" value="${job.jobId}"/>
    <div class="form-group">
        <label for="inputName" class="col-sm-2 control-label">职位名：</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="inputName" placeholder="name" name="jobName" value="${job.jobName}">
        </div>
    </div>
    <div class="form-group">
        <label for="inputRemark" class="col-sm-2 control-label">备注：</label>
        <div class="col-sm-10">
            <textarea class="form-control" rows="3" name="jobRemark" id="inputRemark">${job.remark}</textarea>
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

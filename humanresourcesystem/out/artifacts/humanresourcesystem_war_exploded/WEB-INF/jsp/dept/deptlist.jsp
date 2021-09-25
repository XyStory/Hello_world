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
    <script type="text/javascript">

        $(document).ready(function () {

            $("#allCheckedId").on("change",function(){

                var checkboxObj = $(this);
                var isChecked = checkboxObj.prop('checked');

                if(isChecked)
                {
                    $("input[name=deptIds]").attr("checked",true);
                }else
                    $("input[name=deptIds]").attr("checked",false);
            });
        });


        function toPageNum(pagenum) {
            $("input[name=pageIndex]").val(pagenum);
            $("#queryform").attr("action","${ctx}/show-dept");
            $("#queryform").submit();
        }

        function query() {
            $("input[name=pageIndex]").val(1);
            $("#queryform").attr("action","${ctx}/show-dept");
            $("#queryform").submit();
        }

        function dels() {
            $("#queryform").attr("action","${ctx}/delete-dept");
            $("#queryform").submit();
        }

        function del(deptId) {
            $("input[name=deptIds]").val([deptId]);
            $("#queryform").attr("action","${ctx}/delete-dept");
            $("#queryform").submit();
        }


        function addtab(title,tabId,url) {
            parent.addtab(title,tabId,url);
        }

    </script>
</head>
<body>
<form  class="form-inline"  method="post" id="queryform">
<div class="panel panel-default">
    <div class="panel-heading">
        搜索
    </div>
    <div class="panel-body">
            <input type="hidden" value="${pageModel.pageIndex}" name="pageIndex"/>
            <div class="form-group">
                <label for="deptName">名称</label>
                <input type="text" class="form-control" id="deptName" placeholder="请输入名称" name="deptName" value="${deptName}">
            </div>
            <div class="form-group">
                <button type="button" class="btn btn-default" onclick="query()">开始搜索</button>
                <button type="button" class="btn btn-default" onclick="dels()">批量删除</button>
            </div>
    </div>
</div>
<!--
    列表展示
-->
<div class="table-responsive">
    <table class="table table-striped ">
        <thead>
        <tr>
            <th><input type="checkbox" id="allCheckedId" /> 全选</th>
            <th>部门ID</th>
            <th>部门名</th>
            <th>部门备注</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${deptList}" var="dept">
        <tr>
            <td><input type="checkbox" name="deptIds" value="${dept.deptId}"/></td>
            <td>${dept.deptId}</td>
            <td>${dept.name}</td>
            <td>${dept.remark}</td>
            <td>
                <div class="btn-group">
                    <a href="#" class="btn btn-default" onclick="addtab('部门编辑','tab-dept'+${dept.deptId},'${ctx}/updateforward?deptId=${dept.deptId}');">修改</a><a href="#" onclick="del(${dept.deptId})" class="btn btn-danger">删除</a>
                </div>
            </td>
        </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
</form>
<ul class="pagination" style="float: right;">
    <li><a href="#" onclick="toPageNum(${pageModel.pageIndex-1})">&laquo;</a>
    </li>
    <c:forEach begin="1" end="${pageModel.totalPageSum}" var="pagenum" >
        <c:choose>
            <c:when test="${pagenum==pageModel.pageIndex}">
                <li class="active"><a href="#" onclick="toPageNum(${pagenum})">${pagenum}</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="#" onclick="toPageNum(${pagenum})">${pagenum}</a></li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <li><a href="#" onclick="toPageNum(${pageModel.pageIndex+1})"><span aria-hidden="true">&raquo;</span></a>
    </li>
</ul>

</body>
</html>
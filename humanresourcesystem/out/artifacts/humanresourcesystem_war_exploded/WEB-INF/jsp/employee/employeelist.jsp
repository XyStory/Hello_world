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
                    $("input[name=employeeIds]").attr("checked",true);
                }else
                    $("input[name=employeeIds]").attr("checked",false);
            });
        });


        function toPageNum(pagenum) {
            $("input[name=pageIndex]").val(pagenum);
            $("#queryform").attr("action","${ctx}/show-employee");
            $("#queryform").submit();
        }

        function query() {
            $("input[name=pageIndex]").val(1);
            $("#queryform").attr("action","${ctx}/show-employee");
            $("#queryform").submit();
        }

        function dels() {
            $("#queryform").attr("action","${ctx}/delete-employee");
            $("#queryform").submit();
        }

        function del(employeeId) {
            $("input[name=employeeIds]").val([employeeId]);
            $("#queryform").attr("action","${ctx}/delete-employee");
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
            <label for="jobId">职位</label>
            <select name="jobId" id="jobId" class="form-control">
                <option value="0">--请选择职位--</option>
                <c:forEach items="${jobs}" var="job">
                    <option ${job.jobId == jobId ? 'selected' : ''} value="${job.jobId}">${job.jobName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="employeeName">名称</label>
            <input type="text" class="form-control" id="employeeName" placeholder="请输入名称" name="employeeName" value="${employeeName}">
        </div>
        <div class="form-group">
            <label for="cardId">身份证号码</label>
            <input type="text" class="form-control" id="cardId" placeholder="请输入身份证号码" name="cardId" value="${cardId}">
        </div>
    </div>

    <div class="panel-body">
        <div class="form-group">
            <label for="sex">性别</label>
            <select name="sex" id="sex" class="form-control">
                <option value="0">--请选择性别--</option>
                <option ${1 == sex ? 'selected' : ''} value="1">1</option>
                <option ${2 == sex ? 'selected' : ''} value="2">2</option>
            </select>
            <label for="phone">手机</label>
            <input type="text" class="form-control" id="phone" placeholder="手机号码" name="phone" value="${phone}">
            <label for="deptId">所属部门</label>
            <select name="deptId" id="deptId" class="form-control">
                <option value="0">--请选择部门--</option>
                <c:forEach items="${deptList}" var="dept">
                    <option ${dept.deptId == deptID ? 'selected' : ''} value="${dept.deptId}">${dept.name}</option>
                </c:forEach>
            </select>
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
            <th>姓名</th>
            <th>性别</th>
            <th>手机号码</th>
            <th>邮箱</th>
            <th>职位</th>
            <th>学历</th>
            <th>身份证号码</th>
            <th>部门</th>
            <th>联系地址</th>
            <th>建档日期</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${employees}" var="employee">
        <tr>
            <td><input type="checkbox" name="employeeIds" value="${employee.employeeId}"/></td>
            <td>${employee.employeeName}</td>
            <td>
                <c:if test="${employee.sex == 0}">未知</c:if>
                <c:if test="${employee.sex == 1}">男</c:if>
                <c:if test="${employee.sex == 2}">女</c:if>
            </td>
            <td>${employee.phone}</td>
            <td>${employee.email}</td>
            <td><c:forEach items="${jobs}" var="job"><c:if test="${job.jobId == employee.jobId}">${job.jobName}</c:if></c:forEach></td>
            <td>${employee.education}</td>
            <td>${employee.cardId}</td>
            <td><c:forEach items="${deptList}" var="dept"><c:if test="${dept.deptId == employee.deptId}">${dept.name}</c:if></c:forEach></td>
            <td>${employee.address}</td>
            <td>${employee.createDate}</td>
            <td>
                <div class="btn-group">
                    <a href="#" class="btn btn-default" onclick="addtab('员工编辑','tab-employee'+${employee.employeeId},'${ctx}/updateforward-employee?employeeId=${employee.employeeId}');">修改</a><a href="#" onclick="del(${employee.employeeId})" class="btn btn-danger">删除</a>
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
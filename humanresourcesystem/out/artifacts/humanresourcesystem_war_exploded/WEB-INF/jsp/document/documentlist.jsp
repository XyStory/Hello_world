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
                    $("input[name=documentIds]").attr("checked",true);
                }else
                    $("input[name=documentIds]").attr("checked",false);
            });
        });

        function toPageNum(pagenum) {
            $("input[name=pageIndex]").val(pagenum);
            $("#queryform").attr("action","${ctx}/show-document");
            $("#queryform").submit();
        }

        function query() {
            $("input[name=pageIndex]").val(1);
            $("#queryform").attr("action","${ctx}/show-document");
            $("#queryform").submit();
        }

        function dels() {
            $("#queryform").attr("action","${ctx}/delete-document");
            $("#queryform").submit();
        }

        function del(documentId) {
            $("input[name=documentIds]").val([documentId]);
            $("#queryform").attr("action","${ctx}/delete-document");
            $("#queryform").submit();
        }

        function downloadFile(documentId) {
            $("input[name=documentIds]").val([documentId]);
            $("#queryform").attr("action","${ctx}/download");
            $("#queryform").submit();
        }

        function getUsername(userId) {

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
        ??????
    </div>
    <div class="panel-body">
        <input type="hidden" value="${pageModel.pageIndex}" name="pageIndex"/>
        <div class="form-group">
            <label for="title">??????</label>
            <input type="text" class="form-control" id="title" placeholder="???????????????" name="title">
        </div>
        <div class="form-group">
            <label for="filename">?????????</label>
            <input type="text" class="form-control" id="filename" placeholder="??????????????????" name="filename">
        </div>
        <div class="form-group">
            <button type="button" class="btn btn-default" onclick="query()">????????????</button>
            <button type="button" class="btn btn-default" onclick="dels()">????????????</button>
        </div>
    </div>
</div>
<!--
    ????????????
-->
<div class="table-responsive">
    <table class="table table-striped ">
        <thead>
        <tr>
            <th><input type="checkbox" id="allCheckedId" /> ??????</th>
            <th>??????</th>
            <th>?????????</th>
            <th>??????</th>
            <th>????????????</th>
            <th>????????????</th>
            <th>??????</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${documents}" var="document">
        <tr>
            <td><input type="checkbox" name="documentIds" value="${document.documentId}"/></td>
            <td>${document.title}</td>
            <td>${document.filename}</td>
            <td>${document.remark}</td>
            <td>${document.getCreateDate()}</td>
            <td><c:forEach items="${users}" var="user"><c:if test="${user.userId == document.userId}">${user.username}</c:if></c:forEach></td>
            <td>
                <div class="btn-group">
                    <a href="#" class="btn btn-default" onclick="downloadFile(${document.documentId})">??????</a><a href="#" onclick="del(${document.documentId})" class="btn btn-danger">??????</a>
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
<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2021/8/30
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="${ctx}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${ctx}/js/jquery-3.2.1.min.js"></script>
    <script src="${ctx}/js/bootstrap.min.js"></script>
    <style>
        html{
            width: 100%;
            height: 100%;
            overflow: hidden;
            font-style: inherit;
        }
        body{
            width: 100%;
            height: 100%;
            font-family: 'Open Sans',sans-serif;
            margin: 0;
            background-color: #4A374A;
        }
        #login{
            position: absolute;
            top: 50%;
            left:50%;
            margin: -150px 0 0 -150px;
            width: 300px;
            height: 300px;
        }
        #login h1{
            color: #fff;
            letter-spacing: 1px;
            text-align: center;
        }
        h1{
            font-size: 2em;
            margin: 0.67em 0;
        }
        input{
            width: 278px;
            height: 18px;
            margin-bottom: 10px;
            outline: none;
            padding: 10px;
            font-size: 13px;
            color: #fff;
            border-top: 1px solid #312E3D;
            border-left: 1px solid #312E3D;
            border-right: 1px solid #312E3D;
            border-bottom: 1px solid #56536A;
            border-radius: 4px;
            background-color: #2D2D3F;
        }
    </style>
</head>
<body>
<div id="login">
    <h1>Login</h1>
    <form action="/login" method="post">
        <input type="text" required="required" class="form-control" placeholder="用户名" name="loginName" />
        <input type="password" required="required" class="form-control" placeholder="密码" name="password" />
        <input type="submit" class="btn btn-primary btn-lg btn-block input-lg" value="登录">
    </form>
</div>
</body>
</html>

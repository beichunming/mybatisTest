<%--
  Created by IntelliJ IDEA.
  User: beichunming
  Date: 2017/4/20
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>
<%-- 定义若干超链接. 每个超链接代表一个功能. 如: 分页查询用户等 --%>
<div style="padding: 15px; padding-left : 25px">

    <ul>
        <li>
            <span>用户管理</span>
            <ul>
                
                <li>
                    <%-- 分页查询用户数据. 如果未传递请求参数.那么默认查询第一页数据,每页显示3条数据.
                        请求参数名 : 第几页 - page.  每页显示多少行 - rows
                     --%>
                    <a href="user/getUsersByPage.action"
                       target="center"><span>查询用户</span></a>
                </li>
                <li>
                    <%-- 访问Controller的方法. 跳转到文件上传页面. 请求转发.
                     --%>
                    <a href="user/toUploadUsers.action"
                       target="center"><span>导入用户数据</span></a>
                </li>
            </ul>
        </li>
    </ul>

</div>
</body>
</html>
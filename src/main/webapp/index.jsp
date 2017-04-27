<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://www.springframework.org/tags/form" %>
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
<h3 align="left"><a  target="_top" href="user/logout.action">注销</a></h3><br>
<h4 align="center">用户信息</h4>
<table align="center" width="95%" border="1px" cellpadding="1px"
       cellspacing="1px">

    <tr>
        <td width="10%" align="center" bgcolor="#EEEEEE">姓名</td>
        <td width="10%" align="center" bgcolor="#EEEEEE">分组名称</td>
    </tr>

    <tr>
        <td width="10%" align="center" bgcolor="#EEEEEE">${sessionScope.userInfo.name}</td>
        <td width="10%" align="center" bgcolor="#EEEEEE">${sessionScope.userInfo.group.name}</td>
    </tr>

</table>
</body>
</html>

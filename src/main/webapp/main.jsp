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
<frameset rows="20%, * , 10%">
    <frame name="header" src="index.jsp"></frame>
    <frameset cols="20%, *">
        <frame name="left" src="${pageContext.request.contextPath}/left.jsp"></frame>
        <frame name="center" src="${pageContext.request.contextPath}/center.jsp"></frame>
    </frameset>
    <frame name="footer" src="${pageContext.request.contextPath}/footer.jsp"></frame>
</frameset>
</html>
<%--
  Created by IntelliJ IDEA.
  User: beichunming
  Date: 2017/4/20
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<center>
    <form action="user/doRegister.action" method="post">
        <table cellpadding="0" cellspacing="0" border="0">
            <caption>用户注册</caption>
            <tr>
                <td style="text-align:right; padding-right:5px">
                    用户名 :
                </td>
                <td style="text-align:left; padding-left:5px">
                    <input type="text" name="name" />
                </td>
            </tr>
            <tr>
                <td style="text-align:right; padding-right:5px">
                    用户登录名 :
                </td>
                <td style="text-align:left; padding-left:5px">
                    <input type="text" name="loginName" />
                </td>
            </tr>
            <tr>
                <td style="text-align:right; padding-right:5px">
                    密码 :
                </td>
                <td style="text-align:left; padding-left:5px">
                    <input type="text" name="loginPswd" />
                </td>
            </tr>
            <tr>
                <td style="text-align:right; padding-right:5px">
                    确认密码 :
                </td>
                <td style="text-align:left; padding-left:5px">
                    <input type="text" name="rePassword" />
                </td>
            </tr>
            <tr>
                <td style="text-align:right; padding-right:5px">
                    备注 :
                </td>
                <td style="text-align:left; padding-left:5px">
                    <input type="text" name="remark" />
                </td>
            </tr>
            <tr>
                <td style="text-align:right; padding-right:5px">
                    组名 :
                </td>
                <td style="text-align:left; padding-left:5px">
                    <select name="groupId">
                        <option selected="selected" value="0">请选择</option>
                        <c:forEach items="${groupList}" var="group">
                             <option value="${group.id}">${group.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td style="text-align:left; padding-left:5px" colspan="2">
                    <input type="submit" value="注册"/>&nbsp;&nbsp;&nbsp;
                    <input type="reset" value="重置" />&nbsp;&nbsp;&nbsp;
                    <input type="button" value="登录" onclick="javascript: window.location.href='user/login.jsp'" />
                </td>
            </tr>
        </table>
    </form>
</center>

</body>
</html>
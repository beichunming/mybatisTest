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

<center>
    <form action="user/login.action" method="post">
        <table cellpadding="0" cellspacing="0" border="0">
            <caption>用户登录</caption>
            <tr>
                <td style="text-align:right; padding-right:5px">
                    用户登录名 :
                </td>
                <td style="text-align:left; padding-left:5px">
                    <input type="text" name="TXT_NAME_01" />
                </td>
            </tr>
            <tr>
                <td style="text-align:right; padding-right:5px">
                    密码 :
                </td>
                <td style="text-align:left; padding-left:5px">
                    <input type="text" name="PWD_NAME_01" />
                </td>
            </tr>
            <tr>
                <td style="text-align:left; padding-left:5px" colspan="2">
                    <input type="submit" value="登录"/>&nbsp;&nbsp;&nbsp;
                    <input type="reset" value="重置" />&nbsp;&nbsp;&nbsp;
                    <input type="button" value="注册" onclick="javascript: window.location.href='user/preRegister.action'" />
                </td>
            </tr>
        </table>
    </form>
</center>

</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: beichunming
  Date: 2017/4/20
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
    <script type="text/javascript" src="js/myAjax.js"></script>
    <script type="text/javascript">
        function createTable() {
            myAjax("get","user/doPage.action?row=1",null,function(request){
                //获取响应数据
                var result=request.responseText;
                //处理
                //获取去div对象
                var showdiv = document.getElementById("showdiv");
                //清空div
                showdiv.innerHTML="";
                //create table
                var table = document.createElement("table");
                table.border="1.px";
                // 创建表头
                var tr = document.createElement("tr");
                var td1 = document.createElement("td");
                td1.innerHTML="id";
                var td2 = document.createElement("td");
                td2.innerHTML="name";
                var td3 = document.createElement("td");
                td3.innerHTML="loginName";
                var td4 = document.createElement("td");
                td4.innerHTML="groupId";
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                table.appendChild(tr);
                //使用eval将字符串转换为可执行的js代码
                eval("var user="+result);
                for(var i=0;i<user.length;i++){
                    var tr = document.createElement("tr");
                    var td1 = document.createElement("td");
                    td1.innerHTML=user[i].id;
                    var td2 = document.createElement("td");
                    td2.innerHTML=user[i].name;
                    var td3 = document.createElement("td");
                    td3.innerHTML=user[i].loginName;
                    var td4 = document.createElement("td");
                    td4.innerHTML=user[i].groupId;
                    tr.appendChild(td1);
                    tr.appendChild(td2);
                    tr.appendChild(td3);
                    tr.appendChild(td4);
                    table.appendChild(tr);
                }
                showdiv.appendChild(table);
            });
        }
    </script>

    <style type="text/css">
        #table{
            width: 400px;
            height: 100px;
        }
    </style>
</head>
<body>
    <input type="button" name="" value="try it look look" onclick="createTable()"/>
    <div id="showdiv"></div>
</body>
</html>
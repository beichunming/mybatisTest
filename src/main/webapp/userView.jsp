<%--
  Created by IntelliJ IDEA.
  User: beichunming
  Date: 2017/4/20
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript">
        
        $(function () {
            // 页面加载后立刻运行的代码. 发起一个异步请求.查询用户数据.
            AJAXgetUserByPage(1,6);
        });
        var currentPage = 1; // 当前是第几页
        var totalPages = 0; // 总计有多少页
        var pageSize = 0; // 每页显示多少行
        /*
         * 分页查询用户数据.
         * 请求参数 - 
         * 1. 查询第几页.
         * 2. 每页查询多少行.
         */
        function AJAXgetUserByPage(page, rows) {
            // 变量设置.设置当前页面的变量.
            currentPage = page;
            pageSize = rows;
            $.ajax({
                'url':'user/getUserByPage2.action', //
                'type':'post',
                'data':'page='+page+"&rows="+rows, // 请求参数
                'beforeSend':function () {
                    // 提交请求前的校验, 函数返回true,提交请求,函数没有返回提交请求. 函数返回false,终止提交.
                    return true;
                },
                'success':function (data) {
                    // 将查询结果的总计页面数量,赋值给页面变量.
                    totalPages = data.totalPages;

                    // 当前函数是回调函数. 当响应返回后,执行的代码. data参数是响应流中输出是数据.
                    // var obj = eval("("+data+")");
                    // alert(obj.total);
                    var tr = "";
                    /*
                     * $.each() - 循环迭代函数.
                     * 1. $.each(collection, function(i, n){});
                     * 	循环集合collection[数组,对象等], 每次循环过程中调用回调函数.
                     *  函数的参数 i - 索引   n - 本次循环的变量
                     * 2. collection.each(function(i, n){}); 循环collection集合[数组,map,对象]
                     */
                    $.each(data.userList,function (i, n) {
                        tr += '<tr>                           ';
                        tr += '	<th style="padding:5px">      ';
                        tr += n.name;
                        tr += '	</th>                         ';
                        tr += '	<th style="padding:5px">      ';
                        tr += n.loginName;
                        tr += '	</th>                         ';
                        tr += '	<th style="padding:5px">      ';
                        tr += n.remark;
                        tr += '	</th>                         ';
                        tr += '	<th style="padding:5px">      ';
                        tr += n.groupId;
                        tr += '	</th>                         ';
                        tr += '</tr>                          ';
                    });

                    var footer = "";
                    footer += '<tr>                                                                                 ';
                    footer += '	<td style="text-align:right; padding-right:10px; padding:5px" colspan="4">          ';
                    if(data.currentPage != 1){
                        footer += '		<input type="button" value="首页" onclick="firstPage();"/>&nbsp;&nbsp;          ';
                        footer += '		<input type="button" value="上一页" onclick="prePage();"/>&nbsp;&nbsp;          ';
                    }
                    if(data.currentPage != data.totalPages){
                        footer += '		<input type="button" value="下一页" onclick="nextPage();"/>&nbsp;&nbsp;         ';
                        footer += '		<input type="button" value="尾页" onclick="lastPage();"/>&nbsp;&nbsp;           ';
                    }
                    footer += '		<span>跳转到</span>                                                             ';
                    footer += '		<input type="text" name="page" size="3" id="toPageNo"/> / '+data.totalPages;
                    footer += '		<input type="button" value="跳转" onclick="toPageNo();"/>&nbsp;&nbsp;           ';
                    footer += '		<input type="button" value="导出用户数据" onclick="downloadUsers();"/>          ';
                    footer += '	</td>                                                                               ';
                    footer += '</tr>        ';

                    var tBody = $("#userView"); // id选择器.根据id属性查询标签.
                    tBody.empty(); // 清空当前标签内所有内容.
                    tBody.append(tr); // 在当前标签内部追加新的元素. tr
                    tBody.append(footer); // 在当前标签内部追加新的元素. footer
                }
            });
        }
        
        function prePage(){
            // 调用AJAXgetUserByPage()函数. 需要传递参数. page rows.
            if(currentPage > 1){
                AJAXgetUserByPage(currentPage-1, pageSize);
            }
        }
        function nextPage(){
            if(currentPage < totalPages){
                AJAXgetUserByPage(currentPage+1, pageSize);
            }
        }
        function firstPage(){
            if(currentPage > 1){
                AJAXgetUserByPage(1, pageSize);
            }
        }
        function lastPage(){
            if(currentPage < totalPages){
                AJAXgetUserByPage(totalPages, pageSize);
            }
        }
        function toPage(page){
            window.location.href='user/getUsersByPage.action?page='+page+'&rows=3';
        }
        function toPageNo(){// 跳转到第几页
           var pageNo = $("#toPageNo").val();
            if(pageNo < 1 || pageNo > totalPages){
                // 输入的页码超出范围
                AJAXgetUserByPage(1, pageSize);
            }else{
                // 输入的页面在有效范围内
                AJAXgetUserByPage(pageNo, pageSize);
            }
        }
        function downloadUsers(){
            var currentPage = ${items.currentPage};
            window.location.href="user/downloadUsers.action?page="+currentPage+"&rows=3";
        }
    </script>
</head>
<body>
<%-- 数据显示 --%>
<div style="text-align:center; padding: 15px">
    <table style="text-align:center" cellpadding="0" cellspacing="0" border="0">
        <caption>用户信息</caption>
        <thead>
        <tr>
            <th style="padding:5px">
                姓名
            </th>
            <th style="padding:5px">
                登录名
            </th>
            <th style="padding:5px">
                描述
            </th>
            <th style="padding:5px">
                分组名称
            </th>
        </tr>
        </thead>
        <tbody id="userView">

        </tbody>
    </table>
</div>

</body>
</html>
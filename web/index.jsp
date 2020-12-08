<%--
  Created by IntelliJ IDEA.
  User: Yoshino
  Date: 2020/6/28
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <script src="js/jquery-3.5.1.min.js"></script>
    <script>
        function login() {
            $.ajax({
                url:"toDo",
                data:{
                    cmd:"0",
                    username:document.getElementById("username").value,
                    password:document.getElementById("password").value
                },
                dataType:"json",
                success:function (d) {
                    //alert(d);
                    document.getElementById("msg").innerHTML=d.code;
                    document.getElementById("msg1").innerHTML=d.message;
                    document.getElementById("msg2").innerHTML=d.result;
                    console.log(d.result.toString());
                }
            });
        }

        function sign() {
            $.ajax({
                url:"toDo",
                data:{
                    cmd:"1",
                    username:document.getElementById("username_sign").value,
                    password:document.getElementById("password_sign").value
                },
                dataType:"json",
                success:function (d) {
                    //alert(d);
                    document.getElementById("msg_sign").innerHTML=d.code;
                    document.getElementById("msg1_sign").innerHTML=d.message;
                    document.getElementById("msg2_sign").innerHTML=JSON.parse(d.result);
                    console.log(d.result.toString());
                }
            });
        }

        function qq() {
            $.ajax({
                url:"toDo",
                data:{
                    cmd:"2",
                    username:document.getElementById("username_qq").value,
                    qqid:document.getElementById("qqid").value,
                    year:document.getElementById("year").value,
                    sex:document.getElementById("sex").value,
                    imgurl:document.getElementById("imgurl").value
                },
                dataType:"json",
                success:function (d) {
                    //alert(d);
                    document.getElementById("msg_qq").innerHTML=d.toString();
                }
            });
        }
    </script>
</head>
<body>
<form action="" method="post">

    <table border="1">
        <tr>
            <td>cmd:</td>
            <td>1 注册</td>
        </tr>
        <tr>
            <td>账号:</td>
            <td><input type="text" name="username" id="username_sign"></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input type="text" name="password" id="password_sign"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="button" value="注册" onclick="sign();">
        </tr>
        <tr>
            <td id="msg_sign" colspan="2" style="color: red"></td>
        </tr>
        <tr>
            <td id="msg1_sign" colspan="2" style="color: red"></td>
        </tr>
        <tr>
            <td id="msg2_sign" colspan="2" style="color: red"></td>
        </tr>
    </table>
</form>

<form action="" method="post">

    <table border="1">
        <tr>
            <td>cmd:</td>
            <td>0 登录</td>
        </tr>
        <tr>
            <td>账号:</td>
            <td><input type="text" name="username" id="username"></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input type="text" name="password" id="password"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="button" value="登录" onclick="login();">
        </tr>
        <tr>
            <td id="msg" colspan="2" style="color: red"></td>
        </tr>
        <tr>
            <td id="msg1" colspan="2" style="color: red"></td>
        </tr>
        <tr>
            <td id="msg2" colspan="2" style="color: red"></td>
        </tr>
    </table>
</form>

<form action="" method="post">

    <table border="1">
        <tr>
            <td>cmd:</td>
            <td>2 qq</td>
        </tr>
        <tr>
            <td>账号:</td>
            <td><input type="text" name="username" id="username_qq"></td>
        </tr>
        <tr>
            <td>qqid:</td>
            <td><input type="text" name="qqid" id="qqid"></td>
        </tr>
        <tr>
            <td>year:</td>
            <td><input type="text" name="year" id="year"></td>
        </tr>
        <tr>
            <td>sex:</td>
            <td><input type="text" name="sex" id="sex"></td>
        </tr>
        <tr>
            <td>imgurl:</td>
            <td><input type="text" name="imgurl" id="imgurl"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="button" value="登录" onclick="qq();">
        </tr>
        <tr>
            <td id="msg_qq" colspan="2" style="color: red"></td>
        </tr>
    </table>
</form>
</body>
</html>

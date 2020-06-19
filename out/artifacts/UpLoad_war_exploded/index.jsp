<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/5/26
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <h1>使用fileupload jar包上传下载</h1>
    <form action="UpFileServlet" method="post" enctype="multipart/form-data">
      用户名：<input type="text" name="username"/><br/>
      文件1：<input type="file" name="file1"/><br/>
      <input type="submit" value="提交"/>
    </form>
    <h1>使用SmartUpload jar包上传下载</h1>
    <form action="SmartUpFileServlet" method="post" enctype="multipart/form-data">
      用户名：<input type="text" name="username"/><br/>
      文件1：<input type="file" name="file1"/><br/>
      文件2：<input type="file" name="file2"><br>
      <input type="submit" value="提交"/>
    </form>
    <img src="./photo/never.jpg" alt="">
    <a href="SmartDownLoad?filename=never.jpg">下载照片</a>
  </body>
</html>

package com.rj.upload;

import com.jspsmart.upload.SmartUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SmartDownLoad extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filename = req.getParameter("filename");
//        实例化上传组件
        SmartUpload su = new SmartUpload();
//        序列化
        su.initialize(getServletConfig(),req,resp);
//        设置响应类型
        su.setContentDisposition(null);
        try {
            su.downloadFile("/photo/" + filename);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
    }
}

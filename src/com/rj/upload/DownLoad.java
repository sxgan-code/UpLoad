package com.rj.upload;

import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

public class DownLoad extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*设置编码格式*/
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("charset=utf-8");
        /*数据处理*/
        System.out.println("文件正在下载");
        /*下载的基本步骤*/
//        1.获取下载的文件名
        String downFileName = "未闻花名.jpg";
//        2.读取要下载的文件内容（需要用到servletContext对象）
        //创建servletContext对象
        ServletContext servletContext = this.getServletContext();
        //获取文件类型
        String mimeType = servletContext.getMimeType("/photo/" + downFileName);
        System.out.println("下载的文件类型："+mimeType);
//        3.在回传前，通过响应头告诉客户端返回的数据类型
        //设置响应的文件类型
        resp.setContentType(mimeType+";charset=utf-8");
//        4.告诉客户端收到的数据是用于下载的,并且设置文件名的格式为utf-8
        /*判断是不是火狐*/
        if(req.getHeader("User-Agent").contains("Firefox")){
            //如果含有Firefox就用base64编码(也支持Chrome)解决决火狐乱码问题
            resp.setHeader("Content-Disposition","attachment;filename=?UTF-8?B?"+new BASE64Encoder().encode(downFileName.getBytes("UTF-8")) +"?=");
        }else {
            //使用URLEncoder即可,URLEncoder.encode适用于Chrome和IE，不适用于火狐
            resp.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(downFileName,"utf-8"));
        }
        //Content-Disposition :响应头，表示收到的文件如何处理
        //attachment;filename :attachment是附件的意思，filename表示要下载的文件名
//        5.将下载的内容回传客户端
        //获取文件输入流
        InputStream inputStream = servletContext.getResourceAsStream("/photo/"+downFileName);
        //获取输出流
        OutputStream outputStream = resp.getOutputStream();
        //响应中将输入流复制到输出流
        IOUtils.copy(inputStream,outputStream);
    }
}

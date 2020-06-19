package com.rj.upload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UpFileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*设置编码格式*/
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        /*数据处理*/
        System.out.println("文件上传了");
        if(ServletFileUpload.isMultipartContent(req)){//判断上传的文件是否是多段的，只有多段才能解析
            /**
             * 1、创建工厂
             * 2、创建解析器
             * 3、得到文件列表
             */
//        1.创建工厂
            DiskFileItemFactory dfif = new DiskFileItemFactory();
//        2.创建解析器
            ServletFileUpload servletFileUpload = new ServletFileUpload(dfif);
    
            try {
                //        3.使用解析器对象来解析request，得到FileItem列表
                List<FileItem> fileItems = servletFileUpload.parseRequest(req);
                //遍历列表进行处理
                for (FileItem fileItem : fileItems) {
                    if(fileItem.isFormField()){//判断是否是普通表单项
                        //普通表单项
                        System.out.println("获取普通表单项的name名称："+fileItem.getFieldName());
                        //获取值，同时设置编码格式
                        System.out.println("获取普通表单项的value值："+fileItem.getString("UTF-8"));
                
                    }else {
                        //文件表单项
                        // 获取文件的名称：
                        String fileName = fileItem.getName();
                        System.out.println("文件名："+fileName);
                        /*判断文件名是否为空，或null，如是则用户没有选择文件*/
                        if(fileName==null||fileName.isEmpty()){
                            continue;
                        }
                        //将文件保存到指定位置
                        System.out.println("hello");
                        fileItem.write(new File("E:\\JAVA\\JAVAWebPor\\UpLoad\\web\\photo" ,fileName));
                        //获取文件类型
                        System.out.println(fileItem.getContentType());
                        //获取文件的大小
                        System.out.println(fileItem.getSize());
                
                    }
            
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
    }
    
}

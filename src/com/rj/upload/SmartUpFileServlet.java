package com.rj.upload;

import com.jspsmart.upload.SmartUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class SmartUpFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        设置文件路径，字符串类型后面会用，分开写
        String filePath ="E:\\JAVA\\JAVAWebPor\\UpLoad\\web\\photo";
//        创建虚拟文件目录
        
        File file = new File(filePath);
        if(!file.exists()){
//             文件不存在创建文件夹
            file.mkdir();
        }
//        实例化上传组件
        SmartUpload su = new SmartUpload();
//        初始化
        su.initialize(getServletConfig(),req,resp);
        
        
//        设置单个文件最大长度限制为10MB
        su.setMaxFileSize(1024*1024*10);
//        设定允许上传文件的总长度
        su.setTotalMaxFileSize(1024*1024*20);
//        设置允许的文件类型（后缀名），字符串类型每个后缀用英文逗号隔开，后缀名区分大小写
        su.setAllowedFilesList("txt,png,jpg,png");

        String str = null;
        String name=null;
        try {
            //设置禁止上传的文件文件类型
            su.setDeniedFilesList("doc,xml");
            //上传文件
            su.upload();

            //接收请求参数必须在upload后方能获取到
            name = su.getRequest().getParameter("username");
            str = name+"：上传成功！";
            //            获取多个文件
            for( int i = 0 ;i <2 ; i++ ){
                
                com.jspsmart.upload.File upFile = su.getFiles().getFile(i);
//                判断用户是否选择了文件，选择了为false
                if(!upFile.isMissing()){
                    String filePathName = upFile.getFilePathName();
                    System.out.println(filePathName);
                    //保存文件,注意是字符串地址
                    upFile.saveAs(filePath+"\\"+"photo"+i+"."+upFile.getFileExt());
                }
            }
        }catch (Exception e) {
            if(name==null){
                str="：上传失败！";
            }else {
                str=name+"：上传失败！";
            }
           
            e.printStackTrace();
        }finally {
            System.out.println(str);
        }
        resp.sendRedirect("index.jsp");
    }
}

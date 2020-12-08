package action;


import model.UserModel;
import utils.GetUUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.UUID;

public class CmdAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost");

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        //获取数据
        String cmdStr = req.getParameter("cmd");
        if(cmdStr == null){
            cmdStr = "999";
        }
        int cmd = Integer.parseInt(cmdStr);
        switch (cmd){
            case 999:
                System.out.println("cmd null error");
                outPage("cmd null error:"+cmd,resp);
                break;
            case 0: //登录
                login(req,resp);
                break;
            case 1: //注册
                sign(req,resp);
                break;
            case 2: //qq登录
                qq(req,resp);
                break;
            case 3: //发送验证码
                qq(req,resp);
                break;
            default:
                System.out.println("unknown cmd error");
                outPage("unknown cmd error:"+cmd,resp);
        }

    }


    private void qq(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String result = "0";
        String str = "";
        String uuid = GetUUID.get();
        UserModel um = new UserModel();
        String username = req.getParameter("username");
        String sex = req.getParameter("sex");
        String qqId = req.getParameter("qqid");
        String year = req.getParameter("year");
        String imgUrl = req.getParameter("imgurl");
        try {
            result = um.qq(uuid,username,sex,qqId,year,imgUrl);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        if(!result.equals("1")){
            str = result;
        }else{
            str = "{ \"code\":1,\"message\":\"qq失败\" }";
        }
        outPage(str,resp);
    }

    private void sign(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String result = "0";
        String str = "";
        String uuid = GetUUID.get();
        UserModel um = new UserModel();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            result = um.sign(uuid,username,password);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        if(result.equals("0")){
            str = "{ \"code\":0,\"message\":\"注册成功\" }";
        }else{
            str = "{ \"code\":1,\"message\":\"注册失败，用户已存在\" }";
        }
        outPage(str,resp);
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String result = "0";
        String str = "";
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserModel um = new UserModel();
        try {
            result = um.login(username,password);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        if(result.equals("1")){
            str = "{ \"code\":1,\"message\":\"登录失败，密码错误\" ,\"result\":"+result+"}";
        }else if(result.equals("2")){
            str = "{ \"code\":2,\"message\":\"登录失败，没有该用户\"  ,\"result\":"+result+"}";
        }else{
            str = "{ \"code\":0,\"message\":\"登录成功\" ,\"result\":"+result+"}";
        }
        outPage(str,resp);
    }

    private void outPage(String str,HttpServletResponse resp) throws IOException {
        System.out.println("outPage:==========="+str);
        PrintWriter out=resp.getWriter();
        out.print(str);
        out.flush();
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

}

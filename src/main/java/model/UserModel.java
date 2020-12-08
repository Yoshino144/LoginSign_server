package model;

import dao.UserDao;

import java.sql.SQLException;

public class UserModel {

    public String login(String username,String password) throws SQLException, ClassNotFoundException {

        UserDao ud = new UserDao();
        String pwd = ud.queryUserByUserName(username);
        if(pwd!=null){
            if(password.equals(pwd)){
                System.out.println("UserModel==="+"登陆成功");
                return ud.getJson();
            }else{
                System.out.println("UserModel==="+"登录失败");

                return "1";
            }
        }else{
            System.out.println("UserModel==="+"登陆失败，账号不存在");
            return "2";
        }
    }

    public String sign(String uuid,String username,String password) throws SQLException, ClassNotFoundException {

        UserDao ud = new UserDao();
        int pwd = ud.addNewUser(uuid,username,password);
        if(pwd == 1){
            System.out.println("UserModel==="+"注册成功");
            return "0";
        }else{
            System.out.println("UserModel==="+"注册失败，账号已存在");
            return "1";
        }
    }

    public String qq(String uuid,String username,String sex,String qqId,String year,String imgUrl) throws SQLException, ClassNotFoundException {

        UserDao ud = new UserDao();
        String pwd = ud.qq(uuid,username,sex,qqId,year,imgUrl);
        if(!pwd.equals("false")){
            System.out.println("UserModel==="+"qq成功");
            return pwd;
        }else{
            System.out.println("UserModel==="+"qq失败");
            return "1";
        }
    }

}

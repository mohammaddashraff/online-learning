package com.example.admin.Service;

public class adminService {
    public boolean signIn(String userName , String password){
        if(userName.equals("admin")&&password.equals("admin")){
            return true;
        }
        return false;
    }
}

package com.journaldev.jsf.beans;

import com.journaldev.jsf.dao.LoginDAO;
import com.journaldev.jsf.entity.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class Register implements Serializable {

    private static final long serialVersionUID = 1094801825228386363L;

    private String pwd;
    private String userName;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String register(){
        User user = new User();
        user.setUsername(userName);
        user.setPassword(pwd);
        boolean valid = LoginDAO.register(user);
        if(valid){
            return "login";
        }else{
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Registration Failed",
                    "Please enter correct username and Password"));
            return "register";
        }
    }


}

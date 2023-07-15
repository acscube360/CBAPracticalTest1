package com.example.arunawijesinghe.model;

public class LoginResponse {
    private int res_code;
    private String res_desc;
    private User user_data;

    public LoginResponse(int res_code, String res_desc, User user_data) {
        this.res_code = res_code;
        this.res_desc = res_desc;
        this.user_data = user_data;
    }

    public LoginResponse() {

    }

    public int getRes_code() {
        return res_code;
    }

    public void setRes_code(int res_code) {
        this.res_code = res_code;
    }

    public String getRes_desc() {
        return res_desc;
    }

    public void setRes_desc(String res_desc) {
        this.res_desc = res_desc;
    }

    public User getUser() {
        return user_data;
    }

    public void setUser(User user_data) {
        this.user_data = user_data;
    }
}

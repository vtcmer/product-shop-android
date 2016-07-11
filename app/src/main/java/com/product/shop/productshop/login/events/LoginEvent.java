package com.product.shop.productshop.login.events;

/**
 * Created by vtcmer on 10/07/2016.
 */
public class LoginEvent {

    public static final int LOGIN_SUCCESS = 0;
    public static final int LOGIN_FAIL = 1;
    public static final int REGISTER_SUCCESS = 2;
    public static final int REGISTER_FAIL = 3;

    private int type;
    private String msg;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

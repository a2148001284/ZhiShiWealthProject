package com.zhouy.login.util;

import com.zhouy.login.dao.pojo.User;

public class UserThreadLocal {

    private UserThreadLocal(){}

    private static final ThreadLocal<User> LOCAL =new ThreadLocal<>();

    public static void put(User user){ LOCAL.set(user);}

    public static User get() { return LOCAL.get();}

    public static void remove(){ LOCAL.remove();
    }
}

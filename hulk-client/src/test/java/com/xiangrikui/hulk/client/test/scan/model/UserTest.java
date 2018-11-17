package com.xiangrikui.hulk.client.test.scan.model;

import java.lang.reflect.Field;

/**
 * 创建时间：2017年4月1日
 * <p>修改时间：2017年4月1日
 * <p>类说明：TODO
 * 
 * @author jerry
 * @version 1.0
 */
public class UserTest {

    public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalAccessException {
        User user = new User();
        Class<?> clazz = user.getClass();
        Field field = clazz.getDeclaredField("userName");
        field.setAccessible(true);
        System.out.println(field.get(user));
        field.set(user, "9000");
        System.out.println(field.get(user));
        field.set(user, "8000");
        System.out.println(field.get(user));
    }
}

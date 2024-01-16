package com.example.usercenter.service;

import com.example.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author nicefang
* &#064;description  针对表【user】的数据库操作Service
* &#064;createDate  2023-10-30 21:06:35
 */
public interface UserService extends IService<User> {
    /**
     *  用户登录态
     */
    /**
     * 用户注释
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkpassword  用户确认密码
     */
    long userRegister(String userAccount, String userPassword,String checkpassword,String planetcode);

    /**
     * 用户登录
     *
     * @return
     */
    User dologin(String userAccount, String userPassword, HttpServletRequest request);

    //用户脱敏
    User getSaftyUser(User user);
    /**
     * 用户注销
     */
    int userlogout(HttpServletRequest request);

    /**
     * 根据tag查询用户
     * @return
     */
    List<User> searchuserbytags(List<String> tagnamelist);

    boolean isAdmin(HttpServletRequest request);
    boolean isAdmin(User user);

    /**
     * 用户更新
     */
    int updateUser(User user, User loginuser);

    User getLoninUser(HttpServletRequest request);

}

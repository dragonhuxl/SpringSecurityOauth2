package com.example.demo.entity;

import javax.persistence.*;

@Table(name = "project_user")
public class ProjectUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    /**
     * 用户中文名称
     */
    @Column(name = "user_name_cn")
    private String userNameCn;

    /**
     * 密码
     */
    @Column(name = "user_password")
    private String userPassword;

    /**
     * 性别
     */
    @Column(name = "user_sex")
    private String userSex;

    /**
     * 邮箱
     */
    @Column(name = "user_email")
    private String userEmail;

    /**
     * 用户简介和描述
     */
    @Column(name = "user_intro")
    private String userIntro;



    //只有名字的最后一个字--用于页面展示
    private String singleUserName;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNameCn() {
        return userNameCn;
    }

    public void setUserNameCn(String userNameCn) {
        this.userNameCn = userNameCn;
    }

    /**
     * 获取密码
     *
     * @return user_password - 密码
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * 设置密码
     *
     * @param userPassword 密码
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * 获取性别
     *
     * @return user_sex - 性别
     */
    public String getUserSex() {
        return userSex;
    }

    /**
     * 设置性别
     *
     * @param userSex 性别
     */
    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    /**
     * 获取邮箱
     *
     * @return user_email - 邮箱
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * 设置邮箱
     *
     * @param userEmail 邮箱
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * 获取用户简介和描述
     *
     * @return user_intro - 用户简介和描述
     */
    public String getUserIntro() {
        return userIntro;
    }

    /**
     * 设置用户简介和描述
     *
     * @param userIntro 用户简介和描述
     */
    public void setUserIntro(String userIntro) {
        this.userIntro = userIntro;
    }

    public String getSingleUserName() {
        return singleUserName;
    }

    public void setSingleUserName(String singleUserName) {
        this.singleUserName = singleUserName;
    }
}
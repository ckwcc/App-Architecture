package com.ckw.zfsoft.ckwapparchitecture.entity;

/**
 * Created by ckw
 * on 2017/12/13.
 */

public class User {
    private String name; //用户名
    private String role; //
    private String department; //部门
    private String userId; //用户id
    private String headPicturePath;   //我的头像路径
    private String appname; // app 名字
    private String schoolName;//学校名称
    private String className;//班级
    private String gradeName;//年纪
    private String nowSchoolYearTerm;//当前学年学期
    private String app_token; // token

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHeadPicturePath() {
        return headPicturePath;
    }

    public void setHeadPicturePath(String headPicturePath) {
        this.headPicturePath = headPicturePath;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getNowSchoolYearTerm() {
        return nowSchoolYearTerm;
    }

    public void setNowSchoolYearTerm(String nowSchoolYearTerm) {
        this.nowSchoolYearTerm = nowSchoolYearTerm;
    }

    public String getApp_token() {
        return app_token;
    }

    public void setApp_token(String app_token) {
        this.app_token = app_token;
    }
}

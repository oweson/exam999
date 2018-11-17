package top.sun521.dao;

import org.springframework.transaction.annotation.Transactional;
import top.sea521.mapper.CollegeMapper;
import top.sea521.mapper.UserloginMapper;
import top.sea521.po.College;
import top.sea521.po.Userlogin;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/6/13 0013 17:20
 */
public class TestDao extends  BaseTest {
    @Autowired
    UserloginMapper userloginMapper;
    @Autowired
    CollegeMapper collegeMapper;

    @Test
    public void testSth(){
        College college = collegeMapper.selectByPrimaryKey(1);
        System.out.println(college + "------------------------------------------------------");


        System.out.println("");
    }

    @Test
    public void testMyLogin() {
        Userlogin admin = userloginMapper.selectLogin("admin", "123");
        System.out.println(admin.toString() + "-----------------------------------------------");
    }

    @Test
    public void testMyInsert() {
        Userlogin userlogin = new Userlogin();
        userlogin.setPassword("123");
        userlogin.setUsername("tomcat");
        userlogin.setRole(1);
        int i = userloginMapper.myInsert(userlogin);
        System.out.println(i + "=====================================");
    }

    @Test
    @Transactional

    public void deleteOneUser() {
        int i = userloginMapper.deleteByPrimaryKey(16);
        System.out.println(i + "===========================================");

    }

}

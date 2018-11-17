package top.sea521.service;

import top.sea521.po.Userlogin;


public interface UserloginService {

    /**
     * 1 根据名字查找用户
     */
    Userlogin findByName(String name) throws Exception;

    /**
     * 2 保存用户登录信息
     */
    void save(Userlogin userlogin) throws Exception;

    /**
     * 3 根据姓名删除？？？？？？？？？？？？？？？？？
     */
    void removeByName(String name) throws Exception;

    /**
     * 5 根据用户名更新
     */
    void updateByName(String name, Userlogin userlogin);

}

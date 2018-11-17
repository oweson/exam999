package top.sea521.service;

import top.sea521.po.TeacherCustom;

import java.util.List;

/**
 * Teacher老师Service层
 */
public interface TeacherService {

    /**
     * 1 根据id更新老师信息
     */
    void updateById(Integer id, TeacherCustom teacherCustom) throws Exception;

    /**
     * 2 根据id删除老师信息
     */
    void removeById(Integer id) throws Exception;

    /**
     * 3 获取分页查询老师信息
     */
    List<TeacherCustom> findByPaging(Integer toPageNo) throws Exception;

    /**
     * 4 保存老师信息
     */
    Boolean save(TeacherCustom teacherCustom) throws Exception;

    /**
     * 5获取老师总数
     */
    int getCountTeacher() throws Exception;

    /**
     * 6 根据id查询
     */
    TeacherCustom findById(Integer id) throws Exception;

    /**
     * 7 根据名字查询
     */
    List<TeacherCustom> findByName(String name) throws Exception;

    /**
     * 8获取全部教师
     */
    List<TeacherCustom> findAll() throws Exception;
}

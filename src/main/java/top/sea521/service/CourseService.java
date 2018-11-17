package top.sea521.service;

import top.sea521.po.CourseCustom;

import java.util.List;


public interface CourseService {
    /**
     * 1 根据id更新课程信息
     */
    void upadteById(Integer id, CourseCustom courseCustom) throws Exception;

    /**
     * 2 根据id删除课程信息
     */
    Boolean removeById(Integer id) throws Exception;

    /**
     * 3 获取分页查询课程信息
     */
    List<CourseCustom> findByPaging(Integer toPageNo) throws Exception;

    /**
     * 4 插入课程信息
     */
    Boolean save(CourseCustom couseCustom) throws Exception;

    /**
     * 5 获取课程总数
     */
    int getCountCouse() throws Exception;

    //根据id查询
    CourseCustom findById(Integer id) throws Exception;

    //根据名字查询
    List<CourseCustom> findByName(String name) throws Exception;

    //根据教师id查找课程
    List<CourseCustom> findByTeacherID(Integer id) throws Exception;
}

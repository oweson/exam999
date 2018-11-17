package top.sea521.service;

import top.sea521.po.SelectedCourseCustom;

import java.util.List;

/**
 * 选课表servic层
 */
public interface SelectedCourseService {

    /**
     * 1 根据课程ID查询选过这门课程的人员信息
     */
    List<SelectedCourseCustom> findByCourseID(Integer id) throws Exception;

    /**
     * 2 根据课程id分页查询课程
     */
    List<SelectedCourseCustom> findByCourseIDPaging(Integer page, Integer id) throws Exception;

    /**
     * 3 获取该课程学生数
     */
    Integer countByCourseID(Integer id) throws Exception;

    /**
     * 4 查询指定学生成绩
     */
    SelectedCourseCustom findOne(SelectedCourseCustom selectedCourseCustom) throws Exception;

    /**
     * 5 打分
     */
    void updataOne(SelectedCourseCustom selectedCourseCustom) throws Exception;

    /**
     * 6选课
     */
    void save(SelectedCourseCustom selectedCourseCustom) throws Exception;

    /**
     * 7 根据学生id查找课程
     */
    List<SelectedCourseCustom> findByStudentID(Integer id) throws Exception;

    /**
     * 8 退课
     */
    void remove(SelectedCourseCustom selectedCourseCustom) throws Exception;


}

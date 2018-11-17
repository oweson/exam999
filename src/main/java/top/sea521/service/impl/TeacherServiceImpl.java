package top.sea521.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.sea521.exception.CustomException;
import top.sea521.mapper.CollegeMapper;
import top.sea521.mapper.CourseMapper;
import top.sea521.mapper.TeacherMapper;
import top.sea521.mapper.TeacherMapperCustom;
import top.sea521.po.*;
import top.sea521.service.TeacherService;

import java.util.ArrayList;
import java.util.List;


@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private TeacherMapperCustom teacherMapperCustom;

    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private CourseMapper courseMapper;

    /**
     * 1 根据教师的id进行更新
     */
    public void updateById(Integer id, TeacherCustom teacherCustom) throws Exception {
        teacherMapper.updateByPrimaryKey(teacherCustom);
    }

    /**
     * 2 删除教师，必须删除他的课程
     */
    public void removeById(Integer id) throws Exception {
        CourseExample courseExample = new CourseExample();

        CourseExample.Criteria criteria = courseExample.createCriteria();
        criteria.andTeacheridEqualTo(id);
        /**查询这个老师的课程*/
        List<Course> list = courseMapper.selectByExample(courseExample);

        if (list.size() != 0) {
            throw new CustomException("请先删除该名老师所教授的课程");
        }
        /**课程已经删除，删除老师根据老师的id*/

        teacherMapper.deleteByPrimaryKey(id);
    }

    /**
     * 3 老师的分页
     */
    public List<TeacherCustom> findByPaging(Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        List<TeacherCustom> list = teacherMapperCustom.findByPaging(pagingVO);

        return list;
    }

    /**
     * 4 修改老师?????????????????????????
     */
    public Boolean save(TeacherCustom teacherCustom) throws Exception {

        Teacher tea = teacherMapper.selectByPrimaryKey(teacherCustom.getUserid());
        if (tea == null) {
            teacherMapper.insert(teacherCustom);
            return true;
        }
        return false;
    }

    /**
     * 5 查询老师的总数，为分页做准备
     */
    public int getCountTeacher() throws Exception {
        /**自定义查询对象*/
        TeacherExample teacherExample = new TeacherExample();
        /**通过criteria构造查询条件*/
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andUseridIsNotNull();

        return teacherMapper.countByExample(teacherExample);
    }

    /**
     * 6 根据老师的id查询老师的详情
     */
    public TeacherCustom findById(Integer id) throws Exception {
        Teacher teacher = teacherMapper.selectByPrimaryKey(id);
        TeacherCustom teacherCustom = null;
        if (teacher != null) {
            /**?????????????????????????????????????*/
            teacherCustom = new TeacherCustom();
            /**1.import org.springframework.beans.BeanUtils
             这个包下的BeanUtils.copyProperties(对象A,对象B),是将A的值复制到B

             2.import org.apache.commons.beanutils.BeanUtils
             这个包下的BeanUtils.copyProperties(对象A,对象B),是将B的值复制到A

             ---------------------

             本文来自 zhangSir134 的CSDN 博客 ，全文地址请点击：https://blog.csdn.net/java_zhangshuai/article/details/80273970?utm_source=copy */
            BeanUtils.copyProperties(teacher, teacherCustom);
            /**这里是spring   teacher赋值给teacherCustom*/
        }

        return teacherCustom;
    }

    /**
     * 7 根据用户名查询老师
     */
    public List<TeacherCustom> findByName(String name) throws Exception {
        TeacherExample teacherExample = new TeacherExample();
        //自定义查询条件
        TeacherExample.Criteria criteria = teacherExample.createCriteria();

        criteria.andUsernameLike("%" + name + "%");

        List<Teacher> list = teacherMapper.selectByExample(teacherExample);

        List<TeacherCustom> teacherCustomList = null;

        if (list != null) {
            teacherCustomList = new ArrayList<TeacherCustom>();
            for (Teacher t : list) {
                TeacherCustom teacherCustom = new TeacherCustom();
                /**类拷贝,逐一的拷贝*/
                BeanUtils.copyProperties(t, teacherCustom);
                /**获取院系信息，colleage表，需要设置到teacherCustomer*/
                College college = collegeMapper.selectByPrimaryKey(t.getCollegeid());
                teacherCustom.setcollegeName(college.getCollegename());
/**设置完毕放入集合*/
                teacherCustomList.add(teacherCustom);
            }
        }

        return teacherCustomList;
    }

    public List<TeacherCustom> findAll() throws Exception {

        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();

        criteria.andUsernameIsNotNull();

        List<Teacher> list = teacherMapper.selectByExample(teacherExample);
        List<TeacherCustom> teacherCustomsList = null;
        if (list != null) {
            teacherCustomsList = new ArrayList<TeacherCustom>();
            for (Teacher t : list) {
                TeacherCustom teacherCustom = new TeacherCustom();
                BeanUtils.copyProperties(t, teacherCustom);
                teacherCustomsList.add(teacherCustom);
            }
        }
        return teacherCustomsList;
    }
}

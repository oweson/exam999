package top.sea521.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.sea521.po.CourseCustom;
import top.sea521.po.SelectedCourseCustom;
import top.sea521.service.CourseService;
import top.sea521.service.SelectedCourseService;
import top.sea521.service.TeacherService;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Resource(name = "teacherServiceImpl")
    private TeacherService teacherService;

    @Resource(name = "courseServiceImpl")
    private CourseService courseService;

    @Resource(name = "selectedCourseServiceImpl")
    private SelectedCourseService selectedCourseService;

    /**
     * 1  显示我的课程
     */
    @RequestMapping(value = "/showCourse")
    public String stuCourseShow(Model model) throws Exception {

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        List<CourseCustom> list = courseService.findByTeacherID(Integer.parseInt(username));
        model.addAttribute("courseList", list);

        return "teacher/showCourse";
    }

    /**
     * 2  显示成绩，选过这门课的信息显示
     */
    @RequestMapping(value = "/gradeCourse")
    public String gradeCourse(Integer id, Model model) throws Exception {
        if (id == null) {
            return "";
        }
        List<SelectedCourseCustom> list = selectedCourseService.findByCourseID(id);
        model.addAttribute("selectedCourseList", list);
        return "teacher/showGrade";
    }

    /**
     * 3 打分
     */
    //todo
    @RequestMapping(value = "/mark", method = {RequestMethod.GET})
    public String markUI(SelectedCourseCustom scc, Model model) throws Exception {

        SelectedCourseCustom selectedCourseCustom = selectedCourseService.findOne(scc);

        model.addAttribute("selectedCourse", selectedCourseCustom);

        return "teacher/mark";
    }

    /**
     * 4 打分
     */
    @RequestMapping(value = "/mark", method = {RequestMethod.POST})
    public String mark(SelectedCourseCustom scc) throws Exception {
        /**分数入库*/
        selectedCourseService.updataOne(scc);

        return "redirect:/teacher/gradeCourse?id=" + scc.getCourseid();
    }

    /**
     * 5 修改密码
     */
    @RequestMapping(value = "/passwordRest")
    public String passwordRest() throws Exception {
        return "teacher/passwordRest";
    }

    /**
     * 6 修改分数
     */
    @RequestMapping(value = "/editMark", method = RequestMethod.GET)
    public String edit(SelectedCourseCustom scc, Model model) throws Exception {
        SelectedCourseCustom selectedCourseCustom = selectedCourseService.findOne(scc);

        model.addAttribute("selectedCourse", selectedCourseCustom);
        return "teacher/edit";

    }

    /**
     * 7 更新分数
     */
    @RequestMapping(value = "/updateScore", method = RequestMethod.POST)
    public String updateScore(SelectedCourseCustom scc) throws Exception {
        /**分数入库*/
        selectedCourseService.updataOne(scc);

        return "redirect:/teacher/gradeCourse?id=" + scc.getCourseid();

    }

}

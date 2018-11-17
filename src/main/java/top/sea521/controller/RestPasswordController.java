package top.sea521.controller;

import top.sea521.exception.CustomException;
import top.sea521.po.Userlogin;
import top.sea521.service.UserloginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;


@Controller
public class RestPasswordController {

    @Resource(name = "userloginServiceImpl")
    private UserloginService userloginService;

    /**
     * 1  本账户密码重置
     */
    @RequestMapping(value = "/passwordRest", method = {RequestMethod.POST})
    public String passwordRest(String oldPassword, String password1) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        /**从shiro得到当前的用户
         * 根据用户名查找对应的用户
         * 如果为null顺明用户不存爱*/
        Userlogin userlogin = userloginService.findByName(username);

        if (!oldPassword.equals(userlogin.getPassword())) {
            throw new CustomException("旧密码不正确");
        } else {
            /**更新密码*/
            userlogin.setPassword(password1);
            userloginService.updateByName(username, userlogin);
        }
        /**更新成功让管理员查询登录*/
        return "redirect:/logout";
    }

}

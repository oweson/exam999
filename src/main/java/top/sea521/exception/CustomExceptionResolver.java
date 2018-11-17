package top.sea521.exception;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * 全局异常处理器
 * springmvc提供一个HandlerExceptionResolver接口
 * 只要实现该接口，并配置到spring 容器里，该类就能
 * 成为默认全局异常处理类
 * <p>
 * 全局异常处理器只有一个，配置多个也没用。
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        ModelAndView modelAndView = new ModelAndView();

        CustomException customException;
        if (e instanceof SQLException) {
            modelAndView.addObject("message", "数据库异常...");
            modelAndView.setViewName("error");
            return modelAndView;
        } else if (e instanceof RuntimeException) {
            modelAndView.addObject("message", "系统运行异常...");
            modelAndView.setViewName("error");
            return modelAndView;


        } else if (e instanceof UnknownAccountException) {
            //用户名错误异常
            modelAndView.addObject("message", "没有该用户");
            modelAndView.setViewName("error");
            return modelAndView;
        } else if (e instanceof IncorrectCredentialsException) {
            //用户名错误异常
            modelAndView.addObject("message", "密码错误");
            modelAndView.setViewName("error");
            return modelAndView;
        } else if (e instanceof CustomException) {
            customException = (CustomException) e;

        } else {
            customException = new CustomException("未知错误");
        }

        //错误信息
        String message = customException.getMessage();


        //错误信息传递和错误页面跳转
        modelAndView.addObject("message", message);
        modelAndView.setViewName("error");


        return modelAndView;
    }
}

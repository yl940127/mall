package com.rhinoceros.mall.web.controller;

import com.rhinoceros.mall.core.dto.RegisterUserDto;
import com.rhinoceros.mall.service.impl.exception.UserExistException;
import com.rhinoceros.mall.service.service.UserService;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.Request;
import sun.security.x509.AttributeNameEnumeration;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *注册控制层
 */
@Controller
public class RegisterController {

    //创建用户业务逻辑对象
    @Autowired
    private UserService userService;

    /**
     * 当点击注册时，跳转至注册界面
     * @return
     */
    @RequestMapping("/register")
    public String register() {

        return "register";
    }

    /**
     * 当点击提交时判断是否允许注册
     * @param userDto
     * @return
     */
    @RequestMapping("/registerSubmit")
    public String registerSubmit(@Validated @ModelAttribute("registerUser") RegisterUserDto userDto, BindingResult br, Model model) {

        if(br.hasErrors()){
            model.addAttribute("msg",br.getFieldError().getDefaultMessage());
            return "register";
        }

        if(!userDto.getPassword().equals(userDto.getRePassword())){
            model.addAttribute("msg","两次密码不一致");
            return "register";
        }
        //若正确操作则录入该用户信息，并跳转至注册成功界面，否则抛出异常跳转至当前界面
        try {
            userService.register(userDto);
            return "registerSuccess";
        } catch (UserExistException e) {
            model.addAttribute("msg","该用户名已被占用");
            return "register";
        }
    }
}



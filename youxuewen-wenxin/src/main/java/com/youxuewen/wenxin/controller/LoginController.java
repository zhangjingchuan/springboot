package com.youxuewen.wenxin.controller;

import com.youxuewen.wenxin.bo.UserBo;
import com.youxuewen.wenxin.bo.YouxueResult;
import com.youxuewen.wenxin.pojo.Users;
import com.youxuewen.wenxin.service.UserServiceImpl;
import com.youxuewen.wenxin.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Resource
    private UserServiceImpl userService;

    /**
     * 登录/注册
     * @param userVo
     * @return
     */
    @PostMapping("/registerOrLogin")
    public YouxueResult registerOrLogin(@Valid @RequestBody UserVo userVo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            logger.info(errorMessage);

            return YouxueResult.error(errorMessage);
        }

        //判断用户是否存在，存在就登录，不存在就注册
        if(this.userService.queryUsernameIsExist(userVo.getUsername())){
            //登录
            return userService.querUserByUsernameAndPassword(userVo.getUsername(),userVo.getPassword());
        }else{
            //注册
            Users user = new Users();
            user.setNickname(userVo.getUsername());
            user.setUsername(userVo.getUsername());
            user.setFaceImage("");
            user.setFaceImageBig("");
            user.setCid(userVo.getCid());
            String md5Password = DigestUtils.md5DigestAsHex(userVo.getPassword().getBytes());
            user.setPassword(md5Password);

            Users users = this.userService.addUser(user);

            UserBo result = new UserBo();
            BeanUtils.copyProperties(users,result);
            return YouxueResult.ok(result);
        }
    }
}

package com.youxuewen.wenxin.service;

import com.youxuewen.wenxin.bo.YouxueResult;
import com.youxuewen.wenxin.dao.UsersMapper;
import com.youxuewen.wenxin.pojo.Users;
import com.youxuewen.wenxin.pojo.UsersExample;
import com.youxuewen.wenxin.vo.UserVo;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl {

    @Resource
    private UsersMapper usersMapper;

    /**
     * 检查用户名是否存在
     * @param username
     * @return
     */
    public boolean queryUsernameIsExist(String username){
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);

        List<Users> users = this.usersMapper.selectByExample(example);

        return users!=null&&users.size()>0;
    }

    /**
     * 根据用户名密码查询用户
     * @param username
     * @param password
     * @return
     */
    public YouxueResult querUserByUsernameAndPassword(String username,String password){
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);

        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        criteria.andPasswordEqualTo(md5Password);

        List<Users> users = this.usersMapper.selectByExample(example);

        if(users!=null&&!users.isEmpty()) {

            UserVo vo = new UserVo();
            BeanUtils.copyProperties(users.get(0),vo);
            return YouxueResult.ok(vo);
        }

        return YouxueResult.error("用户名或密码不正确");
    }

    /**
     * 新增用户
     * @param user
     */
    public Users addUser(Users user){
        String userId = Sid.next();
        user.setId(userId);
        //TODO 为用户生成二维码
        user.setQrcode("");
        this.usersMapper.insertSelective(user);
        return user;
    }

}

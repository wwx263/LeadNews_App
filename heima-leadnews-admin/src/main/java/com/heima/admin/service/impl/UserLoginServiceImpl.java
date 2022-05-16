package com.heima.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.heima.admin.mapper.AdUserMapper;
import com.heima.admin.service.UserLoginService;
import com.heima.model.admin.dtos.AdUserDto;
import com.heima.model.admin.pojos.AdUser;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.utils.common.AppJwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Map;

@Service
//开启事务,当登录报错后重试
@Transactional
public class UserLoginServiceImpl extends ServiceImpl<AdUserMapper, AdUser> implements UserLoginService {
    /**
     * 用户登录功能
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult login(AdUserDto dto) {

        Wrapper wrapper = new QueryWrapper<AdUser>();
        //给适配器设置查询条件
        ((QueryWrapper) wrapper).eq("name", dto.getName());
        //使用list来批量查询数据
        List<AdUser> list = list(wrapper);
        if (list.size() == 1 && list != null){
            AdUser adUser = list.get(0);
            String pswd = DigestUtils.md5DigestAsHex((dto.getPassword() + adUser.getSalt()).getBytes());
            //判断密码是否正确
            if (adUser.getPassword().equals(pswd)){
                Map<Object, Object> map = Maps.newHashMap();
                //置空用户密码和盐以防黑客截取
                adUser.setPassword("");
                adUser.setSalt("");
                map.put("user",adUser);
                //使用
                map.put("token", AppJwtUtil.getToken(adUser.getId().longValue()));
                //成功之后返回map,里面装有adUser和token信息
                return ResponseResult.okResult(map);
            } else {
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
            }

        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.AP_USER_DATA_NOT_EXIST,"用户不存在");
        }
    }

//    public static void main(String[] args) {
//        String pswd = DigestUtils.md5DigestAsHex("123".getBytes());
//        System.out.println(pswd);
//    }
}

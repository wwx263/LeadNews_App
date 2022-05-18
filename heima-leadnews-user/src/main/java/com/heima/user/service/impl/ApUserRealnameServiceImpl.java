package com.heima.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.common.constants.user.AdminConstans;
import com.heima.model.article.pojos.ApAuthor;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.media.pojos.WmUser;
import com.heima.model.user.dtos.AuthDto;
import com.heima.model.user.pojos.ApUser;
import com.heima.model.user.pojos.ApUserRealname;
import com.heima.user.feign.ArticleFeign;
import com.heima.user.feign.WemediaFeign;
import com.heima.user.mapper.ApUserMapper;
import com.heima.user.mapper.ApUserRealnameMapper;
import com.heima.user.service.ApUserRealnameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ApUserRealnameServiceImpl extends ServiceImpl<ApUserRealnameMapper, ApUserRealname> implements ApUserRealnameService {
    @Autowired
    private ArticleFeign articleFeign;

    @Autowired
    private WemediaFeign wemediaFeign;

    @Autowired
    private ApUserMapper apUserMapper;
    /**
     * 根据状态查询需要认证相关的用户信息
     *
     * @param dto
     * @return
     */
    @Override
    public PageResponseResult loadListByStatus(AuthDto dto) {
        //设置默认页数
        dto.checkParam();

        QueryWrapper<ApUserRealname> queryWrapper = new QueryWrapper<>();
        if (dto.getStatus() != null) {
            //使用适配器的eq方法,查询dto中与表匹配的数据
            queryWrapper.lambda().eq(ApUserRealname::getStatus,dto.getStatus());
        }
        //初始化分页查询参数
        IPage pageParam = new Page(dto.getPage(), dto.getSize());
        //分页查询符合条件的数据
        IPage page = page(pageParam, queryWrapper);
        //分页查询结果
        PageResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int)page.getTotal());
        responseResult.setCode(0);
        responseResult.setData(page.getRecords());

        return responseResult;
    }
    /**
     * 根据状态进行审核
     * @param dto
     * @param status
     * @return
     */
    @Override
    @Transactional
    public ResponseResult updateStatusById(AuthDto dto, Short status) {
        //1.检查参数
        if (dto == null || dto.getId()==null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        if (statusCheck(status)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //2.修改状态
        ApUserRealname apUserRealname = new ApUserRealname();
        apUserRealname.setId(dto.getId());
        apUserRealname.setStatus(status);
        if (dto.getMsg() != null){
            apUserRealname.setReason(dto.getMsg());
        }
        updateById(apUserRealname);

        //3 认证通过添加自媒体账号和作者账号
        if (status.equals(AdminConstans.PASS_AUTH)) {
            ResponseResult createResult = createWmUserAndAuthor(dto);
            if (createResult != null) {
                return createResult;
            }
            //TODO 发送通知消息
        }
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }


    private ResponseResult createWmUserAndAuthor(AuthDto dto) {
        //添加自媒体账号, 查询ap_user信息封装到wmuser中
        ApUserRealname aur = getById(dto.getId());
        ApUser apUser =  apUserMapper.selectById(aur.getUserId());
        if (apUser == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        // 检测自媒体用户是否存在
        ResponseResult byName = wemediaFeign.findByName(apUser.getName());
        WmUser wmUser =(WmUser)byName.getData();
        if (wmUser == null || wmUser.getId()==null) {
            wmUser = new WmUser();
            //设置ApUserId
            wmUser.setApUserId(apUser.getId());
            wmUser.setCreatedTime(new Date());
            wmUser.setSalt(apUser.getSalt());
            wmUser.setName(apUser.getName());
            wmUser.setPassword(apUser.getPassword());
            wmUser.setStatus(9);
            wmUser.setPhone(apUser.getPhone());
            wemediaFeign.save(wmUser);
        }

        //创建作者账号
        createAuthor(wmUser);
        //修改ap_user标记
        apUser.setFlag((short) 1);
        apUserMapper.updateById(apUser);
        return null;
    }
    /**
     * 创建作者账号
     * @param wmUser
     * @return
     */
    private void createAuthor(WmUser wmUser) {
        Integer apUserId = wmUser.getApUserId();
        ApAuthor apAuthor =  articleFeign.findByUserId(apUserId);
        if (apAuthor == null) {
            apAuthor = new ApAuthor();
            apAuthor.setName(wmUser.getName());
            apAuthor.setType(AdminConstans.AUTHOR_TYPE);
            apAuthor.setCreatedTime(new Date());
            apAuthor.setUserId(apUserId);
            articleFeign.save(apAuthor);
        }
    }

    /**
     * 状态监测
     * @param status
     * @return
     */
    private boolean statusCheck(Short status) {
        if (status == null
                || ( !status.equals(AdminConstans.FAIL_AUTH)
                && !status.equals(AdminConstans.PASS_AUTH))) {
            return true;
        }
        return false;
    }
}

package com.youqusoft.vision.flow.tenant.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youqusoft.vision.flow.tenant.mapper.TenantUserMapper;
import com.youqusoft.vision.flow.tenant.service.TenantUserService;
import com.youqusoft.vision.flow.tenant.model.entity.TenantUser;
import com.youqusoft.vision.flow.tenant.model.form.TenantUserForm;
import com.youqusoft.vision.flow.tenant.model.query.TenantUserQuery;
import com.youqusoft.vision.flow.tenant.model.vo.TenantUserVO;
import com.youqusoft.vision.flow.tenant.converter.TenantUserConverter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;

/**
 * 租户用户（多租户）服务实现类
 *
 * @author youqulife
 * @since 2025-09-27 08:53
 */
@Service
@RequiredArgsConstructor
public class TenantUserServiceImpl extends ServiceImpl<TenantUserMapper, TenantUser> implements TenantUserService {

    private final TenantUserConverter tenantUserConverter;

    /**
    * 获取租户用户（多租户）分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<TenantUserVO>} 租户用户（多租户）分页列表
    */
    @Override
    public IPage<TenantUserVO> getTenantUserPage(TenantUserQuery queryParams) {
        Page<TenantUserVO> pageVO = this.baseMapper.getTenantUserPage(
                new Page<>(queryParams.getPageNum(), queryParams.getPageSize()),
                queryParams
        );
        return pageVO;
    }
    
    /**
     * 获取租户用户（多租户）表单数据
     *
     * @param id 租户用户（多租户）ID
     * @return 租户用户（多租户）表单数据
     */
    @Override
    public TenantUserForm getTenantUserFormData(Long id) {
        TenantUser entity = this.getById(id);
        return tenantUserConverter.toForm(entity);
    }
    
    /**
     * 新增租户用户（多租户）
     *
     * @param formData 租户用户（多租户）表单对象
     * @return 是否新增成功
     */
    @Override
    public boolean saveTenantUser(TenantUserForm formData) {
        TenantUser entity = tenantUserConverter.toEntity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新租户用户（多租户）
     *
     * @param id   租户用户（多租户）ID
     * @param formData 租户用户（多租户）表单对象
     * @return 是否修改成功
     */
    @Override
    public boolean updateTenantUser(Long id,TenantUserForm formData) {
        TenantUser entity = tenantUserConverter.toEntity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除租户用户（多租户）
     *
     * @param ids 租户用户（多租户）ID，多个以英文逗号(,)分割
     * @return 是否删除成功
     */
    @Override
    public boolean deleteTenantUsers(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的租户用户（多租户）数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

}

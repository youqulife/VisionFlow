package com.youqusoft.vision.flow.tenant.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youqusoft.vision.flow.tenant.mapper.TenantMapper;
import com.youqusoft.vision.flow.tenant.service.TenantService;
import com.youqusoft.vision.flow.tenant.model.entity.Tenant;
import com.youqusoft.vision.flow.tenant.model.form.TenantForm;
import com.youqusoft.vision.flow.tenant.model.query.TenantQuery;
import com.youqusoft.vision.flow.tenant.model.vo.TenantVO;
import com.youqusoft.vision.flow.tenant.converter.TenantConverter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;

/**
 * 租户信息服务实现类
 *
 * @author youqulife
 * @since 2025-09-26 22:24
 */
@Service
@RequiredArgsConstructor
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements TenantService {

    private final TenantConverter tenantConverter;

    /**
    * 获取租户信息分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<TenantVO>} 租户信息分页列表
    */
    @Override
    public IPage<TenantVO> getTenantPage(TenantQuery queryParams) {
        Page<TenantVO> pageVO = this.baseMapper.getTenantPage(
                new Page<>(queryParams.getPageNum(), queryParams.getPageSize()),
                queryParams
        );
        return pageVO;
    }
    
    /**
     * 获取租户信息表单数据
     *
     * @param id 租户信息ID
     * @return 租户信息表单数据
     */
    @Override
    public TenantForm getTenantFormData(Long id) {
        Tenant entity = this.getById(id);
        return tenantConverter.toForm(entity);
    }
    
    /**
     * 新增租户信息
     *
     * @param formData 租户信息表单对象
     * @return 是否新增成功
     */
    @Override
    public boolean saveTenant(TenantForm formData) {
        Tenant entity = tenantConverter.toEntity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新租户信息
     *
     * @param id   租户信息ID
     * @param formData 租户信息表单对象
     * @return 是否修改成功
     */
    @Override
    public boolean updateTenant(Long id,TenantForm formData) {
        Tenant entity = tenantConverter.toEntity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除租户信息
     *
     * @param ids 租户信息ID，多个以英文逗号(,)分割
     * @return 是否删除成功
     */
    @Override
    public boolean deleteTenants(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的租户信息数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

}

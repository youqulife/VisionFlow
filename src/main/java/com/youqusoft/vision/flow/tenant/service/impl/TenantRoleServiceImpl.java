package com.youqusoft.vision.flow.tenant.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youqusoft.vision.flow.tenant.mapper.TenantRoleMapper;
import com.youqusoft.vision.flow.tenant.service.TenantRoleService;
import com.youqusoft.vision.flow.tenant.model.entity.TenantRole;
import com.youqusoft.vision.flow.tenant.model.form.TenantRoleForm;
import com.youqusoft.vision.flow.tenant.model.query.TenantRoleQuery;
import com.youqusoft.vision.flow.tenant.model.vo.TenantRoleVO;
import com.youqusoft.vision.flow.tenant.converter.TenantRoleConverter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;

/**
 * 租户角色服务实现类
 *
 * @author youqulife
 * @since 2025-09-27 08:47
 */
@Service
@RequiredArgsConstructor
public class TenantRoleServiceImpl extends ServiceImpl<TenantRoleMapper, TenantRole> implements TenantRoleService {

    private final TenantRoleConverter tenantRoleConverter;

    /**
    * 获取租户角色分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<TenantRoleVO>} 租户角色分页列表
    */
    @Override
    public IPage<TenantRoleVO> getTenantRolePage(TenantRoleQuery queryParams) {
        Page<TenantRoleVO> pageVO = this.baseMapper.getTenantRolePage(
                new Page<>(queryParams.getPageNum(), queryParams.getPageSize()),
                queryParams
        );
        return pageVO;
    }
    
    /**
     * 获取租户角色表单数据
     *
     * @param id 租户角色ID
     * @return 租户角色表单数据
     */
    @Override
    public TenantRoleForm getTenantRoleFormData(Long id) {
        TenantRole entity = this.getById(id);
        return tenantRoleConverter.toForm(entity);
    }
    
    /**
     * 新增租户角色
     *
     * @param formData 租户角色表单对象
     * @return 是否新增成功
     */
    @Override
    public boolean saveTenantRole(TenantRoleForm formData) {
        TenantRole entity = tenantRoleConverter.toEntity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新租户角色
     *
     * @param id   租户角色ID
     * @param formData 租户角色表单对象
     * @return 是否修改成功
     */
    @Override
    public boolean updateTenantRole(Long id,TenantRoleForm formData) {
        TenantRole entity = tenantRoleConverter.toEntity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除租户角色
     *
     * @param ids 租户角色ID，多个以英文逗号(,)分割
     * @return 是否删除成功
     */
    @Override
    public boolean deleteTenantRoles(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的租户角色数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

}

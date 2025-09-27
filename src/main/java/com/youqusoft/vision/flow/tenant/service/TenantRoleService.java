package com.youqusoft.vision.flow.tenant.service;

import com.youqusoft.vision.flow.tenant.model.entity.TenantRole;
import com.youqusoft.vision.flow.tenant.model.form.TenantRoleForm;
import com.youqusoft.vision.flow.tenant.model.query.TenantRoleQuery;
import com.youqusoft.vision.flow.tenant.model.vo.TenantRoleVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 租户角色服务类
 *
 * @author youqulife
 * @since 2025-09-27 08:47
 */
public interface TenantRoleService extends IService<TenantRole> {

    /**
     *租户角色分页列表
     *
     * @return {@link IPage<TenantRoleVO>} 租户角色分页列表
     */
    IPage<TenantRoleVO> getTenantRolePage(TenantRoleQuery queryParams);

    /**
     * 获取租户角色表单数据
     *
     * @param id 租户角色ID
     * @return 租户角色表单数据
     */
     TenantRoleForm getTenantRoleFormData(Long id);

    /**
     * 新增租户角色
     *
     * @param formData 租户角色表单对象
     * @return 是否新增成功
     */
    boolean saveTenantRole(TenantRoleForm formData);

    /**
     * 修改租户角色
     *
     * @param id   租户角色ID
     * @param formData 租户角色表单对象
     * @return 是否修改成功
     */
    boolean updateTenantRole(Long id, TenantRoleForm formData);

    /**
     * 删除租户角色
     *
     * @param ids 租户角色ID，多个以英文逗号(,)分割
     * @return 是否删除成功
     */
    boolean deleteTenantRoles(String ids);

}

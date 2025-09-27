package com.youqusoft.vision.flow.tenant.service;

import com.youqusoft.vision.flow.tenant.model.entity.Tenant;
import com.youqusoft.vision.flow.tenant.model.form.TenantForm;
import com.youqusoft.vision.flow.tenant.model.query.TenantQuery;
import com.youqusoft.vision.flow.tenant.model.vo.TenantVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 租户信息服务类
 *
 * @author youqulife
 * @since 2025-09-26 22:24
 */
public interface TenantService extends IService<Tenant> {

    /**
     *租户信息分页列表
     *
     * @return {@link IPage<TenantVO>} 租户信息分页列表
     */
    IPage<TenantVO> getTenantPage(TenantQuery queryParams);

    /**
     * 获取租户信息表单数据
     *
     * @param id 租户信息ID
     * @return 租户信息表单数据
     */
     TenantForm getTenantFormData(Long id);

    /**
     * 新增租户信息
     *
     * @param formData 租户信息表单对象
     * @return 是否新增成功
     */
    boolean saveTenant(TenantForm formData);

    /**
     * 修改租户信息
     *
     * @param id   租户信息ID
     * @param formData 租户信息表单对象
     * @return 是否修改成功
     */
    boolean updateTenant(Long id, TenantForm formData);

    /**
     * 删除租户信息
     *
     * @param ids 租户信息ID，多个以英文逗号(,)分割
     * @return 是否删除成功
     */
    boolean deleteTenants(String ids);

}

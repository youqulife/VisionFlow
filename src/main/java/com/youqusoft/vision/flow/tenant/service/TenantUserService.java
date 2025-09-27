package com.youqusoft.vision.flow.tenant.service;

import com.youqusoft.vision.flow.tenant.model.entity.TenantUser;
import com.youqusoft.vision.flow.tenant.model.form.TenantUserForm;
import com.youqusoft.vision.flow.tenant.model.query.TenantUserQuery;
import com.youqusoft.vision.flow.tenant.model.vo.TenantUserVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 租户用户（多租户）服务类
 *
 * @author youqulife
 * @since 2025-09-27 08:53
 */
public interface TenantUserService extends IService<TenantUser> {

    /**
     *租户用户（多租户）分页列表
     *
     * @return {@link IPage<TenantUserVO>} 租户用户（多租户）分页列表
     */
    IPage<TenantUserVO> getTenantUserPage(TenantUserQuery queryParams);

    /**
     * 获取租户用户（多租户）表单数据
     *
     * @param id 租户用户（多租户）ID
     * @return 租户用户（多租户）表单数据
     */
     TenantUserForm getTenantUserFormData(Long id);

    /**
     * 新增租户用户（多租户）
     *
     * @param formData 租户用户（多租户）表单对象
     * @return 是否新增成功
     */
    boolean saveTenantUser(TenantUserForm formData);

    /**
     * 修改租户用户（多租户）
     *
     * @param id   租户用户（多租户）ID
     * @param formData 租户用户（多租户）表单对象
     * @return 是否修改成功
     */
    boolean updateTenantUser(Long id, TenantUserForm formData);

    /**
     * 删除租户用户（多租户）
     *
     * @param ids 租户用户（多租户）ID，多个以英文逗号(,)分割
     * @return 是否删除成功
     */
    boolean deleteTenantUsers(String ids);

}

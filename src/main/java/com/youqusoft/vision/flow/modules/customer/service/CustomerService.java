package com.youqusoft.vision.flow.modules.customer.service;

import com.youqusoft.vision.flow.modules.customer.model.entity.Customer;
import com.youqusoft.vision.flow.modules.customer.model.form.CustomerForm;
import com.youqusoft.vision.flow.modules.customer.model.query.CustomerQuery;
import com.youqusoft.vision.flow.modules.customer.model.vo.CustomerVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 顾客信息服务类
 *
 * @author youqusoft
 * @since 2025-09-27 15:49
 */
public interface CustomerService extends IService<Customer> {

    /**
     *顾客信息分页列表
     *
     * @return {@link IPage<CustomerVO>} 顾客信息分页列表
     */
    IPage<CustomerVO> getCustomerPage(CustomerQuery queryParams);

    /**
     * 获取顾客信息表单数据
     *
     * @param id 顾客信息ID
     * @return 顾客信息表单数据
     */
     CustomerForm getCustomerFormData(Long id);

    /**
     * 新增顾客信息
     *
     * @param formData 顾客信息表单对象
     * @return 是否新增成功
     */
    boolean saveCustomer(CustomerForm formData);

    /**
     * 修改顾客信息
     *
     * @param id   顾客信息ID
     * @param formData 顾客信息表单对象
     * @return 是否修改成功
     */
    boolean updateCustomer(Long id, CustomerForm formData);

    /**
     * 删除顾客信息
     *
     * @param ids 顾客信息ID，多个以英文逗号(,)分割
     * @return 是否删除成功
     */
    boolean deleteCustomers(String ids);

}

package com.youqusoft.vision.flow.modules.customer.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youqusoft.vision.flow.modules.customer.model.entity.Customer;
import com.youqusoft.vision.flow.modules.customer.model.form.CustomerForm;
import com.youqusoft.vision.flow.modules.customer.model.query.CustomerQuery;
import com.youqusoft.vision.flow.modules.customer.model.vo.CustomerVO;

/**
 * 顾客信息服务接口层
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
public interface CustomerService extends IService<Customer> {

    /**
     * 顾客分页列表
     *
     * @param queryParams 顾客查询参数
     * @return 顾客分页列表
     */
    IPage<CustomerVO> getCustomerPage(CustomerQuery queryParams);

    /**
     * 保存顾客信息
     *
     * @param customerForm 顾客表单数据
     * @return 是否保存成功
     */
    boolean saveCustomer(CustomerForm customerForm);

    /**
     * 获取顾客表单数据
     *
     * @param id 顾客ID
     * @return 顾客表单数据
     */
    CustomerForm getCustomerForm(Long id);

    /**
     * 更新顾客信息
     *
     * @param id 顾客ID
     * @param customerForm 顾客表单数据
     * @return 是否更新成功
     */
    boolean updateCustomer(Long id, CustomerForm customerForm);

    /**
     * 删除顾客信息
     *
     * @param ids 顾客ID数组
     * @return 是否删除成功
     */
    boolean deleteCustomers(Long[] ids);
}
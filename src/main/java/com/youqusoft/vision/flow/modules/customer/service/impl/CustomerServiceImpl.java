package com.youqusoft.vision.flow.modules.customer.service.impl;

import com.youqusoft.vision.flow.common.constant.TenantConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youqusoft.vision.flow.modules.customer.mapper.CustomerMapper;
import com.youqusoft.vision.flow.modules.customer.service.CustomerService;
import com.youqusoft.vision.flow.modules.customer.model.entity.Customer;
import com.youqusoft.vision.flow.modules.customer.model.form.CustomerForm;
import com.youqusoft.vision.flow.modules.customer.model.query.CustomerQuery;
import com.youqusoft.vision.flow.modules.customer.model.vo.CustomerVO;
import com.youqusoft.vision.flow.modules.customer.converter.CustomerConverter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;

/**
 * 顾客信息服务实现类
 *
 * @author youqusoft
 * @since 2025-09-27 15:49
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    private final CustomerConverter customerConverter;

    /**
    * 获取顾客信息分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<CustomerVO>} 顾客信息分页列表
    */
    @Override
    public IPage<CustomerVO> getCustomerPage(CustomerQuery queryParams) {
        Page<CustomerVO> pageVO = this.baseMapper.getCustomerPage(
                new Page<>(queryParams.getPageNum(), queryParams.getPageSize()),
                queryParams
        );
        return pageVO;
    }
    
    /**
     * 获取顾客信息表单数据
     *
     * @param id 顾客信息ID
     * @return 顾客信息表单数据
     */
    @Override
    public CustomerForm getCustomerFormData(Long id) {
        Customer entity = this.getById(id);
        return customerConverter.toForm(entity);
    }
    
    /**
     * 新增顾客信息
     *
     * @param formData 顾客信息表单对象
     * @return 是否新增成功
     */
    @Override
    public boolean saveCustomer(CustomerForm formData) {
        Customer entity = customerConverter.toEntity(formData);

        entity.setTenantId(TenantConstants.DEFAULT_TENANT_ID);
        return this.save(entity);
    }
    
    /**
     * 更新顾客信息
     *
     * @param id   顾客信息ID
     * @param formData 顾客信息表单对象
     * @return 是否修改成功
     */
    @Override
    public boolean updateCustomer(Long id,CustomerForm formData) {
        Customer entity = customerConverter.toEntity(formData);
        entity.setTenantId(TenantConstants.DEFAULT_TENANT_ID);
        return this.updateById(entity);
    }
    
    /**
     * 删除顾客信息
     *
     * @param ids 顾客信息ID，多个以英文逗号(,)分割
     * @return 是否删除成功
     */
    @Override
    public boolean deleteCustomers(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的顾客信息数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

}

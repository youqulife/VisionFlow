package com.youqusoft.vision.flow.modules.customer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youqusoft.vision.flow.modules.customer.model.entity.Customer;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youqusoft.vision.flow.modules.customer.model.query.CustomerQuery;
import com.youqusoft.vision.flow.modules.customer.model.vo.CustomerVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 顾客信息Mapper接口
 *
 * @author youqusoft
 * @since 2025-09-27 15:49
 */
@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {

    /**
     * 获取顾客信息分页数据
     *
     * @param page 分页对象
     * @param queryParams 查询参数
     * @return {@link Page<CustomerVO>} 顾客信息分页列表
     */
    Page<CustomerVO> getCustomerPage(Page<CustomerVO> page, CustomerQuery queryParams);

}

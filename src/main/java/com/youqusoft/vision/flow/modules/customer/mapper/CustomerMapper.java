package com.youqusoft.vision.flow.modules.customer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youqusoft.vision.flow.modules.customer.model.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

/**
 * 顾客信息访问层
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {

}
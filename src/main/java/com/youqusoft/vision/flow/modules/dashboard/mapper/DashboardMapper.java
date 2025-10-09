package com.youqusoft.vision.flow.modules.dashboard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youqusoft.vision.flow.modules.dashboard.model.vo.DashboardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.Map;

@Mapper
public interface DashboardMapper extends BaseMapper<DashboardVO> {
    /**
     * 统计指定日期新增客户数
     * @param date 日期
     * @return 新增客户数
     */
    int getNewCustomerCount(@Param("date") LocalDate date);

    /**
     * 统计指定日期验光次数
     * @param date 日期
     * @return 验光次数
     */
    int getEyeExamCount(@Param("date") LocalDate date);

    /**
     * 统计指定日期订单数量及金额
     * @param date 日期
     * @return 订单统计信息
     */
    Map<String, Object> getOrderStats(@Param("date") LocalDate date);
}
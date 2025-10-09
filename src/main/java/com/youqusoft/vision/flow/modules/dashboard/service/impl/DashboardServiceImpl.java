package com.youqusoft.vision.flow.modules.dashboard.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youqusoft.vision.flow.modules.customer.service.CustomerService;
import com.youqusoft.vision.flow.modules.customer.service.EyeExamService;
import com.youqusoft.vision.flow.modules.dashboard.mapper.DashboardMapper;
import com.youqusoft.vision.flow.modules.dashboard.model.vo.DashboardVO;
import com.youqusoft.vision.flow.modules.dashboard.service.DashboardService;
import com.youqusoft.vision.flow.modules.order.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl extends ServiceImpl<DashboardMapper, DashboardVO> implements DashboardService {
    private final DashboardMapper dashboardMapper;

    @Override
    public DashboardVO getStats(String date) {
        // 解析日期参数
        LocalDate statsDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        DashboardVO dashboardVO = new DashboardVO();
        
        // 统计新增客户数
        int newCustomerCount = dashboardMapper.getNewCustomerCount(statsDate);
        DashboardVO.CustomerStatsVO customerStats = new DashboardVO.CustomerStatsVO();
        customerStats.setNewCustomerCount(newCustomerCount);
        dashboardVO.setCustomerStats(customerStats);
        
        // 统计验光次数
        int eyeExamCount = dashboardMapper.getEyeExamCount(statsDate);
        DashboardVO.EyeExamStatsVO eyeExamStats = new DashboardVO.EyeExamStatsVO();
        eyeExamStats.setEyeExamCount(eyeExamCount);
        dashboardVO.setEyeExamStats(eyeExamStats);
        
        // 统计订单数量及金额
        Map<String, Object> orderStatsMap = dashboardMapper.getOrderStats(statsDate);
        DashboardVO.OrderStatsVO orderStats = new DashboardVO.OrderStatsVO();
        orderStats.setOrderCount((Long) orderStatsMap.get("orderCount"));
        orderStats.setOrderAmount((BigDecimal) orderStatsMap.get("orderAmount"));
        dashboardVO.setOrderStats(orderStats);
        
        return dashboardVO;
    }
}
package com.youqusoft.vision.flow.modules.dashboard.controller;


import com.youqusoft.vision.flow.common.result.Result;
import com.youqusoft.vision.flow.modules.dashboard.model.vo.DashboardVO;
import com.youqusoft.vision.flow.modules.dashboard.service.DashboardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "10.统计")
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    /**
     * 获取统计数据
     *
     * @return 统计数据
     */
    @RequestMapping("/today-stats")
    public Result<DashboardVO> getDashboardData() {
        log.info("获取统计数据");
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return Result.success(dashboardService.getStats(today));
    }
}
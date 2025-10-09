package com.youqusoft.vision.flow.modules.dashboard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.youqusoft.vision.flow.modules.dashboard.model.vo.DashboardVO;

public interface DashboardService extends IService<DashboardVO> {
    DashboardVO getStats(String date);
}

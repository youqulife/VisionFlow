package com.youqusoft.vision.flow.modules.customer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youqusoft.vision.flow.modules.customer.model.entity.EyeExam;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youqusoft.vision.flow.modules.customer.model.query.EyeExamQuery;
import com.youqusoft.vision.flow.modules.customer.model.vo.EyeExamVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 验光记录Mapper接口
 *
 * @author youqusoft
 * @since 2025-10-01 11:31
 */
@Mapper
public interface EyeExamMapper extends BaseMapper<EyeExam> {

    /**
     * 获取验光记录分页数据
     *
     * @param page 分页对象
     * @param queryParams 查询参数
     * @return {@link Page<EyeExamVO>} 验光记录分页列表
     */
    Page<EyeExamVO> getEyeExamPage(Page<EyeExamVO> page, EyeExamQuery queryParams);

}

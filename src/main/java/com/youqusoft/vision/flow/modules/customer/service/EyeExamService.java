package com.youqusoft.vision.flow.modules.customer.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youqusoft.vision.flow.modules.customer.model.entity.EyeExam;
import com.youqusoft.vision.flow.modules.customer.model.form.EyeExamForm;
import com.youqusoft.vision.flow.modules.customer.model.query.CustomerQuery;
import com.youqusoft.vision.flow.modules.customer.model.vo.EyeExamVO;

/**
 * 验光记录服务接口层
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
public interface EyeExamService extends IService<EyeExam> {

    /**
     * 验光记录分页列表
     *
     * @param queryParams 验光记录查询参数
     * @return 验光记录分页列表
     */
    IPage<EyeExamVO> getEyeExamPage(CustomerQuery queryParams);

    /**
     * 保存验光记录
     *
     * @param eyeExamForm 验光记录表单数据
     * @return 是否保存成功
     */
    boolean saveEyeExam(EyeExamForm eyeExamForm);

    /**
     * 获取验光记录表单数据
     *
     * @param id 验光记录ID
     * @return 验光记录表单数据
     */
    EyeExamForm getEyeExamForm(Long id);

    /**
     * 更新验光记录
     *
     * @param id 验光记录ID
     * @param eyeExamForm 验光记录表单数据
     * @return 是否更新成功
     */
    boolean updateEyeExam(Long id, EyeExamForm eyeExamForm);

    /**
     * 删除验光记录
     *
     * @param ids 验光记录ID数组
     * @return 是否删除成功
     */
    boolean deleteEyeExams(Long[] ids);
}
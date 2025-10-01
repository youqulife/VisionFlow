package com.youqusoft.vision.flow.modules.customer.service;

import com.youqusoft.vision.flow.modules.customer.model.entity.EyeExam;
import com.youqusoft.vision.flow.modules.customer.model.form.EyeExamForm;
import com.youqusoft.vision.flow.modules.customer.model.query.EyeExamQuery;
import com.youqusoft.vision.flow.modules.customer.model.vo.EyeExamVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 验光记录服务类
 *
 * @author youqusoft
 * @since 2025-10-01 11:31
 */
public interface EyeExamService extends IService<EyeExam> {

    /**
     *验光记录分页列表
     *
     * @return {@link IPage<EyeExamVO>} 验光记录分页列表
     */
    IPage<EyeExamVO> getEyeExamPage(EyeExamQuery queryParams);

    /**
     * 获取验光记录表单数据
     *
     * @param id 验光记录ID
     * @return 验光记录表单数据
     */
     EyeExamForm getEyeExamFormData(Long id);

    /**
     * 新增验光记录
     *
     * @param formData 验光记录表单对象
     * @return 是否新增成功
     */
    boolean saveEyeExam(EyeExamForm formData);

    /**
     * 修改验光记录
     *
     * @param id   验光记录ID
     * @param formData 验光记录表单对象
     * @return 是否修改成功
     */
    boolean updateEyeExam(Long id, EyeExamForm formData);

    /**
     * 删除验光记录
     *
     * @param ids 验光记录ID，多个以英文逗号(,)分割
     * @return 是否删除成功
     */
    boolean deleteEyeExams(String ids);

}

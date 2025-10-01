package com.youqusoft.vision.flow.modules.customer.service.impl;

import com.youqusoft.vision.flow.common.constant.TenantConstants;
import com.youqusoft.vision.flow.modules.customer.mapper.CustomerMapper;
import com.youqusoft.vision.flow.modules.customer.model.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youqusoft.vision.flow.modules.customer.mapper.EyeExamMapper;
import com.youqusoft.vision.flow.modules.customer.service.EyeExamService;
import com.youqusoft.vision.flow.modules.customer.model.entity.EyeExam;
import com.youqusoft.vision.flow.modules.customer.model.form.EyeExamForm;
import com.youqusoft.vision.flow.modules.customer.model.query.EyeExamQuery;
import com.youqusoft.vision.flow.modules.customer.model.vo.EyeExamVO;
import com.youqusoft.vision.flow.modules.customer.converter.EyeExamConverter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;

/**
 * 验光记录服务实现类
 *
 * @author youqusoft
 * @since 2025-10-01 11:31
 */
@Service
@RequiredArgsConstructor
public class EyeExamServiceImpl extends ServiceImpl<EyeExamMapper, EyeExam> implements EyeExamService {
    private final CustomerMapper customerMapper;
    private final EyeExamConverter eyeExamConverter;

    /**
    * 获取验光记录分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<EyeExamVO>} 验光记录分页列表
    */
    @Override
    public IPage<EyeExamVO> getEyeExamPage(EyeExamQuery queryParams) {
        Page<EyeExamVO> pageVO = this.baseMapper.getEyeExamPage(
                new Page<>(queryParams.getPageNum(), queryParams.getPageSize()),
                queryParams
        );
        return pageVO;
    }
    
    /**
     * 获取验光记录表单数据
     *
     * @param id 验光记录ID
     * @return 验光记录表单数据
     */
    @Override
    public EyeExamForm getEyeExamFormData(Long id) {
        EyeExam entity = this.getById(id);
        Customer customer = customerMapper.selectById(entity.getCustomerId());
        EyeExamForm formData = eyeExamConverter.toForm(entity);
        formData.setCustomerName(customer.getName());
        formData.setCustomerPhone(customer.getPhone());
        return formData;
    }
    
    /**
     * 新增验光记录
     *
     * @param formData 验光记录表单对象
     * @return 是否新增成功
     */
    @Override
    public boolean saveEyeExam(EyeExamForm formData) {
        EyeExam entity = eyeExamConverter.toEntity(formData);
        entity.setTenantId(TenantConstants.DEFAULT_TENANT_ID);
        return this.save(entity);
    }
    
    /**
     * 更新验光记录
     *
     * @param id   验光记录ID
     * @param formData 验光记录表单对象
     * @return 是否修改成功
     */
    @Override
    public boolean updateEyeExam(Long id,EyeExamForm formData) {
        EyeExam entity = eyeExamConverter.toEntity(formData);
        entity.setId(id);
        return this.updateById(entity);
    }
    
    /**
     * 删除验光记录
     *
     * @param ids 验光记录ID，多个以英文逗号(,)分割
     * @return 是否删除成功
     */
    @Override
    public boolean deleteEyeExams(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的验光记录数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

}

package com.youqusoft.vision.flow.modules.customer.converter;

import org.mapstruct.Mapper;
import com.youqusoft.vision.flow.modules.customer.model.entity.EyeExam;
import com.youqusoft.vision.flow.modules.customer.model.form.EyeExamForm;

/**
 * 验光记录对象转换器
 *
 * @author youqusoft
 * @since 2025-10-01 11:31
 */
@Mapper(componentModel = "spring")
public interface EyeExamConverter{

    EyeExamForm toForm(EyeExam entity);

    EyeExam toEntity(EyeExamForm formData);
}
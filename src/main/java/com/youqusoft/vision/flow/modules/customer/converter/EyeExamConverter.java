package com.youqusoft.vision.flow.modules.customer.converter;

import com.youqusoft.vision.flow.modules.customer.model.entity.EyeExam;
import com.youqusoft.vision.flow.modules.customer.model.form.EyeExamForm;
import com.youqusoft.vision.flow.modules.customer.model.form.WearingHistoryData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * 验光记录对象转换器
 *
 * @author youqusoft
 * @since 2025-10-01 11:31
 */
@Mapper(componentModel = "spring")
public interface EyeExamConverter{

    @Mapping(source = "wearingHistory", target = "wearingHistoryObj", qualifiedByName = "stringToWearingHistoryData")
    EyeExamForm toForm(EyeExam entity);

    @Mapping(source = "wearingHistoryObj", target = "wearingHistory", qualifiedByName = "wearingHistoryDataToString")
    EyeExam toEntity(EyeExamForm formData);
    
    @Named("stringToWearingHistoryData")
    default WearingHistoryData stringToWearingHistoryData(String wearingHistory) {
        return WearingHistoryData.fromJsonString(wearingHistory);
    }
    
    @Named("wearingHistoryDataToString")
    default String wearingHistoryDataToString(WearingHistoryData wearingHistoryData) {
        return wearingHistoryData != null ? wearingHistoryData.toJsonString() : null;
    }
}
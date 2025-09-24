package com.youqusoft.vision.flow.system.converter;

import com.youqusoft.vision.flow.system.model.entity.Dept;
import com.youqusoft.vision.flow.system.model.vo.DeptVO;
import com.youqusoft.vision.flow.system.model.form.DeptForm;
import org.mapstruct.Mapper;

/**
 * 部门对象转换器
 *
 * @author haoxr
 * @since 2022/7/29
 */
@Mapper(componentModel = "spring")
public interface DeptConverter {

    DeptForm toForm(Dept entity);
    
    DeptVO toVo(Dept entity);

    Dept toEntity(DeptForm deptForm);

}
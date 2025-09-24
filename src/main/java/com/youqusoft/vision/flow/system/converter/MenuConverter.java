package com.youqusoft.vision.flow.system.converter;

import com.youqusoft.vision.flow.system.model.entity.Menu;
import com.youqusoft.vision.flow.system.model.vo.MenuVO;
import com.youqusoft.vision.flow.system.model.form.MenuForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 菜单对象转换器
 *
 * @author Ray Hao
 * @since 2024/5/26
 */
@Mapper(componentModel = "spring")
public interface MenuConverter {

    MenuVO toVo(Menu entity);

    @Mapping(target = "params", ignore = true)
    MenuForm toForm(Menu entity);

    @Mapping(target = "params", ignore = true)
    Menu toEntity(MenuForm menuForm);

}
package com.youqusoft.vision.flow.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youqusoft.vision.flow.system.model.entity.Role;
import com.youqusoft.vision.flow.system.model.vo.RolePageVO;
import com.youqusoft.vision.flow.common.model.Option;
import com.youqusoft.vision.flow.system.model.form.RoleForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 角色对象转换器
 *
 * @author haoxr
 * @since 2022/5/29
 */
@Mapper(componentModel = "spring")
public interface RoleConverter {

    Page<RolePageVO> toPageVo(Page<Role> page);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option<Long> entity2Option(Role role);

    List<Option<Long>> entities2Options(List<Role> roles);

    Role toEntity(RoleForm roleForm);

    RoleForm toForm(Role entity);
}
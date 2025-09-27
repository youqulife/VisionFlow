package com.youqusoft.vision.flow.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youqusoft.vision.flow.system.model.entity.DictItem;
import com.youqusoft.vision.flow.system.model.query.DictItemPageQuery;
import com.youqusoft.vision.flow.system.model.vo.DictItemPageVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典项映射层
 *
 * @author Ray Hao
 * @since 2.9.0
 */
@Mapper
public interface DictItemMapper extends BaseMapper<DictItem> {

    /**
     * 字典项分页列表
     */
    Page<DictItemPageVO> getDictItemPage(Page<DictItemPageVO> page, DictItemPageQuery queryParams);
}





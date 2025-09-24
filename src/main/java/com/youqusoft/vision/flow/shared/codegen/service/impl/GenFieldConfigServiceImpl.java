package com.youqusoft.vision.flow.shared.codegen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youqusoft.vision.flow.shared.codegen.mapper.GenFieldConfigMapper;
import com.youqusoft.vision.flow.shared.codegen.model.entity.GenFieldConfig;
import com.youqusoft.vision.flow.shared.codegen.service.GenFieldConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 代码生成字段配置服务实现类
 *
 * @author Ray
 * @since 2.10.0
 */
@Service
@RequiredArgsConstructor
public class GenFieldConfigServiceImpl extends ServiceImpl<GenFieldConfigMapper, GenFieldConfig> implements GenFieldConfigService {


}

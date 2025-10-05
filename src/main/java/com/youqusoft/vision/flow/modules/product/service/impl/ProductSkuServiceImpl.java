package com.youqusoft.vision.flow.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youqusoft.vision.flow.modules.product.mapper.ProductSkuMapper;
import com.youqusoft.vision.flow.modules.product.model.entity.ProductSku;
import com.youqusoft.vision.flow.modules.product.service.ProductSkuService;
import org.springframework.stereotype.Service;

@Service
public class ProductSkuServiceImpl extends ServiceImpl<ProductSkuMapper, ProductSku> implements ProductSkuService {
}

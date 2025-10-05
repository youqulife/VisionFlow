package com.youqusoft.vision.flow.modules.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youqusoft.vision.flow.modules.product.mapper.ProductSkuMapper;
import com.youqusoft.vision.flow.modules.product.service.ProductSkuService;
import com.youqusoft.vision.flow.modules.product.model.entity.ProductSku;
import com.youqusoft.vision.flow.modules.product.model.form.ProductSkuForm;
import com.youqusoft.vision.flow.modules.product.model.query.ProductSkuQuery;
import com.youqusoft.vision.flow.modules.product.model.vo.ProductSkuVO;
import com.youqusoft.vision.flow.modules.product.converter.ProductSkuConverter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;

/**
 * 商品SKU服务实现类
 *
 * @author youqusoft
 * @since 2025-10-05 09:55
 */
@Service
@RequiredArgsConstructor
public class ProductSkuServiceImpl extends ServiceImpl<ProductSkuMapper, ProductSku> implements ProductSkuService {

    private final ProductSkuConverter productSkuConverter;
    
    /**
     * 获取商品SKU表单数据
     *
     * @param id 商品SKUID
     * @return 商品SKU表单数据
     */
    @Override
    public ProductSkuForm getProductSkuFormData(Long id) {
        ProductSku entity = this.getById(id);
        return productSkuConverter.toForm(entity);
    }
    
    /**
     * 新增商品SKU
     *
     * @param formData 商品SKU表单对象
     * @return 是否新增成功
     */
    @Override
    public boolean saveProductSku(ProductSkuForm formData) {
        ProductSku entity = productSkuConverter.toEntity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新商品SKU
     *
     * @param id   商品SKUID
     * @param formData 商品SKU表单对象
     * @return 是否修改成功
     */
    @Override
    public boolean updateProductSku(Long id,ProductSkuForm formData) {
        ProductSku entity = productSkuConverter.toEntity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除商品SKU
     *
     * @param ids 商品SKUID，多个以英文逗号(,)分割
     * @return 是否删除成功
     */
    @Override
    public boolean deleteProductSkus(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的商品SKU数据为空");
        
        // 解析要删除的SKU ID列表
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        
        // 检查是否尝试删除所有SKU（至少保留一条）
        if (!idList.isEmpty()) {
            // 获取这些SKU对应的商品ID
            List<ProductSku> skusToDelete = this.listByIds(idList);
            if (!skusToDelete.isEmpty()) {
                // 按商品ID分组检查
                Map<Long, List<ProductSku>> skusByProduct = skusToDelete.stream()
                        .collect(Collectors.groupingBy(ProductSku::getProductId));
                
                for (Map.Entry<Long, List<ProductSku>> entry : skusByProduct.entrySet()) {
                    Long productId = entry.getKey();
                    List<ProductSku> skusToRemove = entry.getValue();
                    
                    // 获取该商品的SKU总数
                    long totalSkuCount = this.count(new LambdaQueryWrapper<ProductSku>()
                            .eq(ProductSku::getProductId, productId)
                            .eq(ProductSku::getIsDeleted, 0)); // 只统计未删除的
                    
                    // 如果要删除的SKU数量等于该商品的总SKU数量，说明要删除所有SKU，不允许
                    if (skusToRemove.size() >= totalSkuCount) {
                        throw new RuntimeException("每个商品至少需要保留一条SKU记录");
                    }
                }
            }
        }
        
        // 执行删除操作
        return this.removeByIds(idList);
    }

}
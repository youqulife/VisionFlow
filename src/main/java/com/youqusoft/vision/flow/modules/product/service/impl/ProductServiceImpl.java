package com.youqusoft.vision.flow.modules.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.youqusoft.vision.flow.common.constant.TenantConstants;
import com.youqusoft.vision.flow.modules.product.model.entity.ProductSku;
import com.youqusoft.vision.flow.modules.product.model.form.ProductAttribute;
import com.youqusoft.vision.flow.modules.product.model.form.ProductSKUAttribute;
import com.youqusoft.vision.flow.modules.product.model.form.ProductSkuForm;
import com.youqusoft.vision.flow.modules.product.model.vo.ProductSkuVO;
import com.youqusoft.vision.flow.modules.product.service.ProductSkuService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youqusoft.vision.flow.modules.product.mapper.ProductMapper;
import com.youqusoft.vision.flow.modules.product.service.ProductService;
import com.youqusoft.vision.flow.modules.product.model.entity.Product;
import com.youqusoft.vision.flow.modules.product.model.form.ProductForm;
import com.youqusoft.vision.flow.modules.product.model.query.ProductQuery;
import com.youqusoft.vision.flow.modules.product.model.vo.ProductVO;
import com.youqusoft.vision.flow.modules.product.converter.ProductConverter;
import com.youqusoft.vision.flow.modules.product.converter.ProductSkuConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品信息服务实现类
 *
 * @author youqusoft
 * @since 2025-09-27 11:59
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    private final ProductConverter productConverter;
    private final ProductSkuConverter productSkuConverter;
    private final ProductSkuService productSkuService;

    /**
    * 获取商品信息分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<ProductVO>} 商品信息分页列表
    */
    @Override
    public IPage<ProductVO> getProductPage(ProductQuery queryParams) {
        Page<ProductVO> pageVO = this.baseMapper.getProductPage(
                new Page<>(queryParams.getPageNum(), queryParams.getPageSize()),
                queryParams
        );
        if (pageVO.getRecords().isEmpty()) {
            return pageVO;
        }
        List<Long> productIds = pageVO.getRecords().stream()
                .map(ProductVO::getId)
                .toList();
        List<ProductSku> skuList = productSkuService.list(new LambdaQueryWrapper<ProductSku>().in(ProductSku::getProductId, productIds));
        
        // 将ProductSku按productId分组
        Map<Long, List<ProductSku>> skuMapByProductId = skuList.stream()
                .collect(Collectors.groupingBy(ProductSku::getProductId));
        
        // 将ProductSku转换为ProductSkuVO并按productId分组
        Map<Long, List<ProductSkuVO>> skuVoMapByProductId = skuMapByProductId.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .map(productSkuConverter::toVO)
                                .toList()
                ));
        
        // 将ProductSkuVO列表设置到对应的ProductVO中，并处理attribute字段的转换
        pageVO.getRecords().forEach(productVO -> {
            // 将ProductSkuVO列表设置到ProductVO中
            List<ProductSkuVO> productSkuVOs = skuVoMapByProductId.getOrDefault(productVO.getId(), Lists.newArrayList());
            productVO.setSkus(productSkuVOs);
            
            // 处理attribute字段的转换（从JSON字符串到ProductAttribute对象）
            if (productVO.getAttributes() != null) {
                String attributeJson = productVO.getAttributes();
                ProductAttribute attribute = ProductAttribute.fromJsonString(attributeJson);
                productVO.setAttribute(attribute);
            }
        });

        return pageVO;
    }
    
    /**
     * 获取商品信息表单数据
     *
     * @param id 商品信息ID
     * @return 商品信息表单数据
     */
    @Override
    public ProductForm getProductFormData(Long id) {
        Product entity = this.getById(id);
        ProductForm formData = productConverter.toForm(entity);
        List<ProductSku> skus = productSkuService.list(new LambdaQueryWrapper<ProductSku>().eq(ProductSku::getProductId, id));
        List<ProductSkuForm> skuForms = skus.stream()
                .map(productSkuConverter::toForm)
                .toList();
        formData.setSkus(skuForms);
        return formData;
    }
    
    /**
     * 新增商品信息
     *
     * @param formData 商品信息表单对象
     * @return 是否新增成功
     */
    @Override
    @Transactional
    public boolean saveProduct(ProductForm formData) {
        Product entity = productConverter.toEntity(formData);
        entity.setTenantId(TenantConstants.DEFAULT_TENANT_ID);
        entity.setIsDeleted(0);
        boolean saveResult = this.save(entity);
        if (!saveResult) {
            throw new RuntimeException("新增商品信息失败");
        }
        List<ProductSku> skus = Lists.newArrayList();
        for (ProductSkuForm skuFormData: formData.getSkus()) {
            ProductSku sku = productSkuConverter.toEntity(skuFormData);
            sku.setTenantId(TenantConstants.DEFAULT_TENANT_ID);
            sku.setProductId(entity.getId());
            sku.setIsDeleted(0);
            skus.add(sku);
        }
        return this.productSkuService.saveBatch(skus);
    }
    
    /**
     * 更新商品信息
     *
     * @param id   商品信息ID
     * @param formData 商品信息表单对象
     * @return 是否修改成功
     */
    @Override
    @Transactional
    public boolean updateProduct(Long id, ProductForm formData) {
        // 更新商品主表信息
        Product entity = productConverter.toEntity(formData);
        entity.setId(id); // 确保ID正确
        boolean updateResult = this.updateById(entity);
        
        if (!updateResult) {
            throw new RuntimeException("更新商品信息失败");
        }
        
        // 处理SKU信息的更新
        if (formData.getSkus() != null) {
            // 获取当前数据库中该商品的所有SKU
            List<ProductSku> existingSkus = productSkuService.list(
                new LambdaQueryWrapper<ProductSku>().eq(ProductSku::getProductId, id)
            );
            
            // 收集需要更新和新增的SKU
            List<ProductSku> skusToSave = new ArrayList<>();
            // 收集需要删除的SKU ID
            List<Long> skusToDelete = new ArrayList<>();
            
            for (ProductSkuForm skuForm : formData.getSkus()) {
                ProductSku sku = productSkuConverter.toEntity(skuForm);
                sku.setProductId(id);
                sku.setTenantId(TenantConstants.DEFAULT_TENANT_ID);
                sku.setIsDeleted(0);
                
                if (skuForm.getId() != null) {
                    // 更新已存在的SKU
                    sku.setId(skuForm.getId());
                    skusToSave.add(sku);
                } else {
                    // 新增SKU
                    skusToSave.add(sku);
                }
            }
            
            // 查找需要删除的SKU（存在于数据库但不在表单中的SKU）
            List<Long> formSkuIds = formData.getSkus().stream()
                .map(ProductSkuForm::getId)
                .filter(Objects::nonNull)
                .toList();
                
            for (ProductSku existingSku : existingSkus) {
                if (!formSkuIds.contains(existingSku.getId())) {
                    skusToDelete.add(existingSku.getId());
                }
            }
            
            // 执行SKU的批量更新和新增
            if (!skusToSave.isEmpty()) {
                productSkuService.saveOrUpdateBatch(skusToSave);
            }
            
            // 执行SKU的删除操作
            if (!skusToDelete.isEmpty()) {
                productSkuService.removeByIds(skusToDelete);
            }
        }
        
        return true;
    }
    
    /**
     * 删除商品信息
     *
     * @param ids 商品信息ID，多个以英文逗号(,)分割
     * @return 是否删除成功
     */
    @Override
    public boolean deleteProducts(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的商品信息数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

}
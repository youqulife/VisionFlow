package com.youqusoft.vision.flow.modules.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;

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
        return productConverter.toForm(entity);
    }
    
    /**
     * 新增商品信息
     *
     * @param formData 商品信息表单对象
     * @return 是否新增成功
     */
    @Override
    public boolean saveProduct(ProductForm formData) {
        Product entity = productConverter.toEntity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新商品信息
     *
     * @param id   商品信息ID
     * @param formData 商品信息表单对象
     * @return 是否修改成功
     */
    @Override
    public boolean updateProduct(Long id,ProductForm formData) {
        Product entity = productConverter.toEntity(formData);
        return this.updateById(entity);
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

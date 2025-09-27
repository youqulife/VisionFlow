package com.youqusoft.vision.flow.modules.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youqusoft.vision.flow.modules.product.model.entity.Product;
import com.youqusoft.vision.flow.modules.product.model.form.ProductForm;
import com.youqusoft.vision.flow.modules.product.model.query.ProductQuery;
import com.youqusoft.vision.flow.modules.product.model.vo.ProductVO;

/**
 * 商品信息服务接口层
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
public interface ProductService extends IService<Product> {

    /**
     * 商品分页列表
     *
     * @param queryParams 商品查询参数
     * @return 商品分页列表
     */
    IPage<ProductVO> getProductPage(ProductQuery queryParams);

    /**
     * 保存商品信息
     *
     * @param productForm 商品表单数据
     * @return 是否保存成功
     */
    boolean saveProduct(ProductForm productForm);

    /**
     * 获取商品表单数据
     *
     * @param id 商品ID
     * @return 商品表单数据
     */
    ProductForm getProductForm(Long id);

    /**
     * 更新商品信息
     *
     * @param id 商品ID
     * @param productForm 商品表单数据
     * @return 是否更新成功
     */
    boolean updateProduct(Long id, ProductForm productForm);

    /**
     * 删除商品信息
     *
     * @param ids 商品ID数组
     * @return 是否删除成功
     */
    boolean deleteProducts(Long[] ids);
}
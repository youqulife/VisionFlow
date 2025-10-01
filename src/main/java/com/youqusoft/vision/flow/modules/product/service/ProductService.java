package com.youqusoft.vision.flow.modules.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youqusoft.vision.flow.modules.product.model.entity.Product;
import com.youqusoft.vision.flow.modules.product.model.form.ProductForm;
import com.youqusoft.vision.flow.modules.product.model.query.ProductQuery;
import com.youqusoft.vision.flow.modules.product.model.vo.ProductVO;

/**
 * 商品信息服务类
 *
 * @author youqusoft
 * @since 2025-09-27 11:57
 */
public interface ProductService extends IService<Product> {

    /**
     *商品信息分页列表
     *
     * @return {@link IPage<ProductVO>} 商品信息分页列表
     */
    IPage<ProductVO> getProductPage(ProductQuery queryParams);

    /**
     * 获取商品信息表单数据
     *
     * @param id 商品信息ID
     * @return 商品信息表单数据
     */
     ProductForm getProductFormData(Long id);

    /**
     * 新增商品信息
     *
     * @param formData 商品信息表单对象
     * @return 是否新增成功
     */
    boolean saveProduct(ProductForm formData);

    /**
     * 修改商品信息
     *
     * @param id   商品信息ID
     * @param formData 商品信息表单对象
     * @return 是否修改成功
     */
    boolean updateProduct(Long id, ProductForm formData);

    /**
     * 删除商品信息
     *
     * @param ids 商品信息ID，多个以英文逗号(,)分割
     * @return 是否删除成功
     */
    boolean deleteProducts(String ids);

}

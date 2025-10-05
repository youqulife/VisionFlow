package com.youqusoft.vision.flow.modules.product.service;

import com.youqusoft.vision.flow.modules.product.model.entity.ProductSku;
import com.youqusoft.vision.flow.modules.product.model.form.ProductSkuForm;
import com.youqusoft.vision.flow.modules.product.model.query.ProductSkuQuery;
import com.youqusoft.vision.flow.modules.product.model.vo.ProductSkuVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 商品SKU服务类
 *
 * @author youqusoft
 * @since 2025-10-05 09:55
 */
public interface ProductSkuService extends IService<ProductSku> {

    /**
     * 获取商品SKU表单数据
     *
     * @param id 商品SKUID
     * @return 商品SKU表单数据
     */
     ProductSkuForm getProductSkuFormData(Long id);

    /**
     * 新增商品SKU
     *
     * @param formData 商品SKU表单对象
     * @return 是否新增成功
     */
    boolean saveProductSku(ProductSkuForm formData);

    /**
     * 修改商品SKU
     *
     * @param id   商品SKUID
     * @param formData 商品SKU表单对象
     * @return 是否修改成功
     */
    boolean updateProductSku(Long id, ProductSkuForm formData);

    /**
     * 删除商品SKU
     *
     * @param ids 商品SKUID，多个以英文逗号(,)分割
     * @return 是否删除成功
     */
    boolean deleteProductSkus(String ids);

}

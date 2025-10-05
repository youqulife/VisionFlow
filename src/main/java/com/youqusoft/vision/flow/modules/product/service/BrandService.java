package com.youqusoft.vision.flow.modules.product.service;

import com.youqusoft.vision.flow.modules.product.model.entity.Brand;
import com.youqusoft.vision.flow.modules.product.model.form.BrandForm;
import com.youqusoft.vision.flow.modules.product.model.query.BrandQuery;
import com.youqusoft.vision.flow.modules.product.model.vo.BrandVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 品牌服务类
 *
 * @author youqusoft
 * @since 2025-10-04 20:49
 */
public interface BrandService extends IService<Brand> {

    /**
     *品牌分页列表
     *
     * @return {@link IPage<BrandVO>} 品牌分页列表
     */
    IPage<BrandVO> getBrandPage(BrandQuery queryParams);

    /**
     * 获取品牌表单数据
     *
     * @param id 品牌ID
     * @return 品牌表单数据
     */
     BrandForm getBrandFormData(Long id);

    /**
     * 新增品牌
     *
     * @param formData 品牌表单对象
     * @return 是否新增成功
     */
    boolean saveBrand(BrandForm formData);

    /**
     * 修改品牌
     *
     * @param id   品牌ID
     * @param formData 品牌表单对象
     * @return 是否修改成功
     */
    boolean updateBrand(Long id, BrandForm formData);

    /**
     * 删除品牌
     *
     * @param ids 品牌ID，多个以英文逗号(,)分割
     * @return 是否删除成功
     */
    boolean deleteBrands(String ids);

}

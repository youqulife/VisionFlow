package com.youqusoft.vision.flow.modules.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youqusoft.vision.flow.modules.product.mapper.BrandMapper;
import com.youqusoft.vision.flow.modules.product.service.BrandService;
import com.youqusoft.vision.flow.modules.product.model.entity.Brand;
import com.youqusoft.vision.flow.modules.product.model.form.BrandForm;
import com.youqusoft.vision.flow.modules.product.model.query.BrandQuery;
import com.youqusoft.vision.flow.modules.product.model.vo.BrandVO;
import com.youqusoft.vision.flow.modules.product.converter.BrandConverter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;

/**
 * 品牌服务实现类
 *
 * @author youqusoft
 * @since 2025-10-04 20:49
 */
@Service
@RequiredArgsConstructor
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    private final BrandConverter brandConverter;

    /**
    * 获取品牌分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<BrandVO>} 品牌分页列表
    */
    @Override
    public IPage<BrandVO> getBrandPage(BrandQuery queryParams) {
        Page<BrandVO> pageVO = this.baseMapper.getBrandPage(
                new Page<>(queryParams.getPageNum(), queryParams.getPageSize()),
                queryParams
        );
        return pageVO;
    }
    
    /**
     * 获取品牌表单数据
     *
     * @param id 品牌ID
     * @return 品牌表单数据
     */
    @Override
    public BrandForm getBrandFormData(Long id) {
        Brand entity = this.getById(id);
        return brandConverter.toForm(entity);
    }
    
    /**
     * 新增品牌
     *
     * @param formData 品牌表单对象
     * @return 是否新增成功
     */
    @Override
    public boolean saveBrand(BrandForm formData) {
        Brand entity = brandConverter.toEntity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新品牌
     *
     * @param id   品牌ID
     * @param formData 品牌表单对象
     * @return 是否修改成功
     */
    @Override
    public boolean updateBrand(Long id,BrandForm formData) {
        Brand entity = brandConverter.toEntity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除品牌
     *
     * @param ids 品牌ID，多个以英文逗号(,)分割
     * @return 是否删除成功
     */
    @Override
    public boolean deleteBrands(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的品牌数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

}

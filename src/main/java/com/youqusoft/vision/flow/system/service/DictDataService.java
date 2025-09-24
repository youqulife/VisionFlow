package com.youqusoft.vision.flow.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youqusoft.vision.flow.common.model.Option;
import com.youqusoft.vision.flow.system.model.entity.DictData;
import com.youqusoft.vision.flow.system.model.form.DictDataForm;
import com.youqusoft.vision.flow.system.model.query.DictDataPageQuery;
import com.youqusoft.vision.flow.system.model.vo.DictDataPageVO;

import java.util.List;

/**
 * 字典数据接口
 *
 * @author Ray Hao
 * @since 2023/3/4
 */
public interface DictDataService extends IService<DictData> {

    /**
     * 字典数据分页列表
     *
     * @param queryParams
     * @return
     */
    Page<DictDataPageVO> getDictDataPage(DictDataPageQuery queryParams);

    /**
     * 获取字典数据表单
     *
     * @param id 字典数据ID
     * @return
     */
    DictDataForm getDictDataForm(Long id);

    /**
     * 保存字典数据
     *
     * @param formData
     * @return
     */
    boolean saveDictData(DictDataForm formData);

    /**
     * 更新字典数据
     *
     * @param formData 字典数据表单
     * @return
     */
    boolean updateDictData(DictDataForm formData);

    /**
     * 删除字典数据
     *
     * @param ids 字典数据ID,多个逗号分隔
     */
    void deleteDictDataByIds(String ids);

    /**
     * 获取字典数据列表
     *
     * @param dictCode 字典编码
     * @return
     */
    List<Option<String>> getDictDataList(String dictCode);
}

package com.youqusoft.vision.flow.tenant.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.youqusoft.vision.flow.common.model.Option;
import com.youqusoft.vision.flow.shared.codegen.model.entity.GenConfig;
import com.youqusoft.vision.flow.system.model.entity.Menu;
import com.youqusoft.vision.flow.system.model.form.MenuForm;
import com.youqusoft.vision.flow.system.model.query.MenuQuery;
import com.youqusoft.vision.flow.system.model.vo.MenuVO;
import com.youqusoft.vision.flow.system.model.vo.RouteVO;
import com.youqusoft.vision.flow.tenant.model.entity.TenantMenu;
import com.youqusoft.vision.flow.tenant.model.form.TenantMenuForm;
import com.youqusoft.vision.flow.tenant.model.query.TenantMenuQuery;
import com.youqusoft.vision.flow.tenant.model.vo.TenantMenuVO;
import com.youqusoft.vision.flow.tenant.model.vo.TenantRouteVO;

import java.util.List;

/**
 * 菜单业务接口
 * 
 * @author haoxr
 * @since 2020/11/06
 */
public interface TenantMenuService extends IService<TenantMenu> {

    /**
     * 获取菜单表格列表
     */
    List<TenantMenuVO> listMenus(TenantMenuQuery queryParams);

    /**
     * 获取菜单下拉列表
     *
     * @param onlyParent 是否只查询父级菜单
     */
    List<Option<Long>> listMenuOptions(boolean onlyParent);

    /**
     * 新增菜单
     *
     * @param menuForm  菜单表单对象
     */
    boolean saveMenu(TenantMenuForm menuForm);

    /**
     * 获取当前用户的菜单路由列表
     */
    List<TenantRouteVO> listCurrentUserRoutes();

    /**
     * 获取当前用户的菜单路由列表（指定数据源）
     *
     * @param datasource 数据源名称，如：master(主库)、naiveui(NaiveUI数据库)、template(模板数据库)
     */
    List<TenantRouteVO> listCurrentUserRoutes(String datasource);

    /**
     * 修改菜单显示状态
     * 
     * @param menuId 菜单ID
     * @param visible 是否显示(1-显示 0-隐藏)
     */
    boolean updateMenuVisible(Long menuId, Integer visible);

    /**
     * 获取菜单表单数据
     *
     * @param id 菜单ID
     */
    TenantMenuForm getMenuForm(Long id);

    /**
     * 删除菜单
     *
     * @param id 菜单ID
     */
    boolean deleteMenu(Long id);

    /**
     * 代码生成时添加菜单
     *
     * @param parentMenuId 父菜单ID
     * @param genConfig   实体名
     */
    void addMenuForCodegen(Long parentMenuId, GenConfig genConfig);
}

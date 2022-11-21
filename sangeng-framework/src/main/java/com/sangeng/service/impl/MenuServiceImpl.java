package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.constants.SysConstants;
import com.sangeng.domain.entity.Menu;
import com.sangeng.mapper.MenuMapper;
import com.sangeng.service.IMenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author YEHANG
 * @since 2022-11-21
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    /**
     * 根据用户id查询 权限信息
     * @param id 用户id
     */
    @Override
    public List<String> selectPermsKeyByUserId(Long id) {
        //如果是管理员 返回所有的权限
        if (id == 1L){
            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(Menu::getMenuType, SysConstants.MENU,SysConstants.BUTTON);
            wrapper.eq(Menu::getStatus,SysConstants.STATUS_NORMAL);
            List<Menu> menus = list(wrapper);
            /*List<String> perms = menus.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return perms;*/
            return menus.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
        }
        //否则返回当前用户所具有的权限
        return getBaseMapper().selectPermsByUserId(id);
    }
}

package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.entity.Menu;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author YEHANG
 * @since 2022-11-21
 */
public interface IMenuService extends IService<Menu> {

    /**根据用户id查询 权限信息*/
    List<String> selectPermsKeyByUserId(Long userId);
}

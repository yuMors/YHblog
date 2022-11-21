package com.sangeng.service;

import com.sangeng.domain.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author YEHANG
 * @since 2022-11-21
 */
public interface IRoleService extends IService<Role> {

    /**根据用户id查询 角色信息*/
    List<String> selectRolesKeyByUserId(Long userId);
}

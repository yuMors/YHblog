package com.sangeng.service.impl;

import com.sangeng.domain.entity.Role;
import com.sangeng.mapper.RoleMapper;
import com.sangeng.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author YEHANG
 * @since 2022-11-21
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    /**
     * 根据用户id查询 角色信息
     * @param userId 用户id
     */
    @Override
    public List<String> selectRolesKeyByUserId(Long userId) {
        return null;
    }
}

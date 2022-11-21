package com.sangeng.mapper;

import com.sangeng.domain.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author YEHANG
 * @since 2022-11-21
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**返回当前用户所具有的权限*/
    List<String> selectPermsByUserId(Long userId);
}

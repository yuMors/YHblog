package com.sangeng.constants;

/**
 * 字面值处理
 * 实际开发中尽量不用数字 用字面值 把字面值放在一个类中 方便维护
 */
public class SysConstants {

    /**文章是草稿 draft 草稿*/
    public static final int ARTICLE_STATUS_DRAFT = 1;

    /**文章是正常分布状态 normal 正常*/
    public static final int ARTICLE_STATUS_NORMAL = 0;

    /**状态是否禁用 0 正常 1 禁用*/
    public static final String STATUS_NORMAL = "0";

    /**友联 审核状态 (0代表审核通过，1代表审核未通过，2代表未审核)*/

    public static final String Link_STATUS_NORMAL = "0";

    /**评论类型为：文章评论*/
    public static final String ARTICLE_COMMENT = "0";

    /**评论类型为：友联评论*/
    public static final String LINK_COMMENT = "1";

    /**文章浏览量*/
    public static final String ARTICLE_VIEW_COUNT = "article:viewCount";

    /**用户登录信息*/
    public static final String blogLogin = "bloglogin:";

    /**登录*/
    public static final String login = "login:";

    /**后端登录token*/
    public static final String token = "token";

    /**permissions需要有所有菜单类型为C或者F的，状态为正常的，未删除的权限*/
    public static final String MENU = "C";

    /**C是菜单 F是按钮*/
    public static final String BUTTON = "F";

}

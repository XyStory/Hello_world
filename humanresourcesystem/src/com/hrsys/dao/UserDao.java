package com.hrsys.dao;

import com.hrsys.bean.PageModel;
import com.hrsys.entity.User;

import java.util.List;

/**
 * @author steve
 */
public interface UserDao {
    /**
     * getUserByLoginName 方法用来验证用户登录
     * @param user 登录用户的对象
     * @return 如用户存在且密码正确则返回对应的user，否则返回null
     */
    User getUser(User user);

    /**
     * insertUser 方法用来添加用户
     * @param user 需要添加的用户对象
     * @return 返回值判断用户添加是否成功
     */
    boolean insertUser(User user);

    /**
     * removeUserById
     * @param ids
     * @return
     */
    int removeUserById(List<Integer> ids);

    /**
     * updateUserById
     * @param user
     * @return
     */
    boolean updateUserById(User user);

    /**
     * getUserById
     * @param id
     * @return
     */
    User getUserById(int id);

    /**
     * listUsers
     * @param user
     * @param pageModel
     * @return
     */
    List<User> listUsers(User user, PageModel pageModel);

    /**
     * countTotalRecordSum
     * @param user
     * @return
     */
    int countTotalRecordSum(User user);
}

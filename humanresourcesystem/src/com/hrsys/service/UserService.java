package com.hrsys.service;

import com.hrsys.bean.PageModel;
import com.hrsys.entity.User;

import java.util.List;

/**
 * @author steve
 */
public interface UserService {
    /**
     * loginService方法用来判断用户登录是否成功
     * @param user 保存了登录请求中的用户名和密码的对象
     * @return 返回登录成功与否
     */
    boolean loginService(User user);

    /**
     * insertUserService方法用来添加用户
     * @param user 需要添加的用户对象
     * @return 返回值判断是否添加成功
     */
    boolean insertUserService(User user);

    /**
     * removeUserService
     * @param ids
     * @param userId
     * @return
     */
    int[] removeUserService(List<Integer> ids, int userId);

    /**
     * updateUserService
     * @param user
     * @return
     */
    boolean updateUserService(User user);

    /**
     * getUserService
     * @param id
     * @return
     */
    User getUserService(int id);

    /**
     * listUsersService
     * @param user
     * @param pageModel
     * @return
     */
    List<User> listUsersService(User user, PageModel pageModel);
}

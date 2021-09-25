package com.hrsys.service.impl;

import com.hrsys.bean.PageModel;
import com.hrsys.dao.DaoFactory;
import com.hrsys.dao.UserDao;
import com.hrsys.entity.User;
import com.hrsys.service.UserService;

import java.util.List;
import java.util.Objects;

/**
 * @author steve
 */
public class UserServiceImpl implements UserService {
    UserDao userDao;

    public UserServiceImpl() {
        userDao = DaoFactory.userDao();
    }

    @Override
    public boolean loginService(User user) {
        User resultUser = userDao.getUser(user);
        if (resultUser != null) {
            user.setStatus(resultUser.getStatus());
            user.setCreateDate(resultUser.getCreateDate());
            user.setUsername(resultUser.getUsername());
            return true;
        }
        return false;
    }

    @Override
    public boolean insertUserService(User user) {
        if (user.getLoginName() == null || user.getPwd() == null || user.getUsername() == null
                || Objects.equals(user.getLoginName(), "") || Objects.equals(user.getPwd(), "")
                || Objects.equals(user.getUsername(), "")) {
            return false;
        }
        return userDao.insertUser(user);
    }

    @Override
    public int[] removeUserService(List<Integer> ids, int userId) {
        ids.removeIf(id -> id == userId);

        int success = userDao.removeUserById(ids);
        int fail = ids.size() - success;
        int[] removeCount = {success, fail};
        return removeCount;
    }

    @Override
    public boolean updateUserService(User user) {
        return userDao.updateUserById(user);
    }

    @Override
    public User getUserService(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> listUsersService(User user, PageModel pageModel) {
        if (user.getUsername() == null || Objects.equals(user.getUsername(), "")) {
            user.setUsername("%");
        } else {
            user.setUsername("%" + user.getUsername() + "%");
        }
        int totalCount = userDao.countTotalRecordSum(user);
        pageModel.setTotalRecordSum(totalCount);
        return userDao.listUsers(user, pageModel);
    }
}

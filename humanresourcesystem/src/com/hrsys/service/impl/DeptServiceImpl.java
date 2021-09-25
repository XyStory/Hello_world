package com.hrsys.service.impl;

import com.hrsys.bean.PageModel;
import com.hrsys.dao.DaoFactory;
import com.hrsys.dao.DeptDao;
import com.hrsys.entity.Dept;
import com.hrsys.service.DeptService;

import java.util.List;
import java.util.Objects;

/**
 * @author steve
 */
public class DeptServiceImpl implements DeptService {
    DeptDao deptDao;

    public DeptServiceImpl() {
        deptDao = DaoFactory.deptDao();
    }

    @Override
    public boolean insertDeptService(Dept dept) {
        if (dept.getName() == null || Objects.equals(dept.getName(), "")) {
            return false;
        }
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(1);
        pageModel.setPageSize(1024*1024);
        List<Dept> deptList = listDeptByName("", pageModel);
        for (Dept deptQuery : deptList) {
            if (Objects.equals(deptQuery.getName(), dept.getName())) {
                return false;
            }
        }

        return deptDao.insertDept(dept);
    }

    @Override
    public int[] removeDeptService(int[] ids) {
        int success = deptDao.removeDeptById(ids);
        int fail = ids.length - success;
        int[] deleteCount = {success, fail};
        return deleteCount;
    }

    @Override
    public Dept getDeptService(int id) {
        return deptDao.getDeptById(id);
    }

    @Override
    public List<Dept> listDeptByName(String deptName, PageModel pageModel) {
        if (deptName == null || Objects.equals(deptName, "")) {
            deptName = "%";
        } else {
            deptName = "%" + deptName + "%";
        }
        int totalCount = deptDao.countTotalRecordSum(deptName);
        pageModel.setTotalRecordSum(totalCount);
        return deptDao.listDepts(deptName, pageModel);
    }

    @Override
    public boolean updateDeptService(Dept dept) {
        return deptDao.updateDept(dept);
    }
}

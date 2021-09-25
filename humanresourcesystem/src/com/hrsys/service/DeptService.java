package com.hrsys.service;

import com.hrsys.bean.PageModel;
import com.hrsys.entity.Dept;

import java.util.List;

/**
 * @author steve
 */
public interface DeptService {
    /**
     * insertDeptService方法用于添加新的部门
     * @param dept 需要添加的部门对象
     * @return 返回是否添加成功
     */
    boolean insertDeptService(Dept dept);

    /**
     * removeDeptService方法用来删除对应id的部门
     * @param ids 需要删除的数据的id
     * @return 返回成功了多少条数据
     */
    int[] removeDeptService(int[] ids);

    /**
     * getDeptService
     * @param id
     * @return
     */
    Dept getDeptService(int id);

    /**
     * listDeptByName方法通过获得的部门名来获取部门集合
     * @param deptName 部门名
     * @param pageModel 页码对象
     * @return  返回获取到的部门对象集合，没有对应部门时则返回 null
     */
    List<Dept> listDeptByName(String deptName, PageModel pageModel);

    /**
     * updateDeptService 方法用于更新对应部门id的部门数据
     * @param dept 需要更改的部门对象
     * @return 返回数据更新是否成功
     */
    boolean updateDeptService(Dept dept);
}

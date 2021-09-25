package com.hrsys.dao;

import com.hrsys.bean.PageModel;
import com.hrsys.entity.Dept;

import java.util.List;

/**
 * @author steve
 */
public interface DeptDao {
    /**
     * insertDept方法用来添加部门
     * @param dept 要添加的部门对象
     * @return 返回添加成功与否
     */
    boolean insertDept(Dept dept);

    /**
     * removeDeptById 用来删除与id对应的部门数据
     * @param ids 要删除的数据的id
     * @return 返回成功删除了多少条数据
     */
    int removeDeptById(int[] ids);

    /**
     * getDeptById
     * @param id
     * @return
     */
    Dept getDeptById(int id);

    /**
     * listDepts方法用来获取部门信息
     * @param deptName 要搜索的部门名
     * @return 返回值是部门对象组成的集合
     */
    List<Dept> listDepts(String deptName, PageModel pageModel);

    /**
     * updateDept 用于更新部门数据
     * @param dept 保存了需要更新的数据的对象
     * @return 返回更新是否成功
     */
    boolean updateDept(Dept dept);

    /**
     * countTotalRecordSum 用于统计表中数据数量
     * @param deptName
     * @return 返回表中数据条数
     */
    int countTotalRecordSum(String deptName);
}

package com.hrsys.dao;

import com.hrsys.bean.PageModel;
import com.hrsys.entity.Notice;

import java.util.List;

/**
 * @author steve
 * @version 1.0
 */
public interface NoticeDao {
    /**
     * insertNotice
     * @param notice
     * @return
     */
    boolean insertNotice(Notice notice);

    /**
     * removeNotice
     * @param ids
     * @return
     */
    int removeNotice(int[] ids);

    /**
     * listNotices
     * @param notice
     * @param pageModel
     * @return
     */
    List<Notice> listNotices(Notice notice, PageModel pageModel);

    /**
     * countTotalRecordSum
     * @param notice
     * @return
     */
    int countTotalRecordSum(Notice notice);
}

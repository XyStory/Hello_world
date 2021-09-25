package com.hrsys.service;

import com.hrsys.bean.PageModel;
import com.hrsys.entity.Notice;

import java.util.List;

/**
 * @author steve
 * @version 1.0
 */
public interface NoticeService {
    /**
     * insertNoticeService
     * @param notice
     * @return
     */
    boolean insertNoticeService(Notice notice);

    /**
     * removeNoticeService
     * @param ids
     * @return
     */
    int[] removeNoticeService(int[] ids);

    /**
     * listNoticesService
     * @param notice
     * @param pageModel
     * @return
     */
    List<Notice> listNoticesService(Notice notice, PageModel pageModel);
}

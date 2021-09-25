package com.hrsys.service.impl;

import com.hrsys.bean.PageModel;
import com.hrsys.dao.DaoFactory;
import com.hrsys.dao.NoticeDao;
import com.hrsys.entity.Notice;
import com.hrsys.service.NoticeService;

import java.util.List;
import java.util.Objects;

/**
 * @author steve
 * @version 1.0
 */
public class NoticeServiceImpl implements NoticeService {
    private NoticeDao noticeDao;

    public NoticeServiceImpl() {
        noticeDao = DaoFactory.noticeDao();
    }

    @Override
    public boolean insertNoticeService(Notice notice) {
        if (notice.getTitle() == null || Objects.equals(notice.getTitle(), "")
                || notice.getContent() == null || Objects.equals(notice.getContent(), "")) {
            return false;
        }
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(1);
        pageModel.setPageSize(1024*1024);
        List<Notice> notices = listNoticesService(new Notice(), pageModel);
        for (Notice noticeQuery : notices) {
            if (Objects.equals(noticeQuery.getTitle(), notice.getTitle())) {
                return false;
            }
        }

        return noticeDao.insertNotice(notice);
    }

    @Override
    public int[] removeNoticeService(int[] ids) {
        int success = noticeDao.removeNotice(ids);
        int fail = ids.length - success;
        int[] removeCount = {success, fail};
        return removeCount;
    }

    @Override
    public List<Notice> listNoticesService(Notice notice, PageModel pageModel) {
        if (notice.getTitle() == null || Objects.equals(notice.getTitle(), "")) {
            notice.setTitle("%");
        } else {
            notice.setTitle("%" + notice.getTitle() + "%");
        }
        int total = noticeDao.countTotalRecordSum(notice);
        pageModel.setTotalRecordSum(total);
        return noticeDao.listNotices(notice, pageModel);
    }
}

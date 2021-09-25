package com.hrsys.service.impl;

import com.hrsys.bean.PageModel;
import com.hrsys.dao.DaoFactory;
import com.hrsys.dao.DocumentDao;
import com.hrsys.entity.Document;
import com.hrsys.service.DocumentService;

import java.util.List;
import java.util.Objects;

/**
 * @author steve
 */
public class DocumentServiceImpl implements DocumentService {
    DocumentDao documentDao;

    public DocumentServiceImpl() {
        documentDao = DaoFactory.documentDao();
    }

    @Override
    public boolean insertDocumentService(Document document) {
        if (document.getTitle() == null || Objects.equals(document.getTitle(), "")) {
            return false;
        }
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(1);
        pageModel.setPageSize(1024*1024);
        List<Document> documents = listDocuments(new Document(), pageModel);
        for (Document doc : documents) {
            if (Objects.equals(doc.getTitle(), document.getTitle())) {
                return false;
            }
        }

        return documentDao.insertDocument(document);
    }

    @Override
    public int[] removeDocumentService(int[] ids) {
        int success = documentDao.removeDocumentById(ids);
        int fail = ids.length - success;
        int[] deleteCount = {success, fail};
        return deleteCount;
    }

    @Override
    public List<Document> listDocuments(Document document, PageModel pageModel) {
        if (document.getTitle() == null || Objects.equals(document.getTitle(), "")) {
            document.setTitle("%");
        } else {
            document.setTitle("%" + document.getTitle() + "%");
        }

        if (document.getFilename() == null || Objects.equals(document.getFilename(), "")) {
            document.setFilename("%");
        } else {
            document.setFilename("%" + document.getFilename() + "%");
        }
        int totalCount = documentDao.countTotalRecordSum(document);
        pageModel.setTotalRecordSum(totalCount);
        List<Document> documents = documentDao.listDocuments(document, pageModel);
        return documents;
    }

    @Override
    public List<String> listFileNamesService(int[] ids) {
        return documentDao.listFileNames(ids);
    }
}

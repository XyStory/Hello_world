package com.hrsys.dao;

import com.hrsys.bean.PageModel;
import com.hrsys.entity.Document;

import java.util.List;

/**
 * @author steve
 */
public interface DocumentDao {
    /**
     * insertDocument
     * @param document
     * @return
     */
    boolean insertDocument(Document document);

    /**
     * removeDocumentById
     * @param ids
     * @return
     */
    int removeDocumentById(int[] ids);

    /**
     * listDocuments
     * @param document
     * @param pageModel
     * @return
     */
    List<Document> listDocuments(Document document, PageModel pageModel);

    /**
     * countTotalRecordSum
     * @param document
     * @return
     */
    int countTotalRecordSum(Document document);

    /**
     * listFileNames
     * @param ids
     * @return
     */
    List<String> listFileNames(int[] ids);
}

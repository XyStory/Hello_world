package com.hrsys.service;

import com.hrsys.bean.PageModel;
import com.hrsys.entity.Document;

import java.util.List;

/**
 * @author steve
 */
public interface DocumentService {
    /**
     * insertDocumentService
     * @param document
     * @return
     */
    boolean insertDocumentService(Document document);

    /**
     * removeDocumentService
     * @param ids
     * @return
     */
    int[] removeDocumentService(int[] ids);

    /**
     * listDocuments
     * @param document
     * @param pageModel
     * @return
     */
    List<Document> listDocuments(Document document, PageModel pageModel);

    /**
     * listFileNamesService
     * @param ids
     * @return
     */
    List<String> listFileNamesService(int[] ids);
}

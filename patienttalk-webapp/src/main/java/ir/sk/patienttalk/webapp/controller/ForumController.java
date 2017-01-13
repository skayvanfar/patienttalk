package ir.sk.patienttalk.webapp.controller;

import ir.sk.patienttalk.common.persistence.PersistenceException;
import ir.sk.patienttalk.common.persistence.file.web.TempFileAccess;
import ir.sk.patienttalk.common.persistence.jpa.PagingDataList;
import ir.sk.patienttalk.model.domain.Thread;
import ir.sk.patienttalk.webapp.service.ForumServce;
import ir.sk.patienttalk.webapp.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/12/2017.
 */
@Controller
@RequestMapping(value = "/forum")
public class ForumController extends BaseController{

    @Autowired
    private ThreadService threadService;

    @Autowired
    TempFileAccess tempFileAccess;

    @RequestMapping(value = {"/threads"})
    public String threads(Map<String, Object> model,
                         @RequestParam(value = "query", required = false) String query,
                         @RequestParam(value = "page", required = false) Integer page,
                         @RequestParam(value = "cat", required = false) String cat) throws PersistenceException { //tod cat geted in persian
        if (page == null) page = 0;
        PagingDataList<Thread> threadPagingDataList;
       /* if (cat != null && cat.length() != 0) {
            Category category = db.category().readCategory(cat);
            db.category().refreshChildren(category);
            productions = db.p().searchProductions(query, null, null, null, null, null, category, true, null, page, 12);
        } else*/
        threadPagingDataList = threadService.searchThreadsByPaging(query, page, 10);
        model.put("threadPagingDataList", threadPagingDataList);
        model.put("query", query);
        LoginController.anonymousHome(model);
        return "thread";
    }
}

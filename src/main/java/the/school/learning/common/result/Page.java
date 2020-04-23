package the.school.learning.common.result;

import java.util.Collections;
import java.util.List;

/**
 * 分页
 */
public class Page<T> {
    private int totalRecord;
    private int currentPage;
    private int pageSize;
    private List<T> list;

    public static <T> Page<T> empty() {
        Page<T> page = new Page<T>();
        page.totalRecord = 0;
        page.setList(Collections.emptyList());
        return page;
    }

    public int limit() {
        if (pageSize <= 0) {
            pageSize = 10;
        }
        return pageSize;
    }

    public int offset() {
        if (currentPage <= 0) {
            currentPage = 1;
        }
        return (currentPage - 1) * limit();
    }


    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}

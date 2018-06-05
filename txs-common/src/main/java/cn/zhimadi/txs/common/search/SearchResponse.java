package cn.zhimadi.txs.common.search;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * DataTables 响应
 *
 * @param <T> the type parameter
 * @author : mingweigao / mwgao@vip.qq.com
 * @version : 1.0
 */
@Data
public class SearchResponse<T> implements Serializable {

    private static final long serialVersionUID = 7962000463384155164L;
    private long recordsTotal;
    private long recordsFiltered;
    private List<T> data;

    /**
     *
     */
    public SearchResponse() {
        super();
    }

    /**
     * @param recordsTotal
     * @param recordsFiltered
     * @param data
     */
    public SearchResponse(long recordsTotal, long recordsFiltered, List<T> data) {
        super();
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }

}

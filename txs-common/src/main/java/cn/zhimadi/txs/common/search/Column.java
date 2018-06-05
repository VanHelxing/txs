package cn.zhimadi.txs.common.search;

import lombok.Data;

import java.io.Serializable;

/**
 * DataTables列定义
 *
 * @author : mingweigao / mwgao@vip.qq.com
 * @version : 1.0
 */
@Data
public class Column implements Serializable {

    private static final long serialVersionUID = 6807275991259883569L;

    private String data;
    private String name;
    private Boolean searchable;
    private Boolean orderable;
    private Search search;

    public Column() {
        super();
    }

    public Column(String data, String name, Boolean searchable, Boolean orderable) {
        super();
        this.data = data;
        this.name = name;
        this.searchable = searchable;
        this.orderable = orderable;
    }

    public Column(String data, String name, Boolean searchable, Boolean orderable, Search search) {
        super();
        this.data = data;
        this.name = name;
        this.searchable = searchable;
        this.orderable = orderable;
        this.search = search;
    }

}

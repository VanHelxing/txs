package cn.zhimadi.txs.common.search;

import lombok.Data;

import java.io.Serializable;

/**
 * 搜索
 *
 * @author : mingweigao / mwgao@vip.qq.com
 * @version : 1.0
 */
@Data
public class Search implements Serializable {
    private static final long serialVersionUID = -4046020743666829009L;

    private String value;
    private Boolean regex;

    public Search() {
        super();
    }

    public Search(String value) {
        this.value = value;
    }

    public Search(String value, Boolean regex) {
        super();
        this.value = value;
        this.regex = regex;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Search [value=" + value + ", regex=" + regex + "]";
    }

}

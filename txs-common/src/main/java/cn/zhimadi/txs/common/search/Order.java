package cn.zhimadi.txs.common.search;

import lombok.Data;

import java.io.Serializable;

/**
 * 排序
 *
 * @author : mingweigao / mwgao@vip.qq.com
 * @version : 1.0
 */
@Data
public class Order implements Serializable {

    private static final long serialVersionUID = 1054845804325949551L;

    private int column;
    private String dir;

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Order [column=" + column + ", dir=" + dir + "]";
    }

}

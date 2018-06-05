package cn.zhimadi.txs.common.search;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import java.io.Serializable;
import java.util.List;

/**
 * DataTables 请求
 *
 * @author : mingweigao / mwgao@vip.qq.com
 * @version : 1.0
 */
@Data
public class DataTable implements Serializable {

    private static final long serialVersionUID = -3603909416447343299L;

    protected int draw = 1;
    /**
     * 开始行号
     */
    private int start = 0;
    /**
     * 每页多少条记录
     */
    protected int length = 10;
    /**
     * 快速查询
     */
    protected Search search;
    /**
     * 排序
     */
    protected List<Order> order;
    /**
     * 列集合
     */
    protected List<Column> columns;

    /**
     * 条件查询
     */
    protected List<SearchParam> searchParams;

    /**
     * 创建分页请求.
     * @return
     */
    public PageRequest buildPageRequest() {
        Sort sort = null;
        Order temp = order.get(0);
        String sortOrder = temp.getDir();
        String sortName = columns.get(temp.getColumn()).getData();
        if ("desc".equals(sortOrder)) {
            sort = new Sort(Direction.DESC, sortName);
        } else if ("asc".equals(sortOrder)) {
            sort = new Sort(Direction.ASC, sortName);
        }
        return new PageRequest(start / length, length, sort); //已过期的方法
//        return PageRequest.of(start / length, length, sort);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DataTable{" +
                "draw=" + draw +
                ", start=" + start +
                ", length=" + length +
                ", search=" + search +
                ", order=" + order +
                ", columns=" + columns +
                ", searchParams=" + searchParams +
                '}';

    }

}

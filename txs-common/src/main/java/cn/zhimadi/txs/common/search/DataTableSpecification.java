package cn.zhimadi.txs.common.search;

import cn.zhimadi.txs.common.util.DateUtils;
import cn.zhimadi.txs.common.util.ListUtils;
import cn.zhimadi.txs.common.util.StringUtils;
import com.google.common.collect.Lists;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * 构建DataTables全局搜索
 *
 * @author : mingweigao / mwgao@vip.qq.com
 * @version : 1.0
 */
public class DataTableSpecification {

    /**
     * 构建DataTables全局搜索
     *
     * @param <T>       the type parameter
     * @param dataTable the data table
     * @param clazz     the clazz
     * @return the specification
     * @author : mingweigao / 2017-04-30
     */
    public static <T> Specification<T> buildGlobalSpec(final DataTable dataTable, final Class<T> clazz) {
        return new Specification<T>() {

            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

                List<SearchParam> searchParams = dataTable.getSearchParams();
                List<Column> columns = dataTable.getColumns();
                String searchValue = dataTable.getSearch().getValue();

                //search属性为空，走原来的逻辑
                if (null == searchParams || searchParams.size() == 0){

                    if (!ListUtils.isEmpty(columns) && StringUtils.isNotEmpty(searchValue)) {

                        List<Predicate> predicates = Lists.newArrayList();
                        for (Column column : columns) {
                            if (column.getSearchable()) {
                                // nested path translate, 如Task的名为"user.name"的filedName, 转换为Task.user.name属性
                                String fieldName = column.getData();
                                if (StringUtils.isNotEmpty(fieldName) && !"id".equals(fieldName)) {
                                    String[] names = StringUtils.split(column.getData(), ".");
                                    Path<String> expression = root.get(names[0]);
                                    for (int i = 1; i < names.length; i++) {
                                        expression = expression.get(names[i]);
                                    }
                                    predicates.add(builder.like(expression, "%" + searchValue + "%"));
                                }
                            }
                        }

                        // 将所有条件用 or 联合起来
                        if (predicates.size() > 0) {
                            return builder.or(predicates.toArray(new Predicate[predicates.size()]));
                        }
                    }
                }else{

                    //条件查询，最终所有条件用AND连起来
                    List<SearchParam> condQuerys = dataTable.getSearchParams();
                    List<Predicate> predicates = Lists.newArrayList();
                    for (Column column : columns) {
                        if (column.getSearchable()) {
                            for (SearchParam searchParam : condQuerys) {

                                //列名类型_列名_查询类型_查询参数   string_name_eq_lee、string_name_between_8_15
                                Object value = null;//查询参数
                                if ("date".equalsIgnoreCase(searchParam.getJavaType()) && StringUtils.isNotEmpty(searchParam.getValue())) {
                                    value = DateUtils.string2Date(searchParam.getValue(), "yyyy-MM-dd");
                                } else {
                                    value = searchParam.getValue();
                                }

                                // nested path translate, 如Task的名为"user.name"的filedName, 转换为Task.user.name属性
                                String fieldName = searchParam.getProperty();
                                if (StringUtils.isNotEmpty(fieldName)) {

                                    String[] names = StringUtils.split(searchParam.getProperty(), ".");
                                    Path expression = root.get(names[0]);
                                    for (int i = 1; i < names.length; i++) {
                                        expression = expression.get(names[i]);
                                    }

                                    //根据搜索类型，构建相应的表达式
                                    switch (searchParam.getType().toLowerCase()) {

                                        case "eq"://相等 =
                                            predicates.add(builder.equal(expression, value));
                                            break;
                                        case "ne"://不等于 !=
                                            predicates.add(builder.notEqual(expression, value));
                                            break;
                                        case "like"://模糊查询 like
                                            predicates.add(builder.like(expression, "%" + value + "%"));
                                            break;
                                        case "between": //区间


                                            //如果value不为空,构建大于等于表达式
                                            if (StringUtils.isNotEmpty(searchParam.getValue())) {
                                                predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) value));
                                            } else {

                                                //如果value1不为空,构建小于等于表达式
                                                Object otherObj = null;
                                                if ("date".equalsIgnoreCase(searchParam.getJavaType())) {
                                                    otherObj = DateUtils.string2Date(searchParam.getValue1(), "yyyy-MM-dd");
                                                } else {
                                                    otherObj = searchParam.getValue1();
                                                }
                                                predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) otherObj));
                                            }
                                            break;
                                        case "ge"://大于等于 >=
                                            predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) value));
                                            break;
                                        case "le":// 小于等于 <=
                                            predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) value));
                                            break;
                                        case "gt"://大于 >
                                            predicates.add(builder.greaterThan(expression, (Comparable) value));
                                            break;
                                        case "lt"://小于 <
                                            predicates.add(builder.lessThan(expression, (Comparable) value));
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            }
                        }


                        // 将所有条件用 and 联合起来
                        if (predicates.size() > 0) {
                            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
                        }
                    }
                }

                return builder.conjunction();
            }
        };
    }

    public static <T> Specification<T> buildConditionSpec(final DataTable dataTable, final Class<T> clazz) {
        return new Specification<T>() {

            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

                List<Column> columns = dataTable.getColumns();
                if (!ListUtils.isEmpty(columns)) {
                    List<Predicate> predicates = Lists.newArrayList();
                    for (Column column : columns) {
                        if (column.getSearchable()) {
                            Search search = column.getSearch();
                            String searchValue = search.getValue();
                            if (StringUtils.isNotEmpty(searchValue)) {
                                // nested path translate, 如Task的名为"user.name"的filedName, 转换为Task.user.name属性
                                String fieldName = column.getData();
                                if (StringUtils.isNotEmpty(fieldName)) {
                                    String[] names = StringUtils.split(column.getData(), ".");
                                    Path<String> expression = root.get(names[0]);
                                    for (int i = 1; i < names.length; i++) {
                                        expression = expression.get(names[i]);
                                    }
                                    predicates.add(builder.like(expression, "%" + searchValue + "%"));
                                }
                            }
                        }
                    }

                    // 将所有条件用 or 联合起来
                    if (predicates.size() > 0) {
                        return builder.or(predicates.toArray(new Predicate[predicates.size()]));
                    }
                }

                return builder.conjunction();
            }
        };
    }
}

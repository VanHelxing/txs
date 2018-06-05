package cn.zhimadi.txs.common.search;

import lombok.Data;

import java.io.Serializable;


/**
 * 搜索参数
 * @author : lixiaoqiang / lixiaoqiang@zhimadi.cn
 * @version : 1.0
 */
@Data
public class SearchParam implements Serializable {

    private static final long serialVersionUID = -4046020743666829009L;

    /**
     * 搜索列名属性类型
     */
    private String javaType;

    /**
     * 搜索列名属性
     */
    private String property;

    /**
     * 搜索类型
     */
    private String type;

    /**
     * 搜索类型
     */
    private String value;

    /**
     * 搜索类型
     */
    private String value1;

    /**
     * 构造方法
     */
    public SearchParam() {
    }


    /**
     * 构造方法
     *
     * @param javaType the property type
     * @param property     the property
     * @param type         the type
     * @param value       the value
     * @param value1       the value 1
     */
    public SearchParam(String javaType, String property, String type, String value, String value1) {
        this.javaType = javaType;
        this.property = property;
        this.type = type;
        this.value = value;
        this.value1 = value1;
    }

    @Override
    public String toString() {
        return "CondSearch{" +
                "propertyType='" + javaType + '\'' +
                ", property='" + property + '\'' +
                ", type='" + type + '\'' +
                ", value1='" + value + '\'' +
                ", value2='" + value1 + '\'' +

                '}';
    }

}

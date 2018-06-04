package cn.zhimadi.txs.common.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 响应的数据格式
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Data
public class ResponseData implements Serializable {

    private static final long serialVersionUID = 7990154410762171509L;
    /** 响应代码 */
    private String code;

    /** 信息 */
    private String message;

    /** 数据 */
    private Map<String, Object> data;

    public ResponseData() {
    }

    public ResponseData(String code, String message, Map<String, Object> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseData ok(Map<String, Object> data){
        return new ResponseData("ok", "success", data);
    }

    public static ResponseData fail(String code, String message){
        return new ResponseData(code, message, null);
    }

}

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

    /** 操作是否成功 */
    private boolean success;

    /** 响应代码 */
    private String code;

    /** 信息 */
    private String message;

    /** 数据 */
    private Object data;

    public ResponseData() {
    }

    public ResponseData(boolean success, String code, String message,Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = success;
    }

    public static ResponseData ok(){
        return new ResponseData(true, "0000", "操作成功!", null);
    }

    public static ResponseData ok(Object data){
        return new ResponseData(true, "0000", "success", data);
    }

    public static ResponseData fail(String code, String message){
        return new ResponseData(false, code, message, null);
    }

}

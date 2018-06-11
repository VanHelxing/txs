package cn.zhimadi.txs.common.exception;

import lombok.Data;

/**
 * 自定义异常
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Data
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 5301233893782305510L;
    /**
     * 错误代码
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;


    /**
     * Custom exception.
     */
    public CustomException() {
        super();
    }

    /**
     * Custom exception.
     *
     * @param message the message
     */
    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Custom exception.
     *
     * @param code    the code
     * @param message the message
     */
    public CustomException(String code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * Custom exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Custom exception.
     *
     * @param code    the code
     * @param message the message
     * @param cause   the cause
     */
    public CustomException(String code, String message, Throwable cause){
        super(message, cause);
        this.code = code;
        this.message = message;
    }
}

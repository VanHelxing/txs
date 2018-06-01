package cn.zhimadi.txs.common.util.idgen;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * 生成唯一性ID的工具类.
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
public class IdUtils {

    private static SecureRandom secureRandom = new SecureRandom();
    private static IdWorker idWorker = new IdWorker(-1, -1);


    /**
     * UUID生成唯一ID, 去掉'-'字符间隔
     * @return
     */
    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * SecureRandom随机生成Long
     * @return
     */
    public static long randomLong(){
        return Math.abs(secureRandom.nextLong());
    }

    /**
     * twitter开源生成唯一Id (建议使用)
     * @return
     */
    public static String twitterId(){
        return String.valueOf(idWorker.nextId());
    }
}

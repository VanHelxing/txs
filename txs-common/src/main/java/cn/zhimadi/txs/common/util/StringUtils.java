package cn.zhimadi.txs.common.util;

/**
 * 字符串工具类
 *
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
public class StringUtils {


    /**
     * 字符串是否为空
     *
     * @param s the s
     * @return the boolean
     * @author : yangjunqing / 2018-05-21
     */
    public static boolean isEmpty(String s){
        if (s == null || "".equals(s.trim())){
            return true;
        }
        return false;
    }


    /**
     * 字符串是否不为空
     * @param s
     * @return
     */
    public static boolean isNotEmpty(String s){
        if (s != null && !("".equals(s.trim()))){
            return true;
        }
        return false;
    }


    /**
     * 是否包含字符串
     *
     * @param str  验证字符串
     * @param strs 包含的字符串组
     * @return 包含返回true boolean
     * @author : yangjunqing / 2018-05-21
     */
    public static boolean inStringIgnoreCase(String str, String... strs){
        if (str != null && strs != null){
            for (String s : strs){
                if (str.equalsIgnoreCase(s.trim())){
                    return true;
                }
            }
        }
        return false;
    }

}

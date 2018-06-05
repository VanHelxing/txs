package cn.zhimadi.txs.common.util;

import java.util.ArrayList;
import java.util.List;

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
     * <p>Checks if a CharSequence is whitespace, empty ("") or null.</p>
     * <p>
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is null, empty or whitespace
     * @author : mingweigao / 2017-04-30
     * @since 3.0 Changed signature from isBlank(String) to isBlank(CharSequence)
     */
    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Checks if a CharSequence is not empty (""), not null and not whitespace only.</p>
     * <p>
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is  not empty and not null and not whitespace
     * @author : mingweigao / 2017-04-30
     * @since 3.0 Changed signature from isNotBlank(String) to isNotBlank(CharSequence)
     */
    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
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


    /**
     * 分离
     * @param str
     * @param separatorChars
     * @return
     */
    public static String[] split(String str, String separatorChars) {
        return splitWorker(str, separatorChars, -1, false);
    }

    /**
     * 分离
     * @param str
     * @param separatorChars
     * @param max
     * @param preserveAllTokens
     * @return
     */
    private static String[] splitWorker(String str, String separatorChars, int max, boolean preserveAllTokens) {
        if (str == null) {
            return null;
        } else {
            int len = str.length();
            if (len == 0) {
                return null;
            } else {
                List<String> list = new ArrayList();
                int sizePlus1 = 1;
                int i = 0;
                int start = 0;
                boolean match = false;
                boolean lastMatch = false;
                if (separatorChars != null) {
                    if (separatorChars.length() != 1) {
                        label87:
                        while(true) {
                            while(true) {
                                if (i >= len) {
                                    break label87;
                                }

                                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                                    if (match || preserveAllTokens) {
                                        lastMatch = true;
                                        if (sizePlus1++ == max) {
                                            i = len;
                                            lastMatch = false;
                                        }

                                        list.add(str.substring(start, i));
                                        match = false;
                                    }

                                    ++i;
                                    start = i;
                                } else {
                                    lastMatch = false;
                                    match = true;
                                    ++i;
                                }
                            }
                        }
                    } else {
                        char sep = separatorChars.charAt(0);

                        label71:
                        while(true) {
                            while(true) {
                                if (i >= len) {
                                    break label71;
                                }

                                if (str.charAt(i) == sep) {
                                    if (match || preserveAllTokens) {
                                        lastMatch = true;
                                        if (sizePlus1++ == max) {
                                            i = len;
                                            lastMatch = false;
                                        }

                                        list.add(str.substring(start, i));
                                        match = false;
                                    }

                                    ++i;
                                    start = i;
                                } else {
                                    lastMatch = false;
                                    match = true;
                                    ++i;
                                }
                            }
                        }
                    }
                } else {
                    label103:
                    while(true) {
                        while(true) {
                            if (i >= len) {
                                break label103;
                            }

                            if (Character.isWhitespace(str.charAt(i))) {
                                if (match || preserveAllTokens) {
                                    lastMatch = true;
                                    if (sizePlus1++ == max) {
                                        i = len;
                                        lastMatch = false;
                                    }

                                    list.add(str.substring(start, i));
                                    match = false;
                                }

                                ++i;
                                start = i;
                            } else {
                                lastMatch = false;
                                match = true;
                                ++i;
                            }
                        }
                    }
                }

                if (match || preserveAllTokens && lastMatch) {
                    list.add(str.substring(start, i));
                }

                return (String[])list.toArray(new String[list.size()]);
            }
        }
    }

}

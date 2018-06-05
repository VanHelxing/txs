package cn.zhimadi.txs.common.web.controller;

import cn.zhimadi.txs.common.util.StringUtils;
import com.sun.beans.editors.StringEditor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 基础Controller (暂时使用联航的规范)
 *
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Controller
public class BaseController {

    private static Logger logger = LogManager.getLogger(BaseController.class);

    protected static String PARAM_DRAW = "draw";
    protected static String PARAM_RECORDS_TOTAL = "recordsTotal";
    protected static String PARAM_RECORDS_FILTERED = "recordsFiltered";
    protected static String PARAM_DATA = "data";

    protected static String PARAM_DTO = "dto";


    /**
     * 数据绑定
     *
     * @param binder the binder
     * @author : yangjunqing / 2018-06-05
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(String.class, "password", new StringEditor());
    }


    /**
     * 添加页面公共参数
     *
     * @param <T>   the type parameter
     * @param clazz the clazz
     * @param model the model
     * @author : mingweigao / 2017-04-22
     */
    protected <T> void addEntityParam(final Class<T> clazz, Model model) {
        String clazzName = clazz.getName();
        String packName = getPackName(clazzName);
        String[] classSimpleNames = splitClassSimpleName(clazz.getSimpleName());

        StringBuilder entityPath = new StringBuilder(packName);
        StringBuilder entitySecurity = new StringBuilder(packName);
        StringBuilder entityMessage = new StringBuilder(packName);

        for (String string : classSimpleNames) {
            entityPath.append("/").append(string.toLowerCase());
            entitySecurity.append(":").append(string.toLowerCase());
            entityMessage.append(".").append(string.toLowerCase());
        }
        //Bread crumb 列表界面标题
        model.addAttribute("moduleName", packName);
        //path封装
        model.addAttribute("entityPath", entityPath);
        //权限
        model.addAttribute("entitySecurity", entitySecurity);
        model.addAttribute("entityMessage", entityMessage);

        logger.error("entityPath = " + entityPath + " || entitySecurity = " + entitySecurity + " || entityMessage = " + entityMessage);
    }


    /**
     * 获取list页面路径
     *
     * @param clazz  the clazz
     * @param prefix the prefix
     * @return the string
     * @author : mingweigao / 2017-04-22
     */
    public static String getListPagePath(Class clazz, String prefix) {
        return getPagePath(clazz, prefix, "list");
    }

    /**
     * 获取页面路径
     *
     * @param clazz  the clazz
     * @param prefix the prefix
     * @param page   the page
     * @return the string
     * @author : mingweigao / 2017-04-22
     */
    public static String getPagePath(Class clazz, String prefix, String page) {
        String clazzName = clazz.getName();
        String packName = getPackName(clazzName);
        String[] classSimpleNames = splitClassSimpleName(clazz.getSimpleName());
        StringBuilder entityPath = new StringBuilder();
        if (StringUtils.isNotEmpty(prefix)) {
            entityPath.append(prefix).append("/");
        }
        entityPath.append(packName);
        for (String string : classSimpleNames) {
            entityPath.append("/").append(string.toLowerCase());
        }
        entityPath.append("/").append(page);
        logger.info("packName = " + packName + "    ||      entityPath = " + entityPath);
        return entityPath.toString();
    }

    /**
     * 根据实体获取包名
     *
     * @param clazzName the class name
     * @return the string
     * @author : mingweigao / 2017-04-22
     */
    public static String getPackName(String clazzName) {
        String[] packages = clazzName.split("\\.");
        String packName = packages[packages.length - 3];
        return packName;
    }

    /**
     * 把类名分割成数组
     *
     * @param classSimpleName the class simple name
     * @return the string [ ]
     * @author : mingweigao / 2017-04-22
     */
    public static String[] splitClassSimpleName(String classSimpleName) {
        StringBuffer sb = new StringBuffer();
        if (classSimpleName != null) {
            for (int i = 0; i < classSimpleName.length(); i++) {
                char c = classSimpleName.charAt(i);
                if (Character.isUpperCase(c)) {
                    if (i == 0) {
                        sb.append(c);
                    } else {
                        sb.append("-").append(c);//如果是大写字母则加入分割线
                    }
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString().split("-");
    }
}

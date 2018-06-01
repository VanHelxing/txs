package cn.zhimadi.txs.common.util.auto;

import cn.zhimadi.txs.common.util.StringUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 代码自动生成(仅使用与本项目)
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
public class AutoCreateCodeUtil {

    //工作区路径
    private final static String WORKSPACE_PATH = "D:/workspace/txs";

    //模板目录
    private final static String TEMPLATE_PATH = "D:/workspace/txs/txs-common/src/main/resources/freemarker";
    private final static String TEMPLATE_DAO_FILE = "DaoTemplate.ftl";
    private final static String TEMPLATE_SERVICE_FILE = "ServiceTemplate.ftl";
    private final static String TEMPLATE_SERVICE_IMPL_FILE = "ServiceImplTemplate.ftl";

    //基本Package和Path
    private final static String BASE_PATH = "D:/workspace/springbt/src/main/java";
    private final static String BASE_PACKAGE = "com.hx.springbt";

    //-----------------------------------------------------------------------------------------------
    /**
     * 需要调整的值
     */
    /** entity name */
    private final static String ENTITY_NAME = "DictDetail";
    /** 模块名称 **/
    private final static String MODULE_NAME = "system";
    //-----------------------------------------------------------------------------------------------


    private Configuration cfg;
    private Template template;
    private Map<String, String> dataModel;


    /**
     * 初始化数据
     * @throws IOException
     */
    public void init() throws IOException {
        cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
        dataModel = new HashMap<String, String>();

        //封装替换的值
        dataModel.put("entity_name", ENTITY_NAME);
        dataModel.put("entity_package", BASE_PACKAGE + "." + MODULE_NAME + "." + "entity");
        dataModel.put("dao_package", BASE_PACKAGE + "." + MODULE_NAME + "." + "dao");
        dataModel.put("service_package", BASE_PACKAGE + "." + MODULE_NAME + "." + "service");
        dataModel.put("service_impl_package", BASE_PACKAGE + "." + MODULE_NAME + "." + "service");
        dataModel.put("lower_entity_name", setFirstCharToLower(ENTITY_NAME));
    }

    /**
     * 生成Dao文件
     */
    public void createDaoFile() throws IOException, TemplateException {

        //Dao路径
        String filePath = BASE_PATH + "/" + BASE_PACKAGE.replaceAll("\\.", "/") + "/"
                + MODULE_NAME.replaceAll("\\.", "/") + "/dao/" + ENTITY_NAME + "Dao.java";
        System.out.println("Dao文件路径为: " + filePath);

        //文件
        File file = new File(filePath);
        if (!file.exists()){
            file.createNewFile();
        }

        template = cfg.getTemplate(TEMPLATE_DAO_FILE);
        FileWriter writer = new FileWriter(file);
        template.process(dataModel, writer);
        writer.close();
        System.out.println("Dao文件生成完成！");
    }


    /**
     * 生成Service文件
     * @throws IOException
     * @throws TemplateException
     */
    public void createServiceFile() throws IOException, TemplateException {

        //Service路径
        String filePath = BASE_PATH + "/" + BASE_PACKAGE.replaceAll("\\.", "/") + "/"
                + MODULE_NAME.replaceAll("\\.", "/") + "/service/" + ENTITY_NAME + "Service.java";
        System.out.println("Service文件路径为: " + filePath);

        //文件
        File file = new File(filePath);
        if (!file.exists()){
            file.createNewFile();
        }

        template = cfg.getTemplate(TEMPLATE_SERVICE_FILE);
        FileWriter writer = new FileWriter(file);
        template.process(dataModel, writer);
        writer.close();
        System.out.println("Service文件生成完成！");
    }


    /**
     * 生成ServiceImpl文件
     * @throws IOException
     * @throws TemplateException
     */
    public void createServiceImplFile() throws IOException, TemplateException {

        //ServiceImpl路径
        String filePath = BASE_PATH + "/" + BASE_PACKAGE.replaceAll("\\.", "/") + "/"
                + MODULE_NAME.replaceAll("\\.", "/") + "/service/" + ENTITY_NAME + "ServiceImpl.java";
        System.out.println("ServiceImpl文件路径为: " + filePath);

        //文件
        File file = new File(filePath);
        if (!file.exists()){
            file.createNewFile();
        }

        template = cfg.getTemplate(TEMPLATE_SERVICE_IMPL_FILE);
        FileWriter writer = new FileWriter(file);
        template.process(dataModel, writer);
        writer.close();
        System.out.println("ServiceImpl文件生成完成！");
    }

    /**
     * 字符串首字母小写
     * @param str
     * @return
     */
    private String setFirstCharToLower(String str){
        if (StringUtils.isEmpty(str)){
            return "";
        }
        if (Character.isLowerCase(str.charAt(0))){
            return str;
        }else {
            return (new StringBuilder()).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
        }
    }


    public static void main(String[] args) throws IOException, TemplateException {
        AutoCreateCodeUtil codeUtil = new AutoCreateCodeUtil();
        //初始化需要的数据
        codeUtil.init();

        //生成Dao文件
        codeUtil.createDaoFile();
        //生成Service文件
        codeUtil.createServiceFile();
        //生成ServiceImpl文件
        codeUtil.createServiceImplFile();
    }
}

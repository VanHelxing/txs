package cn.zhimadi.txs.common.util.auto;

import cn.zhimadi.txs.common.util.StringUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 代码自动生成(仅适用与本项目)
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
public class AutoCreateCodeUtil {

    private static Logger logger = LogManager.getLogger(AutoCreateCodeUtil.class);


    //工作区路径(请核对自己的工作区目录)
    private final static String WORKSPACE_PATH = "D:/workspace/txs";

    //模板目录
    private final static String TEMPLATE_PATH = WORKSPACE_PATH + "/txs-common/src/main/resources/freemarker";
    private final static String TEMPLATE_DAO_FILE = "DaoTemplate.ftl";
    private final static String TEMPLATE_SERVICE_FILE = "ServiceTemplate.ftl";
    private final static String TEMPLATE_SERVICE_IMPL_FILE = "ServiceImplTemplate.ftl";

    //基本Package和Path
    private final static String BASE_PATH = "src/main/java/cn/zhimadi/txs";
    private final static String BASE_PACKAGE = "cn.zhimadi.txs";


    /**
     * 需要调整的变量
     */
    /** anthor name */
    private final static String AUTHOR_NAME = "yangjunqing";

    /** module name (支持多层, 例如txs-system.txs-security)* */
    private final static String MODULE_NAME = "txs-system.txs-dict";

    /** package name (支持多层, 例如security.user) */
    private final static String PACKAGE_NAME = "dict";

    /** 实体类名称 */
    private final static String ENTITY_NAME = "DictDetail";
    /** 实体类描述 */
    private final static String ENTITY_DESCRIPTION = "字典明细";


    private Configuration cfg;
    private Template template;
    private Map<String, String> dataModel;


    //执行入口
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
        dataModel.put("lower_entity_name", setFirstCharToLower(ENTITY_NAME));
        dataModel.put("entity_description", ENTITY_DESCRIPTION);
        dataModel.put("author_name", AUTHOR_NAME);
        dataModel.put("entity_package", BASE_PACKAGE + "." + PACKAGE_NAME + "." + "entity");
        dataModel.put("dao_package", BASE_PACKAGE + "." + PACKAGE_NAME + "." + "dao");
        dataModel.put("service_package", BASE_PACKAGE + "." + PACKAGE_NAME + "." + "service");
        dataModel.put("service_impl_package", BASE_PACKAGE + "." + PACKAGE_NAME + "." + "service");
    }

    /**
     * 生成Dao文件
     */
    public void createDaoFile() throws IOException, TemplateException {

        //Dao路径
        String filePath = WORKSPACE_PATH + "/" +  MODULE_NAME.replaceAll("\\.", "/") + "/" + BASE_PATH + "/"
                + PACKAGE_NAME.replaceAll("\\.", "/") + "/dao/" + ENTITY_NAME + "Dao.java";

        logger.info("Dao文件路径为: " + filePath);

        //文件
        File file = new File(filePath);
        if (!file.exists()){
            file.createNewFile();
        }else {
            logger.error("Dao文件已经存在,不自动生成！");
            return;
        }

        template = cfg.getTemplate(TEMPLATE_DAO_FILE);
        FileWriter writer = new FileWriter(file);
        template.process(dataModel, writer);
        writer.close();
        logger.info("Dao文件生成完成！");
    }


    /**
     * 生成Service文件
     * @throws IOException
     * @throws TemplateException
     */
    public void createServiceFile() throws IOException, TemplateException {

        //Service路径
        String filePath = WORKSPACE_PATH + "/" +  MODULE_NAME.replaceAll("\\.", "/") + "/" + BASE_PATH + "/"
                + PACKAGE_NAME.replaceAll("\\.", "/") + "/service/" + ENTITY_NAME + "Service.java";

        logger.info("Service文件路径为: " + filePath);

        //文件
        File file = new File(filePath);
        if (!file.exists()){
            file.createNewFile();
        }else {
            logger.error("Service文件已经存在,不自动生成！");
            return;
        }

        template = cfg.getTemplate(TEMPLATE_SERVICE_FILE);
        FileWriter writer = new FileWriter(file);
        template.process(dataModel, writer);
        writer.close();
        logger.info("Service文件生成完成！");
    }


    /**
     * 生成ServiceImpl文件
     * @throws IOException
     * @throws TemplateException
     */
    public void createServiceImplFile() throws IOException, TemplateException {

        //ServiceImpl路径
        String filePath = WORKSPACE_PATH + "/" +  MODULE_NAME.replaceAll("\\.", "/") + "/" + BASE_PATH + "/"
                + PACKAGE_NAME.replaceAll("\\.", "/") + "/service/" + ENTITY_NAME + "ServiceImpl.java";

        logger.info("ServiceImpl文件路径为: " + filePath);

        //文件
        File file = new File(filePath);
        if (!file.exists()){
            file.createNewFile();
        }else {
            logger.error("ServiceImpl文件已经存在,不自动生成！");
            return;
        }

        template = cfg.getTemplate(TEMPLATE_SERVICE_IMPL_FILE);
        FileWriter writer = new FileWriter(file);
        template.process(dataModel, writer);
        writer.close();
        logger.info("ServiceImpl文件生成完成！");
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

}

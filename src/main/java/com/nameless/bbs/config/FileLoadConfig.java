package com.nameless.bbs.config;

import com.nameless.bbs.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @ClassName: FileLoadConfig
 * @Description: 在不借助其他服务器的情况下，解决spring boot打成jar包后，jar包外的文件访问方法
 * @author: Zhousheng
 * @date: 2018年3月19日 上午9:21:44
 */
@Configuration
public class FileLoadConfig implements WebMvcConfigurer {

    public static Logger logger = LoggerFactory.getLogger(FileLoadConfig.class);

    /**
     * 访问文件方法
     *
     * @return
     * @author 曾欣
     * @date 2019/3/25 16:49
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String filePath = "";
        String path = FileLoadConfig.class.getClassLoader().getResource("").getPath();
        if (path.indexOf(Constants.JAR_SUFFIX) > 0) {
            path = path.substring(0, path.indexOf(Constants.JAR_SUFFIX));
            logger.info("【FileConfig配置path】：" + path);
        } else if (path.indexOf(Constants.CLASS_SUFFIX) > 0) {
            path = "file:" + path.substring(0, path.indexOf(Constants.CLASS_SUFFIX));
            logger.info("【FileConfig配置path】：" + path);
        }
        path = path.substring(0, path.lastIndexOf(Constants.SEPARATOR)) + Constants.SEPARATOR + Constants.PREFIX + Constants.SEPARATOR;
        logger.info("【FileConfig配置path】：" + path);
        filePath = path;
        LoggerFactory.getLogger(FileLoadConfig.class).info("filePath=" + filePath);
        registry.addResourceHandler(Constants.SEPARATOR + Constants.PREFIX + Constants.SEPARATOR + Constants.SUFFIX).addResourceLocations(filePath);
    }

}

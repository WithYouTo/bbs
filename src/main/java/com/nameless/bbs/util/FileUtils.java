package com.nameless.bbs.util;

import com.nameless.bbs.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author LHR
 * Create By 2017/8/26
 */
public class FileUtils {

    public static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static String uploadFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "";
        }
        if (!file.isEmpty()) {
            ApplicationHome home = new ApplicationHome(FileUtils.class);
            File jarFile = home.getSource();
            String jarPath = jarFile.getParentFile().getAbsolutePath();
            logger.info("【jar包绝对路径：】" + jarFile);
            String suffix = "." + file.getContentType().split("/")[1];
            logger.info("【文件后缀名：】" + suffix);
            String storeFileName = UUID.randomUUID().toString().replace("-", "");
            logger.info("【保存文件名：】" + storeFileName);
            String storeFilePath = Constants.SEPARATOR + "file" + Constants.SEPARATOR + storeFileName + suffix;
            logger.info("【文件相对路径：】" + storeFilePath);
            File dest = new File(jarPath + storeFilePath);

            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);
            logger.info("【文件绝对路径：】" + Constants.HOST_IP_ADDRESS + storeFilePath);
            return Constants.HOST_IP_ADDRESS + storeFilePath;
        }
        return null;
    }
}

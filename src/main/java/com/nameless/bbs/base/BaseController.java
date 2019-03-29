package com.nameless.bbs.base;

import com.nameless.bbs.dto.RespResult;
import com.nameless.bbs.exception.ServiceProcessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author LHR
 * Create By 2017/8/13
 */
public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected RespResult restProcessor(ResultProcessor processor) {
        RespResult result = null;
        try {
            result = processor.process();
        } catch (ServiceProcessException e1) {
            logger.error("ServiceProcess Error Log :" + e1.getLocalizedMessage(), e1);
            result = RespResult.error(e1.getMessage());
        } catch (Exception e) {
            logger.error("Error Log :" + e.getLocalizedMessage(), e);
            result = RespResult.error("服务器出现异常");
        }

        return result;
    }
}

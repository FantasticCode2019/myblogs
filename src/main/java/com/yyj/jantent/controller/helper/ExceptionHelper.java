package com.yyj.jantent.controller.helper;

import org.slf4j.Logger;
import com.yyj.jantent.exception.TipException;
import com.yyj.jantent.modal.bo.RestResponseBo;

/**
 * @author janti
 */
public class ExceptionHelper {
    /**
     * 统一处理异常
     *
     * @param logger
     * @param msg
     * @param e
     * @return
     */
    public static RestResponseBo handlerException(Logger logger, String msg, Exception e) {
        if (e instanceof TipException) {
            msg = e.getMessage();
        } else {
            logger.error(msg, e);
        }
        return RestResponseBo.fail(msg);
    }
}

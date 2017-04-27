package cn.sxt.exception.service;

import cn.sxt.exception.UserSystemException;

/**
 * Created by beichunming on 2017/4/24.
 */
public class ServiceException extends UserSystemException{
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}

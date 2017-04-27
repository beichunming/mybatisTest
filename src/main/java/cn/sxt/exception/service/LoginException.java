package cn.sxt.exception.service;

/**
 * Created by beichunming on 2017/4/24.
 */
public class LoginException extends ServiceException{
    public LoginException() {
        super();
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginException(Throwable cause) {
        super(cause);
    }
}

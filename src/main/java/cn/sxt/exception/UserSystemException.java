package cn.sxt.exception;

/**
 * Created by beichunming on 2017/4/24.
 */
public class UserSystemException extends Exception {

    public UserSystemException() {
        super();
    }

    public UserSystemException(String message) {
        super(message);
    }

    public UserSystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserSystemException(Throwable cause) {
        super(cause);
    }
}

package application.core.api.exception;

/**
 * Created by Hubert on 2017-05-28.
 */
public class ClientNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public ClientNotFoundException() {
    }

    public ClientNotFoundException(String arg0) {
        super(arg0);
    }

    public ClientNotFoundException(Throwable arg0) {
        super(arg0);
    }

    public ClientNotFoundException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public ClientNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

}

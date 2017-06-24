package application.core.api.exception;

public class ReplacementGroupNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public ReplacementGroupNotFoundException() {
    }

    public ReplacementGroupNotFoundException(String arg0) {
        super(arg0);
    }

    public ReplacementGroupNotFoundException(Throwable arg0) {
        super(arg0);
    }

    public ReplacementGroupNotFoundException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public ReplacementGroupNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }
}

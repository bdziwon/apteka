package application.core.api.exception;

public class EmployeeNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public EmployeeNotFoundException() {
    }

    public EmployeeNotFoundException(String arg0) {
        super(arg0);
    }

    public EmployeeNotFoundException(Throwable arg0) {
        super(arg0);
    }

    public EmployeeNotFoundException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public EmployeeNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

}

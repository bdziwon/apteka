package application.core.api.exception;

public class MedicineOrderNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public MedicineOrderNotFoundException() {
    }

    public MedicineOrderNotFoundException(String arg0) {
        super(arg0);
    }

    public MedicineOrderNotFoundException(Throwable arg0) {
        super(arg0);
    }

    public MedicineOrderNotFoundException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public MedicineOrderNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

}
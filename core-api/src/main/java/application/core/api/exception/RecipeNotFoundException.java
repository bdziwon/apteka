package application.core.api.exception;

/**
 * Created by Hubert on 2017-05-28.
 */
public class RecipeNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public RecipeNotFoundException() {
    }

    public RecipeNotFoundException(String arg0) {
        super(arg0);
    }

    public RecipeNotFoundException(Throwable arg0) {
        super(arg0);
    }

    public RecipeNotFoundException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public RecipeNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }
}

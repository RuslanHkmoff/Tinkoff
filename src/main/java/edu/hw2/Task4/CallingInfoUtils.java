package edu.hw2.Task4;

public class CallingInfoUtils {
    private static final int INDEX = 1;

    private CallingInfoUtils() {
    }

    public static CallingInfo callingInfo() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        if (stackTrace.length < INDEX + 1) {
            return new CallingInfo("", "");
        }
        String className = stackTrace[INDEX].getClassName();
        String methodName = stackTrace[INDEX].getMethodName();
        return new CallingInfo(className, methodName);
    }
}

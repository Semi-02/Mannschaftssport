package de.hsos.swa.mannschaftssport.logging;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@Interceptor
@Priority(2)
@Log(Log.Level.INFO)
public class LogInterceptorInfo {

    @Inject
    Logger logger;

    @AroundInvoke
    public Object aroundInvoke(InvocationContext context) throws Exception {
        // Vor der Methodenausführung auf INFO-Level loggen
        logger.log(Level.INFO, "Entering method (INFO): " + context.getMethod().getName() +
                " with parameters: " + Arrays.toString(context.getParameters()));

        Object result = context.proceed();

        // Nach der Methodenausführung das Ergebnis auf INFO-Level loggen
        logger.log(Level.INFO, "Exiting method (INFO): " + context.getMethod().getName() +
                " with result: " + result);

        return result;
    }
}

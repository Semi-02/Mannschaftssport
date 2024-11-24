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
@Priority(1)
@Log(Log.Level.TRACE)
public class LogInterceptorTrace {

    @Inject
    Logger logger;

    @AroundInvoke
    public Object aroundInvoke(InvocationContext context) throws Exception {
        // Vor der Methodenausführung auf TRACE-Level loggen
        logger.log(Level.FINEST, "Entering method (TRACE): " + context.getMethod().getName() +
                " with parameters: " + Arrays.toString(context.getParameters()));

        Object result = context.proceed();

        // Nach der Methodenausführung das Ergebnis auf TRACE-Level loggen
        logger.log(Level.FINEST, "Exiting method (TRACE): " + context.getMethod().getName() +
                " with result: " + result);

        return result;
    }
}
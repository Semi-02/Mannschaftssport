package de.hsos.swa.mannschaftssport.logging;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;


import java.util.logging.Logger;

@ApplicationScoped
public class LoggerConfiguration {

    @Produces
    Logger getLogger() {
        return Logger.getLogger(LoggerConfiguration.class.getName());
    }
}

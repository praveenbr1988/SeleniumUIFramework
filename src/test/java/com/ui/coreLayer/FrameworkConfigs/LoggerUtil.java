package com.ui.coreLayer.FrameworkConfigs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {
    public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }



}

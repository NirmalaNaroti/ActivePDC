package com.fragma.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "conf")
public class ConfigurationHelper {

    private static String query;
    private static String lrgquery;
    private static String excelLocation;

    public static String getQuery() {
        return query;
    }

    public static void setQuery(String query) {
        ConfigurationHelper.query = query;
    }

    public static String getLrgquery() {
        return lrgquery;
    }

    public static void setLrgquery(String lrgquery) {
        ConfigurationHelper.lrgquery = lrgquery;
    }

    public static String getExcelLocation() {
        return excelLocation;
    }

    public static void setExcelLocation(String excelLocation) {
        ConfigurationHelper.excelLocation = excelLocation;
    }
}

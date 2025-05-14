package com.x.webAutomation.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SetUpTest {

    public static String strUrlVal;

    public String getURL(String env) throws IOException {
        Properties envProps = new Properties();
        if (env.equalsIgnoreCase("PROD")) {
            envProps.load(new FileInputStream(
                    System.getProperty("user.dir") + "/src/main/resources/ENV.properties"));
            strUrlVal = envProps.getProperty("PROD");
        }
        else if (env.equalsIgnoreCase("QA")) {
            envProps.load(new FileInputStream(
                    System.getProperty("user.dir") + "/src/main/resources/ENV.properties"));
            strUrlVal = envProps.getProperty("QA");
        }
        else if (env.equalsIgnoreCase("UAT")) {
            envProps.load(new FileInputStream(
                    System.getProperty("user.dir") + "/src/main/resources/utils.properties"));
            strUrlVal = envProps.getProperty("UAT");
        }
        else {
            strUrlVal = "https://the-internet.herokuapp.com/";
        }
        return strUrlVal;
    }
}

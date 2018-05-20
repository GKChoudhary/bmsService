package com.rank.ccms.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.support.ManagedProperties;
import org.springframework.stereotype.Service;

@Service("vidyoConstants")
public class Constants {

    public static String dockerUrl;
    public static String socketHostPublic;
    public static String vidyoIOKey;
    public static String vidyoIOAppID;
    public static String vidyoIOUserName;

    private static final Logger logger = Logger.getLogger(Constants.class);

    public Constants() {
        addUrlConstants();
    }

    private void addUrlConstants() {
        String projectHome = System.getenv("FUTURE_GENERLI");   
        if (null == projectHome) {

            dockerUrl = "";
            socketHostPublic = "";
            vidyoIOKey = "";
            vidyoIOAppID = "";
            vidyoIOUserName = "";

           
        } else {
            String vidyoPortalProps = projectHome + File.separator + "configuration" + File.separator + "vidyoConstants.properties";

            File vidyoPropsFile = new File(vidyoPortalProps);
            if (vidyoPropsFile.exists()) {
                Properties properties = new ManagedProperties();
                try {
                    properties.load(new FileInputStream(vidyoPropsFile));
                } catch (IOException e) {
                    logger.error("Error:vidyoPropsFile" + e.getMessage());
                }

                if (properties.size() > 0) {

                    dockerUrl = properties.getProperty("dockerUrl");
                    socketHostPublic = properties.getProperty("socketHostPublic");
                    vidyoIOKey = properties.getProperty("vidyoIOKey");
                    vidyoIOAppID = properties.getProperty("vidyoIOAppID");
                    vidyoIOUserName = properties.getProperty("vidyoIOUserName");

                }
            } else {
                
                dockerUrl = "https://192.168.1.128";
                socketHostPublic = "";
                vidyoIOKey = "";
                vidyoIOAppID = "";
                vidyoIOUserName = "";
            }
        }
    }

}

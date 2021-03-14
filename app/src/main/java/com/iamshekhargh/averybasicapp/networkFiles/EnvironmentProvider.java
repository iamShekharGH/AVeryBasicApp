package com.iamshekhargh.averybasicapp.networkFiles;

/**
 * Created by <<-- iamShekharGH -->>
 * on 12 March 2021
 * at 7:40 PM.
 */
public class EnvironmentProvider {
    public static boolean inProduction = false;

    public static String getUrl() {
        if (inProduction) {
            //This becomes ProductionEnvironment
            return new StagingEnvironment().getUrl();
        } else {
            return new StagingEnvironment().getUrl();
        }
    }
}


package cn.droidlover.txzn.sys01.net;

import cn.droidlover.txzn.net.XApi;
import cn.droidlover.txzn.sys01.net.elec.ElecAlarmDataService;

/**
 * Created by wanglei on 2016/12/31.
 */

public class Api {
    public static String HTTP = "http://";
    public static String IP = "192.168.1.6:8080";//192.168.1.7:8080 116.247.116.42:8090
    public static String PROJECT = "/2018008/";//zxjc 2018008
    public static String API_ROOT = HTTP + IP + "/";
    public static String API_USER = HTTP + IP + PROJECT;

    private static ElecAlarmDataService elecAlarmDataService;


    private static SysService sysService;


    public static void initURL() {
        API_ROOT = HTTP + IP + "/";
        Api.API_USER = Api.HTTP + Api.IP + Api.PROJECT;
        sysService = null;
    }

    public static SysService getSysService() {
        if (sysService == null) {
            synchronized (Api.class) {
                if (sysService == null) {
                    sysService = XApi.getInstance().getRetrofit(API_USER, true).create(SysService.class);
                }
            }
        }
        return sysService;
    }


    public static ElecAlarmDataService getElecAlarmDataService() {
        if (elecAlarmDataService == null) {
            synchronized (Api.class) {
                if (elecAlarmDataService == null) {
                    elecAlarmDataService = XApi.getInstance().getRetrofit(API_USER, true).create(ElecAlarmDataService.class);
                }
            }
        }
        return elecAlarmDataService;
    }


}

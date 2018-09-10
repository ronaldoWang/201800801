package cn.droidlover.txzn.sys01.event;

import cn.droidlover.txzn.event.IBus;

/**
 * Created by ThinkPad on 2018/8/23.
 */

public class MyEvent implements IBus.IEvent {

    public static final String REFRESH_STATUS = "REFRESH_STATUS";//刷新状态
    public static final String REFRESH_CONTENT = "REFRESH_CONTENT";//刷新状态
    String title = "";
    private String tag;

    public MyEvent(String tag, String title) {
        this.title = title;
        this.tag = tag;
    }

    @Override
    public String getTag() {
        return this.tag;
    }

    public String getTitle() {
        return title;
    }
}

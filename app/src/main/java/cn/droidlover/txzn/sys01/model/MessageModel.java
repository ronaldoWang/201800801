package cn.droidlover.txzn.sys01.model;

import cn.droidlover.txzn.net.IModel;

/**
 * Created by ThinkPad on 2017/10/21.
 */

public class MessageModel extends BaseModel<String> implements IModel {
    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isAuthError() {
        return false;
    }

    @Override
    public boolean isBizError() {
        return false;
    }

    @Override
    public String getErrorMsg() {
        return null;
    }
}

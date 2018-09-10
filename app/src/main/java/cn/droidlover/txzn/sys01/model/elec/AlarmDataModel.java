package cn.droidlover.txzn.sys01.model.elec;

import java.io.Serializable;
import java.util.List;

import cn.droidlover.txzn.net.IModel;
import cn.droidlover.txzn.sys01.model.BaseModel;

/**
 * Created by ThinkPad on 2018/8/16.
 */

public class AlarmDataModel extends BaseModel<List<AlarmDataModel.AlarmData>> implements IModel {


    public static class AlarmData implements Serializable {
        private Integer id;
        private Integer dasid; // 下位机ID
        private String dasname; // 下位机名称
        private String channelid; // 通道ID
        private String channelname; // 通道名称
        private String evid; // 事件在下位机的编号
        private String mood; // 2时间开始、持续、完成
        private String defid; // 物理防区标号
        private String defname; // 物理防区名称
        private Integer cat; // 事件分类编号 , 报警类型
        private Integer divno; // 事件分类特征编号
        private Integer evlevel; // 报警级别
        private String evTime;// 发生时间：年月日时分秒
        private String evTimems;// 发生时间：毫秒
        private String lastTime;// 事件持续时间
        private String evPos;// 事件发生位置
        private String evFrom;// 起始位置
        private String evTo;// 结束位置
        private String ave;// 震动平均强度
        private Double longitude; // 经度
        private Double latitude; // 纬度

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getDasid() {
            return dasid;
        }

        public void setDasid(Integer dasid) {
            this.dasid = dasid;
        }

        public String getDasname() {
            return dasname;
        }

        public void setDasname(String dasname) {
            this.dasname = dasname;
        }

        public String getChannelid() {
            return channelid;
        }

        public void setChannelid(String channelid) {
            this.channelid = channelid;
        }

        public String getChannelname() {
            return channelname;
        }

        public void setChannelname(String channelname) {
            this.channelname = channelname;
        }

        public String getEvid() {
            return evid;
        }

        public void setEvid(String evid) {
            this.evid = evid;
        }

        public String getMood() {
            return mood;
        }

        public void setMood(String mood) {
            this.mood = mood;
        }

        public String getDefid() {
            return defid;
        }

        public void setDefid(String defid) {
            this.defid = defid;
        }

        public String getDefname() {
            return defname;
        }

        public void setDefname(String defname) {
            this.defname = defname;
        }

        public Integer getCat() {
            return cat;
        }

        public void setCat(Integer cat) {
            this.cat = cat;
        }

        public Integer getDivno() {
            return divno;
        }

        public void setDivno(Integer divno) {
            this.divno = divno;
        }

        public Integer getEvlevel() {
            return evlevel;
        }

        public void setEvlevel(Integer evlevel) {
            this.evlevel = evlevel;
        }

        public String getEvTime() {
            return evTime;
        }

        public void setEvTime(String evTime) {
            this.evTime = evTime;
        }

        public String getEvTimems() {
            return evTimems;
        }

        public void setEvTimems(String evTimems) {
            this.evTimems = evTimems;
        }

        public String getLastTime() {
            return lastTime;
        }

        public void setLastTime(String lastTime) {
            this.lastTime = lastTime;
        }

        public String getEvPos() {
            return evPos;
        }

        public void setEvPos(String evPos) {
            this.evPos = evPos;
        }

        public String getEvFrom() {
            return evFrom;
        }

        public void setEvFrom(String evFrom) {
            this.evFrom = evFrom;
        }

        public String getEvTo() {
            return evTo;
        }

        public void setEvTo(String evTo) {
            this.evTo = evTo;
        }

        public String getAve() {
            return ave;
        }

        public void setAve(String ave) {
            this.ave = ave;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }
    }

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

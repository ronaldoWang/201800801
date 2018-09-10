package cn.droidlover.txzn.sys01.model.elec.vo;

/**
 * Created by ThinkPad on 2018/8/11.
 */

public class LatLngVo {
    public double latitude;
    public double longitude;
    public double distance;// 距离上一个点的距离

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

package cn.droidlover.txzn.sys01.net.elec;

import java.util.Map;

import cn.droidlover.txzn.sys01.model.elec.AlarmDataModel;
import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by ThinkPad on 2018/8/16.
 */

public interface ElecAlarmDataService {

    @FormUrlEncoded
    @POST("api/alarmdata/1/appUploadAlarm")
    Flowable<AlarmDataModel> appUploadAlarm(@Field("data") String data);
}

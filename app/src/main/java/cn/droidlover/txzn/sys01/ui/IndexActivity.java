package cn.droidlover.txzn.sys01.ui;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import butterknife.BindView;
import cn.droidlover.txzn.event.BusProvider;
import cn.droidlover.txzn.mvp.XActivity;
import cn.droidlover.txzn.net.ApiSubscriber;
import cn.droidlover.txzn.net.NetError;
import cn.droidlover.txzn.net.XApi;
import cn.droidlover.txzn.sys01.R;
import cn.droidlover.txzn.sys01.event.MyEvent;
import cn.droidlover.txzn.sys01.model.elec.AlarmDataModel;
import cn.droidlover.txzn.sys01.net.Api;
import cn.droidlover.txzn.sys01.widget.LoadingDialog;
import io.reactivex.functions.Consumer;

public class IndexActivity extends XActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.link_status)
    TextView link_status;

    @BindView(R.id.link_content)
    TextView link_content;

    private String content = "";

    private String status = "";

    private Handler handler = null;


    @Override
    public void initData(Bundle savedInstanceState) {
        handler = new Handler();
        new SocketServerThread().start();
    }

    Runnable runnableUiStatus = new Runnable() {
        @Override
        public void run() {
            link_status.setText("客户端已接入");
        }

    };

    Runnable runnableUiContent = new Runnable() {
        @Override
        public void run() {
            link_content.setText(link_content.getText().toString() + content);
        }

    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_index;
    }

    @Override
    public Object newP() {
        return null;
    }

    public class SocketServerThread extends Thread {

        private BufferedOutputStream out;
        private Socket client;

        @Override
        public void run() {
            try {
                Log.e("wsy", "等待连接");
                ServerSocket serverSocket = new ServerSocket(10010);
                while (true) {
                    client = serverSocket.accept();
                    out = new BufferedOutputStream(client.getOutputStream());
                    // 开启子线程去读去数据
                    new Thread(new SocketReadThread(new BufferedInputStream(client.getInputStream()))).start();
                    handler.post(runnableUiStatus);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        class SocketReadThread implements Runnable {

            private BufferedInputStream in;

            public SocketReadThread(BufferedInputStream inStream) throws IOException {
                this.in = inStream;
            }

            public void run() {
                try {
                    while (true) {
                        try {
                            if (!client.isConnected()) {
                                break;
                            }
                            String jsonData = readMsgFromSocket(in);
                            if (StringUtils.isEmpty(jsonData)) {
                                break;
                            } else {
                                content = TimeUtils.date2String(new Date()) + "接收报警数据：" + jsonData + "\n";
                                handler.post(runnableUiContent);
                                Api.getElecAlarmDataService().appUploadAlarm(jsonData)
                                        .compose(XApi.<AlarmDataModel>getApiTransformer())
                                        .compose(XApi.<AlarmDataModel>getScheduler())
                                        .compose(IndexActivity.this.<AlarmDataModel>bindToLifecycle())
                                        .subscribe(new ApiSubscriber<AlarmDataModel>() {
                                            @Override
                                            protected void onFail(NetError error) {
                                                LoadingDialog.cancelDialogForLoading();
                                                link_content.setText(link_content.getText().toString() + TimeUtils.date2String(new Date()) + "发送失败\n");
                                            }

                                            @Override
                                            public void onNext(AlarmDataModel alarmData) {
                                                LoadingDialog.cancelDialogForLoading();
                                                if (alarmData.isSuccess()) {
                                                    link_content.setText(link_content.getText().toString() + TimeUtils.date2String(new Date()) + "发送成功\n");
                                                } else {
                                                    link_content.setText(link_content.getText().toString() + TimeUtils.date2String(new Date()) + "发送失败\n");
                                                }
                                            }

                                            @Override
                                            public void onComplete() {
                                                LoadingDialog.cancelDialogForLoading();
                                            }
                                        });
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //读取PC端发送过来的数据
            private String readMsgFromSocket(InputStream in) {
                String msg = "";
                byte[] tempbuffer = new byte[1024];
                try {
                    int numReadedBytes = in.read(tempbuffer, 0, tempbuffer.length);
                    msg = new String(tempbuffer, 0, numReadedBytes, "utf-8");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return msg;
            }
        }
    }

}

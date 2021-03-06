package cn.droidlover.txzn.sys01.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.txzn.mvp.XActivity;
import cn.droidlover.txzn.router.Router;
import cn.droidlover.txzn.sys01.R;
import cn.droidlover.txzn.sys01.ui.style.ButtonBootstrapStyle;

public class PwdUpdateActivity extends XActivity {
    @BindView(R.id.et_old_pwd)
    BootstrapEditText et_old_pwd;
    @BindView(R.id.et_new_pwd)
    BootstrapEditText et_new_pwd;
    @BindView(R.id.et_confirm_new_pwd)
    BootstrapEditText et_confirm_new_pwd;
    @BindView(R.id.button_save_upload)
    BootstrapButton button_save_upload;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void initData(Bundle savedInstanceState) {
        toolbar.setTitle("修改密码");
        setSupportActionBar(toolbar);
        button_save_upload.setBootstrapBrand(new ButtonBootstrapStyle(this));
    }

    @OnClick({R.id.button_save_upload})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.button_save_upload://上传
                doUpdatePwd();
                break;
        }
    }

    private void doUpdatePwd() {
        String oldPwd = et_old_pwd.getText().toString();
        String newPwd = et_new_pwd.getText().toString();
        String confirmPwd = et_confirm_new_pwd.getText().toString();
        if (StringUtils.isTrimEmpty(oldPwd)) {
            ToastUtils.showShort("原密码不能为空");
            return;
        }
        if (StringUtils.isTrimEmpty(newPwd)) {
            ToastUtils.showShort("新密码不能为空");
            return;
        }
        if (StringUtils.isTrimEmpty(confirmPwd)) {
            ToastUtils.showShort("确认新密码不能为空");
            return;
        }
        if (!newPwd.equals(confirmPwd)) {
            ToastUtils.showShort("新密码两次输入不一致");
            return;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Router.pop(context);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_pwd_update;
    }

    @Override
    public Object newP() {
        return null;
    }
}

package cn.droidlover.txzn.sys01.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import cn.droidlover.txzn.mvp.XActivity;
import cn.droidlover.txzn.router.Router;
import cn.droidlover.txzn.sys01.R;
import cn.droidlover.txzn.sys01.widget.StatusBarCompat;

/**
 * des:启动页
 * Created by xsf
 * on 2016.09.15:16
 */
public class SplashActivity extends XActivity {
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.tv_name)
    TextView tvName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView(Bundle bundle) {
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        StatusBarCompat.translucentStatusBar(this);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0.3f, 1f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.3f, 1f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.3f, 1f);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(tvName, alpha, scaleX, scaleY);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofPropertyValuesHolder(ivLogo, alpha, scaleX, scaleY);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator1, objectAnimator2);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.setDuration(2000);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Router.newIntent(context)        //context表示当前上下文
                        .to(IndexActivity.class)    //to()指定目标context
                        .launch();
                context.finish();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animatorSet.start();
    }

    @Override
    public int getLayoutId() {
        return R.layout.splash;
    }

    @Override
    public Object newP() {
        return null;
    }
}

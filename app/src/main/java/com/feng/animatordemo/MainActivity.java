package com.feng.animatordemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView mPicOneImage;
    private Button mAlphaButton;
    private Button mSetButton;
    private Button mCountButton;
    private TextView mCountTextView;
    private Button mWidthButton;
    private Button mTestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mPicOneImage = findViewById(R.id.iv_main_pic_one);

        mAlphaButton = findViewById(R.id.btn_main_alpha);
        mAlphaButton.setOnClickListener(this);

        mSetButton = findViewById(R.id.btn_main_set);
        mSetButton.setOnClickListener(this);

        mCountButton = findViewById(R.id.btn_main_connt);
        mCountButton.setOnClickListener(this);

        mCountTextView = findViewById(R.id.tv_main_count_content);

        mWidthButton = findViewById(R.id.btn_main_width);
        mWidthButton.setOnClickListener(this);

        mTestButton = findViewById(R.id.btn_main_test);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_alpha:
                //ObjectAnimator的典型使用：改变控件（对象）的透明度（从不透明到透明又变回不透明）
                ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mPicOneImage,
                        "alpha", 1f, 0f, 1f);
                alphaAnimator.setDuration(3000);
                alphaAnimator.setRepeatCount(2);    //重复两次，实际上是执行三次
                //设置动画重复次数，ValueAnimator.INFINITE为无限重复
                alphaAnimator.start();
                break;

            case R.id.btn_main_set:
                AnimatorSet animatorSet = new AnimatorSet();
                ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(mPicOneImage,
                        "scaleX", 1f, 2f, 1f);
                ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(mPicOneImage,
                        "scaleY", 1f, 2f, 1f);
                ObjectAnimator translationXAnimator = ObjectAnimator.ofFloat(mPicOneImage,
                        "translationX", 0f, 300f, 0f);
                ObjectAnimator translationYAnimator = ObjectAnimator.ofFloat(mPicOneImage,
                        "translationY", 0f, -300f, 0f);
                //同时沿x、y轴缩放，该操作在沿Y轴向下之后，在沿X轴向右之前
                animatorSet.play(scaleXAnimator).with(scaleYAnimator).before(translationXAnimator)
                        .after(translationYAnimator);
                //都设置3s
                animatorSet.setDuration(3000);
                animatorSet.start();
                break;

            case R.id.btn_main_connt:
                ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100);
                valueAnimator.setDuration(10*1000);
                valueAnimator.setInterpolator(new LinearInterpolator());    //匀速播放动画
                //添加动画监听，手动改变对象的属性值（属性值发生改变就会调用该方法）
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int currentCount = (int) animation.getAnimatedValue();
                        //获取当前的值
                        mCountTextView.setText(String.valueOf(currentCount));
                        //更新文本
                    }
                });
                valueAnimator.start();  //开始动画
                break;

            case R.id.btn_main_width:
                ViewWrapper wrapper = new ViewWrapper(mTestButton);     //对button进行包装
                int buttonWidth = mTestButton.getMeasuredWidth();   //获取button宽度
                ObjectAnimator objectAnimator = ObjectAnimator.ofInt(wrapper,
                        "width", buttonWidth, 500, buttonWidth);
                //改变button的宽度（先变为500，后恢复）
                objectAnimator.setDuration(3000).start();
                break;

            default:
                break;
        }
    }
}

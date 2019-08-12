package com.play.library_base.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentActivity;

import com.play.library_base.R;
import com.play.library_base.utils.ResUtil;

/**
 * Created by dongxl on 2018/1/3.
 */

public class TitleBarWhite extends RelativeLayout {
    private Context mContext;

    private AppCompatTextView mTitleTV;
    private ProgressBar mTitleBarPB;
    private AppCompatImageView mFilterIV;

    private AppCompatImageView mTitleMiddleIV;

    private AppCompatImageView mTitleLeftIV;
    private AppCompatTextView mTitleLeftTV;

    private RelativeLayout mRightContent;
    private FrameLayout mTitleRightFl;
    private AppCompatTextView mTitleRightTV;
    private AppCompatImageView mTitleRightIV;
    private AppCompatImageView mTitleRight2IV;
    private AppCompatImageView mTitleRight3IV;
    private FrameLayout mTitleRight4IV;

    private LinearLayout mMiddleTitle;
    private AppCompatTextView mMiddleTitleTV1;
    private AppCompatTextView mMiddleTitleTV2;


    private View mTitleBottomLine;

    private int titleBarHeight;


    public TitleBarWhite(Context context) {
        this(context, null);
    }

    public TitleBarWhite(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBarWhite(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TitleBarWhite(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    @SuppressLint("ResourceType")
    private void init(Context context) {
        mContext = context;
        titleBarHeight = ResUtil.getDimens(mContext, 60);
        setTitleBarBackground(ResUtil.getColor(R.color.colorPrimary));
        LayoutInflater.from(mContext).inflate(R.layout.common_title_bar_white, this, true);
        ViewGroup.LayoutParams lp = this.getLayoutParams();
        if (null == lp) {
            lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, titleBarHeight);
        } else {
            lp.height = titleBarHeight;
        }
        this.setLayoutParams(lp);
        mTitleTV = (AppCompatTextView) findViewById(R.id.title_bar_title_TV);
        mTitleBarPB = (ProgressBar) findViewById(R.id.title_bar_PB);
        mFilterIV = (AppCompatImageView) findViewById(R.id.title_bar_filter_IV);

        mTitleMiddleIV = (AppCompatImageView) findViewById(R.id.title_bar_title_middle_IV);

        mTitleLeftIV = (AppCompatImageView) findViewById(R.id.title_bar_title_left_IV);
        mTitleLeftTV = (AppCompatTextView) findViewById(R.id.title_bar_title_left_TV);

        mRightContent = (RelativeLayout) findViewById(R.id.title_bar_right_content);

        mTitleRightFl = (FrameLayout) findViewById(R.id.title_bar_title_right_FL);
        mTitleRightTV = (AppCompatTextView) findViewById(R.id.title_bar_title_right_TV);

        mTitleRightIV = (AppCompatImageView) findViewById(R.id.title_bar_title_right_IV);
        mTitleRight2IV = (AppCompatImageView) findViewById(R.id.title_bar_title_right2_IV);
        mTitleRight3IV = (AppCompatImageView) findViewById(R.id.title_bar_title_right3_IV);
        mTitleRight4IV = findViewById(R.id.title_bar_title_right4_V);

        mTitleBottomLine = findViewById(R.id.title_bar_bottom_line);

        mMiddleTitle = findViewById(R.id.ll_title);
        mMiddleTitleTV1 = findViewById(R.id.title_bar_title_TV1);
        mMiddleTitleTV2 = findViewById(R.id.title_bar_title_TV2);
    }

    public void setTitleBarBackground(int color) {
        this.setBackgroundColor(color);
    }

    public void setTitleBarBackground(Drawable background) {
        this.setBackground(background);
    }

    public void setTitle(String title) {
        if (mTitleTV.getVisibility() != VISIBLE) {
            mTitleTV.setVisibility(VISIBLE);
        }
        mTitleTV.setText(title);
    }

    public void noTitleBar() {
        mOnLeftClick = null;
        setVisibility(View.GONE);
    }

    public AppCompatTextView getmTitleTV() {
        return mTitleTV;
    }

    public ProgressBar getmTitleBarPB() {
        return mTitleBarPB;
    }


    public void setTitleColor(int color) {
        if (mTitleTV.getVisibility() != VISIBLE) {
            mTitleTV.setVisibility(VISIBLE);
        }
        mTitleTV.setTextColor(color);
    }

    public void setLineVisibility(int visibility) {
        mTitleBottomLine.setVisibility(visibility);
    }

    public void setTitleLeftImageVisibility(int visible) {
        mTitleLeftIV.setVisibility(visible);
    }

    public void setLeftImage(Drawable d) {
        mTitleLeftIV.setImageDrawable(d);
        mTitleLeftIV.setVisibility(VISIBLE);
    }

    public void setLeftImageVisiable(boolean visiable) {
        mTitleLeftIV.setVisibility(visiable ? VISIBLE : GONE);
    }

    public void setRightTextVisibility(int visible) {
        mRightContent.setVisibility(visible);
        mTitleRightTV.setVisibility(visible);
    }

    /**
     * 设置左侧文字
     *
     * @param text
     */
    public void setLeftText(String text) {
        mTitleLeftTV.setVisibility(VISIBLE);
        mTitleLeftTV.setText(text);
    }

    public AppCompatTextView getLeftText() {
        return mTitleLeftTV;
    }

    //设置右侧文本
    public void setRightText(String text) {
        setRightTextVisibility(VISIBLE);
        mTitleRightTV.setText(text);
    }

    //设置右侧文本
    public AppCompatTextView getRightText() {
        return mTitleRightTV;
    }

    //设置右侧文本颜色
    public void setRightTextColor(int color) {
        mTitleRightTV.setTextColor(color);
    }

    /**
     * 设置右侧第一个按钮
     *
     * @param d
     */
    public void setRightImage(Drawable d) {
        mTitleRightIV.setImageDrawable(d);
        mRightContent.setVisibility(VISIBLE);
        mTitleRightIV.setVisibility(VISIBLE);
    }

    public AppCompatImageView getmTitleMiddleIV() {
//        mTitleMiddleIV.setVisibility(VISIBLE);
        return mTitleMiddleIV;
    }

    public AppCompatImageView getmTitleRightIV() {
        return mTitleRightIV;
    }

    /**
     * 设置右侧第二个按钮
     *
     * @param d
     */
    public void setRightImage2(Drawable d) {
        mTitleRight2IV.setImageDrawable(d);
        mRightContent.setVisibility(VISIBLE);
        mTitleRight2IV.setVisibility(VISIBLE);
    }

    /**
     * 设置右侧第三个按钮
     *
     * @param d
     */
    public void setRightImage3(Drawable d) {
        mTitleRight3IV.setImageDrawable(d);
        mRightContent.setVisibility(VISIBLE);
        mTitleRight3IV.setVisibility(VISIBLE);
    }

    /**
     * 设置右侧第四个按钮
     */
    public void setRightView4() {
        mRightContent.setVisibility(VISIBLE);
        mTitleRight4IV.setVisibility(VISIBLE);
    }

    public void setLeftOnClickListener(OnClickListener listener) {
        setTitleLeftImageVisibility(View.VISIBLE);
        mTitleLeftIV.setOnClickListener(listener);
    }

    public void setRightTextOnClickListener(OnClickListener listener) {
        mTitleRightTV.setOnClickListener(listener);
    }

    /**
     * 设置右侧第一个按钮的点击事件
     *
     * @param clickListener
     */
    public void setRightImageOnClickListener(OnClickListener clickListener) {
        mTitleRightIV.setOnClickListener(clickListener);
    }

    /**
     * 设置右侧第二个按钮的点击事件
     *
     * @param clickListener
     */
    public void setRightImage2OnClickListener(OnClickListener clickListener) {
        mTitleRight2IV.setOnClickListener(clickListener);
    }

    /**
     * 设置右侧第三个按钮的点击事件
     *
     * @param clickListener
     */
    public void setRightImage3OnClickListener(OnClickListener clickListener) {
        mTitleRight3IV.setOnClickListener(clickListener);
    }

    /**
     * 设置右侧第四个按钮的点击事件
     *
     * @param clickListener
     */
    public void setRightView4OnClickListener(OnClickListener clickListener) {
        mTitleRight4IV.setOnClickListener(clickListener);
    }

    public void setHeaderTitle(String title) {
        setUpMainTitle(title);
        mOnLeftClick = null;
        setTitleLeftImageVisibility(View.GONE);
        setRightTextVisibility(View.GONE);
    }


    public void setTitleBarWithLeftImage(String title) {
        this.setTitleBarWithLeftImage(title, null);
    }

    public void setTitleBarWithLeftImage(String title, OnLeftClick listener) {

        setUpMainTitle(title);

        setUpLeftImage(listener);

        setRightTextVisibility(View.GONE);
    }

    public void setTitleBarWithLeftAndRight(String title, String rightTitle, OnLeftClick leftListener, OnClickListener rightListener) {

        setUpMainTitle(title);

        setUpLeftImage(leftListener);

        setUpRightText(rightTitle, rightListener);
    }

    /**
     * 设置标题，左侧按钮，右侧图片按钮与点击事件
     *
     * @param title         标题
     * @param drawable      右侧图片
     * @param leftListener  左侧按钮点击事件
     * @param rightListener 右侧按钮点击事件
     */
    public void setTitleBarWithLeftAndRightImage(String title, Drawable drawable, OnLeftClick leftListener, OnClickListener rightListener) {

        setUpMainTitle(title);

        setUpLeftImage(leftListener);

        setUpRightImage(drawable, rightListener);
    }

    public void setTitleBarWithRight(String title, String rightTitle, OnClickListener rightListener) {

        setUpMainTitle(title);

        setUpRightText(rightTitle, rightListener);
    }


    /**
     * 设置主标题
     *
     * @param title
     */
    public void setUpMainTitle(String title) {
        setVisibility(View.VISIBLE);
        setTitle(title);
    }

    /**
     * 设置左侧图片，默认点击返回
     *
     * @param listener
     */
    public void setUpLeftImage(OnLeftClick listener) {
        setTitleLeftImageVisibility(View.VISIBLE);

        mOnLeftClick = listener;
        setLeftOnClickListener(new TitleBarLeftDefaultListener());
    }

    /**
     * 右边添加自定义布局
     *
     * @param view
     */
    public void setUpRightCusView(View view) {
        setRightCusViewVisibility(View.VISIBLE);
//        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//        lp.gravity = Gravity.CENTER_VERTICAL;
//        ViewGroup parent = (ViewGroup) v.getParent();
//
//        if (parent != null) {
//
//            parent.removeAllViews();
//
//        }
//        mTitleRightFl.addView(view, lp);
        mTitleRightFl.addView(view);
    }

    /**
     * 设置自定义布局显示隐藏
     *
     * @param visibility
     */
    public void setRightCusViewVisibility(int visibility) {
        mTitleRightFl.setVisibility(visibility);
        mRightContent.setVisibility(visibility);
    }

    /**
     * 获得自定义view 容器
     *
     * @return
     */
    public View getRightCusView() {
        return mRightContent;
    }

    /**
     * 设置右侧文本和点击事件
     *
     * @param rightTitle
     * @param rightListener
     */
    private void setUpRightText(String rightTitle, OnClickListener rightListener) {
        setRightText(rightTitle);
        if (rightListener != null) {
            setRightTextOnClickListener(rightListener);
        }
    }

    /**
     * 设置右侧图片和点击事件
     *
     * @param drawable
     * @param rightListener
     */
    public void setUpRightImage(Drawable drawable, OnClickListener rightListener) {
        setRightImage(drawable);
        if (rightListener != null) {
            setRightImageOnClickListener(rightListener);
        }
    }

    /**
     * 设置右侧第二个图片和点击事件
     *
     * @param drawable
     * @param rightListener
     */
    public void setUpRightImage2(Drawable drawable, OnClickListener rightListener) {
        setRightImage2(drawable);
        if (rightListener != null) {
            setRightImage2OnClickListener(rightListener);
        }
    }

    /**
     * 设置右侧第三个图片和点击事件
     *
     * @param drawable
     * @param rightListener
     */
    public void setUpRightImage3(Drawable drawable, OnClickListener rightListener) {
        setRightImage3(drawable);
        if (rightListener != null) {
            setRightImage3OnClickListener(rightListener);
        }
    }

    /**
     * 设置右侧第三个图片和点击事件
     *
     * @param rightListener
     */
    public void setUpRightView4(OnClickListener rightListener) {
        setRightView4();
        if (rightListener != null) {
            setRightView4OnClickListener(rightListener);
        }
    }

    public int getTitleBarHeight() {
        return titleBarHeight;
    }

    /**
     * 设置中间标题
     *
     * @param title1
     * @param title2
     */
    public void setMiddleTitle2(String title1, String title2) {
        if (mTitleTV.getVisibility() == VISIBLE) {
            mTitleTV.setVisibility(GONE);
        }
        if (mMiddleTitle.getVisibility() != VISIBLE) {
            mMiddleTitle.setVisibility(VISIBLE);
        }
        mMiddleTitleTV1.setText(title1);
        mMiddleTitleTV2.setText(title2);
    }

    public AppCompatTextView getmMiddleTitleTV1() {
        return mMiddleTitleTV1;
    }

    public AppCompatTextView getmMiddleTitleTV2() {
        return mMiddleTitleTV2;
    }

    private class TitleBarLeftDefaultListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            onBackPressed();
        }
    }

    public void onBackPressed() {
        if (mOnLeftClick == null || !mOnLeftClick.onLeftClick()) {
            FragmentActivity activity = ((FragmentActivity) mContext);
            if (null != activity) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    if (!activity.isDestroyed()) {
                        activity.onBackPressed();
                    }
                } else {
                    activity.onBackPressed();
                }
            }
        }
    }

    private OnLeftClick mOnLeftClick;

    public interface OnLeftClick {
        /**
         * 顶部导航栏左侧返回按钮点击事件
         *
         * @return true 为事件已被处理； false 事件为处理。
         * 如果返回false，默认会调用onBackPress方法，不需要手动调用，否则会引起无限递归
         */
        boolean onLeftClick();
    }

}

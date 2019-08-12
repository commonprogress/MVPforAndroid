package com.play.module_me.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.play.library_base.router.RouterActivityPath;
import com.play.library_base.router.RouterFragmentPath;
import com.play.library_base.utils.SharePreUtils;
import com.play.library_base.view.ItemView;
import com.play.library_mvp.base.common.BaseFragment;
import com.play.library_mvp.base.common.BaseNestingLazyFragment;
import com.play.library_mvp.base.common.BasePresenter;
import com.play.module_me.R;
import com.play.module_me.contract.MeContract;
import com.play.module_me.presenter.MePresenter;

/**
 * @author：jhonjson
 * @data：2019/2/20 下午16:40
 * @描述: 我屏
 */

@Route(path = RouterFragmentPath.User.PAGER_USERDETAIL)
public class MeFragment extends BaseNestingLazyFragment<MeContract.View, MeContract.Presenter> implements MeContract.View {

    private TextView tvName;
    private ItemView ivFavorite, ivSetting, ivAbout;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_me_layout;
    }

    @Override
    public MeContract.Presenter createPresenter() {
        return new MePresenter(mContext);
    }

    @Override
    public MeContract.View createView() {
        return this;
    }

    @Override
    public void init(View rootView) {
        tvName = rootView.findViewById(R.id.tvName);
        ivFavorite = rootView.findViewById(R.id.iv_favorite);
        ivSetting = rootView.findViewById(R.id.iv_setting);
        ivAbout = rootView.findViewById(R.id.iv_about);
        tvName.setText(SharePreUtils.getString("username"));

        ivFavorite.setOnClickListener(View -> {

        });

        ivSetting.setOnClickListener(View -> {

        });

        ivAbout.setOnClickListener(View -> {
            ARouter.getInstance().build(RouterActivityPath.Setting.PAGER_SETTING_ABOUT)
                    .navigation();
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}

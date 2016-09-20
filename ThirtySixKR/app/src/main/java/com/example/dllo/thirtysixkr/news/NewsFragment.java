package com.example.dllo.thirtysixkr.news;

import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.dllo.thirtysixkr.BaseFragment;
import com.example.dllo.thirtysixkr.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class NewsFragment extends BaseFragment implements View.OnClickListener {

    private DrawerLayout drawerLayout;
    private LinearLayout left;

    private ImageView ivDrawer;
    private ImageView ivSearch;
    private ImageView ivBack;
    private TextView drawerTvAll;
    private TextView drawerTvEarly;
    private TextView drawerTvDeep;
    private TextView drawerTvBig;
    private TextView drawerTvEight;
    private TextView drawerTvIcon;
    private TextView drawerTvFriend;
    private TextView drawerTvResearch;


    @Override
    protected int setLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {
        PullToRefreshListView pullToRefreshlv = bindView(R.id.Refresh_headline);
        drawerLayout = bindView(R.id.drawerLayout);

        left = bindView(R.id.left);

        ivDrawer = bindView(R.id.news_iv_drawer);

        ivSearch = bindView(R.id.news_iv_search);

        ivBack = bindView(R.id.iv_return);

        drawerTvAll = bindView(R.id.tv_all);

        drawerTvBig = bindView(R.id.tv_big_company);

        drawerTvEarly = bindView(R.id.tv_early);

        drawerTvDeep = bindView(R.id.tv_deep);

        drawerTvEight = bindView(R.id.tv_eight);

        drawerTvIcon = bindView(R.id.tv_icon);

        drawerTvFriend = bindView(R.id.tv_friend);

        drawerTvResearch = bindView(R.id.tv_research);

        ivDrawer.setOnClickListener(this);

        ivBack.setOnClickListener(this);

        drawerTvBig.setOnClickListener(this);

        drawerTvAll.setOnClickListener(this);

        drawerTvEight.setOnClickListener(this);

        drawerTvEarly.setOnClickListener(this);

        drawerTvIcon.setOnClickListener(this);

        drawerTvDeep.setOnClickListener(this);

        drawerTvFriend.setOnClickListener(this);

        drawerTvResearch.setOnClickListener(this);

        RequestQueue queue = Volley.newRequestQueue(mContext);



    }

    @Override
    protected void initData() {
        NewsAdapter newsAdapter = new NewsAdapter(mContext);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.news_iv_drawer:
                drawerLayout.openDrawer(left);
                break;
            case R.id.iv_return:
                drawerLayout.closeDrawer(left);
                break;
            case R.id.tv_all:
                break;
            case R.id.tv_early:
                break;
            case R.id.tv_deep:
                break;
            case R.id.tv_big_company:
                break;
            case R.id.tv_eight:
                break;
            case R.id.tv_icon:
                break;
            case R.id.tv_friend:
                break;
            case R.id.tv_research:
                break;


            default:
                break;
        }
    }
}

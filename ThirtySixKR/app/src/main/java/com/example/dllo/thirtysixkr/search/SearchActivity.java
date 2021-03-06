package com.example.dllo.thirtysixkr.search;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.thirtysixkr.BaseActivity;
import com.example.dllo.thirtysixkr.R;
import com.example.dllo.thirtysixkr.news.NewsAdapter;
import com.example.dllo.thirtysixkr.news.NewsBean;
import com.example.dllo.thirtysixkr.tools.db_tools.DBTools;
import com.example.dllo.thirtysixkr.tools.url.Kr36Url;
import com.example.dllo.thirtysixkr.tools.web_request.SendGetRequest;

import java.util.ArrayList;

public class SearchActivity extends BaseActivity implements View.OnClickListener {

    private EditText editText;
    private Button cancel;
    private ImageView delete;
    private ImageButton search;
    private ListView lv;
    private DBTools tools;
    private RelativeLayout noSearchRL;
    private RelativeLayout searchMoreRL;
    private ArrayList<String> contents = new ArrayList<>();
    private SearchAdapter searchAdapter;
    private View historyViewHeader;
    private View historyViewFooter;
    private View viewMore;
    private TextView deleteHistory;
    private NewsAdapter adapter;

    @Override
    protected int setLayout() {
        return R.layout.search_activity;
    }

    @Override
    protected void initView() {


        search = bindView(R.id.search_ib);

        searchAdapter = new SearchAdapter(this);

        adapter = new NewsAdapter(this);

        editText = bindView(R.id.search_et);

        cancel = bindView(R.id.search_cancel);

        noSearchRL = bindView(R.id.search_rl);

        delete = bindView(R.id.search_delete);


        lv = bindView(R.id.search_lv);

        tools = new DBTools(this);

        contents = tools.queryALLDB();

        Log.d("SearchActivity", "contents:" + contents);


        viewMore = LayoutInflater.from(this).inflate(R.layout.search_lv_header, null);

        historyViewHeader = LayoutInflater.from(this).inflate(R.layout.search_lv_history_header, null);

        historyViewFooter = LayoutInflater.from(this).inflate(R.layout.search_lv_history_footer, null);

        deleteHistory = (TextView) historyViewFooter.findViewById(R.id.search_footer_tv);
//        lv.addHeaderView(viewMore);
//        lv.addHeaderView(historyViewHeader);
//        lv.addFooterView(historyViewFooter);
        searchMoreRL = (RelativeLayout) viewMore.findViewById(R.id.search_rl_more);


        if (contents.size() != 0) {
            noSearchRL.setVisibility(View.GONE);
            lv.setVisibility(View.VISIBLE);
            searchAdapter.setBeans(contents);
//            lv.removeHeaderView(viewMore);
//            lv.setAdapter(searchAdapter);
            setHistoryAdapter(searchAdapter);
        } else {
            noSearchRL.setVisibility(View.VISIBLE);
//            lv.setAdapter(adapter);
            setSearchAdapter(adapter);
        }


        deleteHistory.setOnClickListener(this);
        cancel.setOnClickListener(this);
        delete.setOnClickListener(this);
        search.setOnClickListener(this);


    }

    @Override
    protected void initData() {


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                delete.setVisibility(View.VISIBLE);
                lv.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editText.getText().length() != 0) {
                    String url = Kr36Url.research(editText.getText() + "");
                    SendGetRequest.sendGetRequest(url, NewsBean.class, new SendGetRequest.OnResponseListener<NewsBean>() {
                        @Override
                        public void onResponse(NewsBean response) {
                            if (editText.getText().toString().length() == 0) {
                                return;
                            }
                            if (response.getD().getTotalCount() == 0) {
                                noSearchRL.setVisibility(View.VISIBLE);
                                lv.setVisibility(View.GONE);
                            } else {
                                noSearchRL.setVisibility(View.GONE);
                                lv.setVisibility(View.VISIBLE);
                                adapter.setBeans(response.getD().getData());
//                                Log.d("SearchActivity", lv.getAdapter().getClass().getSimpleName());
                                HeaderViewListAdapter headAdapter = (HeaderViewListAdapter) lv.getAdapter();
                                if (!(headAdapter.getWrappedAdapter() instanceof NewsAdapter)) {
                                    setSearchAdapter(adapter);
                                }

                            }
                        }


                        @Override
                        public void onError() {

                        }
                    });
                } else {
                    contents = tools.queryALLDB();
                    delete.setVisibility(View.INVISIBLE);
                    if (contents.size() != 0) {
                        noSearchRL.setVisibility(View.GONE);
                        lv.setVisibility(View.VISIBLE);
                        searchAdapter.setBeans(contents);
                        Log.d("SearchActivity", lv.getAdapter().getClass().getSimpleName());
                        HeaderViewListAdapter headAdapter = (HeaderViewListAdapter) lv.getAdapter();
                        if (!(headAdapter.getWrappedAdapter() instanceof SearchAdapter)) {
                            setHistoryAdapter(searchAdapter);
                        }

                    } else {
                        noSearchRL.setVisibility(View.VISIBLE);
                        lv.setVisibility(View.GONE);
                        delete.setVisibility(View.INVISIBLE);


                    }
                }

            }
        });


    }

    //重新设置为历史记录的Adapter
    private void setHistoryAdapter(SearchAdapter searchAdapter) {
        if (lv.getHeaderViewsCount() > 0) {
            lv.removeHeaderView(viewMore);//去掉搜索的头布局
        }
        lv.addHeaderView(historyViewHeader);
        lv.addFooterView(historyViewFooter);
        lv.setAdapter(searchAdapter);

    }

    //更新为搜索Adapter
    private void setSearchAdapter(NewsAdapter newsAdapter) {
        lv.removeHeaderView(historyViewHeader);
        lv.removeFooterView(historyViewFooter);
        lv.addHeaderView(viewMore);
        lv.setAdapter(newsAdapter);
    }

//    private void addHeadHistory() {
//        lv.addHeaderView(historyViewHeader);
//        lv.addFooterView(historyViewFooter);
//        lv.removeHeaderView(viewMore);
//    }

//    private void addHeadViewMore() {
//        lv.addHeaderView(viewMore);
//        lv.removeHeaderView(historyViewHeader);
//        lv.removeFooterView(historyViewFooter);
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_cancel:

                finish();

                break;
            case R.id.search_delete:

                editText.setText("");

                break;
            case R.id.search_ib:
                if (editText.getText().length() != 0) {
                    tools.insertDB(editText.getText() + "");
                }

                break;
            case R.id.search_footer_tv:
                tools.deleteAllDB();
                lv.setVisibility(View.GONE);
                noSearchRL.setVisibility(View.VISIBLE);
                Log.d("SearchActivity", "ssad");
                break;
        }

    }
}

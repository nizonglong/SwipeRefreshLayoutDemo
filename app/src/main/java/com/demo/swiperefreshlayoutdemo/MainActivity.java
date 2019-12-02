package com.demo.swiperefreshlayoutdemo;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.demo.swiperefreshlayoutdemo.adapter.DemoAdapter;
import com.demo.swiperefreshlayoutdemo.bean.Tag;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * @author nizonglong
 */
public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView       mRecyclerView;
    private List<Tag>          mTags = new ArrayList<>();
    private DemoAdapter        mAdapter;
    private int count = mTags.size();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initData();
        initView();
        initEvent();
    }

    public void initView() {
        mRefreshLayout = findViewById(R.id.srl_main);
        mRecyclerView = findViewById(R.id.rv_main);

        mAdapter = new DemoAdapter(mTags);

        // layoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void initData() {
        for (int i = 0; i < 5; i++) {
            Tag tag = new Tag(i + "", "name-" + i);
            mTags.add(tag);
        }

        count = mTags.size();
    }

    public void initEvent() {
        // 设置下拉监听
        mRefreshLayout.setOnRefreshListener(this);
        // 刷新渐变颜色
        mRefreshLayout.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.colorAccent
        );

    }

    @Override
    public void onRefresh() {
        addData();
    }

    private void addData() {
        // 延迟2s刷新
        mHandler.postDelayed(mRefresh, 2000);
    }

    private Handler mHandler = new Handler();

    private Runnable mRefresh = new Runnable() {
        @Override
        public void run() {
            mRefreshLayout.setRefreshing(false);

            int now = mTags.size()+5;
            for (; count < now; count++) {
                Tag tag = new Tag(count + "", "name-" + count);
                mTags.add(tag);
            }

            Log.d("tags",mTags.toString());
            Log.d("tags",mTags.size()+"");
            mAdapter.notifyDataSetChanged();
        }
    };
}

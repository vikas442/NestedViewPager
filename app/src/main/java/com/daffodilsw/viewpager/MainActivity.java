package com.daffodilsw.viewpager;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    AWViewPager mAWViewPager;

    private Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupViewPager();
    }

    private void setupViewPager() {
        mAdapter = new Adapter(getSupportFragmentManager());
        mAdapter.addFragment(DemoFragmentFragment.newInstance(1));
        mAdapter.addFragment(DemoFragmentFragment.newInstance(2));
        mAdapter.addFragment(DemoFragmentFragment.newInstance(3));
        mAdapter.addFragment(DemoFragmentFragment.newInstance(4));
        mAdapter.addFragment(DemoFragmentFragment.newInstance(5));
        mAdapter.addFragment(DemoFragmentFragment.newInstance(6));
        mAWViewPager.setPagingEnabled(false);
        mAWViewPager.setAdapter(mAdapter);
    }


    public void showNextPage() {
        mAWViewPager.setCurrentItem(mAWViewPager.getCurrentItem() + 1);
    }

    public void showPrevPage() {
        mAWViewPager.setCurrentItem(mAWViewPager.getCurrentItem() - 1);
    }
}

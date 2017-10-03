package com.daffodilsw.viewpager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class DemoFragmentFragment extends Fragment {

    @BindView(R.id.label)
    TextView mLabel;
    @BindView(R.id.child_view_pager)
    AWViewPager mAWViewPager;

    @BindView(R.id.back)
    Button mBack;

    @BindView(R.id.next)
    Button mNext;


    private Unbinder mUnbinder;

    private static final String EXTRA_INDEX = "page_index";
    private int mIndex;
    private Adapter mAdapter;

    public static DemoFragmentFragment newInstance(int pageIndex) {

        Bundle args = new Bundle();
        args.putInt(EXTRA_INDEX, pageIndex);
        DemoFragmentFragment fragment = new DemoFragmentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public DemoFragmentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndex = getArguments().getInt(EXTRA_INDEX);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_demo, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void handleControlsVisibility() {
        mBack.setVisibility(mAWViewPager.getCurrentItem() == 0 ? View.INVISIBLE : View.VISIBLE);
        mNext.setVisibility(mAWViewPager.getCurrentItem() == mAdapter.getCount() - 1 ? View.INVISIBLE : View.VISIBLE);
    }

    private void setupViewPager() {
        mAdapter = new Adapter(getChildFragmentManager());
        mAdapter.addFragment(ChildFragment.newInstance(1));
        mAdapter.addFragment(ChildFragment.newInstance(2));
        mAdapter.addFragment(ChildFragment.newInstance(3));
        mAdapter.addFragment(ChildFragment.newInstance(4));
        mAWViewPager.setPagingEnabled(false);
        mAWViewPager.setAdapter(mAdapter);
    }

    @OnClick({R.id.back, R.id.next})
    void onClick(View view) {
        saveDataOfCurrentPage();
        switch (view.getId()) {
            case R.id.back:
                if (mAWViewPager.getCurrentItem()==0){
                 if (getActivity() instanceof MainActivity){
                     ((MainActivity)getActivity()).showPrevPage();
                 }
                }else {
                    mAWViewPager.setCurrentItem(mAWViewPager.getCurrentItem() - 1);
                }
                break;
            case R.id.next:
                if (mAWViewPager.getCurrentItem()==mAdapter.getCount()-1){
                    if (getActivity() instanceof MainActivity){
                        ((MainActivity)getActivity()).showNextPage();
                    }
                }else {
                    mAWViewPager.setCurrentItem(mAWViewPager.getCurrentItem() + 1);
                }
                break;
        }
//        handleControlsVisibility();
    }

    private void saveDataOfCurrentPage() {
        Fragment fragment = mAdapter.getItem(mAWViewPager.getCurrentItem());
        if (fragment instanceof ChildFragment) {
            String data = ((ChildFragment) fragment).saveData();
            Toast.makeText(getContext(),data,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViewPager();
        updateUI();
    }

    private void updateUI() {
        mLabel.setText(String.format("Fragment %d", mIndex));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}

package com.daffodilsw.viewpager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChildFragment extends Fragment {

    public static final String EXTRA_INDEX = "index";
    private int mIndex;
    @BindView(R.id.label)
    TextView mLabel;

    @BindView(R.id.edit_text)
    EditText mEditText;

    private Unbinder mUnbinder;

    public static ChildFragment newInstance(int pageIndex) {

        Bundle args = new Bundle();
        args.putInt(EXTRA_INDEX, pageIndex);
        ChildFragment fragment = new ChildFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndex = getArguments().getInt(EXTRA_INDEX);
    }

    public ChildFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_child, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateUI();
    }

    private void updateUI() {
        mLabel.setText(String.format(" Child Fragment %d", mIndex));
    }

    public String saveData() {
        return mEditText.getText().toString();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

}

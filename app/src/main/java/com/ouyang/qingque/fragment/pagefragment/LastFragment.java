package com.ouyang.qingque.fragment.pagefragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ouyang.qingque.fragment.base.BasePagerFragment;
import com.ouyang.testtab.R;


public class LastFragment extends BasePagerFragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public LastFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LastFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LastFragment newInstance(String param1, String param2) {
        LastFragment fragment = new LastFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_last, container, false);
    }

    @Override
    protected void toHeaderAnim() {

    }

    @Override
    protected void childDetache(View view) {

    }

    @Override
    protected void initScrollRv(){

    }

    @Override
    public void onRecyclerViewListener() {

    }

    @Override
    public void updateDataToView(RecyclerView recyclerView, int dx, int dy) {

    }

    @Override
    public void initRecyclerView() {

    }


}

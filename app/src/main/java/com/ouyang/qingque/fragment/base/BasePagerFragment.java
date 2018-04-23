package com.ouyang.qingque.fragment.base;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ouyang.qingque.adapter.BasePagerAdapter;
import com.ouyang.qingque.fragment.pagefragment.FocusFragment;
import com.ouyang.qingque.util.DeviceUtil;
import com.ouyang.qingque.util.ScreenInfo;
import com.ouyang.testtab.R;

import java.lang.reflect.Field;

import cn.jzvd.JZMediaManager;
import cn.jzvd.JZUtils;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerManager;
import rx.Observable;
import rx.Subscriber;

public abstract class BasePagerFragment extends Fragment {


    public int position;
    public BasePagerAdapter mAdapter;
    public RecyclerView recyclerView;
    private OnFragmentInteractionListener mListener;
    public  ImageView civ_header_item,iv_one_item,iv_two_item;
    public  TextView tv_author_item,tv_time_item;
    public RelativeLayout mSuspensionBar;
    public int mSuspensionHeight;
    public int headerHeight;
    public int screenWidth;
    public BasePagerFragment(){}


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        this.recyclerView =  view.findViewById(R.id.flist);
        this.civ_header_item = view.findViewById(R.id.civ_header_item);
        this.tv_author_item = view.findViewById(R.id.tv_author_item);
        this.tv_time_item = view.findViewById(R.id.tv_time_item);
        this.iv_one_item = view.findViewById(R.id.iv_one_item);
        this.iv_two_item = view.findViewById(R.id.iv_two_item);
        this.mSuspensionBar=view.findViewById(R.id.rl_header);
        screenWidth= ScreenInfo.getScreenWidth(getActivity());
        initData();
        initListener();
        return view;

    }

    private void initData() {
        initRecyclerView();
    }


    private void initListener() {
        /**
         * RecyclerView单击与长按监听
         */
        onRecyclerViewListener();
        /**
         * RecyclerView滑动监听
         */
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                initScrollRv();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                updateDataToView(recyclerView, dx, dy);
            }
        });


        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {

            }
            @Override
            public void onChildViewDetachedFromWindow(View view) {
                 //当此项超出屏幕显示项时，要释放资源
                 childDetache(view);
            }
        });

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_UP){
                     toHeaderAnim();
                }
                return false;
            }
        });

    }

    protected abstract void toHeaderAnim();

    public void setMaxFlingVelocity(RecyclerView recyclerView,int velocity){
          try{
              Field field =recyclerView.getClass().getDeclaredField("mMaxFlingVelocity");
              field.setAccessible(true);
              field.set(recyclerView,velocity);
          }catch (Exception e){
             e.printStackTrace();
          }
    }

    protected abstract void childDetache(View view);

    protected abstract void initScrollRv();

    public abstract void onRecyclerViewListener();

    public abstract void updateDataToView(RecyclerView recyclerView, int dx, int dy);


    public abstract void initRecyclerView();

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

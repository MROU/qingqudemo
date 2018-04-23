package com.ouyang.qingque;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.ouyang.qingque.fragment.base.BaseFragment;
import com.ouyang.qingque.fragment.pagefragment.FocusFragment;
import com.ouyang.qingque.fragment.pagefragment.HotFragment;
import com.ouyang.qingque.util.DeviceUtil;
import com.ouyang.qingque.view.MyViewpager;
import com.ouyang.testtab.R;
import com.ouyang.qingque.fragment.pagefragment.LastFragment;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZVideoPlayer;



public class QwanFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyViewpager getVp() {
        return vp;
    }

    public void setVp(MyViewpager vp) {
        this.vp = vp;
    }

    private MyViewpager vp;
    private pagerAdapter adapter;
    List<Fragment> fragmentList=new ArrayList<Fragment>();
    FocusFragment myFocusFragment=new FocusFragment();
    HotFragment hotfragment=new HotFragment();
    LastFragment lastFragment=new LastFragment();
    private OnFragmentInteractionListener mListener;



    public RelativeLayout getRl_quewan() {
        return rl_quewan;
    }

    public void setRl_quewan(RelativeLayout rl_quewan) {
        this.rl_quewan = rl_quewan;
    }

    RelativeLayout rl_quewan;

    public RelativeLayout getRl_use() {
        return rl_use;
    }

    public void setRl_use(RelativeLayout rl_use) {
        this.rl_use = rl_use;
    }
    RelativeLayout rl_use;
    LinearLayout mLinearLayout;
    private TextSwitcher textSwicher;
    // 数组
    private String[] arrayTexts = { "关注", "热门", "最新"};
    // 要显示的图片在图片数组中的Index
    private int textIndex=0;
    // 左右滑动时手指按下的X坐标
    private float touchDownX;
    // 左右滑动时手指松开的X坐标
    private float touchUpX;
    //记录上一次滑动的positionOffsetPixels值
    private int lastValue = -1;
    private boolean isLeft = true;
    public QwanFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static QwanFragment newInstance(String param1, String param2){
        QwanFragment fragment = new QwanFragment();
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

    public LinearLayout getmLinearLayout() {
        return mLinearLayout;
    }

    public void setmLinearLayout(LinearLayout mLinearLayout) {
        this.mLinearLayout = mLinearLayout;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // Inflate the layout for this fragment
  //   View view= inflater.inflate(R.layout.fragment_qwan,container,false);
     View view = View.inflate(getActivity(), R.layout.fragment_qwan,null);
     vp=view.findViewById(R.id.viewpager);
     textSwicher = view.findViewById(R.id.textSwicher);
     rl_quewan=view.findViewById(R.id.rl_quewan);
     rl_use=view.findViewById(R.id.rl_use);
     mLinearLayout=view.findViewById(R.id.group_circle);
     initview();
     return view;
    }

    public void initview(){
        if(fragmentList.size()>0){
            fragmentList.clear();
        }
        if(vp!=null){
            vp.removeAllViews();
        }
       fragmentList.add(myFocusFragment);
        fragmentList.add(hotfragment);
       fragmentList.add(lastFragment);
        adapter=new pagerAdapter(getChildFragmentManager(),fragmentList);
        vp.setAdapter(adapter);
        vp.setCurrentItem(0);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset != 0) {
                    if (lastValue >= positionOffsetPixels){
                        //右滑
                        isLeft = false;
                    } else if (lastValue < positionOffsetPixels) {
                        //左滑
                        isLeft = true;
                    }
                }
                lastValue = positionOffsetPixels;
            }

            @Override
            public void onPageSelected(int position) {

                if (isLeft){
                    Log.e("onPageScrolled","--->左划");
                    // 设置文本切换的动画
                    // 由于Android没有提供slide_out_left和slide_in_right，所以仿照slide_in_left和slide_out_right编写了slide_out_left和slide_in_right
                    textSwicher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(),
                            R.anim.slide_in_from_left));
                    // 设置当前要看的文本
                    textSwicher.setText(arrayTexts[position]);
                    textSwicher.setInAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.slide_out_to_right));
                    //显示小白点
                    mLinearLayout.getChildAt(position).setEnabled(true);
                    for(int i=0;i<arrayTexts.length;i++){
                        if(position!=i){
                            //显示小白点
                            mLinearLayout.getChildAt(i).setEnabled(false);
                        }
                    }
                }else {
                    // 设置文本切换的动画
                    textSwicher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(),
                            android.R.anim.slide_out_right));
                    // 设置当前要看的文本
                    textSwicher.setText(arrayTexts[position]);
                    textSwicher.setInAnimation(AnimationUtils.loadAnimation(getActivity(),
                            android.R.anim.slide_in_left));
                    //显示小白点
                    mLinearLayout.getChildAt(position).setEnabled(true);
                    for(int i=0;i<arrayTexts.length;i++){
                        if(position!=i){
                            //显示小白点
                            mLinearLayout.getChildAt(i).setEnabled(false);
                        }
                    }
                }
                JZVideoPlayer.releaseAllVideos();
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // 为TextSwitcher设置Factory，用来为TextSwitcher制造TextView
        textSwicher.setFactory(new ViewSwitcher.ViewFactory()
        {
            public View makeView()
            {
                TextView tv = new TextView(getActivity());
                tv.setTextSize(34);
          //      tv.getPaint().setFakeBoldText(true);
                tv.setWidth(DeviceUtil.dip2px(getActivity(),100));
                tv.setGravity(Gravity.CENTER);
                tv.setTextColor(Color.BLACK);
                return tv;
            }
        });
        textSwicher.setText(arrayTexts[0]);
        // 设置TextSwitcher左右滑动监听事件
        textSwicher.setOnTouchListener(new TextSwicherOnTouchListener());

        for(int i=0;i<arrayTexts.length;i++){
            //创建底部指示器(小圆点)
            View  view = new View(getActivity());
            view.setBackgroundResource(R.drawable.circle_bg);
            view.setEnabled(false);
            //设置宽高
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);
            //设置间隔
            if (i!=0) {
                layoutParams.leftMargin = 20;
            }
            //添加到LinearLayout
            mLinearLayout.addView(view,layoutParams);
        }
        //显示小白点
        mLinearLayout.getChildAt(0).setEnabled(true);
    }

    //开始按钮监听
    private final class TextSwicherOnTouchListener implements View.OnTouchListener {
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                // 取得左右滑动时手指按下的X坐标
                touchDownX = event.getX();
                return true;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                // 取得左右滑动时手指松开的X坐标
                touchUpX = event.getX();
                // 从左往右，看前一文本
                if (touchUpX - touchDownX > 50){
                    slideAnimTextSwitchLast();
                    vp.setCurrentItem(textIndex,true);
                    for(int i=0;i<arrayTexts.length;i++){
                        if(textIndex!=i){
                            //显示小白点
                            mLinearLayout.getChildAt(i).setEnabled(false);
                        }
                    }
                    // 从右往左，看下一张
                }else if(touchDownX - touchUpX > 50) {
                    slideAnimTextSwitchNext();
                    vp.setCurrentItem(textIndex,true);
                    for(int i=0;i<arrayTexts.length;i++){
                        if(textIndex!=i){
                            //显示小白点
                            mLinearLayout.getChildAt(i).setEnabled(false);
                        }
                    }

                }
                return true;
            }
            return false;

        }
    }

    private void slideAnimTextSwitchLast() {
        // 设置文本切换的动画
        textSwicher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(),
                android.R.anim.slide_out_right));
        // 设置当前要看的文本
        textSwicher.setText(arrayTexts[textIndex=textIndex==0?(arrayTexts.length-1):--textIndex]);
        textSwicher.setInAnimation(AnimationUtils.loadAnimation(getActivity(),
                android.R.anim.slide_in_left));
        //显示小白点
        mLinearLayout.getChildAt(textIndex).setEnabled(true);
    }

    private void slideAnimTextSwitchNext() {
        // 设置文本切换的动画
        // 由于Android没有提供slide_out_left和slide_in_right，所以仿照slide_in_left和slide_out_right编写了slide_out_left和slide_in_right
        textSwicher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_in_from_left));
        // 设置当前要看的文本
        textSwicher.setText(arrayTexts[textIndex=textIndex==(arrayTexts.length-1)?0:++textIndex]);
        textSwicher.setInAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.slide_out_to_right));
        //显示小白点
        mLinearLayout.getChildAt(textIndex).setEnabled(true);
    }

    class pagerAdapter extends FragmentPagerAdapter {

        List<Fragment> list;
        public pagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list=list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
}

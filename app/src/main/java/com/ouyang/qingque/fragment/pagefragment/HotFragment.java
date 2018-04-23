package com.ouyang.qingque.fragment.pagefragment;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ouyang.qingque.fragment.base.BasePagerFragment;
import com.ouyang.qingque.qingqueservice.QingQueService;
import com.ouyang.qingque.util.TimeUtils;
import com.ouyang.qingque.view.GlideCircleTransform;
import com.ouyang.qingque.QwanFragment;
import com.ouyang.testtab.R;
import com.ouyang.qingque.adapter.FocusListAdapter;
import com.ouyang.qingque.model.FocusBean;
import com.ouyang.qingque.model.FoucsModel;
import com.ouyang.qingque.util.DeviceUtil;
import java.util.ArrayList;
import cn.jzvd.JZMediaManager;
import cn.jzvd.JZUtils;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerManager;
import cn.jzvd.JZVideoPlayerStandard;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class HotFragment extends BasePagerFragment {
    ArrayList<FocusBean> focusBeans=new ArrayList<>();
    FocusListAdapter mAdapter;
    private int mCurrentPosition = 0;
    private LinearLayout mLinearLayout;
    private TextSwitcher textSwicher;
    LinearLayoutManager linearLayoutManager;
    // 数组
    private String[] arrayTexts = { "关注", "热门", "最新"};
    // 要显示的图片在图片数组中的Index
    private int textIndex=0;
    // 左右滑动时手指按下的X坐标
    private float touchDownX;
    // 左右滑动时手指松开的X坐标
    private float touchUpX;
    //记录X移动距离
    private float toX;
    //记录Y移动距离
    private float toY;
    public HotFragment() {
        // Required empty public constructor
    }

    private void updateSuspensionBar(ArrayList<FocusBean> focusBeans){
        Glide.with(getActivity()).load(focusBeans.get(mCurrentPosition).getCreatorDetail().getUserIcon()).centerCrop().placeholder(R.mipmap.ic_launcher)
                .transform(new GlideCircleTransform(getActivity(),2,getActivity().getResources().getColor(android.R.color.white)))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(civ_header_item);
        tv_author_item.setText(focusBeans.get(mCurrentPosition).getCreatorDetail().getUserNick());
        String timeStr= TimeUtils.longToDateStr(focusBeans.get(mCurrentPosition).getGmtCreateStamp());
        tv_time_item.setText(TimeUtils.formatTime(timeStr));
    }

    private void setHeaderView(){
        mSuspensionHeight=DeviceUtil.dip2px(getActivity(),80);
        headerHeight=DeviceUtil.dip2px(getActivity(),70);
        View header = View.inflate(getActivity(),R.layout.header_view,null);
        textSwicher = header.findViewById(R.id.textSwicher);
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
                tv.setBackgroundColor(Color.TRANSPARENT);
                return tv;
            }
        });
        textSwicher.setText(arrayTexts[0]);
        // 设置TextSwitcher左右滑动监听事件
        textSwicher.setOnTouchListener(new TextSwicherOnTouchListener());
        mLinearLayout= header.findViewById(R.id.group_circle);
        for(int i=0;i<arrayTexts.length;i++){
            //创建底部指示器(小圆点)
            View  cview = new View(getActivity());
            cview.setBackgroundResource(R.drawable.circle_bg);
            cview.setEnabled(false);
            //设置宽高
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);
            //设置间隔
            if (i!=0) {
                layoutParams.leftMargin = 20;
            }
            //添加到LinearLayout
            mLinearLayout.addView(cview, layoutParams);
        }
        //显示小白点
        mLinearLayout.getChildAt(0).setEnabled(true);
        mAdapter.setHeaderView(header);
    }

    //开始按钮监听
    private final class TextSwicherOnTouchListener implements View.OnTouchListener{
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                // 取得左右滑动时手指按下的X坐标
                touchDownX = event.getX();
                return true;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                // 取得左右滑动时手指松开的X坐标
                touchUpX = event.getX();
                // 从左往右，看前一文本
                if (touchUpX - touchDownX > 50) {
                    // 设置文本切换的动画
                    textSwicher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(),
                            android.R.anim.slide_out_right));
                    // 设置当前要看的文本
                    textSwicher.setText(arrayTexts[textIndex=textIndex==0?(arrayTexts.length-1):--textIndex]);
                    textSwicher.setInAnimation(AnimationUtils.loadAnimation(getActivity(),
                            android.R.anim.slide_in_left));
                    //显示小白点
                    mLinearLayout.getChildAt(textIndex).setEnabled(true);
                    for(int i=0;i<arrayTexts.length;i++){
                        if(textIndex!=i){
                            //显示小白点
                            mLinearLayout.getChildAt(i).setEnabled(false);
                        }
                    }
                    // 从右往左，看下一张
                }else if(touchDownX - touchUpX > 50){
                    // 设置文本切换的动画
                    // 由于Android没有提供slide_out_left和slide_in_right，所以仿照slide_in_left和slide_out_right编写了slide_out_left和slide_in_right
                    textSwicher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(),
                            R.anim.slide_in_from_left));
                    // 设置当前要看的文本
                    textSwicher.setText(arrayTexts[textIndex=textIndex==(arrayTexts.length-1)?0:++textIndex]);
                    textSwicher.setInAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.slide_out_to_right));
                    //显示小白点
                    mLinearLayout.getChildAt(textIndex).setEnabled(true);
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



    public  void  initRecy(){
        //给mRecyclerView添加头header
        setHeaderView();
    }

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    //加载数据
    public void initData(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//新的配置
                .baseUrl("https://mobile.yishihui.com")
                .build();
        QingQueService service = retrofit.create(QingQueService.class);
        service.getFocusList(0, 20, "1521511134249", 2, 1001, "1519837518914",
                35, "0bae4091d1cb955e82b2eeface8377e2", 89746)
                .subscribeOn(Schedulers.newThread())
                .doOnNext(new Action1<FoucsModel>(){
                    @Override
                    public void call(FoucsModel foucsModel){

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Subscriber<FoucsModel>(){
                    @Override
                    public void onCompleted(){
                        Log.i("focusBeans", "success:");
                    }
                    @Override
                    public void onError(Throwable e){

                    }
                    @Override
                    public void onNext(FoucsModel foucsModel){
                        focusBeans.addAll(foucsModel.getData().getNotes());
                        mAdapter.notifyDataSetChanged();
                        //   updateSuspensionBar((ArrayList<FocusBean>) foucsModel.getData().getNotes());
                    }
                });
    }

    //这里仅仅设置了移动，如果需要加动画，可以在此处加上
    @Override
    protected void toHeaderAnim(){
        float b=(screenWidth/2-DeviceUtil.dip2px(getActivity(),50))/2;
        //当X移动的距离小于三个点离屏幕中点的距离时，手指松开将回到初始位置
        if(toX<b){
            ((QwanFragment)(HotFragment.this.getParentFragment())).getRl_quewan().setY(DeviceUtil.dip2px(getActivity(),15));
            ((QwanFragment)(HotFragment.this.getParentFragment())).getmLinearLayout().setX(0);
            recyclerView.scrollToPosition(0);
        }else{
            //当X移动的距离大于三个点离屏幕中点的距离时，手指松开将回到最后位置
            //headerHeight为头布局的高度，toY为移动的距离
            recyclerView.scrollBy(0, (int) (headerHeight-Math.abs(toY)));
        }
    }


    //当此项超出屏幕显示项时，要释放资源
    @Override
    protected void childDetache(View view){
        JZVideoPlayer jzvd = view.findViewById(R.id.videoplayer);
        if (jzvd != null && JZUtils.dataSourceObjectsContainsUri(jzvd.dataSourceObjects, JZMediaManager.getCurrentDataSource())) {
            JZVideoPlayer currentJzvd = JZVideoPlayerManager.getCurrentJzvd();
            if (currentJzvd != null && currentJzvd.currentScreen != JZVideoPlayer.SCREEN_WINDOW_FULLSCREEN) {
                JZVideoPlayer.releaseAllVideos();
            }
        }
    }

    //滚动时，获取现在所在项，播放此项处视频
    @Override
    protected void initScrollRv() {
        if(recyclerView!=null&&linearLayoutManager!=null){
            View view = linearLayoutManager.findViewByPosition(mCurrentPosition);
            if(view!=null){
                JZVideoPlayer jzvd = view.findViewById(R.id.videoplayer);
                //当jzvd不为空，并且不是正在播放时执行
                if(jzvd!=null&&jzvd.currentState!= JZVideoPlayerStandard.CURRENT_STATE_PLAYING){
                    jzvd.startVideo();
                }
            }
        }
    }

    //重写RecyclerView的点击事件
    @Override
    public void onRecyclerViewListener(){
        mAdapter.setOnClickListener(new FocusListAdapter.OnItemClickListener() {
            @Override
            public void ItemClickListener(View view, String id, String img){

            }
            @Override
            public void ItemLongClickListener(View view, int postion) {

            }
        });
    }

    //这里的核心就是通过判断第一个view距离顶端的距离来控制tRl_quewan的显示与影藏，以及三个小点的运动
    @Override
    public void updateDataToView(RecyclerView recyclerView, int dx, int dy) {
        //找到列表第二个可见的View
        View view = linearLayoutManager.findViewByPosition(mCurrentPosition+1);
        if (view == null) return;
        //判断View的top值是否小于悬浮条的高度
        if (view.getTop() <= mSuspensionHeight){
            //被顶掉的效果
            mSuspensionBar.setY(-(mSuspensionHeight - view.getTop()));
        } else {
            mSuspensionBar.setY(0);
        }

        //控制头部内容的显示，影藏
        if(Math.abs(view.getTop())<=headerHeight&&mCurrentPosition==0){
            if(view.getTop()<0){
                //减去三个圆点所占高度的一半
                ((QwanFragment)(HotFragment.this.getParentFragment())).getRl_quewan().setY(-(headerHeight-DeviceUtil.dip2px(getActivity(),10)));
                //减去三个圆点所占宽度的一半
                ((QwanFragment)(HotFragment.this.getParentFragment())).getmLinearLayout().setX((screenWidth/2-DeviceUtil.dip2px(getActivity(),50)));
                mSuspensionBar.setVisibility(View.VISIBLE);
            }else{
                //由于需要回到原来位置
                float ratio=(screenWidth/2-DeviceUtil.dip2px(getActivity(),50))/headerHeight;
                //记录X移动距离
                toX=(headerHeight-(Math.abs(view.getTop())))*ratio;
                //记录Y移动距离
                toY=(Math.abs(view.getTop()))-((headerHeight-DeviceUtil.dip2px(getActivity(),15)));
                ((QwanFragment)(HotFragment.this.getParentFragment())).getRl_quewan().setY(toY);
                ((QwanFragment)(HotFragment.this.getParentFragment())).getmLinearLayout().setX(toX);
            }
        }

        if(view.getTop()==0&&mCurrentPosition==0){
            //     ((QwanFragment)(HotFragment.this.getParentFragment())).getRl_quewan().setY(-(Math.abs(view.getTop())));
            ((QwanFragment)(HotFragment.this.getParentFragment())).getRl_quewan().setVisibility(View.INVISIBLE);
        }

        //判断是否需要更新悬浮条
        if (mCurrentPosition != linearLayoutManager.findFirstVisibleItemPosition()) {
            mCurrentPosition = linearLayoutManager.findFirstVisibleItemPosition();
            //  mSuspensionBar.setY(0);
            //更新悬浮条
            updateSuspensionBar(focusBeans);
        }
    }

    @Override
    public void initRecyclerView(){
        setMaxFlingVelocity(recyclerView,5000);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.scrollToPosition(0);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        // 避免出现RecyclerView has no LayoutManager的错误
        recyclerView.setHasFixedSize(true);
        mAdapter = new FocusListAdapter(getActivity(),focusBeans);
        recyclerView.setAdapter(mAdapter);
        initRecy();
        initData();
    }
}

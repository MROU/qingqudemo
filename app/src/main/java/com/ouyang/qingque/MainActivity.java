package com.ouyang.qingque;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.ouyang.qingque.fragment.base.BaseFragment;
import com.ouyang.qingque.fragment.base.BasePagerFragment;
import com.ouyang.qingque.fragment.pagefragment.HotFragment;
import com.ouyang.testtab.R;
import com.ouyang.qingque.fragment.pagefragment.FocusFragment;
import com.ouyang.qingque.fragment.pagefragment.LastFragment;
import com.ouyang.qingque.util.BottomNavigationViewHelper;

import cn.jzvd.JZVideoPlayer;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends AppCompatActivity implements BaseFragment.OnFragmentInteractionListener,
        BasePagerFragment.OnFragmentInteractionListener{
    private QwanFragment  qwanFragment;
    private QxueFragment  qxueFragment;
    private QliaoFragment qliaoFragment;
    private QmineFragment qmineFragment;
    //底部红点
    Badge qBadgeView;
    Fragment[] fragments;
    //用于选择最近一个Fragment
    private int lastShowFragment = 0;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigation_quewan:
                    if (lastShowFragment != 0){
                        switchFrament(lastShowFragment,0);
                        lastShowFragment = 0;
                    }
                    return true;
                case R.id.navigation_quexue:
                    if (lastShowFragment != 1){
                        switchFrament(lastShowFragment,1);
                        lastShowFragment = 1;
                    }
                    return true;
                case R.id.navigation_queliao:
                    if (lastShowFragment != 2){
                        switchFrament(lastShowFragment, 2);
                        lastShowFragment = 2;
                    }
                    return true;
                case R.id.navigation_mine:
                    if (lastShowFragment != 3){
                        switchFrament(lastShowFragment, 3);
                        lastShowFragment = 3;
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        BottomNavigationItemView queliao_item=navigation.findViewById(R.id.navigation_queliao);
        //disableShiftMode一定要在new QBadgeView前面，因为在disableShiftMode方法里面getChildAt会报错
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        qBadgeView=new QBadgeView(this).bindTarget(queliao_item).setShowShadow(true)
                .setBadgeGravity(Gravity.END | Gravity.TOP )
                .setGravityOffset(10,0,true)
                .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener(){
                    @Override
                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {

                    }
                }).setBadgeNumber(1);
        initFragments();
        initStatus();
    }





    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    private void initStatus(){
        if (Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            int option =View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 切换Fragment
     *
     * @param lastIndex 上个显示Fragment的索引
     * @param index     需要显示的Fragment的索引
     */
    public void switchFrament(int lastIndex, int index){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastIndex]);
        if (!fragments[index].isAdded()) {
            transaction.add(R.id.fragment_container, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }

    private void initFragments() {
        qwanFragment=new QwanFragment();
        qxueFragment=new QxueFragment();
        qliaoFragment=new QliaoFragment();
        qmineFragment=new QmineFragment();
        fragments = new Fragment[]{qwanFragment,qxueFragment,qliaoFragment,qmineFragment};
        lastShowFragment = 0;
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container,qwanFragment)
                .show(qwanFragment)
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
}

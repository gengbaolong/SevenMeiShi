package com.seven.gengbaolong.sevenmeishi.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;

import com.seven.gengbaolong.sevenmeishi.R;
import com.seven.gengbaolong.sevenmeishi.adapter.FragmentAdapter;
import com.seven.gengbaolong.sevenmeishi.bean.CategoryEntity;
import com.seven.gengbaolong.sevenmeishi.presenter.CategoryPresenter;
import com.seven.gengbaolong.sevenmeishi.presenter.impl.CategoryPresenterImpl;
import com.seven.gengbaolong.sevenmeishi.utils.UiHelper;
import com.seven.gengbaolong.sevenmeishi.view.CategoryView;

public class MainActivity extends BaseCompatActivity implements NavigationView.OnNavigationItemSelectedListener, CategoryView{

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private NavigationView mNavigationView;

    private FragmentAdapter mAdapter;
    private CategoryPresenter mPresenter;
    private MainNavigationHeader mMainNavigationHeader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkNetWork();
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mToolbar.setTitle(R.string.app_name);
//        setSupportActionBar(mToolbar);//不能再加，会报错，因为已经有了
        mTabLayout = (TabLayout) this.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) this.findViewById(R.id.view_pager);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        intNavigationView();

        mAdapter = new FragmentAdapter(getSupportFragmentManager());
        mPresenter = new CategoryPresenterImpl(this);
        mPresenter.getCategory();
    }


    private void intNavigationView() {
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        mMainNavigationHeader = new MainNavigationHeader(this, mNavigationView);
        mMainNavigationHeader.bindData();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        mMainNavigationHeader.bindData();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void showMsgBelowTabLayout(String msg) {
        showPopWindwow(mTabLayout, msg);
    }

    public void showPopWindwow(View parent, String text){
        final PopupWindow popupWindow = UiHelper.createSimplePopupWindow(text, R.color.colorPrimaryDark);
        popupWindow.showAsDropDown(parent);
        taskHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                popupWindow.dismiss();
            }
        },1500);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }


    /**
     * return super.onOptionsItemSelected(item)      对没有处理的事件,交给父类来处理
     *
       return true                                  表示处理完菜单项的事件,不需要将该事件继续传播
     * @param item
     * @return
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //这三个方法是接口CategoryView的方法

    @Override
    public void showError() {
        showMsgInBottom(R.string.msg_error_network);
    }

    @Override
    public void addCategoryInfo(CategoryEntity entity) {
        mAdapter.addItem(entity);
        mTabLayout.addTab(mTabLayout.newTab());
    }

    @Override
    public void bindCategoryData() {
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mAdapter);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

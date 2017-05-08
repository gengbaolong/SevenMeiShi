package com.seven.gengbaolong.sevenmeishi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.seven.gengbaolong.sevenmeishi.bean.CategoryEntity;
import com.seven.gengbaolong.sevenmeishi.fragment.HomeMediasFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gengbaolong on 2017/3/8.
 */

// PagerAdapter    FragmentPagerAdapter    FragmentStatePagerAdapter这三个Adapter都是配合ViewPager+Fragment使用的

public class FragmentAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments = new ArrayList<>();
    private List<CategoryEntity> mCategoryLists = new ArrayList<>();

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mCategoryLists.get(position).getName();
    }

    /**
     * Override this method to save Fragment state
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }


    public void addItem(CategoryEntity entity) {
        mCategoryLists.add(entity);
        mFragments.add(HomeMediasFragment.newInstance(entity.getId(), entity.getType()));
    }
}

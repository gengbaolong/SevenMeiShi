package com.seven.gengbaolong.sevenmeishi.view;

import com.seven.gengbaolong.sevenmeishi.bean.CategoryEntity;

/**
 * Created by gengbaolong on 2017/3/15.
 */

public interface CategoryView {
    void showError();
    void addCategoryInfo(CategoryEntity entity);
    void bindCategoryData();

}

package com.seven.gengbaolong.sevenmeishi.presenter.impl;

import com.seven.gengbaolong.sevenmeishi.bean.CategoryEntity;
import com.seven.gengbaolong.sevenmeishi.model.CategoryModel;
import com.seven.gengbaolong.sevenmeishi.presenter.CategoryPresenter;
import com.seven.gengbaolong.sevenmeishi.view.CategoryView;
import com.squareup.okhttp.Request;

import org.sunger.net.support.okhttp.callback.ResultCallback;

import java.util.List;

/**
 * Created by gengbaolong on 2017/3/15.
 */

public class CategoryPresenterImpl implements CategoryPresenter{

    CategoryModel model;
    CategoryView categoryView;

    public CategoryPresenterImpl(CategoryView categoryView){
        this.categoryView = categoryView;
        model = new CategoryModel();
    }


    @Override
    public void getCategory() {
        model.getCategory(new ResultCallback<List<CategoryEntity>>() {
            @Override
            public void onError(Request request, Exception e) {
                //请求失败
                categoryView.showError();
            }

            @Override
            public void onResponse(List<CategoryEntity> response) {
                //请求成功
                for (CategoryEntity entity:response) {
                    entity.setName(entity.getName().trim().replaceAll("#", ""));
                    categoryView.addCategoryInfo(entity);
                }
                categoryView.bindCategoryData();
            }
        });
    }
}

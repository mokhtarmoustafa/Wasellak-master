package com.unicom.wasalakclientproduct.di.module;

import androidx.recyclerview.widget.DiffUtil;

import com.unicom.wasalakclientproduct.model.branch.Category;
import com.unicom.wasalakclientproduct.model.branch.Product;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.FragmentComponent;
import dagger.hilt.android.scopes.FragmentScoped;

@InstallIn(FragmentComponent.class)
@Module
public class ProductModule {

    @Provides
    @FragmentScoped
    DiffUtil.ItemCallback<Product> provideDiffUtil() {
        return Product.itemCallback;
    }


}

package com.unicom.wasalakclientproduct.di.module;

import androidx.recyclerview.widget.DiffUtil;

import com.unicom.wasalakclientproduct.model.user.MarketPlaceTypeModel;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.FragmentComponent;
import dagger.hilt.android.scopes.FragmentScoped;

@InstallIn(FragmentComponent.class)
@Module
public class MarketsModule {

    @Provides
    @FragmentScoped
    DiffUtil.ItemCallback<MarketPlaceTypeModel.Item> provideDiffUtil(){
        return MarketPlaceTypeModel.Item.itemCallback;
    }

   
}

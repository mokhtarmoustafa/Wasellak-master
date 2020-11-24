package com.unicom.wasalakclientproduct.ui.user;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.adapter.MarketsAdapter;
import com.unicom.wasalakclientproduct.databinding.FragmentMarketsBinding;
import com.unicom.wasalakclientproduct.model.user.MarketPlaceTypeModel;
import com.unicom.wasalakclientproduct.utils.Resource;
import com.unicom.wasalakclientproduct.viewmodel.user.MarketsViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import es.dmoral.toasty.Toasty;

@AndroidEntryPoint
public class MarketsFragment extends Fragment implements MarketsAdapter.ClickListener {
    @Inject
    public MarketsAdapter adapter;
    private NavController navController;
    private FragmentMarketsBinding binding;
    private MarketsViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_markets, container, false);
        View v = binding.getRoot();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (navController == null) {
            navController = Navigation.findNavController(view);
        }


        //data binding to view
        viewModel = new ViewModelProvider(this).get(MarketsViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setFragment(this);
        binding.setViewModel(viewModel);

        initializeRecyclerMarkets();
        initializeSwipeRefresh();
//        initializeSearch();
    }

    private void initializeSearch() {
        binding.etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() == 0)
                    viewModel.getMarketPlaceType(null, null, null, null);
            }
        });
    }


    private void initializeSwipeRefresh() {
        binding.refreshMarkets.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (viewModel.name != null)
                    viewModel.getMarketPlaceType(viewModel.name.getValue(), null, null, null);
                else
                    viewModel.getMarketPlaceType(null, null, null, null);
            }
        });
    }

    //
    private void initializeRecyclerMarkets() {
        adapter.setClickListener(this);

        viewModel.getMarketPlaceTypeLiveData(viewModel.name.getValue(), null, null, null).observe(getViewLifecycleOwner(), new Observer<Resource<MarketPlaceTypeModel>>() {
            @Override
            public void onChanged(Resource<MarketPlaceTypeModel> marketPlaceTypeModelResource) {
                switch (marketPlaceTypeModelResource.status) {
                    case ERROR:
                        binding.progressBar.setVisibility(View.GONE);
                        binding.marketsRecycler.setVisibility(View.GONE);
                        binding.tvEmptyData.setText(getString(R.string.error_msg));
                        binding.tvEmptyData.setVisibility(View.VISIBLE);
                        binding.refreshMarkets.setRefreshing(false);
                        break;
                    case LOADING:
                        binding.progressBar.setVisibility(View.VISIBLE);
                        binding.marketsRecycler.setVisibility(View.GONE);
                        binding.tvEmptyData.setVisibility(View.GONE);
                        binding.refreshMarkets.setRefreshing(false);

                        break;
                    case SUCCESS:
                        binding.progressBar.setVisibility(View.GONE);
                        binding.marketsRecycler.setVisibility(View.VISIBLE);
                        binding.tvEmptyData.setVisibility(View.INVISIBLE);
                        binding.refreshMarkets.setRefreshing(false);

                        if (marketPlaceTypeModelResource.data.getResult() != null && marketPlaceTypeModelResource.data.getResult().getItems().size() > 0) {
                            adapter.submitList(marketPlaceTypeModelResource.data.getResult().getItems());
                        } else {
                            binding.tvEmptyData.setText(getString(R.string.no_data_available));
                            binding.tvEmptyData.setVisibility(View.VISIBLE);
                        }
                        break;

                }
            }
        });
    }



    public void navigateToSearch()
    {
        navController.navigate(R.id.action_marketsFragment_to_searchFragment);
    }
    @Override
    public void clickMarket(int position) {
        MarketsFragmentDirections.ActionMarketsFragmentToVendorFinderFrgament action = MarketsFragmentDirections.actionMarketsFragmentToVendorFinderFrgament();
        int market_type_id = adapter.getCurrentList().get(position).getId();
        action.setMarketTypeId(market_type_id);
        navController.navigate(action);


    }

}
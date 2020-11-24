package com.unicom.wasalakclientproduct.ui.user.addresses;

import android.location.Address;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.FragmentAddressesBinding;
import com.unicom.wasalakclientproduct.databinding.FragmentNewAddressBinding;
import com.unicom.wasalakclientproduct.model.user.address.AddressModelResponse;
import com.unicom.wasalakclientproduct.model.user.address.AddressResponse;
import com.unicom.wasalakclientproduct.model.user.address.Result;
import com.unicom.wasalakclientproduct.ui.category.CategoryProductsFragmentDirections;
import com.unicom.wasalakclientproduct.utils.Resource;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddressesFragment extends Fragment implements AddressAdapter.AddressClickListener {

    //region Members
    private String TAG = this.getClass().getSimpleName();
    private FragmentAddressesBinding binding;
    private AddressViewModel viewModel;
    private NavController navController;
    private AddressAdapter adapter;
    private AddressResponse addressResponse;
    private List<Result> addressData;


// endregion

//region Events


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(AddressViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_addresses, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (navController == null) {
            navController = Navigation.findNavController(view);
        }

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigateUp();
            }
        });


        binding.setLifecycleOwner(this);
        binding.setFragment(this);
        getAllAddresses();

        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllAddresses();
            }
        });


        viewModel.userAddressLiveDataObserver().observe(getViewLifecycleOwner(), new Observer<Resource<AddressModelResponse>>() {
            @Override
            public void onChanged(Resource<AddressModelResponse> addressModelResponseResource) {
                if (addressModelResponseResource.data.getSuccess()) {
                    getAllAddresses();
                }
            }
        });
    }


    @Override
    public void onEditAction(Result address) {
        editAddress(address);
    }


    @Override
    public void onDeleteAction(Result address) {
        deleteAddress(address);
    }


    // endregion


    //region Helper Functions

    private void getAllAddresses() {
        viewModel.getMyAddressesLiveData().observe(getViewLifecycleOwner(), new Observer<Resource<AddressResponse>>() {
            @Override
            public void onChanged(Resource<AddressResponse> addressResponseResource) {
                switch (addressResponseResource.status) {
                    case ERROR:
                        binding.loadingProgress.setVisibility(View.GONE);
                        binding.rvAddresses.setVisibility(View.GONE);
                        binding.tvEmptyData.setVisibility(View.VISIBLE);
                        binding.refresh.setRefreshing(false);
                        break;
                    case LOADING:
                        binding.loadingProgress.setVisibility(View.VISIBLE);
                        binding.rvAddresses.setVisibility(View.GONE);
                        binding.tvEmptyData.setVisibility(View.GONE);
                        binding.refresh.setRefreshing(false);
                        break;
                    case SUCCESS:
                        binding.loadingProgress.setVisibility(View.GONE);
                        binding.rvAddresses.setVisibility(View.VISIBLE);
                        binding.tvEmptyData.setVisibility(View.GONE);
                        binding.refresh.setRefreshing(false);

                        addressData = addressResponseResource.data.getResult();

                        if (addressData != null && addressData.size() > 0) {
                            adapter = new AddressAdapter(Result.itemCallback);
                            adapter.setClickListener(AddressesFragment.this);
                            adapter.submitList(addressData);
                            binding.setAddressAdapter(adapter);
                        } else
                            binding.tvEmptyData.setVisibility(View.VISIBLE);

                        break;


                }
            }
        });


    }

    private void editAddress(Result address) {

//        navController.navigate(R.id.action_addressesFragment_to_newAddressFragment);
        AddressesFragmentDirections.ActionAddressesFragmentToNewAddressFragment action =
                AddressesFragmentDirections.actionAddressesFragmentToNewAddressFragment(address);
        navController.navigate(action);
    }

    private void deleteAddress(Result address) {
        viewModel.removeAddressLiveData(address.getId()).observe(getViewLifecycleOwner(), new Observer<Resource<AddressResponse>>() {
            @Override
            public void onChanged(Resource<AddressResponse> addressResponseResource) {
                Log.d(TAG, "onChanged: " + addressResponseResource.data);
                switch (addressResponseResource.status) {
                    case ERROR:
                        Log.d(TAG, "onChanged: ERROR");
                        binding.loadingProgress.setVisibility(View.GONE);

                        break;
                    case LOADING:
                        binding.loadingProgress.setVisibility(View.GONE);
                        binding.rvAddresses.setVisibility(View.VISIBLE);

                        int position = adapter.getCurrentList().indexOf(address);
//                        adapter.getCurrentList().remove(position);
                        adapter.notifyDataSetChanged();

                        break;
                    case SUCCESS:
                        binding.loadingProgress.setVisibility(View.GONE);
                        binding.rvAddresses.setVisibility(View.VISIBLE);

                        break;


                }
            }
        });

    }

    public void openNewAddressWindow(Result addressData) {

        AddressesFragmentDirections.ActionAddressesFragmentToNewAddressFragment action =
                AddressesFragmentDirections.actionAddressesFragmentToNewAddressFragment(addressData);
        navController.navigate(action);

    }
// endregion
}
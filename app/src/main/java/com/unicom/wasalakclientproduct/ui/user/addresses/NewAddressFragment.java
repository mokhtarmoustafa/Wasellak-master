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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.FragmentNewAddressBinding;
import com.unicom.wasalakclientproduct.model.CityClass;
import com.unicom.wasalakclientproduct.model.CountryClass;
import com.unicom.wasalakclientproduct.model.user.address.AddressModelResponse;
import com.unicom.wasalakclientproduct.model.user.address.Result;
import com.unicom.wasalakclientproduct.model.user.address.area.AreaData;
import com.unicom.wasalakclientproduct.utils.Constants;
import com.unicom.wasalakclientproduct.utils.PreferenceUtils;
import com.unicom.wasalakclientproduct.utils.Resource;


import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import es.dmoral.toasty.Toasty;

@AndroidEntryPoint
public class NewAddressFragment extends Fragment {

    //region Members
    String TAG = this.getClass().getSimpleName();
    private NavController navController;
    private FragmentNewAddressBinding binding;
    private AddressViewModel viewModel;
    private Result addressData;
    private NewAddressFragmentArgs args;
    private PreferenceUtils preference;

    //endregion

    //region Events

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_address, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (navController == null) {
            navController = Navigation.findNavController(view);
        }

        viewModel = new ViewModelProvider(this).get(AddressViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setAddressViewModel(viewModel);
        binding.setFragment(this);
        binding.setEditMode(false);

        args = NewAddressFragmentArgs.fromBundle(getArguments());

        if (args != null && args.getAddressData() != null) {
            args = NewAddressFragmentArgs.fromBundle(getArguments());
            binding.setAddressdata(args.getAddressData());
            binding.setEditMode(true);
            viewModel.loadAddressData(args.getAddressData());

        }


        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigateUp();
            }
        });


        viewModel.getCountriesMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<CountryClass>>() {
            @Override
            public void onChanged(List<CountryClass> countryClassList) {
                ArrayAdapter<CountryClass> adapter = new ArrayAdapter<CountryClass>
                        (getActivity(), android.R.layout.simple_list_item_1, countryClassList);
                binding.edtCountry.setThreshold(1);
                binding.edtCountry.setAdapter(adapter);
                binding.edtCountry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        binding.edtCity.setText("");
                        viewModel.countryId.setValue(((CountryClass) parent.getItemAtPosition(position)).getId());
                        viewModel.retrieveCities();
                    }
                });
            }
        });

        viewModel.getCitiesMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<CityClass>>() {
            @Override
            public void onChanged(List<CityClass> cityClassList) {
                ArrayAdapter<CityClass> adapter = new ArrayAdapter<CityClass>
                        (getActivity(), android.R.layout.simple_list_item_1, cityClassList);
                binding.edtCity.setThreshold(1);
                binding.edtCity.setAdapter(adapter);
                binding.edtCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        binding.edtCity.setText("");
                        viewModel.cityId.setValue(((CityClass) parent.getItemAtPosition(position)).getId());
                        viewModel.retrieveAreas();
                    }
                });
            }
        });


        viewModel.getAreasMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<AreaData>>() {
            @Override
            public void onChanged(List<AreaData> cityClassList) {
                ArrayAdapter<AreaData> adapter = new ArrayAdapter<AreaData>
                        (getActivity(), android.R.layout.simple_list_item_1, cityClassList);
                binding.edtArea.setThreshold(1);
                binding.edtArea.setAdapter(adapter);
                binding.edtArea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        AreaData data = (AreaData) parent.getItemAtPosition(position);
                        viewModel.areaId.setValue(((AreaData) parent.getItemAtPosition(position)).getId());
                    }
                });
            }
        });


        viewModel.getUserMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Result>() {
            @Override
            public void onChanged(Result address) {
                binding.txtAddressName.setError(null);
                binding.txtAddress.setError(null);
                binding.edtCountry.setError(null);
                binding.edtCity.setError(null);
                binding.edtArea.setError(null);
                binding.etBuildingNo.setError(null);
                binding.etFloorNo.setError(null);
                binding.etFlatNo.setError(null);


                if (viewModel.addressName.getValue() == null || viewModel.addressName.getValue().toString().isEmpty()) {
                    binding.txtAddressName.setError(getString(R.string.address_name_required));
                    viewModel.enableButton.setValue(true);

                } else if (viewModel.address.getValue() == null || viewModel.address.getValue().toString().isEmpty()) {
                    binding.txtAddress.setError(getString(R.string.address_required));
                    viewModel.enableButton.setValue(true);
                } else if (viewModel.countryId.getValue() == null || viewModel.countryId.getValue() == 0) {
                    binding.txtAddressCountry.setError(getString(R.string.country_required));
                    viewModel.enableButton.setValue(true);
                } else if (viewModel.cityId.getValue() == null || viewModel.cityId.getValue() == 0) {
                    binding.txtAddressCity.setError(getString(R.string.city_required));
                    viewModel.enableButton.setValue(true);
                } else if (viewModel.areaId.getValue() == null) {
                    binding.txtAddressArea.setError(getString(R.string.area_required));
                    viewModel.enableButton.setValue(true);
                } else if (viewModel.buildingId.getValue() == null) {
                    binding.txtAddressBuildingNo.setError(getString(R.string.building_required));
                    viewModel.enableButton.setValue(true);
                } else if (viewModel.floorId.getValue() == null) {
                    binding.txtAddressFloor.setError(getString(R.string.floor_required));
                    viewModel.enableButton.setValue(true);
                } else if (viewModel.flatId.getValue() == null) {
                    binding.txtAddressFlatNo.setError(getString(R.string.flat_required));
                    viewModel.enableButton.setValue(true);
                } else if (viewModel.mapData.getValue() == null || address.getMapAddress().isEmpty()) {
                    Toasty.error(getActivity(), getString(R.string.address_no_location_detected), Toast.LENGTH_LONG).show();
                    viewModel.enableButton.setValue(true);
                } else {

//                    address.setCountryId(1);
//                    address.setCityId(1);

                    if (binding.getEditMode()) {
                        viewModel.updateAddressLiveData(address);
                    } else {
                        viewModel.createNewAddressLiveData(address);
                    }


                }

            }
        });


        viewModel.userAddressLiveDataObserver().observe(getViewLifecycleOwner(), new Observer<Resource<AddressModelResponse>>() {
            @Override
            public void onChanged(Resource<AddressModelResponse> addressModelResponseResource) {
                navController.navigateUp();
            }
        });

        viewModel.userCurrentLocationLiveDataObserver().observe(getViewLifecycleOwner(), new Observer<Address>() {
            @Override
            public void onChanged(Address addressData) {
                bindAddressDataToView(addressData);
            }
        });


        if (Constants.CURRENT_ADDRESS != null)
            bindAddressDataToView(Constants.CURRENT_ADDRESS);
    }


    //endregion

    //region Helper Functions


    public void openMap() {
        navController.navigate(R.id.action_newAddressFragment_to_locationAddressFragment);
    }

    private void bindAddressDataToView(Address address) {
        String data = address.getAddressLine(0);
        viewModel.mapData.setValue(data);
        viewModel.latitude.setValue(String.valueOf(address.getLatitude()));
        viewModel.longitude.setValue(String.valueOf(address.getLongitude()));
        Constants.CURRENT_ADDRESS = null;
    }


    //endregion
}
package com.unicom.wasalakclientproduct.ui.search;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.slider.Slider;
import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.BottomSheetFilterSearchDailogBinding;
import com.unicom.wasalakclientproduct.model.CountryClass;
import com.unicom.wasalakclientproduct.model.store.Activity;
import com.unicom.wasalakclientproduct.model.store.StoreData;
import com.unicom.wasalakclientproduct.model.store.StoreResponse;
import com.unicom.wasalakclientproduct.ui.vendor.Resource;

import java.util.List;

import dagger.hilt.android.scopes.FragmentScoped;


public class FilterSearchSheetDialog extends BottomSheetDialogFragment {

    @FragmentScoped
    public static final String TAG = "FilterSearchSheetDialog";
    private BottomSheetFilterSearchDailogBinding binding;
    OnFilterListener listener;
    private static final String EXTRA_DIS = "EXTRA_DIS";
    private int storeId;
    private int activityId;
    private Boolean isWithDiscount;
    private SearchViewModel viewModel;

    public static FilterSearchSheetDialog newInstance(int defValue) {

        Bundle args = new Bundle();
        args.putInt(EXTRA_DIS, defValue);
        FilterSearchSheetDialog fragment = new FilterSearchSheetDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.ThemeOverlay_app_BottomSheetDialog);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_filter_search_dailog, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(getActivity()).get(SearchViewModel.class);

        int defaultDistance = getArguments().getInt(EXTRA_DIS, 10);
        binding.indicatorSeekBar.setValue(defaultDistance);

        binding.indicatorSeekBar.setLabelFormatter(new Slider.LabelFormatter() {
            @NonNull
            @Override
            public String getFormattedValue(float value) {
                return String.format(getString(R.string.distance_placeholder_without_fraction), (int) value);
            }
        });

        binding.switchShow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isWithDiscount = isChecked;
            }
        });
        binding.btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onFilterClicked((int) binding.indicatorSeekBar.getValue(), storeId, activityId, isWithDiscount);
                dismiss();
            }
        });
        binding.ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        binding.ivReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetData();
            }
        });


        getAllStores();

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        bottomSheetDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface currentDialog) {
                BottomSheetDialog dialog = (BottomSheetDialog) currentDialog;
                FrameLayout bottomSheet = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
                BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
                BottomSheetBehavior.from(bottomSheet).setSkipCollapsed(true);
                BottomSheetBehavior.from(bottomSheet).setHideable(true);
            }
        });
        return bottomSheetDialog;
    }

    public void setListener(OnFilterListener listener) {
        this.listener = listener;
    }

    interface OnFilterListener {
        void onFilterClicked(int distance, int storeId, int activityId, Boolean isWithDiscount);
    }

    private void resetData() {
        binding.indicatorSeekBar.setValue(0);
        binding.edtStore.setText("");
        binding.edtActivity.setText("");
        binding.switchShow.setChecked(false);
    }


    private void getAllStores() {
        viewModel.observerAllStores().observe(getViewLifecycleOwner(), new Observer<Resource<StoreResponse>>() {
            @Override
            public void onChanged(Resource<StoreResponse> storeResponseResource) {

                switch (storeResponseResource.status) {
                    case ERROR:
                        break;
                    case LOADING:
                        break;
                    case SUCCESS:
                        if (storeResponseResource.data != null) {
                            getStoreData(storeResponseResource);
                            getStoreActivities(storeResponseResource.data.getStoreList().get(0));
                            break;
                        }
                }


            }
        });
    }

    private void getStoreActivities(StoreData storeData) {
        List<Activity> activityData = storeData.getActivities();
        ArrayAdapter<Activity> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, activityData);

        binding.edtActivity.setThreshold(1);
        binding.edtActivity.setAdapter(adapter);
        binding.edtActivity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                activityId = activityData.get(position).getId();
            }
        });


    }

    private void getStoreData(Resource<StoreResponse> storeResponseResource) {
        List<StoreData> storeList = storeResponseResource.data.getStoreList();
        ArrayAdapter<StoreData> adapter = new ArrayAdapter<StoreData>
                (getActivity(), android.R.layout.simple_list_item_1, storeList);
        binding.edtStore.setThreshold(1);
        binding.edtStore.setAdapter(adapter);
        binding.edtStore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                storeId = storeList.get(position).getId();
                getStoreActivities(storeResponseResource.data.getStoreList().get(position));
            }
        });
    }


}
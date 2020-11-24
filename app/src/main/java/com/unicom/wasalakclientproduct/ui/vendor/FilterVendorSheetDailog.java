package com.unicom.wasalakclientproduct.ui.vendor;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.slider.Slider;
import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.BottomSheetFilterVendorDailogBinding;

public class FilterVendorSheetDailog extends BottomSheetDialogFragment {

    public static final String TAG = "FilterVendorSheetDailog";
    private BottomSheetFilterVendorDailogBinding binding;
    OnFilterListener listener;
    private static final String EXTRA_DIS = "EXTRA_DIS";

    public static FilterVendorSheetDailog newInstance(int defValue) {

        Bundle args = new Bundle();
        args.putInt(EXTRA_DIS, defValue);
        FilterVendorSheetDailog fragment = new FilterVendorSheetDailog();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_filter_vendor_dailog, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        int defDistnace = getArguments().getInt(EXTRA_DIS, 10);
        binding.indicatorSeekBar.setValue(defDistnace);

        binding.indicatorSeekBar.setLabelFormatter(new Slider.LabelFormatter() {
            @NonNull
            @Override
            public String getFormattedValue(float value) {
                return String.format(getString(R.string.distance_placeholder_without_fraction), (int)value);
            }
        });
        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onFilterByDistance((int) binding.indicatorSeekBar.getValue());
                }
                dismiss();
            }
        });
        binding.closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        binding.resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.indicatorSeekBar.setValue(0);
            }
        });


    }


    public void setListener(OnFilterListener listener) {
        this.listener = listener;
    }

    interface OnFilterListener {
        void onFilterByDistance(int distance);
    }

}
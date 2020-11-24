package com.unicom.wasalakclientproduct.ui.termsandconditions;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.FragmentCartBinding;
import com.unicom.wasalakclientproduct.databinding.FragmentTermsAndConditionsBinding;
import com.unicom.wasalakclientproduct.ui.cart.CartViewModel;
import com.unicom.wasalakclientproduct.ui.cart.ProductCartAdapter;


public class TermsAndConditionsFragment extends Fragment {

    //region Members
    String TAG = this.getClass().getSimpleName();
    FragmentTermsAndConditionsBinding binding;
    CartViewModel viewModel;
    private NavController navController;
    private ProductCartAdapter adapter;

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_terms_and_conditions, container, false);
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

    }
}
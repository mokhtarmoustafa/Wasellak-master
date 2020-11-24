package com.unicom.wasalakclientproduct.ui.branchDetails;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.CategoryMenuDialogBinding;
import com.unicom.wasalakclientproduct.model.branch.Category;
import com.unicom.wasalakclientproduct.model.branch.Result;
import com.unicom.wasalakclientproduct.model.category.Product;
import com.unicom.wasalakclientproduct.ui.category.CategoryAdapter;
import com.unicom.wasalakclientproduct.ui.product_list.ProductVendorListFragment;
import com.unicom.wasalakclientproduct.ui.vendor.VendorFinderFrgament;
import com.unicom.wasalakclientproduct.utils.Constants;

public class CategoryMenuDialog extends BottomSheetDialogFragment implements CategoryMenuAdapter.CategoryClickListener {

    //region Members
    public final String TAG = this.getClass().getSimpleName();
    private CategoryMenuDialogBinding binding;
    ICategoryClicked listener;
    public Result result = new Result();
    CategoryMenuAdapter categoryAdapter;
    //endregion
//region  Events

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.ThemeOverlay_app_BottomSheetDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.category_menu_dialog, container, false);

//        binding.rvCategoryMenu.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                v.getParent().requestDisallowInterceptTouchEvent(true);
//                v.onTouchEvent(event);
//                return true;
//            }
//        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        categoryAdapter = new CategoryMenuAdapter(Category.itemCallback);
        Log.d(TAG, "N: $categoryResponseResource");
        result = Constants.RESULT_DATA;
        if (result.getCategories().size() > 0) {
            categoryAdapter.setCategoryClickListener(this::onFilterCategoryClicked);
            categoryAdapter.submitList(result.getCategories());
            binding.setCategoryAdapter(categoryAdapter);

        }
    }


    //endregion

    //region Helper Functions


    public void setListener(ICategoryClicked listener) {
        this.listener = listener;
    }

    public interface ICategoryClicked {
        void onCategorySelected(Category category);
    }

    @Override
    public void onFilterCategoryClicked(Category category) {
        listener.onCategorySelected(category);
        this.dismiss();
    }


// endregion

}

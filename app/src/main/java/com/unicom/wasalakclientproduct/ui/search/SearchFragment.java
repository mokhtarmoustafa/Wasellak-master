package com.unicom.wasalakclientproduct.ui.search;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.FragmentSearchBinding;
import com.unicom.wasalakclientproduct.model.search.Search;
import com.unicom.wasalakclientproduct.utils.KeyboardUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import es.dmoral.toasty.Toasty;


@AndroidEntryPoint
public class SearchFragment extends Fragment implements SearchAdapter.ClickListener {

    //region Members
    String TAG = this.getClass().getSimpleName();
    @Inject
    public SearchAdapter adapter;
    @Inject
    public KeyboardUtils keyboardUtils;
    private NavController navController;
    SearchViewModel viewModel;
    FragmentSearchBinding binding;

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view);
        getSavedSearchList();


    }


    @Override
    public void clickSearch(Search searchData) {
        navigateToSearchResult(searchData);
    }


    //endregion


    //region Helper Functions

    private void init(View view) {
        Log.d(TAG, "onViewCreated: ");
        if (navController == null) {
            navController = Navigation.findNavController(view);
        }

//        InputMethodManager imgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//        imgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        keyboardUtils.showKeyboard();

        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setFragment(this);
        binding.setViewModel(viewModel);

        binding.topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigateUp();
            }
        });

        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.refresh.setRefreshing(false);
            }
        });

        binding.toolbarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigateUp();
            }
        });
    }

    private void getSavedSearchList() {
        viewModel.ObserveSavedSearchListData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Search>>() {
            @Override
            public void onChanged(ArrayList<Search> searches) {
                binding.progressBar.setVisibility(View.GONE);
                if (searches != null) {
                    adapter.submitList(searches);
                    adapter.setClickListener(SearchFragment.this::clickSearch);
                    binding.clClearSearch.setVisibility(View.VISIBLE);
                    binding.tvEmptyData.setVisibility(View.GONE);
                } else {
                    binding.clClearSearch.setVisibility(View.GONE);
                    binding.tvEmptyData.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void showRemoveSearchDataDialog() {
        new MaterialAlertDialogBuilder(getActivity(), R.style.material_theme_rounded)
                .setTitle(getString(R.string.clear_search_title))
                .setMessage(R.string.clear_search_message)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(R.string.confirm_clear, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        clearSearchResult();
                    }
                })
                .show();
    }

    private void clearSearchResult() {
        viewModel.observeClearSearchListData().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean done) {
                if (done) {
                    adapter.submitList(null);
                    binding.clClearSearch.setVisibility(View.GONE);
                    binding.tvEmptyData.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    private void navigateToSearchResult(Search searchData) {

        SearchFragmentDirections.ActionSearchFragmentToSearchResultFragment action =
                SearchFragmentDirections.actionSearchFragmentToSearchResultFragment(searchData);
        navController.navigate(action);
        binding.etSearch.setText(null);

    }

    public void search(String search) {
        if (search != null && !search.trim().isEmpty()) {
            Search newSearch = new Search(search);
            viewModel.observeSaveToSearchListData(newSearch).observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    navigateToSearchResult(newSearch);
                }
            });
        } else
            Toasty.info(requireContext(), R.string.error_search).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        keyboardUtils.hideKeyboard();
    }


    //endregion
}
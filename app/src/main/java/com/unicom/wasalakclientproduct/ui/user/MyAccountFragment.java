package com.unicom.wasalakclientproduct.ui.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.FragmentMyAccountBinding;
import com.unicom.wasalakclientproduct.model.GlideApp;
import com.unicom.wasalakclientproduct.model.StructueMode;
import com.unicom.wasalakclientproduct.model.user.AccountModel;
import com.unicom.wasalakclientproduct.model.user.Language;
import com.unicom.wasalakclientproduct.model.user.UpdateProfileDTO;
import com.unicom.wasalakclientproduct.ui.branchDetails.CategoryMenuDialog;
import com.unicom.wasalakclientproduct.ui.guest.GuestActivity;
import com.unicom.wasalakclientproduct.ui.user.language.ChooseLanguageBottomSheet;
import com.unicom.wasalakclientproduct.ui.user.language.LanguageAdapter;
import com.unicom.wasalakclientproduct.utils.ChangeLang;
import com.unicom.wasalakclientproduct.utils.Constants;
import com.unicom.wasalakclientproduct.utils.PreferenceUtils;
import com.unicom.wasalakclientproduct.utils.Resource;
import com.unicom.wasalakclientproduct.viewmodel.user.MyAccountViewModel;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.PublishSubject;

@AndroidEntryPoint
public class MyAccountFragment extends Fragment implements LanguageAdapter.ILanguage {
    private FragmentMyAccountBinding binding;
    private NavController navController;
    private MyAccountViewModel myAccountViewModel;
    private ChooseLanguageBottomSheet chooseLanguageBottomSheet;


    @Inject
    public PreferenceUtils preference;
    private PublishSubject<Boolean> backButtonClickSource = PublishSubject.create();
    private static final long EXIT_TIMEOUT = 2000;
    private CircularProgressDrawable circularProgressDrawable;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //data binding to view
        myAccountViewModel = new ViewModelProvider(this).get(MyAccountViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_account, container, false);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (navController == null) {
            navController = Navigation.findNavController(view);
        }
        ((UserActivity) getActivity()).binding.bottomNavigation.setVisibility(View.VISIBLE);

        binding.newOrderToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigateUp();
            }
        });


        binding.setLifecycleOwner(this);
        binding.setFragment(this);
        binding.setViewModel(myAccountViewModel);
        checkSwitchChange();
        getCurrentLanguage();

        myAccountViewModel.getAccountMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Resource<AccountModel>>() {
            @Override
            public void onChanged(Resource<AccountModel> accountModelResource) {
                switch (accountModelResource.status) {
                    case ERROR:
                        binding.progressBar.setVisibility(View.GONE);
                        break;

                    case LOADING:
                        binding.progressBar.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:

                        Log.d("onChanged", "onChanged: " + "http://192.168.50.236:4101/api" + accountModelResource.data.getResult().getUserImage());
                        binding.setAccount(accountModelResource.data.getResult());
                        GlideApp.with(MyAccountFragment.this)
                                .load("http://eg-unicom.dyndns.org:4100/api" + accountModelResource.data.getResult().getUserImage())
                                .placeholder(R.drawable.ic_wasellak_logo_color)
                                .into(binding.profileImage);
                        binding.progressBar.setVisibility(View.GONE);

                        break;
                }
            }
        });


        if (preference.getUserData() != null && preference.getUserData().getResult() != null)
            binding.setAccount(preference.getUserData().getResult());


    }

    @Override
    public void onResume() {
        super.onResume();
        checkSwitchChange();
    }

    @Override
    public void onSelectLanguage(Language language) {
        myAccountViewModel.getLang().setValue(language.getLangValue());
        myAccountViewModel.langService(language.getLangService());
        myAccountViewModel.getLangMutableLiveData().observe(getViewLifecycleOwner(), new Observer<StructueMode>() {
            @Override
            public void onChanged(StructueMode structueMode) {
//                preference.saveLang(language.getLangValue());
                ChangeLang.setNewLocale(getActivity(), myAccountViewModel.getLang().getValue());
                startActivity(new Intent(getActivity(), UserActivity.class));
                getActivity().overridePendingTransition(R.anim.animate_zoom_enter,
                        R.anim.animate_zoom_exit);
                getActivity().finish();
            }
        });
    }

    private boolean isCheck;

    private void checkSwitchChange() {
//        binding.switchNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                isCheck = preference.getNotification();
//                if (isChecked == isCheck) {
//                    return;
//                }
//                myAccountViewModel.notification.setValue(isChecked);
//                myAccountViewModel.publishSubject.onNext("null");
//
//            }
//        });
    }

    private void getCurrentLanguage() {
        String language = preference.getLang();
        if (language != null && !language.isEmpty()) {
            if (preference.getLang().equals("en"))
                binding.tvCurrentLanguage.setText(getString(R.string.english));
            else
                binding.tvCurrentLanguage.setText(getString(R.string.arabic));
        }

    }

    public void onClickLogout() {

        showLogoutDialog();
    }

    public void clickChangePass() {
        navController.navigate(R.id.action_myAccountFragment_to_changePasswordFragment);
    }

    public void clickMyOrders() {
        navController.navigate(R.id.action_myAccountFragment_to_changePasswordFragment);
    }

    public void clickAddresses() {
        navController.navigate(R.id.action_myAccountFragment_to_addressesFragment);
    }

    public void clickQuestionsAndInquiries() {
        navController.navigate(R.id.action_myAccountFragment_to_questionsFragment);
    }

    public void clickTermsAndConditions() {
        navController.navigate(R.id.action_myAccountFragment_to_termsAndConditionsFragment);
    }


    public void clickViewProfile() {
        AccountModel.Result data = null;
        if (myAccountViewModel.getAccountMutableLiveData().getValue().data != null &&
                myAccountViewModel.getAccountMutableLiveData().getValue().data.getResult() != null)
            data = myAccountViewModel.getAccountMutableLiveData().getValue().data.getResult();
        else if (preference.getUserData().getResult() != null)
            data = preference.getUserData().getResult();

        MyAccountFragmentDirections.ActionMyAccountFragmentToProfileFragment toProfileFragment =
                MyAccountFragmentDirections.actionMyAccountFragmentToProfileFragment(data);
        navController.navigate(toProfileFragment);
    }


    public void onClickLanguage() {


        chooseLanguageBottomSheet = new ChooseLanguageBottomSheet();
        chooseLanguageBottomSheet.setListener(this::onSelectLanguage);
        chooseLanguageBottomSheet.show(requireActivity().getSupportFragmentManager(), chooseLanguageBottomSheet.getTag());
    }

    private void setSnackBar() {
//        Snackbar snackbar = Snackbar
//                .make(binding.coordinate, R.string.once_again_to_exit, Snackbar.LENGTH_LONG);
//        snackbar.setTextColor(Color.GREEN);
//        snackbar.show();
    }

    private Disposable observeBackButton() {
        return backButtonClickSource
                .debounce(100, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(__ -> setSnackBar())
                .timeInterval(TimeUnit.MILLISECONDS)
                .skip(1)
                .filter(interval -> interval.time() < EXIT_TIMEOUT)
                .subscribe(__ -> getActivity().finish());
    }


    private void showLogoutDialog() {
        new MaterialAlertDialogBuilder(getActivity(), R.style.material_theme_rounded)
                .setTitle(getString(R.string.logout_hint))
                .setMessage(R.string.sure_logout)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton(R.string.logout, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        preference.saveTokenUser("");
                        preference.setCartCount(0);
                        startActivity(new Intent(getActivity(), GuestActivity.class));
                        getActivity().overridePendingTransition(R.anim.animate_diagonal_right_enter,
                                R.anim.animate_diagonal_right_exit);
                        getActivity().finish();
                    }
                })
                .show();


    }


}
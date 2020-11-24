package com.unicom.wasalakclientproduct.ui.user.language;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.BottomSheetChooseLanguageBinding;
import com.unicom.wasalakclientproduct.model.user.Language;

import java.util.ArrayList;

public class ChooseLanguageBottomSheet extends BottomSheetDialogFragment implements LanguageAdapter.ILanguage {

    //region Members
    private final String TAG = this.getClass().getSimpleName();
    private BottomSheetChooseLanguageBinding binding;
    private LanguageAdapter languageAdapter;
    private ArrayList<Language> languageList;
    private ILanguage iLanguage;
// endregion


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.ThemeOverlay_app_BottomSheetDialog);
    }

    public void setListener(ILanguage iLanguage) {
        this.iLanguage = iLanguage;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_choose_language, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        loadLanguage();
    }

    @Override
    public void onSelectLanguage(Language language) {
        iLanguage.onSelectLanguage(language);
        this.dismiss();
    }


    //region Events
    //endregion

    public interface ILanguage {
        void onSelectLanguage(Language language);
    }


    private void loadLanguage() {
        languageList = new ArrayList<Language>();
        languageList.add(new Language(0, getString(R.string.arabic), "ar", "ar-EG"));
        languageList.add(new Language(1, getString(R.string.english), "en", "en"));
        languageAdapter = new LanguageAdapter(Language.itemCallback);
        languageAdapter.setClickListener(this::onSelectLanguage);
        languageAdapter.submitList(languageList);
        binding.setLanguageAdapter(languageAdapter);


    }
}

package com.unicom.wasalakclientproduct.ui.user.language;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.LanguageRowBinding;
import com.unicom.wasalakclientproduct.databinding.LayoutCategoryFilterBinding;
import com.unicom.wasalakclientproduct.model.user.Language;
import com.unicom.wasalakclientproduct.ui.branchDetails.CategoryMenuAdapter;

import dagger.hilt.android.qualifiers.ActivityContext;

public class LanguageAdapter extends ListAdapter<Language, RecyclerView.ViewHolder> {

    //region Members
    private ILanguage iLanguage;
    LayoutInflater layoutInflater;
    @ActivityContext
    Context context;
    Language language;
    //endregion

    //region Events
    protected LanguageAdapter(@NonNull DiffUtil.ItemCallback<Language> diffCallback) {
        super(diffCallback);
    }

    public void setClickListener(ILanguage iLanguage) {
        this.iLanguage = iLanguage;
    }

    @NonNull
    @Override
    public LanguageAdapter.LanguageAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        LanguageRowBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.language_row, parent, false);
        return new LanguageAdapter.LanguageAdapterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        language = getItem(position);

//        if (position % 2 == 1) {
//            holder.itemView.setBackgroundColor(Color.parseColor("#1A00D472"));
//        }
        LanguageAdapter.LanguageAdapterViewHolder data = ((LanguageAdapter.LanguageAdapterViewHolder) holder);
        data.binding.setLanguage(language);
        data.binding.executePendingBindings();
    }

    class LanguageAdapterViewHolder extends RecyclerView.ViewHolder {
        private final LanguageRowBinding binding;

        public LanguageAdapterViewHolder(final LanguageRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.clParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Language language = getItem(getAdapterPosition());
                    iLanguage.onSelectLanguage(language);
                }
            });
        }


    }

    //endregion
    public interface ILanguage {
        void onSelectLanguage(Language language);
    }
}

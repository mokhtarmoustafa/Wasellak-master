package com.unicom.wasalakclientproduct.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.LayoutSearchListRowBinding;
import com.unicom.wasalakclientproduct.model.search.Search;
import javax.inject.Inject;

public class SearchAdapter extends ListAdapter<Search, SearchAdapter.SearchViewHolder> {
    private SearchAdapter.ClickListener clickListener;
    LayoutInflater layoutInflater;

    @Inject
    protected SearchAdapter() {
        super(Search.itemCallback);
    }

    public void setClickListener(SearchAdapter.ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        LayoutSearchListRowBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.layout_search_list_row, parent, false);
        return new SearchAdapter.SearchViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder holder, int position) {
       Search search = getItem(position);
        holder.binding.setSearchData(search);
        holder.binding.executePendingBindings();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {
        private final LayoutSearchListRowBinding binding;

        public SearchViewHolder(final LayoutSearchListRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.clParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Search searchData= getItem(getAdapterPosition());
                    clickListener.clickSearch(searchData);
                }
            });
        }

    }

    public interface ClickListener {
        void clickSearch(Search searchData);
    }
}
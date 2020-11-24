package com.unicom.wasalakclientproduct.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.unicom.wasalakclientproduct.model.CountryClass;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter implements Filterable {

    private List<CountryClass> itemsModelsl;
    private List<CountryClass> itemsModelListFiltered;


    public CustomAdapter(Context context, int resource, List<CountryClass> itemsModelsl) {
        this.itemsModelsl = itemsModelsl;
        this.itemsModelListFiltered = itemsModelsl;

    }


    @Override
    public int getCount() {
        return itemsModelListFiltered.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsModelListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
        }


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }


    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    filterResults.count = itemsModelsl.size();
                    filterResults.values = itemsModelsl;

                } else {
                    List<CountryClass> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for (CountryClass itemsModel : itemsModelsl) {
                        if (itemsModel.getName().contains(searchStr)) {
                            resultsModel.add(itemsModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }


                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                itemsModelListFiltered = (List<CountryClass>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}

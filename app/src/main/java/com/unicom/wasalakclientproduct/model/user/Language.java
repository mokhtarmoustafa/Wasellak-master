package com.unicom.wasalakclientproduct.model.user;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;


public class Language {
    private int id;
    private String name;
    private String langValue;
    private String langService;

    public Language() {
    }

    public Language(int id, String name, String langValue, String langService) {
        this.id = id;
        this.name = name;
        this.langValue = langValue;
        this.langService = langService;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLangValue() {
        return langValue;
    }

    public void setLangValue(String langValue) {
        this.langValue = langValue;
    }

    public String getLangService() {
        return langService;
    }

    public void setLangService(String langService) {
        this.langService = langService;
    }

    public static DiffUtil.ItemCallback<Language> itemCallback = new DiffUtil.ItemCallback<Language>() {
        @Override
        public boolean areItemsTheSame(@NonNull Language oldItem, @NonNull Language newItem) {
            return oldItem.getName().equals(newItem.getName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Language oldItem, @NonNull Language newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language that = (Language) o;
        return name.equals(that.name);
    }

}



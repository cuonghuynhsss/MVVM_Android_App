package com.cuonghuynh.mvvmapp.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories_table")
public class Category extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "categories_name")
    private String categoriesName;

    @ColumnInfo(name = "categories_description")
    private String categoriesDescription;

    @Ignore
    public Category(){

    }
    @Bindable
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }
    @Bindable
    public String getCategoriesName() {
        return categoriesName;
    }
    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
        notifyPropertyChanged(BR.id);
    }
    @Bindable
    public String getCategoriesDescription() {
        return categoriesDescription;
    }

    public void setCategoriesDescription(String categoriesDescription) {
        this.categoriesDescription = categoriesDescription;
        notifyPropertyChanged(BR.id);
    }

    public Category(int id, String categoriesName, String categoriesDescription) {
        this.id = id;
        this.categoriesName = categoriesName;
        this.categoriesDescription = categoriesDescription;
    }

    @Override
    public String toString() {
        return this.categoriesName;
    }
}

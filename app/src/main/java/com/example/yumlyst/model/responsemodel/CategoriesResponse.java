package com.example.yumlyst.model.responsemodel;

import com.example.yumlyst.model.CategoryDTO;

import java.util.List;

public class CategoriesResponse {

    private List<CategoryDTO> categories;

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }
}

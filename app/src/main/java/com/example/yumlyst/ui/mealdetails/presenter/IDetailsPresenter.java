package com.example.yumlyst.ui.mealdetails.presenter;

import com.example.yumlyst.model.LocalDTO;

public interface IDetailsPresenter {

    void getMealDetails(String id);

    void insert(LocalDTO localDTO );


}

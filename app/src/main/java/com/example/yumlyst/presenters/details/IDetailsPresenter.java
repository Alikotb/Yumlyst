package com.example.yumlyst.presenters.details;

import com.example.yumlyst.model.LocalDTO;

public interface IDetailsPresenter {

    void getMealDetails(String id);

    void insert(LocalDTO localDTO );


}

package com.example.developer01.desko;

/**
 * Created by developer01 on 18/12/2017.
 */

public class BasePresenter<T extends  BasePresenter.ViewInterface> {

    public T mView;

    public BasePresenter(){

    }

    public void onAttach(T view){
        mView = view;
    }

    // called when the view should be detached from the presenter
    public void onDetach(){
        mView = null;
    }

    // Interface for the View in MVP
    public interface ViewInterface{

    }
}

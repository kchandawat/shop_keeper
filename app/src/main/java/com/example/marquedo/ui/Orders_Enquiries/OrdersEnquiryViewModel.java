package com.example.marquedo.ui.Orders_Enquiries;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OrdersEnquiryViewModel extends ViewModel
{
    private MutableLiveData<String> mText;

        public OrdersEnquiryViewModel() {
            mText = new MutableLiveData<>();
            mText.setValue("This is Orders fragment");
        }

        public LiveData<String> getText() {
            return mText;
        }
}
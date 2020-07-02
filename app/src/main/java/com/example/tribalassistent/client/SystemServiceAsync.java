package com.example.tribalassistent.client;

import com.example.tribalassistent.data.model.system.Identified;

public interface SystemServiceAsync {
    void identify(AsyncCallback<Identified> async);
}

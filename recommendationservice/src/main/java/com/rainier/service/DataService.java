package com.rainier.service;

import com.rainier.tool.Result;

/**
 * Created by Barcke on 2018/7/30.
 */
public interface DataService {
    Result uploadUserInformation();

    Result uploadUserAction();

    Result uploadItem();
}

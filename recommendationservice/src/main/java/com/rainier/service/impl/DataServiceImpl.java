package com.rainier.service.impl;

import com.rainier.service.DataService;
import com.rainier.tool.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Barcke on 2018/7/30.
 */
@Service
@Transactional
public class DataServiceImpl implements DataService {

    @Override
    public Result uploadUserInformation() {
        return null;
    }

    @Override
    public Result uploadUserAction() {
        return null;
    }

    @Override
    public Result uploadItem() {
        return null;
    }
}

package com.rainier.client;

import com.rainier.tool.AppAccessToken;
import com.rainier.tool.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by Barcke on 2018/7/30.
 */
@FeignClient("appService")
public interface AppService {

    @RequestMapping(value="/getAccessToken",method= RequestMethod.POST, consumes = "application/json")
    Result<String> getAccessToken(@RequestBody Map<String, String> jsonData);

    @RequestMapping(value="/checkAccessToken",method= RequestMethod.POST, consumes = "application/json")
    Result<AppAccessToken> checkAccessToken(@RequestBody Map<String, String> jsonData);
}

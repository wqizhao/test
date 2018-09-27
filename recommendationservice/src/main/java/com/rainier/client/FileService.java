package com.rainier.client;

import com.rainier.tool.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author qdzwq
 * @ProjectName seedindustry
 * @Title: FileService
 * @Description: TODO
 * @Modified qdzwq
 * @date 2018/9/419:41
 */
@Component
@FeignClient(name = "fileService")

public interface FileService {
    @RequestMapping(value = "/file/upload",method = RequestMethod.POST)
    Result upload(HttpServletRequest request);
}

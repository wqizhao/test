package com.rainier.tool;

import java.util.List;

/**
 * @author qdzwq
 * @ProjectName seedindustry
 * @Title: pageUtil
 * @Description: TODO
 * @Modified qdzwq
 * @date 2018/8/2515:35
 */
public class PageUtil {
    /**
     * 分页
     * @param list 分页数据
     * @param pageSize 每页显示条数
     * @param pageIndex 当前页码
     * @return
     */
    public static <T> PageBean<T> listByPage(List<T> list, Integer pageSize, Integer pageIndex) {
        //判断集合数据
        if (list == null || list.size() == 0) {
            return null;
        }

        //pageIndex默认为1
        if (pageIndex == null) {
            pageIndex = 1;
        }

        // 总记录数
        int totalCount = list.size();

        // 总页数
        int totalPages = 0;
        if (totalCount % pageSize == 0) {
            totalPages = totalCount / pageSize;
        } else {
            totalPages = totalCount / pageSize + 1;
        }

        //当前页码最大值
        if (pageIndex > totalPages) {
            pageIndex = totalPages;
        }

        // 分页起始
        int start = pageSize * (pageIndex - 1);
        // 分页终止
        int end = pageSize * pageIndex;
        if (end > totalCount) {
            end = totalCount;
        }

        //分页
        list = list.subList(start, end);

        PageBean<T> pageBean = new PageBean<>();
        pageBean.setList(list);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPages(totalPages);

        return pageBean;
    }
}

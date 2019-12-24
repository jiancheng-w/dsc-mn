package com.smil.dcs.common;

import com.github.pagehelper.PageInfo;

import java.util.List;

public class PageUtils {
    /**
     * 将指定source的分页信息应用到target数据上
     * <p>
     *     使用该方法应注意target数据本身逻辑上的分页数据与source的分页数据相同
     * </p>
     * @param source 源分页信息数据对象
     * @param target 目标数据源
     * @param <T> 源数据类型
     * @param <U> 目标数据类型
     * @return 转换后的带分页信息的PageInfo对象
     */
    public static <T, U> PageInfo<U> convertPageObject(PageInfo<T> source, List<U> target) {
        PageInfo<U> newPage = new PageInfo<U>();

        newPage.setPageNum(source.getPageNum());
        newPage.setPageSize(source.getPageSize());
        newPage.setStartRow(source.getStartRow());
        newPage.setEndRow(source.getEndRow());
        newPage.setTotal(source.getTotal());
        newPage.setPages(source.getPages());
        newPage.setIsFirstPage(source.isIsFirstPage());
        newPage.setNavigateFirstPage(source.getNavigateFirstPage());
        newPage.setIsLastPage(source.isIsLastPage());
        newPage.setNavigateLastPage(source.getNavigateLastPage());
        newPage.setHasPreviousPage(source.isHasPreviousPage());
        newPage.setHasNextPage(source.isHasNextPage());
        newPage.setNavigatePages(source.getNavigatePages());
        newPage.setNavigatepageNums(source.getNavigatepageNums());

        newPage.setList(target);

        return newPage;
    }
}

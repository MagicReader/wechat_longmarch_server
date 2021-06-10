package com.cyou.wechat_longmarch_server.entity;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
定义了一个静态方法restPage,因为类的定义里面用了范型< T >,返回值是CommonPage< T >,
然后该方法又需要被定义成static, 所以需要再将该方法指定成T类型的范型,
分页功能借助了工具类pageinfo,将数据传到pageInfo里，各个分页属性就会被封装好,
然后再将pageInfo里的各个属性转移到我们自定义的CommonPage类里.
*/

@Data
@ToString
public class CommonPage<T>{
    /**
     * 页码，第几页
     */
    private Integer pageNum;
    /**
     * 页面大小，一页几条数据
     */
    private Integer pageSize;
    /**
     * 总页数，一共有多少页
     */
    private Integer totalPage;
    /**
     * 条数，一共有多少条数据
     */
    private Long total;
    /**
     * 分页数据
     */
    private List<T> list;

    public static <T> CommonPage<T> restPage(List<T> list){
        CommonPage<T> result = new CommonPage<>();
        PageInfo<T> pageInfo = new PageInfo<>(list);
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotalPage(pageInfo.getPages());
        result.setTotal(pageInfo.getTotal());
        result.setList(pageInfo.getList());
        return result;
    }

}

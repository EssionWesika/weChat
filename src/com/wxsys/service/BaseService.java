package com.wxsys.service;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Service接口 - Service接口基类
 */

public interface BaseService<T, PK extends Serializable> {

	/**
     * 根据ID获取实体对象.
     *
     * @param id 记录ID
     * @return 实体对象
     */
    public T get(PK id);

    /**
     * 根据ID数组获取实体对象集合.
     *
     * @param ids ID对象数组
     * @return 实体对象集合
     */
    public List<T> get(PK[] ids);

    /**
     * 根据属性名和属性值获取实体对象.
     *
     * @param propertyName 属性名称
     * @param value        属性值
     * @return 实体对象
     */
    public T get(String propertyName, Object value);

    /**
     * 根据属性名和属性值获取实体对象集合.
     *
     * @param propertyName 属性名称
     * @param value        属性值
     * @return 实体对象集合
     */
    public List<T> getList(String propertyName, Object value);


    /**
     * 根据属性名判断数据是否已存在.
     *
     * @param propertyName 属性名称
     * @param value        值
     * @return boolean
     */
    public boolean isExist(String propertyName, Object value);

    /**
     * 保存实体对象.
     *
     * @param entity 对象
     * @return ID
     */
    public PK save(T entity);

    /**
     * 更新实体对象.
     *
     * @param entity 对象
     */
    public void update(T entity);

    /**
     * 删除实体对象.
     *
     * @param entity 对象
     * @return
     */
    public void delete(T entity);

    /**
     * 根据ID删除实体对象.
     *
     * @param id 记录ID
     */
    public void delete(PK id);

    /**
     * 根据ID数组删除实体对象.
     *
     * @param ids ID数组
     */
    public void delete(PK[] ids);
    /**
     * 获得该类所有数据
     * @return
     */
    List<T> getList();
    /**
     * 将查询参数封装成map，同时传入所需要的分页数据,若不需要获取总数，则查询效率可以提高一倍
     * @param map
     * @param begin 分页起始处
     * @param max 一次分页搜索数量
     * @param getCount 是否需要获取该查询条件下所有记录总数
     * @return list<T>,key1:entityList,key2:pageCount
     */
    Map<String, Object> getList(Map<String, Object> map,Integer begin,Integer max,Boolean getCount,String ...option);
    /**
     * 获取某一属性的String[]
     * @param map
     * @param begin
     * @param max
     * @return String[] 
     */
    String[] getValueList(Map<String, Object> map,String propertyName,Integer begin,Integer max);
}

package com.ssh.dao.base;

import java.util.List;

/**
 * Created by guoyibin on 15/1/30.
 *
 * 基础dao接口
 */
public interface BaseDao<T> {
    /**
     * 根据Id查询实体
     * */
    T getById(Long id);

    /**
     * 保存实体
     * */
    void save(T entity);

    /**
     * 更新实体
     * */
    void update(T entity);

    /**
     * 删除实体
     * */
    void delete(T entity);

    /**
     * 根据ID删除实体
     * */
    void deleteById(Long id);

    /**
     * 获取所有实体
     * */
    List<T> getAll();

    /**
     * 获取实体总数
     * */
    long getCount();
}

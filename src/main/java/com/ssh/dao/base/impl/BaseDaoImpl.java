package com.ssh.dao.base.impl;

import com.ssh.dao.base.BaseDao;
import com.ssh.util.BaseDaoUtil;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by guoyibin on 15/1/30.
 *
 * 基础dao实现类
 */
@Repository
public class BaseDaoImpl<T> implements BaseDao<T> {

    @SuppressWarnings("unchecked")
    protected Class <T> entityClass = BaseDaoUtil.getClassGenricType(getClass());

    @Autowired
    public SessionFactory sessionFactory;

    /**
     * 根据Id查询实体
     *
     * @param id
     */
    @SuppressWarnings("unchecked")
    public T getById(Long id) {
        return (T)sessionFactory.getCurrentSession().get(entityClass, id);
    }

    /**
     * 保存实体
     *
     * @param entity
     */
    @SuppressWarnings("unchecked")
    public void save(T entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    /**
     * 更新实体
     *
     * @param entity
     */
    @SuppressWarnings("unchecked")
    public void update(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    /**
     * 删除实体
     *
     * @param entity
     */
    @SuppressWarnings("unchecked")
    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    /**
     * 根据ID删除实体
     *
     * @param id
     */
    @SuppressWarnings("unchecked")
    public void deleteById(Long id) {
        sessionFactory.getCurrentSession()
                .createQuery("delete from " + entityClass.getSimpleName() + " en where en.id = "+id)
                .executeUpdate();
    }

    /**
     * 获取所有实体
     *
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return getByHql("select en from " + entityClass.getSimpleName() + " en");
    }

    /**
     * 获取实体总数
     *
     */
    @SuppressWarnings("unchecked")
    public long getCount() {
        List<T> l = getByHql("select count(*) from " + entityClass.getSimpleName());

        // 返回查询得到的实体总数
        if (l != null && l.size() == 1 ) {
            return (Long)l.get(0);
        }
        return 0;
    }

    /**
     * 根据hql语句查询实体
     *
     * */
    @SuppressWarnings("unchecked")
    protected List<T> getByHql(String hql) {
        return (List<T>)sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    /**
     * 根据sql语句查询实体
     *
     * */
    @SuppressWarnings("unchecked")
    protected List<T> getBySql(String sql) {
        return (List<T>)sessionFactory.getCurrentSession().createSQLQuery(sql).list();
    }

    /**
     * 根据带占位符参数HQL语句查询实体
     *
     * */
    @SuppressWarnings("unchecked")
    protected List<T> getByHqlAndParams(String hql , Object[] params) {
        // 创建查询
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        // 为包含占位符的HQL语句设置参数
        int len = params.length;
        for(int i = 0 ; i < len ; i++) {
            query.setParameter(i + "" , params[i]);
        }
        return (List<T>)query.list();
    }

    /**
     * 根据带占位符参数SQL语句查询实体
     *
     * */
    @SuppressWarnings("unchecked")
    protected List<T> getBySqlAndParams(String sql , Object[] params) {
        // 创建查询
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        // 为包含占位符的HQL语句设置参数
        int len = params.length;
        for(int i = 0 ; i < len ; i++) {
            query.setParameter(i + "" , params[i]);
        }
        return (List<T>)query.list();
    }

    /**
     * 使用hql 语句进行分页查询操作
     * @param hql 需要查询的hql语句
     * @param currentPage 查询第currentPage页的记录
     * @param pageSize 每页需要显示的记录数
     * @return 当前页的所有记录
     */
    @SuppressWarnings("unchecked")
    protected List<T> getPageByHql(String hql, int currentPage, int pageSize) {
        // 创建查询
        return sessionFactory.getCurrentSession().createQuery(hql)
                .setFirstResult((currentPage - 1) * pageSize)// 执行分页
                .setMaxResults(pageSize)
                .list();
    }


    /**
     * 使用sql 语句进行分页查询操作
     * @param sql 需要查询的sql语句
     * @param currentPage 查询第currentPage页的记录
     * @param pageSize 每页需要显示的记录数
     * @return 当前页的所有记录
     */
    @SuppressWarnings("unchecked")
    protected List<T> getPageBySql(String sql, int currentPage, int pageSize) {
        // 创建查询
        return sessionFactory.getCurrentSession().createSQLQuery(sql)
                .setFirstResult((currentPage - 1) * pageSize)// 执行分页
                .setMaxResults(pageSize)
                .list();
    }

    /**
     * 使用hql 语句进行分页查询操作
     * @param hql 需要查询的hql语句
     * @param params 如果hql带占位符参数，params用于传入占位符参数
     * @param currentPage 查询第currentPage页的记录
     * @param pageSize 每页需要显示的记录数
     * @return 当前页的所有记录
     */
    @SuppressWarnings("unchecked")
    protected List<T> getPageByHqlAndParams(String hql , int currentPage, int pageSize , Object[] params) {
        // 创建查询
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        // 为包含占位符的HQL语句设置参数
        int len = params.length;
        for(int i = 0 ; i < len ; i++) {
            query.setParameter(i + "" , params[i]);
        }
        // 执行分页，并返回查询结果
        return query.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
    }

    /**
     * 使用sql 语句进行分页查询操作
     * @param sql 需要查询的sql语句
     * @param params 如果sql带占位符参数，params用于传入占位符参数
     * @param currentPage 查询第currentPage页的记录
     * @param pageSize 每页需要显示的记录数
     * @return 当前页的所有记录
     */
    @SuppressWarnings("unchecked")
    protected List<T> getPageBySqlAndParams(String sql , int currentPage, int pageSize , Object[] params) {
        // 创建查询
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        // 为包含占位符的HQL语句设置参数
        int len = params.length;
        for(int i = 0 ; i < len ; i++) {
            query.setParameter(i + "" , params[i]);
        }
        // 执行分页，并返回查询结果
        return query.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
    }

}

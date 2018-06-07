package cn.zhimadi.txs.common.service;

import cn.zhimadi.txs.common.dao.BaseDao;
import cn.zhimadi.txs.common.search.DataTable;
import cn.zhimadi.txs.common.search.DataTableSpecification;
import cn.zhimadi.txs.common.search.SearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 基本Service实现类
 * @param <T> the type parameter
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Transactional
@Service
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    /**
     * for datatables
     *
     * @param dataTable
     * @param clazz     the clazz
     * @return data table response
     * @author : mingweigao / 2017-04-04
     */
    @Override
    public SearchResponse<T> findAll(DataTable dataTable, Class<T> clazz) {
        Specification<T> spec = DataTableSpecification.buildGlobalSpec(dataTable, clazz);
        PageRequest pageRequest = dataTable.buildPageRequest();
        Page<T> page = findAll(spec, pageRequest);
        return new SearchResponse<T>(page.getTotalElements(), page.getTotalElements(), page.getContent());
    }

    /**
     * Get cn.zhimadi.txs.monitor.dao base cn.zhimadi.txs.monitor.dao.
     *
     * @return the base cn.zhimadi.txs.monitor.dao
     * @author : yangjunqing / 2018-06-01
     */
    abstract protected BaseDao<T> getDao();

    /**
     * Find all list.
     *
     * @return the list
     * @author : yangjunqing / 2018-06-01
     */
    @Override
    public List<T> findAll() {
        return getDao().findAll();
    }

    /**
     * Find all list.
     *
     * @param ids the ids
     * @return the list
     * @author : yangjunqing / 2018-06-01
     */
    @Override
    public List<T> findAll(Iterable<String> ids) {
        return getDao().findAllById(ids);
    }

    /**
     * Find all list.
     *
     * @param sort the sort
     * @return the list
     * @author : yangjunqing / 2018-06-01
     */
    @Override
    public List<T> findAll(Sort sort) {
        return getDao().findAll(sort);
    }

    /**
     * Find all page.
     *
     * @param pageable the pageable
     * @return the page
     * @author : yangjunqing / 2018-06-01
     */
    @Override
    public Page<T> findAll(Pageable pageable) {
        return getDao().findAll(pageable);
    }

    /**
     * Find all list.
     *
     * @param spec the spec
     * @return the list
     * @author : yangjunqing / 2018-06-01
     */
    @Override
    public List<T> findAll(Specification<T> spec) {
        return getDao().findAll(spec);
    }

    /**
     * Find all page.
     *
     * @param spec     the spec
     * @param pageable the pageable
     * @return the page
     * @author : yangjunqing / 2018-06-01
     */
    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return getDao().findAll(spec, pageable);
    }

    /**
     * Find all list.
     *
     * @param spec the spec
     * @param sort the sort
     * @return the list
     * @author : yangjunqing / 2018-06-01
     */
    @Override
    public List<T> findAll(Specification<T> spec, Sort sort) {
        return getDao().findAll(spec, sort);
    }

    /**
     * Save s.
     *
     * @param entity the cn.zhimadi.txs.monitor.entity
     * @return the s
     * @author : yangjunqing / 2018-06-01
     */
    @Override
    public <S extends T> S save(S entity) {
        return getDao().save(entity);
    }

    /**
     * Save and flush t.
     *
     * @param entity the cn.zhimadi.txs.monitor.entity
     * @return the t
     * @author : yangjunqing / 2018-06-01
     */
    @Override
    public T saveAndFlush(T entity) {
        return getDao().saveAndFlush(entity);
    }

    /**
     * Save list.
     *
     * @param entities the entities
     * @return the list
     * @author : yangjunqing / 2018-06-01
     */
    @Override
    public <S extends T> List<S> save(Iterable<S> entities) {
        return getDao().saveAll(entities);
    }

    /**
     * Exists boolean.
     *
     * @param id the id
     * @return the boolean
     * @author : yangjunqing / 2018-06-01
     */
    @Override
    public boolean exists(String id) {
        return getDao().existsById(id);
    }

    /**
     * Find by id t.
     *
     * @param id the id
     * @return the t
     * @author : yangjunqing / 2018-06-01
     */
    @Override
    public T findById(String id) {
        return getDao().findById(id).get();
    }

    /**
     * Delete.
     *
     * @param id the id
     * @author : yangjunqing / 2018-06-01
     */
    @Override
    public void delete(String id) {
        getDao().deleteById(id);
    }

    /**
     * Delete.
     *
     * @param entity the cn.zhimadi.txs.monitor.entity
     * @author : yangjunqing / 2018-06-01
     */
    @Override
    public void delete(T entity) {
        getDao().delete(entity);
    }

    /**
     * Delete all.
     *
     * @author : yangjunqing / 2018-06-01
     */
    @Override
    public void deleteAll() {
        getDao().deleteAll();
    }

    /**
     * Count long.
     *
     * @return the long
     * @author : yangjunqing / 2018-06-01
     */
    @Override
    public long count() {
        return getDao().count();
    }

    /**
     * Count long.
     *
     * @param spec the spec
     * @return the long
     * @author : yangjunqing / 2018-06-01
     */
    @Override
    public long count(Specification<T> spec) {
        return getDao().count(spec);
    }

    /**
     * Flush.
     *
     * @author : yangjunqing / 2018-06-01
     */
    @Override
    public void flush() {
        getDao().flush();
    }
}

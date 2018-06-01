package cn.zhimadi.txs.common.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * 基本Service接口
 *
 * @param <T> the type parameter
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
public interface BaseService<T> {


    /**
     * Find all list.
     *
     * @return the list
     * @author : yangjunqing / 2018-06-01
     */
    List<T> findAll();

    /**
     * Find all list.
     *
     * @param ids the ids
     * @return the list
     * @author : yangjunqing / 2018-06-01
     */
    List<T> findAll(Iterable<String> ids);

    /**
     * Find all list.
     *
     * @param sort the sort
     * @return the list
     * @author : yangjunqing / 2018-06-01
     */
    List<T> findAll(Sort sort);

    /**
     * Find all page.
     *
     * @param pageable the pageable
     * @return the page
     * @author : yangjunqing / 2018-06-01
     */
    Page<T> findAll(Pageable pageable);

    /**
     * Find all list.
     *
     * @param spec the spec
     * @return the list
     * @author : yangjunqing / 2018-06-01
     */
    List<T> findAll(Specification<T> spec);

    /**
     * Find all page.
     *
     * @param spec     the spec
     * @param pageable the pageable
     * @return the page
     * @author : yangjunqing / 2018-06-01
     */
    Page<T> findAll(Specification<T> spec, Pageable pageable);

    /**
     * Find all list.
     *
     * @param spec the spec
     * @param sort the sort
     * @return the list
     * @author : yangjunqing / 2018-06-01
     */
    List<T> findAll(Specification<T> spec, Sort sort);

    /**
     * Save s.
     *
     * @param <S>    the type parameter
     * @param entity the entity
     * @return the s
     * @author : yangjunqing / 2018-06-01
     */
    <S extends T> S save(S entity);

    /**
     * Save and flush t.
     *
     * @param entity the entity
     * @return the t
     * @author : yangjunqing / 2018-06-01
     */
    T saveAndFlush(T entity);

    /**
     * Save list.
     *
     * @param <S>      the type parameter
     * @param entities the entities
     * @return the list
     * @author : yangjunqing / 2018-06-01
     */
    <S extends T> List<S> save(Iterable<S> entities);

    /**
     * Exists boolean.
     *
     * @param id the id
     * @return the boolean
     * @author : yangjunqing / 2018-06-01
     */
    boolean exists(String id);

    /**
     * Find by id t.
     *
     * @param id the id
     * @return the t
     * @author : yangjunqing / 2018-06-01
     */
    T findById(String id);

    /**
     * Delete.
     *
     * @param id the id
     * @author : yangjunqing / 2018-06-01
     */
    void delete(String id);

    /**
     * Delete.
     *
     * @param entity the entity
     * @author : yangjunqing / 2018-06-01
     */
    void delete(T entity);

    /**
     * Delete all.
     *
     * @author : yangjunqing / 2018-06-01
     */
    void deleteAll();

    /**
     * Count long.
     *
     * @return the long
     * @author : yangjunqing / 2018-06-01
     */
    long count();

    /**
     * Count long.
     *
     * @param spec the spec
     * @return the long
     * @author : yangjunqing / 2018-06-01
     */
    long count(Specification<T> spec);

    /**
     * Flush.
     *
     * @author : yangjunqing / 2018-06-01
     */
    void flush();
}

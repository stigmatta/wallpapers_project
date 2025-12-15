package com.odintsov.wallpapers_project.infrastructure.adapters;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
 * Generic adapter between domain repositories and Spring Data JPA
 */
public abstract class BaseJpaRepositoryAdapter<
        T,
        ID,
        JPA extends JpaRepository<T, ID> & JpaSpecificationExecutor<T>
        > {

    protected final JPA jpaRepository;

    protected BaseJpaRepositoryAdapter(JPA jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public Optional<T> findById(ID id) {
        return jpaRepository.findById(id);
    }

    public List<T> findAll() {
        return jpaRepository.findAll();
    }

    public T save(T entity) {
        return jpaRepository.save(entity);
    }

    public List<T> saveAll(List<T> entities) {
        return jpaRepository.saveAll(entities);
    }

    public void delete(T entity) {
        jpaRepository.delete(entity);
    }

    public long count() {
        return jpaRepository.count();
    }

    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return jpaRepository.findAll(spec, pageable);
    }

    public long count(Specification<T> spec) {
        return jpaRepository.count(spec);
    }
}

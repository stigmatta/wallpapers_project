package com.odintsov.wallpapers_project.infrastructure.adapters.jpa;

import com.odintsov.wallpapers_project.domain.repositories.CrudRepository;
import com.odintsov.wallpapers_project.infrastructure.interfaces.FilterSpecificationBuilder;
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
        F,
        JPA extends JpaRepository<T, ID> & JpaSpecificationExecutor<T>
        > implements CrudRepository<T, ID, F> {

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

    public void delete(ID id) { jpaRepository.deleteById(id);}

    public long count() {
        return jpaRepository.count();
    }

    public Page<T> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable);
    }

    public Page<T> filter(F filter,
                                       Pageable pageable,
                                       FilterSpecificationBuilder<T, F> builder) {
        Specification<T> spec = builder.build(filter);
        return jpaRepository.findAll(spec, pageable);
    }

    public long count(Specification<T> spec) {
        return jpaRepository.count(spec);
    }

    public void flush() {
        jpaRepository.flush();
    }

}

package com.bank.repository;

import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
@Where(clause = "isActive is false")
public interface BaseRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    List<T> findByActive(boolean isActive);

    List<T> findActiveTrueAndByIdIn(List<UUID> ids);

    Optional<T> findByActiveTrueAndId(ID id);
}

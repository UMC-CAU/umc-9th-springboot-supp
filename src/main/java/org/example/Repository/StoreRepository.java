package org.example.Repository;

import org.example.Entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findById(long id);
}

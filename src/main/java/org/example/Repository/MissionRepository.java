package org.example.Repository;

import org.example.Entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Integer> {
    @Query("SELECT m FROM Mission m WHERE m.store.store_id = :storeId")
    Page<Mission> findByStoreId(@Param("storeId") Long storeId, Pageable pageable);
}

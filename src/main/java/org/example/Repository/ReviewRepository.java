package org.example.Repository;

import org.example.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
}

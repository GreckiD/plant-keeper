package com.plant.keeper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasilRepository extends JpaRepository<Basil, Long> {

}

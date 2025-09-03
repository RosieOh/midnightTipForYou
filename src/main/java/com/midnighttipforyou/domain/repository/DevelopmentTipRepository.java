package com.midnighttipforyou.domain.repository;

import com.midnighttipforyou.domain.entity.DevelopmentTip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DevelopmentTipRepository extends JpaRepository<DevelopmentTip, Long> {
    
    List<DevelopmentTip> findByIsActiveTrue();
    
    List<DevelopmentTip> findByCategoryAndIsActiveTrue(String category);
    
    @Query("SELECT dt FROM DevelopmentTip dt WHERE dt.isActive = true ORDER BY RAND() LIMIT 1")
    Optional<DevelopmentTip> findRandomActiveTip();
    
    @Query("SELECT dt FROM DevelopmentTip dt WHERE dt.isActive = true AND dt.id != :excludeId ORDER BY RAND() LIMIT 1")
    Optional<DevelopmentTip> findRandomActiveTipExcluding(@Param("excludeId") Long excludeId);
}

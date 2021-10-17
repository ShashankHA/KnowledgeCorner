package com.learning.kc.repository;

import com.learning.kc.model.SessionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SessionInfoRepository extends JpaRepository<SessionInfo,Long> {

    @Query("select username from SessionInfo info where info.sessionId = ?1")
    String getUsername(String sessioId);
}

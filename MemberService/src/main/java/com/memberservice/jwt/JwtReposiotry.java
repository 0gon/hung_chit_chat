package com.memberservice.jwt;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtReposiotry extends CrudRepository<RefreshToken, Long> {
}

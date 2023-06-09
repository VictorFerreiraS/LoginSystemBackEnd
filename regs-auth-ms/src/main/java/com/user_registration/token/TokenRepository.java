package com.user_registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
            select t from Token t inner join User u\s
            on t.user.id = u.id\s
            where u.id = :id and (t.expired = false or t.revoked = false)\s
            """)
    List<Token> findAllValidTokensByUserId(@Param("id") Integer id);

    @Query(value = """
            select t from Token t inner join User u\s
            on t.user.id = u.id\s
            """)
    List<Token> findAllTokensByUserId(@Param("id") Integer id);

    //    Uses string token to find an optional instance of Token;
    Optional<Token> findByToken(String token);
}
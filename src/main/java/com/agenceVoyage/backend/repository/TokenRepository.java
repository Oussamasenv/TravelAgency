package com.agenceVoyage.backend.repository;

import com.agenceVoyage.backend.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Double> {


    @Query("""
select t from Token t inner join User u on t.user.id = u.id
where t.user.id = :userId and t.loggedOut = false
""")
    List<Token> findAllTokensByUser(Double userId);

    Optional<Token> findByToken(String token);
}

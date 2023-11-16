package com.ziko.userservice.repository;

import com.ziko.userservice.entity.User;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 13 Nov, 2023
 */

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Integer> {

    @Modifying
    @Query(value = """
                        UPDATE users
                        SET balance= balance - :amount
                        WHERE id= :userId
                        AND balance >= :amount
            """)
    Mono<Boolean> updateUserBalance(int userId, int amount);
}

package com.example.repository;

import com.example.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by pyshankov on 11.10.2016.
 */
@RepositoryRestResource(collectionResourceRel = "user", path = "/api/user")
public interface UserRepository extends CrudRepository<User,Long> {


    @PreAuthorize("hasRole('ROLE_USER')")
    User findByUserName(@Param("name") String userName);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    Iterable<User> findAll();

}

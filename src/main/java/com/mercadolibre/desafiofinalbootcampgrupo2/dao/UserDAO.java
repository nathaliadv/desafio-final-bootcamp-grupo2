package com.mercadolibre.desafiofinalbootcampgrupo2.dao;

import com.mercadolibre.desafiofinalbootcampgrupo2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    UserDetails findByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tb_user u WHERE u.user = :user", nativeQuery = false)
    public void deleteAllByUser(@Param("user") User user);
}

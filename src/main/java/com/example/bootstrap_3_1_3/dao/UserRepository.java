package com.example.bootstrap_3_1_3.dao;


import com.example.bootstrap_3_1_3.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

   // @Modifying
   // @Query(value = "update User u set u.firstName = ?1, u.lastName = ?2," +
           // " u.password = ?3, u.email = ?4, u.age = ?5  where u.id =?6")
  //  void update(String firstName, String lastName, String password, String email, Integer age, Long id );
}

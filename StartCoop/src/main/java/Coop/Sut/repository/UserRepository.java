package Coop.Sut.repository;

import Coop.Sut.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

        @Query("select u from User u where u.email=:email and u.password=:password")
        User findUser(@Param("email") String email, @Param("password") String password);

        //@Query(value = "insert into User(firstName,lastName,email,password) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
       // void insertUser(User user);
}

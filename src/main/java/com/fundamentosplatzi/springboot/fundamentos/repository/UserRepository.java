package com.fundamentosplatzi.springboot.fundamentos.repository;

import com.fundamentosplatzi.springboot.fundamentos.dto.UserDto;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long> {
    @Query("select u from User u where u.email=?1")
    Optional<User> findByUserEmail(String email);

    @Query("select u from User u where u.name like ?1%")
    List<User> findAndSort(String name , Sort sort);

    List<User> findByName(String name);
    Optional<User> findByNameAndEmail(String name, String email);

    List<User> findByNameLike(String name);

    List<User> findByNameOrEmail(String name , String email);

    List<User> findByBirthdayBetween(LocalDate begin , LocalDate end);

    List<User> findByNameLikeOrderByIdDesc(String name);

    //like o containing obtienen la misma respuesta
    List<User> findByNameContainingOrderByIdDesc(String name);

    @Query("Select new com.fundamentosplatzi.springboot.fundamentos.dto.UserDto(u.id, u.name , u.birthday) From User u where u.birthday=:birthday AND u.email=:email")
    Optional<UserDto> getAllBirthDateAndEmail(@Param("birthday") LocalDate date, @Param("email") String email) ;



    List<User> findAll();


}

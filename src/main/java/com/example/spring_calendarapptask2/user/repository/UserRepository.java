package com.example.spring_calendarapptask2.user.repository;

import com.example.spring_calendarapptask2.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface UserRepository extends JpaRepository<UserEntity, Long>
{
}

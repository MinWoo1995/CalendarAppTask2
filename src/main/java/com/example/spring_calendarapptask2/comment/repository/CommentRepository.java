package com.example.spring_calendarapptask2.comment.repository;

import com.example.spring_calendarapptask2.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long>
{
}

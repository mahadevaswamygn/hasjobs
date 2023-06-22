package com.example.hasjobs.repository;

import com.example.hasjobs.entity.Collaborator;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator,Integer> {
    Collaborator findByName(String name);
}

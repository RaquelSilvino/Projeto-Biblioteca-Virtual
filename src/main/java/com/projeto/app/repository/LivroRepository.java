package com.projeto.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projeto.app.model.Livro;


public interface LivroRepository extends JpaRepository<Livro, Long> {
}
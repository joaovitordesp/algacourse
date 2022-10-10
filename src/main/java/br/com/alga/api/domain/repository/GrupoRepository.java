package br.com.alga.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alga.api.domain.model.Grupo;

public interface GrupoRepository  extends JpaRepository<Grupo, Long>{

}

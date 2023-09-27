package br.com.alga.api.domain.service;

import java.util.List;

import br.com.alga.api.domain.filter.VendaDiariaFilter;
import br.com.alga.api.domain.model.dto.VendaDiaria;

public interface VendaQueryService {
	//interface para o filter de venda diaria
	
	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro,String timeOffset);
}

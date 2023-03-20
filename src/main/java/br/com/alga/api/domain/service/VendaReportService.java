package br.com.alga.api.domain.service;

import br.com.alga.api.domain.filter.VendaDiariaFilter;

public interface VendaReportService {
	byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
}

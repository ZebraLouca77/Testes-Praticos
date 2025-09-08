package meli.pedidos.api.service;

import meli.pedidos.api.repository.CentroDistribuicaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CentroDistribuicaoServiceTest {

    @Mock
    private CentroDistribuicaoRepository repository;

    @InjectMocks
    private CentroDistribuicaoService service;

    @Test
    void shouldReturnEmptyListWhenNoDistributionCenterIsFound() {
        when(repository.listarCentroDistribuicaoComItens(1L)).thenReturn(Collections.emptyList());

        var result = service.consultaCentroDistribuicao(1L);

        assertTrue(result.isEmpty());
    }
}

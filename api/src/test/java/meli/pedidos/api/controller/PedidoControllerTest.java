package meli.pedidos.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import meli.pedidos.api.itempedido.DadosItemProcessa;
import meli.pedidos.api.pedido.DadosPedidoProcessa;
import meli.pedidos.api.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PedidoController.class)
class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnOkWhenProcessingOrder() throws Exception {
        var dadosPedido = new DadosPedidoProcessa(List.of(
                new DadosItemProcessa(1L)
        ));

        when(pedidoService.processarPedido(any())).thenReturn(List.of());

        mockMvc.perform(post("/pedidos/processar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dadosPedido)))
                .andExpect(status().isOk());
    }
}

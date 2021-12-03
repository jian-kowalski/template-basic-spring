package com.jiankowalski.basicspring.transportlayers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiankowalski.basicspring.datasources.PetFactory;
import com.jiankowalski.basicspring.domain.exception.NotFoundException;
import com.jiankowalski.basicspring.interactors.PetService;
import com.jiankowalski.openapi.model.PetInput;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PetController.class)
class PetControllerTest {

    private final String END_POINT_PET = "/pets";
    private final String END_POINT_PET_ID = END_POINT_PET + "/{id}";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PetService petService;

    @Test
    void deveRetornar201AoCriarComSucesso() throws Exception {
        PetInput pet = PetFactory.criarPetInputValido();
        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT_PET)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pet))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void deveRetornar400AoCriarComSemNome() throws Exception {
        PetInput pet = PetFactory.criarPetInputSemNome();
        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT_PET)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pet))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornarExceptionAoConsultarPetInexistente() throws Exception {
        Mockito.when(petService.getPet(Mockito.anyLong())).thenThrow(new NotFoundException("", 1L));
        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT_PET_ID, -1)).andExpect(status().isNotFound());
    }

    @Test
    void deveRetornarExceptionBadRequestAoConsultarPetInexistente() throws Exception {
        Mockito.when(petService.getPet(Mockito.anyLong())).thenThrow(new NotFoundException("", 1L));
        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT_PET_ID, "PET")).andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornarOKQuandoPetExistente() throws Exception {
        Mockito.when(petService.getPet(Mockito.anyLong())).thenReturn(PetFactory.criarPetValido());
        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT_PET_ID, 1)).andExpect(status().isOk());
    }

    @Test
    void deveExcluirPetComSucesso() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(END_POINT_PET_ID, 1)).andExpect(status().isNoContent());
    }

}
package com.example.batis;

import com.example.batis.exception.ErrorReason;
import com.example.batis.exception.regions.exceptions.EmptyListOfRegionsException;
import com.example.batis.exception.regions.exceptions.NoSuchElementException;
import com.example.batis.exception.regions.exceptions.RegionDeletionException;
import com.example.batis.model.RegionDTO;
import com.example.batis.model.Regions;
import com.example.batis.service.RegionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMybatis
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegionService regionService;

    @Test
    void FindAll_success() throws Exception {
        Regions regions = new Regions();
        regions.add(new RegionDTO(1L, "Moscow", "Msc"));
        regions.add(new RegionDTO(2L, "Saint Petersburg", "SPb"));
        when(regionService.findAll()).thenReturn(regions);

        mockMvc.perform(get("/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].shortName", is("Msc")));
    }

    @Test
    void FindAll_error() throws Exception {
        when(regionService.findAll()).thenThrow(new EmptyListOfRegionsException());
        mockMvc.perform(get("/all"))
                .andDo(print())
                .andExpect(jsonPath("$.message", is("List of regions is empty")))
                .andExpect(jsonPath("$.reason", is(ErrorReason.EMPTY_LIST.name())))
                .andExpect(status().isBadRequest());
    }

    @Test
    void FindById_success() throws Exception {
        when(regionService.findById(1)).thenReturn(new RegionDTO(1L, "Moscow", "Msc"));
        mockMvc.perform(get("/?id=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Moscow")));
    }

    @Test
    void FindById_error() throws Exception {
        when(regionService.findById(1)).thenThrow(new NoSuchElementException(1));
        mockMvc.perform(get("/?id=1"))
                .andExpect(content().string(containsString("Element with parameters [1] not found")))
                .andExpect(status().isBadRequest());
    }

    @Test
    void DeleteById_error() throws Exception {
        doThrow(new RegionDeletionException()).when(regionService).deleteById(1);
        mockMvc.perform(delete("/?id=1"))
                .andExpect(content().string(containsString("Can't delete")))
                .andExpect(status().isBadRequest());
    }

    @Test
    void DeleteById_success() throws Exception {
        doNothing().when(regionService).deleteById(1);
        mockMvc.perform(delete("/?id=1"))
                .andExpect(status().isNoContent());
    }
}

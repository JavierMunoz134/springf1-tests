package org.api.springf1.service;

import org.api.springf1.dto.DriverDTO;
import org.api.springf1.model.Driver;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DriverServiceImplTest {

    @Mock
    DriverServiceImpl driverService;

    @InjectMocks
    DriverServiceImpl driverServiceImpl;


    Driver code;
    DriverDTO driverDTO;

    @BeforeEach
    public void setUp() {

        code = Driver.builder().code("AAA").build();
        driverDTO = DriverDTO.builder().id(1L).code("AAA").forename("Ayrton").surname("Senna").build();
    }

    @Test
    public void shouldReturnDriverDTOWhenFindDriverByCode(){
        // given
        String code = "AAA";
        // when
        DriverDTO driverDTO = driverServiceImpl.getDriverByCode(code);
        // then
        assertEquals(driverDTO, driverDTO);
    }


}
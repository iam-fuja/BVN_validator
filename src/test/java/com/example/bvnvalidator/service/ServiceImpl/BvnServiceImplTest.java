package com.example.bvnvalidator.service.ServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.bvnvalidator.dto.BvnInputDto;
import com.example.bvnvalidator.exception.BvnInvalidException;
import com.example.bvnvalidator.model.Bvn;
import com.example.bvnvalidator.repository.BvnRepository;
import com.example.bvnvalidator.response.BvnResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BvnServiceImpl.class})
@ExtendWith(SpringExtension.class)
class BvnServiceImplTest {
    @MockBean
    private BvnRepository bvnRepository;

    @Autowired
    private BvnServiceImpl bvnServiceImpl;

    @Test
    void testValidate() {
        BvnResponse actualValidateResult = bvnServiceImpl.validate(new BvnInputDto("Bvn"));
        assertEquals("Bvn", actualValidateResult.getBvn());
        assertEquals("The searched BVN is invalid", actualValidateResult.getMessage());
        assertEquals(2, actualValidateResult.getCode().intValue());
    }


    @Test
    void testValidateAgainstErrorCode() {
        BvnResponse actualValidateResult = bvnServiceImpl.validate(new BvnInputDto("The searched BVN is invalid"));
        assertEquals(400, actualValidateResult.getCode().intValue());
    }


    @Test
    void testValidateAgainstErrorBVN() {
        BvnResponse actualValidateResult = bvnServiceImpl.validate(new BvnInputDto(""));
        assertEquals("One of more of your request parameters failed validation. Please Retry",
                actualValidateResult.getMessage());
        assertEquals(400, actualValidateResult.getCode().intValue());
    }





    @Test
    void testValidates() {
        BvnInputDto bvnInputDto = mock(BvnInputDto.class);
        when(bvnInputDto.getBvn()).thenReturn("Bvn");
        BvnResponse actualValidateResult = bvnServiceImpl.validate(bvnInputDto);
        assertEquals("Bvn", actualValidateResult.getBvn());
        assertEquals("The searched BVN is invalid", actualValidateResult.getMessage());
        assertEquals(2, actualValidateResult.getCode().intValue());
        verify(bvnInputDto, atLeast(1)).getBvn();
    }

    @Test
    void testValidateBvn() {
        Bvn bvn = new Bvn();
        bvn.setBvn("Bvn");
        bvn.setId(123L);
        when(bvnRepository.findBvnByBvn((String) any())).thenReturn(bvn);
        BvnResponse actualValidateBvnResult = bvnServiceImpl.validateBvn(new BvnInputDto("Bvn"));
        assertEquals("Bvn", actualValidateBvnResult.getBvn());
        assertEquals("Success", actualValidateBvnResult.getMessage());
        byte[] image2 = actualValidateBvnResult.getImage2();
        assertEquals(1, image2.length);
        assertSame(image2, actualValidateBvnResult.getImage1());
        assertEquals(0, actualValidateBvnResult.getCode().intValue());
        verify(bvnRepository).findBvnByBvn((String) any());
    }




    @Test
    void testValidateBvnResponse() {
        when(bvnRepository.findBvnByBvn((String) any())).thenThrow(new BvnInvalidException("An error occurred"));
        assertThrows(BvnInvalidException.class, () -> bvnServiceImpl.validateBvn(new BvnInputDto("Bvn")));
        verify(bvnRepository).findBvnByBvn((String) any());
    }
}


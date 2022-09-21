package com.example.bvnvalidator.service.ServiceImpl;

import com.example.bvnvalidator.dto.BvnInputDto;
import com.example.bvnvalidator.exception.BvnInvalidException;
import com.example.bvnvalidator.model.Bvn;
import com.example.bvnvalidator.repository.BvnRepository;
import com.example.bvnvalidator.response.BvnResponse;
import com.example.bvnvalidator.service.BvnService;
import com.example.bvnvalidator.util.AppValidators;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;


@Service
@RequiredArgsConstructor
public class BvnServiceImpl implements BvnService {

    private final BvnRepository bvnRepository;
//    private final AppValidators validate;

//    public BvnServiceImpl(BvnRepository bvnRepository) {
//        this.bvnRepository = bvnRepository;
//    }

    @Override
    public BvnResponse validate(BvnInputDto bvnInputDto) {

//        Boolean t = validate.bvnLengthCheck(bvnInputDto.getBvn());

        if(bvnInputDto.getBvn().isEmpty() || bvnInputDto.getBvn() == null){
           BvnResponse bvnResponse = new BvnResponse();
           bvnResponse.setMessage("One of more of your request parameters failed validation. Please Retry");
           bvnResponse.setCode(400);
           return bvnResponse;

        }
        else if(bvnInputDto.getBvn().length() < 11){
            BvnResponse bvnResponse = new BvnResponse();
            bvnResponse.setMessage("The searched BVN is invalid");
            bvnResponse.setCode(02);
            bvnResponse.setBvn(bvnInputDto.getBvn());
            return bvnResponse;

        } else if (!bvnInputDto.getBvn().matches("\\d+")) {
            BvnResponse bvnResponse = new BvnResponse();
            bvnResponse.setMessage("The searched BVN is invalid");
            bvnResponse.setCode(400);
            bvnResponse.setBvn(bvnInputDto.getBvn());
            return bvnResponse;
        } else{
            Bvn bvn = bvnRepository.findBvnByBvn(bvnInputDto.getBvn());
            if(bvn != null){
                throw new BvnInvalidException("Already Exist BVN!");
            }
              Bvn newBvn = new Bvn();
              newBvn.setBvn(bvnInputDto.getBvn());
              bvnRepository.save(newBvn);

            BvnResponse bvnResponse = new BvnResponse();
            bvnResponse.setMessage("Success");
            bvnResponse.setCode(00);
            bvnResponse.setBvn(bvnInputDto.getBvn());
            byte[] bytes = {111};
            bvnResponse.setImage1(bytes);
            bvnResponse.setImage2(bytes);

            return bvnResponse;

            }

        }

    @Override
    public BvnResponse validateBvn(BvnInputDto bvnInputDto) {
        Bvn bvn = bvnRepository.findBvnByBvn(bvnInputDto.getBvn());
        BvnResponse bvnResponse = new BvnResponse();
        if(bvn == null){

            bvnResponse.setMessage("The searched BVN does not exist");
            bvnResponse.setCode(01);

        }
        else {

            bvnResponse.setMessage("Success");
            bvnResponse.setCode(00);
            bvnResponse.setBvn(bvn.getBvn());
            byte[] bytes = {111};
            bvnResponse.setImage1(bytes);
            bvnResponse.setImage2(bytes);

        }
        return bvnResponse;

    }


}


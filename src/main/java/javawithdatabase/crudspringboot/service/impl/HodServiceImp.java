package javawithdatabase.crudspringboot.service.impl;

import javawithdatabase.crudspringboot.model.HoD;
import javawithdatabase.crudspringboot.repository.HodRepository;
import javawithdatabase.crudspringboot.service.HodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HodServiceImp implements HodService {

    @Autowired
    private HodRepository hodRepository;

    @Override
    public HoD saveHoD(HoD hoD) {
        return hodRepository.save(hoD);
    }

    @Override
    public List<HoD> findAll() {
        return hodRepository.findAll();
    }

    @Override
    public HoD findById(Long id) {
        return hodRepository.findById(id).get();
    }
}

package javawithdatabase.crudspringboot.service;

import javawithdatabase.crudspringboot.model.HoD;

import java.util.List;

public interface HodService {

    HoD saveHoD(HoD hoD);

    List<HoD> findAll();
    HoD findById(Long id);
}

package javawithdatabase.crudspringboot.repository;

import javawithdatabase.crudspringboot.model.HoD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HodRepository extends JpaRepository<HoD,Long> {
}

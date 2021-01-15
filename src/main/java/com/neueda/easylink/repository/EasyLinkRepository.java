package com.neueda.easylink.repository;

import com.neueda.easylink.model.EasyLink;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EasyLinkRepository extends CrudRepository<EasyLink, Long> {

    EasyLink findById(String shortLink);
}

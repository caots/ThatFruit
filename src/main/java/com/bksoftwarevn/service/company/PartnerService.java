package com.bksoftwarevn.service.company;

import com.bksoftwarevn.entities.company.Partner;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PartnerService {

    List<Partner> findAllPartnerPage(Pageable pageable);

    List<Partner> findAllPartner();

    Partner findById(int id);

    boolean savePartner(Partner partner);

    boolean deletePartner(Partner partner);

}

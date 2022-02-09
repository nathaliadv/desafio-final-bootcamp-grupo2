package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.RepresentativeDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.RepresentativeDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.RepositoryException;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Representative;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RepresentativeService implements EntityService<Representative> {

    private RepresentativeDAO representativeDAO;

    public RepresentativeService(RepresentativeDAO representativeDAO) {
        this.representativeDAO = representativeDAO;
    }

    @Override
    public Representative findById(Long id) {
        return representativeDAO.findById(id)
                .orElseThrow(() -> new RepositoryException("Representative not exists in database, please contact the administrator"));
    }

    public void verifyIfRepresentativeIsValid( Authentication authentication ){
        findById(getUserId(authentication));
    }

    private Long getUserId(Authentication authentication) {
        return ((Representative) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }


}
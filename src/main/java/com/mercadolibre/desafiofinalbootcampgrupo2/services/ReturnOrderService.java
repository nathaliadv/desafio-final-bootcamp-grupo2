package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.PurchaseItemDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dao.ReturnCauseDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dao.ReturnOrderDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.ReturnItemDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.ReturnOrderResponseDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.RepositoryException;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReturnOrderService {

    @Autowired
    PurchaseItemDAO purchaseItemDAO;

    @Autowired
    ReturnOrderDAO returnOrderDAO;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    ReturnCauseDAO returnCauseDAO;

    public ReturnOrder saveReturnOrder(List<ReturnItemDTO> itens, String cause, Authentication authentication) {
        List<ReturnOrderItens> returnOrderItens = convertListPurchaseItemDtoInListReturnOrderItens(itens);

        ReturnCause returnCause = returnCauseDAO.findByCause(cause);
        if (cause == null) {
            throw new RepositoryException("Cause not exists in the Database");
        }

        ReturnOrder returnOrder = ReturnOrder.builder()
                .returnStatus(new ReturnStatus(2L, "PENDING"))
                .date(LocalDate.now())
                .buyer(buyerService.findById(getUserId(authentication)))
                .returnsCause(returnCause)
                .build();

        returnOrderItens.forEach(item -> item.setReturnOrder(returnOrder));
        returnOrder.setReturnItens(returnOrderItens);

        return returnOrderDAO.save(returnOrder);
    }

    public List<ReturnOrderItens> convertListPurchaseItemDtoInListReturnOrderItens(List<ReturnItemDTO> itens) {
        List<ReturnOrderItens> returnOrderItens = new ArrayList<>();

        for (ReturnItemDTO item : itens) {
            PurchaseItens purchaseItem = purchaseItemDAO.findById(item.getPurchaseItemId())
                    .orElseThrow(() -> new RepositoryException("Purchase item not exists in the Database"));

            ReturnOrderItens returnOrderItem = ReturnOrderItens.builder()
                    .purchaseItem(purchaseItem)
                    .quantity(item.getQuantity())
                    .build();

            returnOrderItens.add(returnOrderItem);
        }

        return returnOrderItens;
    }

    public ReturnOrderResponseDTO getReturnOrderById(Long returnOrderId) {
        ReturnOrder returnOrder = returnOrderDAO.findById(returnOrderId)
                .orElseThrow(() -> new RepositoryException("Return order not exists in the Database"));
        return convertReturnOrderInReturnOrderResponseDTO(returnOrder);
    }

    public ReturnOrderResponseDTO convertReturnOrderInReturnOrderResponseDTO(ReturnOrder returnOrder) {
        return ReturnOrderResponseDTO.builder()
                .date(returnOrder.getDate())
                .returnStatus(returnOrder.getReturnStatus().getStatusCode())
                .buyer(returnOrder.getBuyer().getId())
                .returnsCause(returnOrder.getReturnsCause().getCause())
                .returnItens(converterReturnOrderItensInReturnItensDTO(returnOrder))
                .build();
    }

    public List<ReturnItemDTO> converterReturnOrderItensInReturnItensDTO(ReturnOrder returnOrder) {
        List<ReturnOrderItens> returnOrderItens = returnOrder.getReturnItens();
        List<ReturnItemDTO> listReturnItemDTO = new ArrayList<>();

        for (ReturnOrderItens item : returnOrderItens) {
            ReturnItemDTO returnOrderDto = ReturnItemDTO.builder()
                    .purchaseItemId(item.getPurchaseItem().getId())
                    .quantity(item.getQuantity())
                    .build();

            listReturnItemDTO.add(returnOrderDto);
        }

        return listReturnItemDTO;
    }

    private Long getUserId(Authentication authentication) {
        return ((Buyer) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
}

package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.PurchaseItemDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dao.ReturnCauseDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dao.ReturnOrderDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.ReturnItemCreateDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.ReturnItemDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.ReturnOrderCreateDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.ReturnOrderResponseDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.DontMatchesException;
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


    public ReturnOrder saveReturnOrder(List<ReturnItemCreateDTO> itens, String cause) {
        List<ReturnOrderItens> returnOrderItens = convertListPurchaseItemDtoInListReturnOrderItens(itens);

        ReturnCause returnCause = verifyAndReturnCause(cause);

        ReturnOrder returnOrder = ReturnOrder.builder()
                .returnStatus(new ReturnStatus(2L, "PENDING"))
                .date(LocalDate.now())
                .buyer(buyerService.findById(getUserId()))
                .returnsCause(returnCause)
                .build();

        returnOrderItens.forEach(item -> item.setReturnOrder(returnOrder));
        returnOrder.setReturnItens(returnOrderItens);

        return returnOrderDAO.save(returnOrder);
    }

    public List<ReturnOrderItens> convertListPurchaseItemDtoInListReturnOrderItens(List<ReturnItemCreateDTO> itens) {
        List<ReturnOrderItens> returnOrderItens = new ArrayList<>();

        for (ReturnItemCreateDTO item : itens) {
            PurchaseItens purchaseItem = purchaseItemDAO.findById(item.getPurchaseItemId())
                    .orElseThrow(() -> new RepositoryException("Purchase item not exists in the Database"));

            verifyItsPossibleCreateAReturnOrder(purchaseItem);
            verifyQuantityReturnOrder(purchaseItem, item);

            ReturnOrderItens returnOrderItem = ReturnOrderItens.builder()
                    .purchaseItem(purchaseItem)
                    .quantity(item.getQuantity())
                    .build();
            returnOrderItens.add(returnOrderItem);
        }
        return returnOrderItens;
    }

    private void verifyQuantityReturnOrder(PurchaseItens purchaseItem, ReturnItemCreateDTO item) {
        if (item.getQuantity() > purchaseItem.getQuantity()) {
            throw new DontMatchesException("Return quantity can not be higher than purchase quantity. Please, check the value filled.");
        }
    }

    private void verifyItsPossibleCreateAReturnOrder(PurchaseItens purchaseItem) {
        if (!purchaseItem.getPurchaseOrder().getPurchaseStatus().getStatusCode().equals("DELIVERED")) {
            throw new DontMatchesException("Purchase order was not delivered yet. Does not possible request return yet.");
        }
    }

    public ReturnOrder findById(Long returnOrderId){
        return returnOrderDAO.findById(returnOrderId)
                .orElseThrow(() -> new RepositoryException("Return order not exists in the Database"));
    }

    public ReturnOrderResponseDTO getReturnOrderById(Long returnOrderId) {
        ReturnOrder returnOrder = findById(returnOrderId);
        return convertReturnOrderInReturnOrderResponseDTO(returnOrder);
    }

    public ReturnOrderResponseDTO convertReturnOrderInReturnOrderResponseDTO(ReturnOrder returnOrder) {
        return ReturnOrderResponseDTO.builder()
                .order(returnOrder.getId())
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
                    .returnItemId(item.getId())
                    .quantity(item.getQuantity())
                    .build();

            listReturnItemDTO.add(returnOrderDto);
        }
        return listReturnItemDTO;
    }

    private Long getUserId() {
        return ((Buyer) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

    public ReturnOrder updateReturnOrder(Long returnOrderId, ReturnOrderCreateDTO returnOrderCreateDTO) {
        ReturnOrder returnOrder = findById(returnOrderId);

        ReturnCause returnCause = verifyAndReturnCause(returnOrderCreateDTO.getReturnCause());
        returnOrder.setReturnsCause(returnCause);

        List<ReturnOrderItens> returnOrderItens = convertListPurchaseItemDtoInListReturnOrderItens(returnOrderCreateDTO.getItens());
        returnOrderDAO.deleteAllByReturnOrder(returnOrder);
        for (ReturnOrderItens item : returnOrderItens) {
            item.setReturnOrder(returnOrder);
        }
        returnOrder.setReturnItens(returnOrderItens);
        returnOrder = returnOrderDAO.save(returnOrder);

        return returnOrder;
    }

    public ReturnCause verifyAndReturnCause(String causa){
        ReturnCause returnCause = returnCauseDAO.findByCause(causa);
        if (returnCause == null) {
            throw new RepositoryException("Cause not exists in the Database");
        }
        return returnCause;
    }
}

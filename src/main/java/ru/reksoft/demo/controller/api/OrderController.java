package ru.reksoft.demo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.reksoft.demo.dto.OrderDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.dto.pagination.filters.OrderFilterDTO;
import ru.reksoft.demo.service.OrderService;
import ru.reksoft.demo.service.generic.AuthorizationRequiredException;
import ru.reksoft.demo.service.generic.ResourceCannotCreateException;
import ru.reksoft.demo.service.generic.ResourceNotFoundException;

import static ru.reksoft.demo.util.ResourceLocationBuilder.buildURI;

@RestController
@RequestMapping("${api-path.order}")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }


    /**
     * Return list of orders by a certain statuses by page divider.
     *
     * @param filterDTO - order filter
     * @return page with labels
     */
    @PostMapping("/list")
    public PageDTO<OrderDTO> getList(@RequestBody OrderFilterDTO filterDTO) {
        return orderService.getList(filterDTO);
    }

    /**
     * Returns order by id with full information.
     *
     * @param id - order id
     * @return order
     * @throws ResourceNotFoundException - if order not found
     */
    @GetMapping("/{id}")
    public OrderDTO get(@PathVariable int id) throws ResourceNotFoundException {
        return orderService.get(id);
    }

    /**
     * Make an order by current basket for authenticated user.
     *
     * @return user's order location
     * @throws AuthorizationRequiredException - if user not authorized
     * @throws ResourceCannotCreateException  - if order cannot be made
     */
    @PostMapping
    public ResponseEntity<Void> make()
            throws AuthorizationRequiredException, ResourceCannotCreateException {
        return ResponseEntity.created(buildURI(orderService.make())).build();
    }

    /**
     * Update order status (set it as sent) by order id.
     *
     * @param id - order id
     * @return no content
     * @throws ResourceNotFoundException - if order not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> send(@PathVariable int id) throws ResourceNotFoundException {
        orderService.send(id);
        return ResponseEntity.noContent().build();
    }
}

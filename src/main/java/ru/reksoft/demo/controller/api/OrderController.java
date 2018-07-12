package ru.reksoft.demo.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.reksoft.demo.dto.OrderDTO;

import javax.validation.Valid;

import static ru.reksoft.demo.util.ResourceLocationBuilder.buildURI;

@RestController
@RequestMapping("${api-path.order}")
public class OrderController {

    /**
     * Make an order by current basket for authenticated user.
     *
     * @param orderDTO - order form
     * @return user's order location
     */
    @PostMapping
    public ResponseEntity<Void> make(@Valid OrderDTO orderDTO) {
        // todo: make an order service here
        return ResponseEntity.created(buildURI()).build();
    }
}

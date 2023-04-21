package sia.tacocloud.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import sia.tacocloud.data_model.TacoOrder;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
    @GetMapping("/current")
    public String formOrder() {
        return "orderForm";
    }

    // the tacoOrder parameter is obtained from submitting
    // the form from the /orders/current view
    // setComplete() cleans the previous tacoOrder that was linked with the session
    @PostMapping
    public String processOrder(@Valid TacoOrder tacoOrder, Errors errors,
                               SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        log.info("Session submitted: " + tacoOrder);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}

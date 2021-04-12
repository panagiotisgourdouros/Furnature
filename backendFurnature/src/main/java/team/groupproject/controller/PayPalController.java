package team.groupproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import team.groupproject.dto.OrderDto;
import team.groupproject.model.MessageResponse;
import team.groupproject.service.PaypalService;

@RestController
@RequestMapping("/api/paypal")
@CrossOrigin
public class PayPalController {

    @Autowired
    PaypalService service;

    public static final String SUCCESS_URL = "/pay/success";
    public static final String CANCEL_URL = "/pay/cancel";

    @PostMapping("/pay")
    public ResponseEntity<?> createPayment(@RequestBody OrderDto orderDto) {
        try {
            Payment payment = service.createPayment(orderDto.getPrice(), orderDto.getCurrency(), orderDto.getMethod(),
                    orderDto.getIntent(), orderDto.getDescription(), "http://localhost:8080/api/paypal" + CANCEL_URL,
                    "http://localhost:8080/api/paypal" + SUCCESS_URL);
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    System.out.println(link.getHref());
                    return ResponseEntity.ok(new MessageResponse(link.getHref()));
                }
            }

        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(new MessageResponse("cancel.html"));
    }

    //pay pal sends these
    @GetMapping(value = CANCEL_URL)
    public RedirectView cancelPay() {
        return new RedirectView("http://127.0.0.1:5500/cancel.html");
    }

    @GetMapping(value = SUCCESS_URL)
    public RedirectView successPay(@RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                return new RedirectView("http://127.0.0.1:5500/success.html");
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return new RedirectView("http://127.0.0.1:5500/dashboard.html");
    }

}

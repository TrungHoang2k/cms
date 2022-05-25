package cg.wbd.grandemonstration.controllers;

import cg.wbd.grandemonstration.model.Customer;
import cg.wbd.grandemonstration.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("/view/list.jsp");
        List<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/customers/{id}")
    public ModelAndView showInfo(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/view/info.jsp");
        Customer customer = customerService.findOne(id);
        modelAndView.addObject("customer",customer);
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateInfo(@ModelAttribute Customer customer) {
        ModelAndView modelAndView = new ModelAndView("/view/list.jsp");
        System.out.println(customer.getId());
        customerService.save(customer);
        List<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
}

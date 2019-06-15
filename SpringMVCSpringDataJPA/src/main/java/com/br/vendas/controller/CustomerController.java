package com.br.vendas.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.br.vendas.model.Cliente;
import com.br.vendas.repoisitory.CustomerService;

/**
 * Esta é uma classe de controlador Spring MVC típica, que é anotada com a anotação @Controller  . Você pode ver que uma instância do CustomerService  é injetada nessa classe usando a anotação @Autowired .
   Vamos escrever código para os métodos manipuladores nas seções a seguir.

 * @author aniziomaia
 *
 */
 
@Controller
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;
    
    @RequestMapping("/")
    public ModelAndView home() {
    	System.out.println("***************************CustomerController.home");
        List<Cliente> listCustomer = (List<Cliente>) customerService.listAll();
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("listCustomer", listCustomer);
        return mav;
    }
    
    @RequestMapping("/new")
    public String newCustomerForm(Map<String, Object> model) {
    	System.out.println("***************************CustomerController.new");
        Cliente customer = new Cliente();
        model.put("customer", customer);
        return "new_customer";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("customer") Cliente customer) {
    	System.out.println("***************************CustomerController.save");
        customerService.save(customer);
        return "redirect:/";
    }
    
    @RequestMapping("/edit")
    public ModelAndView editCustomerForm(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("edit_customer");
        Cliente customer = customerService.get(id);
        mav.addObject("customer", customer);
     
        return mav;
    }
    
    @RequestMapping("/delete")
    public String deleteCustomerForm(@RequestParam long id) {
        customerService.delete(id);
        return "redirect:/";       
    }
    
    @RequestMapping("/search")
    public ModelAndView search(@RequestParam String keyword) {
        List<Cliente> result = customerService.search(keyword);
        ModelAndView mav = new ModelAndView("search");
        mav.addObject("result", result);
     
        return mav;    
    }
}
package com.br.vendas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.br.vendas.model.Cliente;
import com.br.vendas.repoisitory.ClienterService;

/**
 * Esta é uma classe de controlador Spring MVC típica, que é anotada com a anotação @Controller  . Você pode ver que uma instância do CustomerService  é injetada nessa classe usando a anotação @Autowired .
   Vamos escrever código para os métodos manipuladores nas seções a seguir.

 * @author aniziomaia
 *
 */
 
@Controller
public class ClienteController {
    
    @Autowired
    private ClienterService customerService;
    
    @RequestMapping("/")
    public ModelAndView home() {  
    	System.out.println("*************************CustomerController.home");
        List<Cliente> listCustomer = (List<Cliente>) customerService.listAll();
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("listCustomer", listCustomer);
        return mav;
    }
    
    @RequestMapping("/new")
    public ModelAndView newCustomerForm(@ModelAttribute("customer") Cliente customer) {
    	System.out.println("***************************CustomerController.new");
        customer = new Cliente();
        ModelAndView model = new ModelAndView("new_customer");
        model.addObject("customer", customer);
        return model;
    }
    
    //
    //@RequestBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveCustomer(@Valid @ModelAttribute("customer") Cliente customer, BindingResult result,Errors errors, ModelMap modell) {
    	System.out.println("***************************CustomerController.save.");
    	ModelAndView model = new ModelAndView("index");
    		
//		//validacao manual
//		if(customer != null) {
//			System.out.println(">>>>>>>>>>>teste de retorno result.hasErrors: " + result.hasErrors()); 
//			System.out.println(">>>>>>>>>>>teste de retorno errors.hasErrors: " + errors.hasErrors()); 
//			if(customer.getName().isEmpty())
//				result.rejectValue("name","error.order.sell.tomanyitems","The field name not be empty.");
//			if(customer.getEmail().isEmpty())
//				result.rejectValue("email","error.order.sell.tomanyitems","The field email not be empty.");
//			if(customer.getAddress().isEmpty())
//				result.rejectValue("address","error.order.sell.tomanyitems","The field address not be empty.");
//			 
//			model.addObject(customer);
//    		 return model;
//		}
    		
		if(result.hasErrors()) {
			System.out.println(">>>>>>>>>>>teste de retorno result.hasErrors: " + result.hasErrors());
			model = new ModelAndView("new_customer");
			model.addObject(customer);
    		return model;
	    }
    		
    	customerService.save(customer);
    	 List<Cliente> listCustomer = (List<Cliente>) customerService.listAll();
    	 model.addObject("listCustomer", listCustomer); 
     
        return model;
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
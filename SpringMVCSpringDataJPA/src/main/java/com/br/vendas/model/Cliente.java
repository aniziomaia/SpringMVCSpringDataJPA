package com.br.vendas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    //@NotBlank(message = "Name é obrigatório")
    @Size(min=5)
    //@NotEmpty(message = "Name é obrigatório")
    @NotNull(message = "Name é obrigatório")
    private String name;
   
    @Pattern(regexp="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[AZa-z0-9]+)*(\\.[A-Za-z]{2,})$")
    @NotNull @Size(min=5)
    @NotEmpty(message = "Email do vinho é obrigatório")
    @Email(message = "O formato do email esta incorreto")
    private String email;
    
    @Size(min=5)
    @NotEmpty(message = "Address do vinho é obrigatório")
    private String address;
 
    public Cliente() {
    }
 
    protected Cliente(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
    
}
package com.br.vendas.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Cliente  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4113373622597675173L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @NotBlank(message = "O nome é obrigatório")
    @Size(min=5)
    @NotEmpty(message = "O nome é obrigatório")
    @NotNull(message = "O nome é obrigatório")
    private String name;
   
    @Pattern(message = "Email fora do padrao", regexp="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[AZa-z0-9]+)*(\\.[A-Za-z]{2,})$")
    @NotNull(message = "Email do cliente é obrigatório")
    @Size(min=5)
    @NotEmpty(message = "Email do cliente é obrigatório")
    @Email(message = "O formato do email esta incorreto")
    private String email;
    
    @Size(min=5)
    @NotEmpty(message = "Address do cliente é obrigatório")
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
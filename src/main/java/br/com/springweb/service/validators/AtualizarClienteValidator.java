package br.com.springweb.service.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.springweb.controllers.exception.FieldError;
import br.com.springweb.entities.Cliente;
import br.com.springweb.entities.dto.ClienteDto;
import br.com.springweb.repositorys.ClienteRepository;

public class AtualizarClienteValidator implements ConstraintValidator<AtualizarCliente, ClienteDto> {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	
	@Override
	public void initialize(AtualizarCliente ann) {
	}

	@Override
	public boolean isValid(ClienteDto clienteDto, ConstraintValidatorContext context) {
		List<FieldError> list = new ArrayList<>();

		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		Integer idUrl = Integer.parseInt(map.get("id"));
		Cliente clienteEmail = clienteRepository.findByEmail(clienteDto.getEmail());
		if(clienteEmail != null && !clienteEmail.getId().equals(idUrl)) {
			list.add(new FieldError("Email","Email ja foi cadastrado na base de dados"));
		}
		

		for (FieldError e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getCampo())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}

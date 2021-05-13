package br.com.springweb.service.validators;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.springweb.controllers.exception.FieldError;
import br.com.springweb.entities.dto.ClienteNovoDTO;
import br.com.springweb.entities.enums.TipoCliente;
import br.com.springweb.utils.ValidadorCPF_CNPJ;

public class AdicionarClienteValidator implements ConstraintValidator<AdicionarCliente, ClienteNovoDTO> {
	@Override
	public void initialize(AdicionarCliente ann) {
	}

	@Override
	public boolean isValid(ClienteNovoDTO clienteNovoDTO, ConstraintValidatorContext context) {
		List<FieldError> list = new ArrayList<>();

		if (clienteNovoDTO.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCodigo())
				&& !ValidadorCPF_CNPJ.isValidCpf(clienteNovoDTO.getCpfOuCnpj())) {
			list.add(new FieldError("CpfOuCnpj", "CPF invalido"));
		}

		if (clienteNovoDTO.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCodigo())
				&& !ValidadorCPF_CNPJ.isValidCnpj(clienteNovoDTO.getCpfOuCnpj())) {
			list.add(new FieldError("CpfOuCnpj", "CNPJ invalido"));
		}

		for (FieldError e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getCampo())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}

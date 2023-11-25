package br.com.locadora.locacao.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.locadora.locacao.entidade.Cliente;
import br.com.locadora.locacao.exception.NegocioException;
import br.com.locadora.locacao.pojo.ClientePojo;
import br.com.locadora.locacao.repository.ClienteRepository;

@Service
public class ClienteService {

	private final ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public ClientePojo salvar(Cliente cliente) {
		validarCliente(cliente);
		return new ClientePojo(clienteRepository.save(cliente));
	}

	private void validarCliente(Cliente cliente) {
		if (cliente.getNome() == null || cliente.getNome() == "") {
			throw new NegocioException("Nome do Cliente é obrigatório");
		}

		List<Cliente> clienteBd = clienteRepository.findByNome(cliente.getNome());
		if (cliente != null && !clienteBd.isEmpty()) {
			throw new NegocioException("Cliente já cadastrado");
		}

	}
	
	public List<ClientePojo> recuperarClientes(){
		List<Cliente> listcliente = clienteRepository.findAll();
		List<ClientePojo> listClientePojo = new ArrayList<>();
		for (Cliente cliente : listcliente) {
			listClientePojo.add(new ClientePojo(cliente));
		}
		return listClientePojo;
	}
	
	public ClientePojo recuperarCliente(Long id) {
		Optional<Cliente> ClienteOptional = clienteRepository.findById(id);
		if (ClienteOptional.isPresent()) {
			return new ClientePojo(ClienteOptional.get());
		}
		return null;
	}
	
	public void deletarCliente(Long id) {
		clienteRepository.deleteById(id);
	}
}

	


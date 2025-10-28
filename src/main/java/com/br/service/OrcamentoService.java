package com.br.service;

import com.br.model.CertificadoDigital;
import com.br.model.Orcamento;
import com.br.repository.OrcamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrcamentoService {

    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @Autowired
    private ProdutoService produtoService; // Para buscar as regras de validade/preço

    // O @Transactional é CRUCIAL para garantir que tudo (Mestre e Detalhes) 
    // seja salvo na mesma transação.
    @Transactional
    public Orcamento salvarOrcamento(Orcamento orcamento) {
        
        // 1. Lógica do Mestre (Orcamento):
        if (orcamento.getId() == null) {
            orcamento.setDataSolicitacao(LocalDate.now());
            // Define status inicial como RASCUNHO (se ainda não tiver sido definido)
            if (orcamento.getStatus() == null) {
                orcamento.setStatus("RASCUNHO");
            }
        }
        
        // 2. Lógica dos Detalhes (CertificadoDigital):
        for (CertificadoDigital item : orcamento.getItens()) {
            
            // a. Liga o Detalhe de volta ao Mestre
            item.setOrcamento(orcamento); 
            
            // b. Validação da Data de Validade e Preço (Regras de Negócio)
            // *Normalmente, você usaria o ID de ProdutoOpcao aqui, mas vamos simular o cálculo.*
            
            // Calculando a Data de Validade (Regra que você pediu):
            LocalDate dataValidade = orcamento.getDataSolicitacao().plusMonths(item.getValidadeMeses());
            item.setDataValidade(dataValidade);
            
            // c. Definindo o status ATIVO (Botão de envio)
            // Assumimos que o frontend vai nos dizer se o cliente ativou ou não (ativo=true/false)
            if (item.getAtivo() == null) {
                item.setAtivo(false); 
            }
        }
        
        // 3. Salva a transação completa (Mestre e Detalhes)
        // Graças ao CascadeType.ALL na entidade Orcamento, o JPA salva os Detalhes automaticamente.
        return orcamentoRepository.save(orcamento);
    }
    
    // Método para buscar um orçamento completo com seus detalhes
    public Optional<Orcamento> buscarOrcamentoCompleto(Long id) {
        return orcamentoRepository.findById(id);
        // Nota: Por padrão, o fetch dos Detalhes será Lazy. 
        // Dependendo da sua configuração, você pode precisar de um @Query aqui 
        // para forçar o carregamento EAGER dos itens.
    }

	public List<Orcamento> listarOrcamentos() {
		// TODO Auto-generated method stub
		return null;
	}
    
    // ... Métodos para listar, atualizar status, etc. ...
}
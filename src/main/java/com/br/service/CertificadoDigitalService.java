package com.br.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.model.CertificadoDigital;
import com.br.repository.CertificadoDigitalRepository;

import java.util.List;

@Service
public class CertificadoDigitalService {

    @Autowired
    private CertificadoDigitalRepository certificadoDigitalRepository;

    public List<CertificadoDigital> listarTodos() {
        return certificadoDigitalRepository.findAll();
    }

    public CertificadoDigital salvar(CertificadoDigital certificado) {
        aplicarValidade(certificado);
        return certificadoDigitalRepository.save(certificado);
    }

    public CertificadoDigital buscarPorId(Long id) {
        return certificadoDigitalRepository.findById(id).orElse(null);
    }


    public void excluir(Long id) {
        certificadoDigitalRepository.deleteById(id);
    }

    private void aplicarValidade(CertificadoDigital certificado) {
        String nome = certificado.getNomeProduto();
        Integer meses = certificado.getValidadeMeses();

        switch (nome) {
            case "A1":
                certificado.setValidadeMeses(12);
                break;
            case "A3":
                if (meses == null || !(meses == 12 || meses == 24 || meses == 36)) {
                    certificado.setValidadeMeses(12);
                }
                break;
            case "NUVEM":
                certificado.setValidadeMeses(60);
                break;
        }
    }
}
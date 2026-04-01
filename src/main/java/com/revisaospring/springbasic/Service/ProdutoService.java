package com.revisaospring.springbasic.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revisaospring.springbasic.Entity.Produto;
import com.revisaospring.springbasic.Repository.ProdutoRepository;

@Service
public class ProdutoService {
    
@Autowired
private ProdutoRepository ProdutoRepository;


public List<Produto> buscarTodos () {
    return ProdutoRepository.findAll();
}

public Optional<Produto> buscarPorId(Long id) {
    return ProdutoRepository.findById(id);
}


public Produto cadastrarProduto(Produto oProduto) {
    return ProdutoRepository.save(oProduto);
}


public Produto alteraProduto(Produto dadosAtualizar, Long id) { 
   
    Produto produtoBuscado = buscarPorId(id).orElseThrow(
  () -> new IllegalArgumentException("Produto Não Encontrado"));
  

    produtoBuscado.setNome(dadosAtualizar.getNome());
    produtoBuscado.setCategoria(dadosAtualizar.getCategoria());
    produtoBuscado.setValor(dadosAtualizar.getValor());

    return ProdutoRepository.save(produtoBuscado);

}

public void deletarProduto(Long id) {
    ProdutoRepository.deleteById(id);
}


}
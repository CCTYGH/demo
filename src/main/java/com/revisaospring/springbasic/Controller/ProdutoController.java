package com.revisaospring.springbasic.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.revisaospring.springbasic.Entity.Produto;
import com.revisaospring.springbasic.Service.ProdutoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;








@Controller
@RequestMapping("/produtoctr")
public class ProdutoController {

@Autowired
private ProdutoService oProdutoService;



@GetMapping("/main")
public String main() {
    return "main";
}


@GetMapping("/listarProduto")
public String telalistarProdtuo(Model model) {

    model.addAttribute("produtos",
    oProdutoService.buscarTodos());
    return "listarProduto";
}


 @GetMapping("/formCadastrar")
 public String telacadastrarProduto(Model model) {
     model.addAttribute("produto",new Produto());
     return "cadastrarProduto";
 }
 
 @PostMapping("/salvar")
 public String salvarProduto(@ModelAttribute Produto oProduto) {
oProdutoService.cadastrarProduto(oProduto);
     return "redirect:/produtoctr/listarProduto";
 }
 
        @PostMapping("/salvarProduto")
    public String cadastrarProduto(@ModelAttribute Produto oProduto) {

        oProdutoService.cadastrarProduto(oProduto);
        return "redirect:/produtoctr/listarProduto";

    }

    @GetMapping("/formAlterar/{id}")
    public String telaAlterarProduto(@PathVariable long id, Model oModel){
     
        Produto oProduto = oProdutoService.buscarPorId(id).orElseThrow(
            () -> new IllegalArgumentException("Produto não Encontrado"));

            oModel.addAttribute( "produtoEditar", oProduto);
            return "editarProduto";
    }

    @PostMapping("alteraProduto/{id}")
    public String alteraProduto(@PathVariable Long id, @ModelAttribute Produto oProduto) {
       oProdutoService.alteraProduto(oProduto, id);
        return "redirect:/produtoctr/listarProduto";
    }
    

@GetMapping("/deletarProduto/{id}")
public String deletaP(@PathVariable Long id) {
    oProdutoService.deletarProduto(id);
    return "redirect:/produtoctr/listarProduto";
}



}



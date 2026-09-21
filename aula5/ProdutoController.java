package com.exemplo.gestao.controller;

import com.exemplo.gestao.model.Produto;
import com.exemplo.gestao.repository.ProdutoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // Listar todos os produtos
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll());
        return "produtos/lista";
    }

    // Abrir formulário de novo produto
    @GetMapping("/novo")
    public String novoFormulario(Model model) {
        model.addAttribute("produto", new Produto());
        return "produtos/form";
    }

    // Salvar produto
    @PostMapping
    public String salvar(@ModelAttribute Produto produto) {
        produtoRepository.save(produto);
        return "redirect:/produtos";
    }

    // Deletar produto
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        produtoRepository.deleteById(id);
        return "redirect:/produtos";
    }
}
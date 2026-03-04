package com.projeto.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.projeto.app.model.Livro;
import com.projeto.app.repository.LivroRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping
    public String listarLivros(Model model) {
        model.addAttribute("livros", livroRepository.findAll());
        return "livros";
    }

    @GetMapping("/lista")
    public String listaAdmin(Model model) {
        model.addAttribute("livros", livroRepository.findAll());
        return "listaLivros";
    }

    @GetMapping("/novo")
    public String novoLivro(Model model) {
        model.addAttribute("livro", new Livro());
        return "formLivro";
    }

    @PostMapping("/salvar")
    public String salvarLivro(@ModelAttribute Livro livro) {

        try {

            // Verifica se enviou imagem
            if (livro.getArquivoImagem() != null &&
                    !livro.getArquivoImagem().isEmpty()) {

                String nomeArquivo = livro.getArquivoImagem().getOriginalFilename();

                String pasta = "src/main/resources/static/imagens/";

                Path diretorio = Paths.get(pasta);

                // Cria pasta se não existir
                if (!Files.exists(diretorio)) {
                    Files.createDirectories(diretorio);
                }

                Path caminhoArquivo = diretorio.resolve(nomeArquivo);

                Files.write(caminhoArquivo,
                        livro.getArquivoImagem().getBytes());

                livro.setImagem(nomeArquivo);
            }

            livroRepository.save(livro);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/livros/lista";
    }

    @GetMapping("/editar/{id}")
    public String editarLivro(@PathVariable Long id, Model model) {

        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));

        model.addAttribute("livro", livro);
        return "formLivro";
    }

    @GetMapping("/excluir/{id}")
    public String excluirLivro(@PathVariable Long id) {
        livroRepository.deleteById(id);
        return "redirect:/livros/lista";
    }
}
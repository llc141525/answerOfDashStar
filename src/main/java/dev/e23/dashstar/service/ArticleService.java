package dev.e23.dashstar.service;

import dev.e23.dashstar.model.Article;
import dev.e23.dashstar.repository.ArticleRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ArticleService {

    @Inject
    private ArticleRepository articleRepository;

    public void createArticle(Article article) {
        articleRepository.create(article);
    }

}

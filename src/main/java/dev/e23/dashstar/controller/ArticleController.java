package dev.e23.dashstar.controller;

import dev.e23.dashstar.model.Article;
import dev.e23.dashstar.service.ArticleService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/articles")
public class ArticleController {

    @Inject
    private ArticleService articleService;

    @GET
    @Path("/")
    public String getArticles() {
        return "Articles";
    }

    @POST
    @Path("/")
    public String postArticles() {
        Article article = new Article();
        article.setAuthorId(1);
        article.setTitle("Hello World");
        article.setContent("This is a test article.");
        articleService.createArticle(article);
        return "Article created";
    }
}

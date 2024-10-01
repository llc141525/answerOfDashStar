package dev.e23.dashstar.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/articles")
public class ArticleController {

    @GET
    @Path("/")
    public String getArticles() {
        return "Articles";
    }
}

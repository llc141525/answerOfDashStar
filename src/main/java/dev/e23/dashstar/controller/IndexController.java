package dev.e23.dashstar.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/")
public class IndexController {

    @GET
    public String index() {
        return "Hello World!";
    }
}

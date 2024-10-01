package dev.e23.dashstar.model.response;

import dev.e23.dashstar.model.User;
import lombok.Data;

import java.util.List;

@Data
public class UserGetResponse {
    private int code;
    private int total;
    private List<User> data;
}

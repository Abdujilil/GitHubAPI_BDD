package com.github.utilities;

import com.github.pojos.requestPayload.PostRepository;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GlobalDataUtils {

    private ValidatableResponse validatableResponse;
    private Response response;
    private PostRepository postRepo;
    private String branchName;
    private String sha;

}

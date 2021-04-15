package com.github.step_definitions;

import com.github.pojos.requestPayload.PostRepository;
import com.github.pojos.responsePayload.repository.GetRepository;
import com.github.utilities.GlobalDataUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.github.utilities.APIUtils.*;
import static com.github.utilities.DataUtils.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class RepositoryStepDefs {

    private GlobalDataUtils globalDataUtils;

    public RepositoryStepDefs(StepData stepData) {
        globalDataUtils = stepData.globalDataUtils;
    }

    @Given("request for creating repo with {string} requirement")
    public void request_for_creating_repo_with_requirement(String infoType) {
        PostRepository postRepo = new PostRepository();
        if (infoType.equalsIgnoreCase("minimum")) {
            postRepo = generateRepoMinReqPOJO();
        } else if (infoType.equalsIgnoreCase("partial")) {
            postRepo = generateRepoPartReqPOJO();
        } else if (infoType.equalsIgnoreCase("full")) {
            postRepo = generateRepoFullRepPOJO();
        }

        post(postRepo, "/user/repos");
        globalDataUtils.setPostRepo(postRepo);
    }

    @And("retrieve created repo information")
    public void retrieveCreatedRepoInformation() {
        Map<String,Object> pathParams = new LinkedHashMap<>();
        pathParams.put("name",globalDataUtils.getPostRepo().getName());
        ValidatableResponse response = get(pathParams, "/repos/{owner}/{name}");
        globalDataUtils.setValidatableResponse(response);
    }

    @Then("verify the minimum repo information matches expected")
    public void verify_the_minimum_repo_information_matches_expected() {
        ValidatableResponse response = globalDataUtils.getValidatableResponse();

        response
                .body("name", is(globalDataUtils.getPostRepo().getName()))
                .body("private", is(false));
        deleteRepo(globalDataUtils.getPostRepo().getName());
    }

    @Then("verify the partial repo information matches expected")
    public void verify_the_partial_repo_information_matches_expected() {
        ValidatableResponse response = globalDataUtils.getValidatableResponse();
        GetRepository getRepo = response.extract().body().as(GetRepository.class);
        PostRepository postRepo = globalDataUtils.getPostRepo();

        assertThat(getRepo.getName(),is(postRepo.getName()));
        assertThat(getRepo.getDescription(),is(postRepo.getDescription()));
        assertThat(getRepo.isPrivate(),is(postRepo.isPrivate()));
        assertThat(getRepo.isHas_wiki(),is(postRepo.isHas_wiki()));
        deleteRepo(getRepo.getName());
    }

    @Then("the basic repo information matches expected")
    public void the_basic_repo_information_matches_expected() {
        ValidatableResponse response = globalDataUtils.getValidatableResponse();
        GetRepository getRepo = response.extract().body().as(GetRepository.class);
        PostRepository postRepo = globalDataUtils.getPostRepo();

        assertThat(getRepo.getName(),is(postRepo.getName()));
        assertThat(getRepo.getDescription(),is(postRepo.getDescription()));
        assertThat(getRepo.isPrivate(),is(postRepo.isPrivate()));
        deleteRepo(getRepo.getName());
    }

    @Then("the repo navigation tab settings matches expected")
    public void the_repo_navigation_tab_settings_matches_expected() {
        ValidatableResponse response = globalDataUtils.getValidatableResponse();
        GetRepository getRepo = response.extract().body().as(GetRepository.class);
        PostRepository postRepo = globalDataUtils.getPostRepo();

        assertThat(getRepo.isHas_issues(),is(postRepo.isHas_issues()));
        assertThat(getRepo.isHas_projects(),is(postRepo.isHas_projects()));
        assertThat(getRepo.isHas_downloads(),is(postRepo.isHas_downloads()));
        assertThat(getRepo.isHas_wiki(),is(postRepo.isHas_wiki()));
        deleteRepo(getRepo.getName());
    }

    @Then("the repo pull request settings matches expected")
    public void the_repo_pull_request_settings_matches_expected() {
        ValidatableResponse response = globalDataUtils.getValidatableResponse();
        GetRepository getRepo = response.extract().body().as(GetRepository.class);
        PostRepository postRepo = globalDataUtils.getPostRepo();

        assertThat(getRepo.isAllow_squash_merge(),is(postRepo.isAllow_squash_merge()));
        assertThat(getRepo.isAllow_merge_commit(),is(postRepo.isAllow_merge_commit()));
        assertThat(getRepo.isAllow_rebase_merge(),is(postRepo.isAllow_rebase_merge()));
        assertThat(getRepo.isDelete_branch_on_merge(),is(postRepo.isDelete_branch_on_merge()));
        deleteRepo(getRepo.getName());
    }

    @Then("the repo timestamps matches expected")
    public void the_repo_timestamps_matches_expected() {
        ValidatableResponse response = globalDataUtils.getValidatableResponse();
        GetRepository getRepo = response.extract().body().as(GetRepository.class);

        assertThat(getRepo.getCreated_at(),is(LocalDate.now()));
        assertThat(getRepo.getUpdated_at(),is(LocalDate.now()));
        assertThat(getRepo.getPushed_at(),is(LocalDate.now()));
        deleteRepo(getRepo.getName());
    }

    @Then("the repo owner information matches expected")
    public void the_repo_owner_information_matches_expected() {
        ValidatableResponse response = globalDataUtils.getValidatableResponse();
        GetRepository getRepo = response.extract().body().as(GetRepository.class);

        assertThat(getRepo.getOwner().getLogin(),is("ownerName"));
        assertThat(getRepo.getOwner().getHtml_url(),is("githubLink"));
        assertThat(getRepo.getOwner().getType(),is("User"));
        assertThat(getRepo.getOwner().isAdmin(),is(false));
        deleteRepo(getRepo.getName());
    }

    @Then("the repo permissions settings matches expected")
    public void the_repo_permissions_settings_matches_expected() {
        ValidatableResponse response = globalDataUtils.getValidatableResponse();
        GetRepository getRepo = response.extract().body().as(GetRepository.class);

        assertThat(getRepo.getPermissions().isAdmin(),is(true));
        assertThat(getRepo.getPermissions().isPull(),is(true));
        assertThat(getRepo.getPermissions().isPush(),is(true));
        deleteRepo(getRepo.getName());
    }

    @When("request for updating repo name")
    public void request_for_updating_repo_name() {
        PostRepository postRepo = globalDataUtils.getPostRepo();
        String repoName = postRepo.getName();
        String newRepoName = generateRandomRepoName();
        postRepo.setName(newRepoName);
        Map<String,Object> pathParams = new LinkedHashMap<>();
        pathParams.put("repo",repoName);

        patch(postRepo,pathParams,"/repos/{owner}/{repo}");

        globalDataUtils.setPostRepo(postRepo);
    }

    @Then("the repo name and timestamp matches expected")
    public void the_repo_name_and_timestamp_matches_expected() {
        ValidatableResponse response = globalDataUtils.getValidatableResponse();
        GetRepository getRepo = response.extract().body().as(GetRepository.class);
        PostRepository postRepo = globalDataUtils.getPostRepo();

        assertThat(getRepo.getName(),is(postRepo.getName()));
        assertThat(getRepo.getUpdated_at(),is(LocalDate.now()));
        deleteRepo(getRepo.getName());
    }

    @When("request for deleting the repo")
    public void request_for_deleting_the_repo() {
        PostRepository postRepo = globalDataUtils.getPostRepo();
        Map<String,Object> pathParams = new LinkedHashMap<>();
        pathParams.put("repo",postRepo.getName());
        Response response = delete(pathParams,"/repos/{owner}/{repo}");
        globalDataUtils.setResponse(response);
    }

    @Then("verify the repo is deleted")
    public void verify_the_repo_is_deleted() {
        Response response = globalDataUtils.getResponse();

        assertThat(response.statusCode(),is(204));
        assertThat(response.body().asString(),is(emptyString()));
    }

    @When("request for transferring the repo to {string}")
    public void request_for_transferring_the_repo_to(String newOwnerName) {
        PostRepository postRepo = globalDataUtils.getPostRepo();
        Map<String,Object> newOwner = new LinkedHashMap<>();
        newOwner.put("new_owner",newOwnerName);
        Map<String,Object> pathParams = new LinkedHashMap<>();
        pathParams.put("owner","ownerName");
        pathParams.put("repo",postRepo.getName());

        Response response = post(newOwner, pathParams, "/repos/{owner}/{repo}/transfer");
        globalDataUtils.setResponse(response);
    }

    @Then("verify the repo is transferred")
    public void verify_the_repo_is_transferred() {
        Response response = globalDataUtils.getResponse();

        assertThat(response.statusCode(),is(202));
        assertThat(response.contentType(),is(("application/json; charset=utf-8")));
    }

}

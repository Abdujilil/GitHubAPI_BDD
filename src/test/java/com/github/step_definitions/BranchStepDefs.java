package com.github.step_definitions;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pojos.responsePayload.branch.Branch;
import com.github.utilities.GlobalDataUtils;
import com.github.utilities.JsonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.List;

import static com.github.utilities.APIUtils.*;
import static com.github.utilities.DataUtils.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class BranchStepDefs {

    private GlobalDataUtils globalDataUtils;

    public BranchStepDefs(StepData stepData) {
        globalDataUtils = stepData.globalDataUtils;
    }

    @Given("request for creating new branch")
    public void request_for_creating_new_branch() {
        String repoName = globalDataUtils.getPostRepo().getName();
        List<Branch> branches = getBranches(repoName);
        String sha = branches
                .stream()
                .filter(p -> p.getName().equals("main"))
                .findFirst()
                .orElse(null)
                .getCommit().getSha();

        ObjectNode branchNode = JsonUtils.createNode();
        String branchName = generateRandomBranchName();
        branchNode.put("ref","refs/heads/"+branchName);
        branchNode.put("sha",sha);
        postBranch(branchNode,repoName);

        globalDataUtils.setBranchName(branchName);
        globalDataUtils.setSha(sha);
    }

    @Then("the branch information matches expected")
    public void the_branch_information_matches_expected() {
        String repoName = globalDataUtils.getPostRepo().getName();
        String branchName = globalDataUtils.getBranchName();
        Branch branch = getBranch(repoName,branchName);

        assertThat(branch.getName(),is(branchName));
        assertThat(branch.getCommit().getSha(),is(globalDataUtils.getSha()));
        deleteRepo(repoName);
    }

    @Given("request for updating branch name")
    public void request_for_updating_branch_name() {
        String repoName = globalDataUtils.getPostRepo().getName();
        String newBranchName = generateRandomBranchName();
        renameBranch(repoName,globalDataUtils.getBranchName(),newBranchName);

        globalDataUtils.setBranchName(newBranchName);
    }

}

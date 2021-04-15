package com.github.utilities;

import com.github.javafaker.Faker;
import com.github.pojos.requestPayload.PostRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataUtils {

    private static Faker faker = new Faker();

    public static String generateTimeStamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMdhhmmsSSS"));
    }

    public static String generateRandomRepoName() {
        return "APITest"+ generateTimeStamp();
    }

    public static String generateRandomBranchName() {
        return "Feature"+ generateTimeStamp();
    }

    public static String generateLoremText() {
        return faker.lorem().paragraph(2);
    }

    public static PostRepository generateRepoMinReqPOJO() {
        String repoName = generateRandomRepoName();
        PostRepository postRepository = new PostRepository(repoName);
        postRepository.setAuto_init(true);
        return postRepository;
    }

    public static PostRepository generateRepoPartReqPOJO() {
        String repoName = generateRandomRepoName();
        String description = generateLoremText();
        return new PostRepository(repoName,description,true,false);
    }

    public static PostRepository generateRepoFullRepPOJO() {
        PostRepository postRepository = new PostRepository();
        String repoName = generateRandomRepoName();
        String description = generateLoremText();
        postRepository.setName(repoName);
        postRepository.setDescription(description);
        postRepository.setPrivate(true);
        postRepository.setHas_issues(false);
        postRepository.setHas_projects(false);
        postRepository.setHas_wiki(false);
        postRepository.setAuto_init(true);
        postRepository.setAllow_squash_merge(false);
        postRepository.setAllow_merge_commit(true);
        postRepository.setAllow_rebase_merge(false);
        postRepository.setDelete_branch_on_merge(false);
        postRepository.setHas_downloads(false);
        postRepository.set_template(false);
        return postRepository;
    }

}

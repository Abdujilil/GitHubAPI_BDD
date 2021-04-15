package com.github.pojos.responsePayload.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetRepository {

    private String name;
    @JsonProperty("private")
    private boolean isPrivate;
    private Owner owner;
    private String description;
    private LocalDate created_at;
    private LocalDate updated_at;
    private LocalDate pushed_at;
    private boolean has_issues;
    private boolean has_projects;
    private boolean has_downloads;
    private boolean has_wiki;
    private Permissions permissions;
    private boolean allow_squash_merge;
    private boolean allow_merge_commit;
    private boolean allow_rebase_merge;
    private boolean delete_branch_on_merge;

}

package com.github.pojos.requestPayload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PostRepository {

    private String name;
    private String description;
    @JsonProperty("private")
    private boolean isPrivate;
    private boolean has_issues;
    private boolean has_projects;
    private boolean has_wiki;
    private boolean auto_init;
    private boolean allow_squash_merge;
    private boolean allow_merge_commit;
    private boolean allow_rebase_merge;
    private boolean delete_branch_on_merge;
    private boolean has_downloads;
    private boolean is_template;

    public PostRepository(String name) {
        this.name = name;
    }

    public PostRepository(String name, String description, boolean isPrivate, boolean has_wiki) {
        this.name = name;
        this.description = description;
        this.isPrivate = isPrivate;
        this.has_wiki = has_wiki;
    }
}

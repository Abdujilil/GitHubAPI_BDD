package com.github.pojos.responsePayload.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner {

    private String login;
    private String html_url;
    private String type;
    @JsonProperty("site_admin")
    private boolean admin;

}

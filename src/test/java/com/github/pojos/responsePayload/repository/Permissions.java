package com.github.pojos.responsePayload.repository;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Permissions {

    private boolean admin;
    private boolean push;
    private boolean pull;

}

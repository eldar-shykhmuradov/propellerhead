package com.elworld.propellerhead.models.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class CustomerNoteRequest {

    private String text;
    @NotBlank
    private String title;

}

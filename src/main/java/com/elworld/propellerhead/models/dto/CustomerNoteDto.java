package com.elworld.propellerhead.models.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerNoteDto {

    private Long id;
    private Long customerId;
    private String text;
    private String title;
    private LocalDateTime createTimestamp;

}

package com.elworld.propellerhead.models.response;

import com.elworld.propellerhead.models.dto.CustomerNoteDto;
import lombok.Data;

@Data
public class CustomerNoteResponse {

    private final CustomerNoteDto note;
}

package com.elworld.propellerhead.models.response;

import com.elworld.propellerhead.models.dto.CustomerNoteDto;
import lombok.Data;

import java.util.List;

@Data
public class CustomerNotesResponse {

    private final List<CustomerNoteDto> notes;
    private final int notesNumber;
}

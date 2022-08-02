package com.canban.web.core.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;


@Data
@Builder
public class KanbanBoardDetailsRs {
    private Long id;
    private String name;
    private String creator;
    private Set<String> users;
}

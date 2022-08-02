package com.canban.web.core.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class KanbanBoardDetailsRs {
    private Long id;
    private String name;
    private String creator;
}

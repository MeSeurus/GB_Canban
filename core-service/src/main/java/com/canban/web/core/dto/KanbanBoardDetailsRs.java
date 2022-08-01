package com.canban.web.core.dto;

import com.canban.web.core.enums.Priority;
import com.canban.web.core.enums.State;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class KanbanBoardDetailsRs {
    private Long id;
    private String name;
    private String creator;
}

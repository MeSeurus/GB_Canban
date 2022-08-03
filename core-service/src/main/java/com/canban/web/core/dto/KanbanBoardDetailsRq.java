package com.canban.web.core.dto;

import com.canban.web.core.enums.Priority;
import com.canban.web.core.enums.State;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class KanbanBoardDetailsRq {
    private String name;
    private String createdBy;
}

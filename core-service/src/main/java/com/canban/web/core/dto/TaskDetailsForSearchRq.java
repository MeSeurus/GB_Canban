package com.canban.web.core.dto;

import com.canban.api.core.Priority;
import com.canban.api.core.State;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Модель для поиска задач")
public class TaskDetailsForSearchRq {

    @Schema(description = "Id канбан-доски", required = true)
    private Long boardId;

    @Schema(description = "Полное или частичное название задачи", required = false)
    private String titlePart;

    @Schema(description = "Cоздатель задачи", required = false)
    private String userCreator;

    @Schema(description = "Исполнитель задачи", required = false)
    private String userExecutor;

    @Schema(description = "Максимальная дата начала задачи", required = false)
    private String maxBeginDate;

    @Schema(description = "Минимальная дата начала задачи", required = false)
    private String minBeginDate;

    @Schema(description = "Минимальная дата окончания задачи", required = false)
    private String maxEndDate;

    @Schema(description = "Минимальная дата окончания задачи", required = false)
    private String minEndDate;

    @Schema(description = "Максимальная фактическая дата окончания задачи", required = false)
    private String maxActualEndDate;

    @Schema(description = "Минимальная фактическая дата окончания задачи", required = false)
    private String minActualEndDate;

    @Schema(description = "Статус задачи", required = false)
    private State state;

    @Schema(description = "Приоритет задачи", required = false)
    private Priority priority;

}

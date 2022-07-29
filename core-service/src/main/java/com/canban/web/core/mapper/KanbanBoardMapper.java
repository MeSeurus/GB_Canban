package com.canban.web.core.mapper;

import com.canban.api.core.EventDto;
import com.canban.api.core.TaskDto;
import com.canban.web.core.dto.KanbanBoardDetailsRq;
import com.canban.web.core.dto.KanbanBoardDetailsRs;
import com.canban.web.core.entities.Event;
import com.canban.web.core.entities.KanbanBoard;
import com.canban.web.core.entities.Task;
import com.canban.web.core.enums.Priority;
import com.canban.web.core.enums.State;
import org.springframework.stereotype.Component;


@Component
public class KanbanBoardMapper {

    public KanbanBoard dtoToEntity(KanbanBoardDetailsRq kanbanBoardDetailsRq) {
        return KanbanBoard.builder()
                .name(kanbanBoardDetailsRq.getName())
                .build();
    }

    public KanbanBoardDetailsRs entityToDto(KanbanBoard kanbanBoard) {
        return KanbanBoardDetailsRs.builder()
                .id(kanbanBoard.getId())
                .name(kanbanBoard.getName())
                .creator(kanbanBoard.getCreator())
                .build();
    }

}

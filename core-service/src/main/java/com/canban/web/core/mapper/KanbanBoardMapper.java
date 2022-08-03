package com.canban.web.core.mapper;

import com.canban.web.core.dto.KanbanBoardDetailsRq;
import com.canban.web.core.dto.KanbanBoardDetailsRs;
import com.canban.web.core.entities.KanbanBoard;
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
                .createdBy(kanbanBoard.getCreatedBy())
                .users(kanbanBoard.getUsers())
                .build();
    }

}

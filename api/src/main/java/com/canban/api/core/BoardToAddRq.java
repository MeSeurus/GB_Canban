package com.canban.api.core;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardToAddRq {

    @Schema(description = "ID доски", required = true, example = "1")
    private Long boardId;

    @Schema(description = "Имя пользователя для добавления на доску", required = true, example = "user1")
    private String userToAdd;

}

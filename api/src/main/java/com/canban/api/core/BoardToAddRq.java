package com.canban.api.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardToAddRq {

    private Long boardId;
    private String userToAdd;

}

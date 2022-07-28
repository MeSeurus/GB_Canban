package com.canban.auth.activemqevents;

import com.canban.api.auth.RegistrationUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeStatusEvent {
    static final long serialVersionUID = -8863620718965821728L;

    private String username;

}

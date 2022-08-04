package com.canban.api.activemqevents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordRemindEvent {
    private String username;
    private String passcode;
    private String email;
}

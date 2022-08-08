package com.canban.api.activemqevents;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivationEvent {
    private String username;
    private String code;
    private String email;
}

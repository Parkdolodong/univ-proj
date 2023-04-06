package com.example.begin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Res {
    @JsonProperty(value = "http_status")
    private String httpStatus;
    private String result;
    private Long idx;
    private String name;
}

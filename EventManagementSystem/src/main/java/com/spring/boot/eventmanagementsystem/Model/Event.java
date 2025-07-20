package com.spring.boot.eventmanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Event {
    private String iD, description;
    private int capacity;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate, endDate;

}
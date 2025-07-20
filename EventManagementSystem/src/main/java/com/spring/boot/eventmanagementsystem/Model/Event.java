package com.spring.boot.eventmanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Event {
    String iD, description;
    int capacity;

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDateTime startDate, endDate;
}
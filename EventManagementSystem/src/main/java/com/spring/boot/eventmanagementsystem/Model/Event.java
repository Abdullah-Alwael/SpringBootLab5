package com.spring.boot.eventmanagementsystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Event {
    String iD, description;
    int capacity;
    String startDate, endDate;
}

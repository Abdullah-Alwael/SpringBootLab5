package com.spring.boot.eventmanagementsystem.Controller;

import com.spring.boot.eventmanagementsystem.Api.ApiResponse;
import com.spring.boot.eventmanagementsystem.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    ArrayList<Event> events = new ArrayList<>();
    int idCounter = 0;

    @PostMapping("/new")
    public ApiResponse createNewEvent(@RequestBody Event event) {
        if (event.getEndDate().isBefore(event.getStartDate())){
            return new ApiResponse("Error end date can not be before the start date", "400 Bad Request");
        }

        idCounter++;
        event.setID(Integer.toString(idCounter));

        events.add(event);
        return new ApiResponse("Event was created successfully", "200 OK");
    }

    @GetMapping("/list")
    public ArrayList<Event> displayEvents() {
        return events;
    }

    @PutMapping("/update/{iD}")
    public ApiResponse updateEvent(@PathVariable String iD, @RequestBody Event event) {
        boolean updated = false;
        event.setID(iD);

        for (Event e : events) {
            if (e.getID().equals(iD)) {
                events.set(events.indexOf(e), event);
                updated = true;
                break;
            }
        }

        if (updated) {
            return new ApiResponse("Event was updated successfully", "200 OK");
        } else {
            return new ApiResponse("Error, Event does not exist", "404 Not found");
        }
    }

    @DeleteMapping("/delete/{iD}")
    public ApiResponse deleteEvent(@PathVariable String iD) {
        boolean deleted = false;

        for (Event e : events) {
            if (e.getID().equals(iD)) {
                events.remove(e);
                deleted = true;
                break;
            }
        }

        if (deleted) {
            return new ApiResponse("Event was deleted successfully", "200 OK");
        } else {
            return new ApiResponse("Error, Event does not exist", "404 Not found");
        }
    }

    @PutMapping("/change-capacity/{iD}/{capacity}")
    public ApiResponse changeCapacity(@PathVariable String iD, @PathVariable int capacity) {
        if (capacity < 0) {
            return new ApiResponse("Error, capacity can not be negative", "400 Bad Request");
        }

        boolean capacityChanged = false;

        for (Event e : events) {
            if (e.getID().equals(iD)) {
                e.setCapacity(capacity);
                capacityChanged = true;
                break;
            }
        }

        if (capacityChanged) {
            return new ApiResponse("Event capacity changed successfully", "200 OK");
        } else {
            return new ApiResponse("Error, Event does not exist", "404 Not found");
        }
    }

    @GetMapping("/search/{iD}")
    public Event searchEventId(@PathVariable String iD) {
        for (Event e : events) {
            if (e.getID().equals(iD)) {
                return e;
            }
        }
        return new Event(null, "Not found", 0,
                null,
                null);
    }

}

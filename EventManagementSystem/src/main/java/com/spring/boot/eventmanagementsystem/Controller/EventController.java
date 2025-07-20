package com.spring.boot.eventmanagementsystem.Controller;

import com.spring.boot.eventmanagementsystem.Api.ApiResponse;
import com.spring.boot.eventmanagementsystem.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {
    //    Q3 : Create event system , where you can get all the event , add ,
//    remove , update event.
    ArrayList<Event> events = new ArrayList<>();
    int idCounter = 0;

    //• Create a new event (ID , description , capacity, startDate , endDate)
    @PostMapping("/new")
    public ApiResponse createNewEvent(@RequestBody Event event) {
        events.add(event);
        return new ApiResponse("Event was created successfully", "200 OK");
    }

    //• Display all event .
    @GetMapping("/list")
    public ArrayList<Event> displayEvents() {
        return events;
    }

    //• Update an event
    @PutMapping("/update/{iD}")
    public ApiResponse updateEvent(@PathVariable String iD, @RequestBody Event event) {
        boolean updated = false;

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

    //• Delete an event
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

//• Change capacity
    @PutMapping("/change-capacity/{iD}/{capacity}")
    public ApiResponse changeCapacity(@PathVariable String iD, @PathVariable int capacity){
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
//• Search for a event by given id
//    Hint ( use @JsonFormat(pattern="yyyy-MM-dd") and LocalDateTime )
}

package io.pivotal.pal.tracker;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    public long currentId=1L;
    private HashMap<Long, TimeEntry> timeEntries = new HashMap<>();

    @Override
    public TimeEntry create(TimeEntry timeEntry){
        Long id = currentId++;
        TimeEntry newTimeEntry = new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(),
                timeEntry.getDate(), timeEntry.getHours());

        timeEntries.put(id, newTimeEntry);
        return newTimeEntry;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return timeEntries.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntries.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if(find(id) == null)
            return null;

        TimeEntry newTimeEntry = new TimeEntry(id,
                timeEntry.getProjectId(), timeEntry.getUserId(),
                timeEntry.getDate(), timeEntry.getHours());
        timeEntries.put(id, newTimeEntry);
        return newTimeEntry;
    }

    /* @Override
    public List<TimeEntry> delete(long id) {
        timeEntries.remove(id);
        return new ArrayList<>(timeEntries.values());
    } */

    @Override
    public void delete(long id) {
        timeEntries.remove(id);
    }

}

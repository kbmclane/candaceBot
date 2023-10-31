package com.candaceBot.Models;

import java.time.ZoneId;
import java.util.List;
import java.util.UUID;
import java.time.ZonedDateTime;

/**
 * Chore Obj TODO
- members
    * pay/value: int
- methods
    */
public class Chore {
    private String id;
    private Assignee assignee;
    private String description;
    private boolean completed;
    private String frequency;
    private List<String> tags;
    private boolean overdue;
    public String name;
    ZonedDateTime dueDate;
    public Chore(String name, Assignee person,
                 String desc, String freq, List<String> tags){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.assignee = person;
        this.description = desc;
        this.frequency = freq;
        this.tags = tags;
        this.completed = false;
        //use freq to determine next dueDate
    }
    public void remind(){
        ZonedDateTime today = ZonedDateTime.now(ZoneId.of(assignee.getTimeZone()));
        if(today.isAfter(dueDate)){
            assignee.notify("overdue", String.format("%s is overdue!", name));
        } else if (dueDate.isEqual(dueDate)){
            assignee.notify("", String.format("%s is due today!", name));
        }

    }
    public void complete(){
        completed = true;
        assignee.incStreak();
        //compute next dueDate
    }
    public List<String> getTags(){
        return tags;
    }

    public void setName(String newName){
        name = newName;
    }
    public void setAssignee(Assignee newAssignee){
        assignee.removeChore(this);
        assignee = newAssignee;
        assignee.addChore(this);
    }
    public void setFrequency(String newCron){
        frequency = newCron;
        //compute new duedate
        assignee.notify("dateChange", String.format("%s is now due %s", name, dueDate));
    }
    public void setDescription(String desc){
        description = desc;
    }
    public void addTags(List<String> moreTags){
        tags.addAll(moreTags);
        //update dictionary
    }
    public void removeTags(List<String> tagsToDelete){
        tags.removeAll(tagsToDelete);
        //update household dictionary
    }



}


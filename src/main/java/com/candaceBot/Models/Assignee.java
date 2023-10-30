package com.candaceBot.Models;

import java.lang.String;
import java.util.List;

/**
 * Assignee Obj TODO
 - members
     * allowance:
     * pets
 - methods
     * purchase
     * getPaid
     * seeAllowance
     * seePets
 */
public class Assignee{
    public String name;
    private String discordName;
    private List<Chore> responsibilities;
    private int streak;
    private boolean admin;
    private String timeZone;

    public Assignee(String name, String userName, boolean admin){
        this.name = name;
        this.discordName = userName;
        this.admin = admin;
        streak = 0;
    }

    public void incStreak() {
        streak++;
    }
    public void resetStreak(){
        streak = 0;
    }
    public int getStreak(){
        return streak;
    }
    public void promote(){
        admin = true;
        notify();
    }
    public void demote(){
        admin = false;
    }
    public void addChore(Chore newChore){
        responsibilities.add(newChore);
    }
    public void removeChore(Chore oldChore){
        responsibilities.remove(oldChore);
    }
    public Assignee getAssignee(){
        return this;
    }
    public void setName(String newName){
        name = newName;
    }
    public void setTimeZone(String zone) {
        timeZone = zone;
    }
    public String getTimeZone(){
        return timeZone;
    }
    public void notify(String type, String message){
        switch(type.toLowerCase()){
            case "overdue": // chore overdue reminder
                break;
            case "requestApproved": // chore request approved
                break;
            case "requestDenied": // chore request denied
                break;
            case "promoted": //promoted to admin
                break;
            case "demoted": //demoted from admin
                break;
            case "requestPending": //new chore request
                break;
            case "newChore": //new chore added
                break;
            case "choreRemoved": //chore removed
                break;
            case "dateChange": //duedate change
                break;
            default: //regular chore reminder
        }

    }

}
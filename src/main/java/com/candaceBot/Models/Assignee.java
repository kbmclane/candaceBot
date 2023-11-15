package com.candaceBot.Models;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.String;
import java.util.Collections;
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
    private String id;
    private User user;
    private int streak;
    private boolean admin;
    private String timeZone;

    public Assignee(User u, Boolean isAdmin){
        name = u.getEffectiveName();
        id = u.getId();
        streak = 0;
        admin = isAdmin;
        timeZone = "utc";
    }

    public Assignee(JSONObject assignee){
        name = assignee.getString("name");
        id = assignee.getString("id");
        streak = assignee.getInt("streak");
        admin = assignee.getBoolean("admin");
        timeZone = assignee.getString("timeZone");
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
    public JSONObject toJson(){
        JSONObject assignee = new JSONObject();
        assignee.put("name", name);
        assignee.put("id",id);
        assignee.put("streak",streak);
        assignee.put("admin",admin);
        assignee.put("timeZone",timeZone);
        return assignee;
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
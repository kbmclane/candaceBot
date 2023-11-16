package com.candaceBot.Models;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/*Household Obj
- members
    - householdName: string
    - reminderDay: day of week
    - Server info stored here
    - members: List<Assignee>
    - chores: List<Chore>
    - admins: List<Assignee>
    - tagDict: HashMap<String, Chore>
    - timeZone: string
    * storeEnabled: bool
    * store: Store*/
    public class Household{
        private static final String defaultRemindDay = System.getProperty("DEFAULT_REMIND_DAY");
        private static final String defaultTimeZone = System.getProperty("DEFAULT_TIME_ZONE");
        private String householdName;
        private List<Object> choreAdmins;
        private static Guild house;
        private String serverId;
        private HashMap<String, String> tagDict;
        private String timeZone;
        public String reminderDay;
        private String ownerId;

        private Member owner;

        public Household(Guild g, String remindDay, String zone){
            householdName = g.getName();
            reminderDay = remindDay;
            timeZone = zone;
            house = g;
            ownerId = g.retrieveOwner().submit().join().getId();
            owner = g.retrieveOwner().submit().join();
        }
    public Household(Guild g){
        householdName = g.getName();
        reminderDay = defaultRemindDay;
        timeZone = defaultRemindDay;
        serverId = g.getId();
        house = g;
        owner = g.retrieveOwner().submit().join();
        ownerId = owner.getId();
    }
    public Household(JSONObject h){
            householdName = h.getString("name");
            serverId = h.getString("serverId");
            choreAdmins = h.getJSONArray("choreAdmins").toList();
            timeZone = h.getString("timeZone");
            reminderDay = h.getString("remindDay");
    }
    public JSONObject toJson(){
        JSONObject houseJson = new JSONObject();
        houseJson.put("name", householdName);
        houseJson.put("serverId", serverId);
        houseJson.put("choreAdmins", List.of(ownerId));
        houseJson.put("timeZone", timeZone);
        houseJson.put("remindDay", reminderDay);
        return houseJson;
    }
    public String getId() {
        return serverId;
    }
    public Member getOwner(){
            return owner;
    }
    public String getHouseholdName(){
            return householdName;
    }
}
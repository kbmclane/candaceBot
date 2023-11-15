package com.candaceBot.Models;

import com.candaceBot.Utils.MessageUtils;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import org.json.JSONObject;

import java.util.Collections;
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
        public String householdName;
        public String reminderDay;
        private List<Assignee> members;
        private List<Assignee> choreAdmins;
        private static Guild house;
        private String id;
        //server info
        private List<Chore> chores;
        private HashMap<String, Chore> tagDict;
        private String timeZone;
        Member owner;

        public Household(Guild g, String remindDay, String zone){
            householdName = g.getName();
            reminderDay = remindDay;
            timeZone = zone;
            house = g;
            owner = g.retrieveOwner().submit().join();
        }
    public Household(Guild g){
        householdName = g.getName();
        reminderDay = defaultRemindDay;
        timeZone = defaultRemindDay;
        house = g;
        owner = g.retrieveOwner().submit().join();
    }
        public void addAdmin(){}
        public void addUser(){}
        public void addUsers(){}
        public void addChore(){}
        public void updateTimeZone(){}
        public void addTag(){}
        public void updateTag(){}
        public void removeTag(){}
    public JSONObject initiateHouseJson(){
        JSONObject houseJson = new JSONObject();
        houseJson.put("name", householdName);
        houseJson.put("choreAdmins", List.of(owner.getId()));
        houseJson.put("members", List.of(owner.getId()));
        houseJson.put("chores", Collections.emptyList());
        houseJson.put("timeZone", timeZone);
        houseJson.put("remindDay", reminderDay);
        MessageUtils.notifyOwnerBotAdded(owner, householdName);
        return houseJson;
    }
    public String getId() {
        return house.getId();
    }
    public Member getOwner(){
            return owner;
    }
}
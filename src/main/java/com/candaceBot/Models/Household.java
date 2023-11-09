package com.candaceBot.Models;

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
        private static final String defaultRemindDay = "* * * * 3";
        private static final String defaultTimeZone = "utc";
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

        public Household(Guild g, String remindDay, String zone){
            householdName = g.getName();
            reminderDay = remindDay;
            timeZone = zone;
            house = g;
        }
    public Household(Guild g){
        householdName = g.getName();
        reminderDay = defaultRemindDay;
        timeZone = defaultRemindDay;
        house = g;
    }
        public void addAdmin(){}
        public void addUser(){}
        public void addUsers(){}
        public void addChore(){}
        public void updateTimeZone(){}
        public void addTag(){}
        public void updateTag(){}
        public void removeTag(){}
    public JSONObject populateHouseJson(){
        Member owner = house.retrieveOwner().submit().join();
        JSONObject houseJson = new JSONObject();
        houseJson.put("name", householdName);
        houseJson.put("choreAdmins", List.of(owner.getId()));
        houseJson.put("members", List.of(owner.getId()));
        houseJson.put("chores", Collections.emptyList());
        houseJson.put("timeZone", timeZone);
        houseJson.put("remindDay", reminderDay);
        notifyOwnerBotAdded(owner, householdName);
        return houseJson;
    }
    public static void notifyOwnerBotAdded(Member owner, String guildName){
        owner.getUser().openPrivateChannel()
                .flatMap(pm -> pm.sendMessage(
                        String.format("Thank you for adding Candace to the **%s** server!" +
                                        "\nAs the server owner, you are automatically added as a member and an choreAdmin." +
                                        "\nTo configure, type `/configure` in any **%s** text channels." +
                                        "\nType `/help` for the 411 on all of my commands!",
                                guildName, guildName)))
                .queue();
    }
}
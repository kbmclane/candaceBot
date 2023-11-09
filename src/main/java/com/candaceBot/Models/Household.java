package com.candaceBot.Models;

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
        public String householdName;
        public String reminderDay;
        private List<Assignee> members;
        private List<Assignee> choreAdmins;
        private String id;
        //server info
        private List<Chore> chores;
        private HashMap<String, Chore> tagDict;
        private String timeZone;

        public Household(String name, String remindDay, String zone, String id){
            this.householdName = name;
            reminderDay = remindDay;
            timeZone = zone;
            this.id = id;
        }
        public void addAdmin(){}
        public void addUser(){}
        public void addUsers(){}
        public void addChore(){}
        public void updateTimeZone(){}
        public void addTag(){}
        public void updateTag(){}
        public void removeTag(){}
}
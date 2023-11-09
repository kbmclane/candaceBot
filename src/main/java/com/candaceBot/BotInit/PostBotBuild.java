package com.candaceBot.BotInit;

import com.candaceBot.Listeners.EventListener;
import com.candaceBot.BotInit.Brain;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostBotBuild {
    static final Logger logger = LoggerFactory.getLogger(PostBotBuild.class);
    static String brainPath = "src/main/resources/Jsons/brain.json";
    public static void botRoutines(JDA bot) {
        logger.info("Adding Event Listener");
        bot.addEventListener(new EventListener());
        bot.updateCommands().addCommands(
                Commands.slash("optin", "Opt into the chore bot")
                        .setGuildOnly(true),
                Commands.slash("optout", "Opt out of the chore bot")
                        .setGuildOnly(true),
                Commands.slash("configure", "Configure the household for Candace")
                        .setGuildOnly(true),
                Commands.slash("promote", "Promote user to Candace Admin")
                        .setGuildOnly(true),
                Commands.slash("demote", "Demote user from Candace Admin")
                        .setGuildOnly(true),
                Commands.slash("getstreak", "Returns your chore Streak")
                        .setGuildOnly(true),
                Commands.slash("gettimezone", "What is my time zone")
                        .setGuildOnly(true),
                Commands.slash("settimezone", "Update your timezone")
                        .setGuildOnly(true),
                Commands.slash("addchore", "Add a new chore")
                        .setGuildOnly(true),
                Commands.slash("deletechore", "Remove a chore")
                        .setGuildOnly(true),
                Commands.slash("updatechore", "Update a chore")
                        .setGuildOnly(true),
                Commands.slash("gettags", "Get the tags for the provided chore")
                        .setGuildOnly(true),
                Commands.slash("complete", "Mark a chore as complete")
                        .setGuildOnly(true),
                Commands.slash("getrequests", "View pending requests")
                        .setGuildOnly(true),
                Commands.slash("requestapprove", "Approve a chore request")
                        .setGuildOnly(true),
                Commands.slash("requestdeny", "Deny a chore request")
                        .setGuildOnly(true),
                Commands.slash("getchores", "Get all of your chores")
                        .setGuildOnly(true),
                Commands.slash("getnextchore", "Get your next due chore (or most overdue)")
                        .setGuildOnly(true),
                Commands.slash("requestchore", "Submit a chore request")
                        .setGuildOnly(true),
                Commands.slash("sendreminder", "Remind a user about their chore")
                        .setGuildOnly(true),
                Commands.slash("sendallreminders", "Remind all users about their chores")
                        .setGuildOnly(true),
                Commands.slash("gettaggedchores", "Get all chores with a certain tag")
                        .setGuildOnly(true),
                Commands.slash("remindtaggedchores", "Remind all chores with a certain tag")
                        .setGuildOnly(true),
                Commands.slash("help", "Get the 411 on all of the commands!")
        ).queue();
        Brain.brainAlive(bot);
    }
}

package com.candaceBot.BotInit;

import com.candaceBot.Listeners.EventListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.restaction.GuildAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostBotBuild {
    static final Logger logger = LoggerFactory.getLogger(PostBotBuild.class);

    public static void botRoutines(JDA bot) {
        bot.addEventListener(new EventListener());
        logger.info(String.format("Candace is ready on %s servers", bot.getGuilds().size()));
        bot.updateCommands().addCommands(
                Commands.slash("optin", "Opt into the chore bot")
                        .setGuildOnly(true),
                Commands.slash("optout", "Opt out of the chore bot")
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
                        .setGuildOnly(true)
        ).queue();

    }
}

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
    public static void botRoutines(JDA bot){
        bot.addEventListener(new EventListener());
        logger.info(String.format("Candace is ready on %s servers", bot.getGuilds().size()));
        for (Guild g : bot.getGuilds()) {
            g.updateCommands().addCommands(
                    Commands.slash("getstreak", "Returns your chore Streak"),
                    Commands.slash("optin", "Opt into the chore bot"),
                    Commands.slash("optout", "Opt out of the chore bot"),
                    Commands.slash("settimezone", "Update your timezone"),
                    Commands.slash("gettimezone", "What is my time zone"),
                    Commands.slash("promote", "Promote user to Candace Admin"),
                    Commands.slash("demote", "Demote user from Candace Admin"),
                    Commands.slash("addchore", "Add a new chore"),
                    Commands.slash("deletechore", "Remove a chore"),
                    Commands.slash("updatechore", "Update a chore"),
                    Commands.slash("gettags", "Get the tags for the provided chore"),
                    Commands.slash("complete", "Mark a chore as complete"),
                    Commands.slash("getrequests", "View pending requests"),
                    Commands.slash("requestapprove","Approve a chore request"),
                    Commands.slash("getchores", "Get all of your chores"),
                    Commands.slash("getnextchore", "Get your next due chore (or most overdue)"),
                    Commands.slash("requestchore", "Submit a chore request"),
                    Commands.slash("sendreminder", "Remind a user about their chore"),
                    Commands.slash("sendallreminders", "Remind all users about their chores"),
                    Commands.slash("gettaggedchores", "Get all chores with a certain tag"),
                    Commands.slash("remindtaggedchores", "Remind all chores with a certain tag")
            ).queue();
        }
    }
}

package com.candaceBot.Listeners;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandListener extends ListenerAdapter {
    static final Logger logger = LoggerFactory.getLogger(CommandListener.class);

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        logger.info("just got a teeexxxxtttt");
        if (event.getUser().isBot()) return;
        if (!event.isFromGuild()) {
            logger.info(event.getFullCommandName());
            event.reply("Please contact me through a discord channel <3").queue();
        } else {
            switch (event.getName()) {
                case "optin":
                    event.deferReply().queue(); // Tell discord we received the command, send a thinking... message to the user
                    event.getHook().sendMessage("hi").queue(); // delayed response updates our inital "thinking..." message with the tag value
                    logger.info(event.toString());
                    break;
                case "optout":
                    event.getUser().openPrivateChannel()
                            .flatMap(channel -> channel.sendMessage("Goodbye"))
                            .queue();
                    break;
                case "promote":
                    break;
                case "demote":
                    break;
                case "getstreak":
                    break;
                case "gettimezone":
                    break;
                case "settimezone":
                    break;
                case "addchore":
                    break;
                case "deletechore":
                    break;
                case "updatechore":
                    break;
                case "gettags":
                    break;
                case "complete":
                    break;
                case "getrequests":
                    break;
                case "requestapprove":
                    break;
                case "requestdeny":
                    break;
                case "getchores":
                    break;
                case "getnextchore":
                    break;
                case "requestchore":
                    break;
                case "sendreminder":
                    break;
                case "sendallreminders":
                    break;
                case "gettaggedchores":
                    break;
                case "remindtaggedchores":
                    break;
                case "help":
                    break;
                default:
                    break;
            }
        }
    }
}

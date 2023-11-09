package com.candaceBot.Listeners;

import com.candaceBot.BotInit.PostBotBuild;
import com.candaceBot.Models.Household;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventListener extends ListenerAdapter {
    static final Logger logger = LoggerFactory.getLogger(EventListener.class);

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        Guild guild = event.getGuild();
        Household h = new Household(guild);
        logger.info("%s server has added Candace - Updating brain");

        // Perform any additional setup or logging you need here
    }

    /*@Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        // We don't want to respond to other bot accounts, including ourself
        Message message = event.getMessage();
        String content = message.getContentRaw();
        // getContentRaw() is an atomic getter
        // getContentDisplay() is a lazy getter which modifies the content for e.g. console view (strip discord formatting)
        if (content.equals("!ping")) {
            event.getAuthor().openPrivateChannel()
                    .flatMap(channel -> channel.sendMessage(String.format("there are %s members in this discord.", event.getGuild().getMembers().size())))
                    .queue();

            logger.info(String.format("event: %s", event.getGuild().getName()));
        }
    }*/

}

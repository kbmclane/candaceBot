package com.candaceBot.Listeners;

import com.candaceBot.BotInit.Brain;
import com.candaceBot.BotInit.PostBotBuild;
import com.candaceBot.Models.Household;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventListener extends ListenerAdapter {
    static final Logger logger = LoggerFactory.getLogger(EventListener.class);

    Brain b = new Brain();
    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        Guild guild = event.getGuild();
        Household h = new Household(guild);
        logger.info("%s server has added Candace - Updating brain", h.getHouseholdName());
        b.addHousehold(h);
    }

    @Override
    public void onGuildLeave(GuildLeaveEvent event) {
        Guild guild = event.getGuild();
        logger.info("%s server has removed Candace :( - Updating brain", guild.getName());
        b.removeHousehold(guild);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        if (!event.isFromGuild()) {
            event.getAuthor().openPrivateChannel()
                    .flatMap(channel -> channel.sendMessage("Please contact me through a discord channel <3"))
                    .queue();
        }
    }
}

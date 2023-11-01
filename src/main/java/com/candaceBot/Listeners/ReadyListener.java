package com.candaceBot.Listeners;

import com.candaceBot.BotInit.PostBotBuild;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadyListener extends EventListener{
    static final Logger logger = LoggerFactory.getLogger(ReadyListener.class);
    @Override
    public void onReady(ReadyEvent event)
    {
        logger.debug(String.format("Candace is ready on %s servers!", event.getGuildAvailableCount()));
    }
}

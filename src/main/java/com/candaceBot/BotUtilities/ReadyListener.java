package com.candaceBot.BotUtilities;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class ReadyListener implements EventListener
{
    public static void main(String[] args)
            throws InterruptedException
    {
        // Note: It is important to register your ReadyListener before building
        JDA jda = JDABuilder.createDefault("token")
                .addEventListeners(new ReadyListener())
                .build();

        // optionally block until JDA is ready
        jda.awaitReady();
    }

    @Override
    public void onEvent(GenericEvent event) {
        if (event instanceof ReadyEvent)
            System.out.println("API is ready!");
    }
}

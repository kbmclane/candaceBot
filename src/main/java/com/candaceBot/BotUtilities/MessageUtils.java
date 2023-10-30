package com.candaceBot.BotUtilities;

import net.dv8tion.jda.api.entities.User;

public class MessageUtils {
    public void sendMessage(User user, String content) {
        user.openPrivateChannel()
                .flatMap(channel -> channel.sendMessage(content))
                .queue();
    }
}

package com.candaceBot.Utils;

import net.dv8tion.jda.api.entities.Member;

public class MessageUtils {
    public static void notifyOwnerBotAdded(Member owner, String guildName){
        owner.getUser().openPrivateChannel()
                .flatMap(pm -> pm.sendMessage(
                        String.format("Thank you for adding Candace to the **%s** server!" +
                                        "\nAs the server owner, you are automatically added as a member and an choreAdmin." +
                                        "\nTo configure, type `/configure` in any **%s** text channels." +
                                        "\nType `/help` for the 411 on all of my commands!" +
                                        "\n### If you did not add Candace to your server, check in with your admins or your server settings.",
                                guildName, guildName)))
                .queue();
    }
    public static void notifyOwnerBotExists(Member owner, String guildName){
        owner.getUser().openPrivateChannel()
                .flatMap(pm -> pm.sendMessage(
                        String.format("Candace bot already exists on the **%s** server." +
                                        "\nI love my mom, but 2 of her??? In the same place??" +
                                        "\n### If you did not add Candace to your server, check in with your admins or your server settings.",
                                guildName)))
                .queue();
    }
    public static void notifyOwnerBrainError(Member owner, String guildName){
        owner.getUser().openPrivateChannel()
                .flatMap(pm -> pm.sendMessage(
                        String.format("Thank you for your interest in Candace bot." +
                                        "\nCandace is excited to join the %s household," +
                                        "\nhowever we're experiencing some technical difficulties!" +
                                        "\nPlease try again later!" +
                                        "\n### If you did not add Candace to your server, check in with your admins or your server settings.",
                                guildName)))
                .queue();
    }
}

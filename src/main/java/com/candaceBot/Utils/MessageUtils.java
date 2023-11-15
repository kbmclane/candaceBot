package com.candaceBot.Utils;

import net.dv8tion.jda.api.entities.Member;

public class MessageUtils {
    public static void notifyOwnerServerAdded(Member owner, String guildName){
        owner.getUser().openPrivateChannel()
                .flatMap(pm -> pm.sendMessage(
                        String.format("Thank you for adding me to the **%s** server!" +
                                        "\nAs the server owner, you are automatically added as a member and an choreAdmin." +
                                        "\nTo configure, type `/configure` in any **%s** text channels." +
                                        "\nType `/help` for the 411 on all of my commands!" +
                                        "\n### If you did not add Candace to your server, check in with your admins or your server settings.",
                                guildName, guildName)))
                .queue();
    }
    public static void notifyOwnerServerAlreadyExists(Member owner, String guildName){
        owner.getUser().getId();
        owner.getUser().openPrivateChannel()
                .flatMap(pm -> pm.sendMessage(
                        String.format("According to my records, I already exists on the **%s** server." +
                                        "\n*I love my mom, but 2 of her??? **In the same place??***" +
                                        "\nIf you're getting this message, and this is your first time adding Candace bot - PANIC THIS SHOULDN'T HAPPEN." +
                                        "\nContact Troof." +
                                        "\n### If you did not add Candace to your server, check in with your admins or your server settings.",
                                guildName)))
                .queue();
    }
    public static void notifyOwnerServerDoesNotExist(Member owner, String guildName){
        owner.getUser().openPrivateChannel()
                .flatMap(pm -> pm.sendMessage(
                        String.format("I cannot find **%s** server in my records." +
                                        "\nIf you're having issues with me. Contact Troof." +
                                        "\n### If you did not add Candace to your server, check in with your admins or your server settings.",
                                guildName)))
                .queue();
    }
    public static void notifyOwnerBrainError(Member owner, String guildName){
        owner.getUser().openPrivateChannel()
                .flatMap(pm -> pm.sendMessage(
                        String.format("I am currently experiencing some technical difficulties" +
                                        "\nregarding changes made to the **%s** household," +
                                        "\nPlease try again later!" +
                                        "\nIf you're repeatedly getting this message, contact Troof." +
                                        "\n### If you did not make changes to Candace in your server, check in with your admins or your server settings.",
                                guildName)))
                .queue();
    }
}

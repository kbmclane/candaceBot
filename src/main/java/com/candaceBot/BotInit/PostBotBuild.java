package com.candaceBot.BotInit;

import com.candaceBot.Listeners.EventListener;
import com.candaceBot.Models.Household;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.restaction.GuildAction;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PostBotBuild {
    static final Logger logger = LoggerFactory.getLogger(PostBotBuild.class);
    static String brainPath = "src/main/resources/Jsons/brain.json";
    public static void botRoutines(JDA bot) {
        bot.addEventListener(new EventListener());
        bot.updateCommands().addCommands(
                Commands.slash("optin", "Opt into the chore bot")
                        .setGuildOnly(true),
                Commands.slash("optout", "Opt out of the chore bot")
                        .setGuildOnly(true),
                Commands.slash("configure", "Configure the household for Candace")
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
                        .setGuildOnly(true),
                Commands.slash("help", "Get the 411 on all of the commands!")
        ).queue();
        if(!new File(brainPath).exists()){
            logger.info("No brain found, creating brain.");
            JSONObject brain = new JSONObject();
            for (Guild g: bot.getGuilds()
            ) {
                brain.put(g.getId(), populateHouseJson(g));
            }
            try (FileWriter file = new FileWriter(brainPath)) {
                file.write(brain.toString(4)); // Indent with four spaces
                logger.info("brain JSON file created: " + brainPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            logger.info("");
            try {
                String content = new String(Files.readAllBytes(Paths.get(brainPath)));
                JSONObject json = new JSONObject(new JSONTokener(content));

            } catch(IOException e){
                logger.error("brainFile failed to load.", e);
                e.printStackTrace();
            }

        }
        logger.info("BONESAW IS READY");
    }
    private static void initiateOwner(String ownerId, JSONObject house){
        logger.info("ping: " + ownerId);
        house.put("choreAdmins", ownerId);
        house.put("members", ownerId);
    }
    private static JSONObject populateHouseJson(Guild g){
        JSONObject houseJson = new JSONObject();

        houseJson.put("name", g.getName());
        //houseJson.put("choreAdmins", Collections.emptyList());
        houseJson.put("members", Collections.emptyList());

        g.retrieveOwner()
                .submit()
                .thenCompose((owner) -> {
                    logger.info(String.format("Owner of guild %s is %s", g.getName(),owner.getUser().getName()));
                    houseJson.put("choreAdmins", "beebus greebus");
                    //initiateOwner(owner.getId(), houseJson);
                    return owner.getUser().openPrivateChannel().submit();
                })
                .thenCompose((channel) -> channel.sendMessage(String.format("Thank you for adding Candace to the **%s** server!\nAs the server owner, you are automatically added as a member and an choreAdmin.\nTo configure, type `/configure` in any **%s** text channels.\nType `/help` for the 411 on all of my commands!", g.getName(), g.getName())).submit())
                .whenComplete((v, error) -> {
                    if (error != null) {
                        logger.error("Failed when getting owner: ", error);
                        error.printStackTrace();
                    }
                });
        houseJson.put("chores", Collections.emptyList());
        houseJson.put("timeZone", "utc");
        houseJson.put("remindDay", "* * * * 3");
        return houseJson;
    }
}

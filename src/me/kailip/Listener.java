package me.kailip;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.Component;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.awt.Color;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Listener extends ListenerAdapter {
    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        User user = event.getAuthor();
        Message message = event.getMessage();
        String content = message.getContentDisplay();
        File registerjson = new File("./register.json");
        List<String> userList = new ArrayList<>();
        if (user.isBot()) {
            return;
        }
        try {
            FileReader reader = new FileReader(registerjson);
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            while (line != null) {
                line = br.readLine();
                userList.add(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if (userList.contains(user.getId())) {
            if (content.contains("/unsus")) {
                String[] args = content.split(" ");
                if (args.length == 2) {
                    EmbedBuilder embedBuilder = new EmbedBuilder();
                    embedBuilder.setColor(Color.BLUE)
                            .setTitle("申請します！");
                    Message m = event.getChannel().sendMessage(embedBuilder.build()).complete();
                    int leftLimit = 97; // letter 'a'
                    int rightLimit = 122; // letter 'z'
                    int targetStringLength = 10;
                    Random random = new Random();
                    StringBuilder buffer = new StringBuilder(targetStringLength);
                    for (int i = 0; i < targetStringLength; i++) {
                        int randomLimitedInt = leftLimit + (int)
                                (random.nextFloat() * (rightLimit - leftLimit + 1));
                        buffer.append((char) randomLimitedInt);
                    }
                    String generatedString = buffer.toString();
                    File f = new File("./unsus.json");
                    if (!f.exists()) {
                        try {
                            f.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        FileWriter fw = new FileWriter(f);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write("{\"id\": \"" + args[1] + "\", \"email\": \"" + "smsfollowerjp+" + new Random().nextInt(1000) + "@gmail.com" + "\", \"response\": \"" + generatedString + "\", \"user\": \"" + user.getId() + "\", \"type\": \"dm\"}");
                        bw.close();
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        ProcessBuilder processBuilder = new ProcessBuilder("./unsus.bat");
                        processBuilder.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    new Thread(() -> {
                        for (int a = 0; a < 1800; a++) {
                            try {
                                File file = new File("./" + generatedString + ".json");
                                if (file.exists()) {
                                    embedBuilder.setColor(Color.GREEN)
                                            .setTitle("申請成功しました！")
                                            .appendDescription("ID: " + args[1]);
                                    m.editMessage(embedBuilder.build()).queue();
                                    file.delete();
                                    break;
                                }
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
                if (args.length == 3) {
                    EmbedBuilder embedBuilder = new EmbedBuilder();
                    embedBuilder.setColor(Color.BLUE)
                            .setTitle("申請します！");
                    Message m = event.getChannel().sendMessage(embedBuilder.build()).complete();
                    int leftLimit = 97; // letter 'a'
                    int rightLimit = 122; // letter 'z'
                    int targetStringLength = 10;
                    Random random = new Random();
                    StringBuilder buffer = new StringBuilder(targetStringLength);
                    for (int i = 0; i < targetStringLength; i++) {
                        int randomLimitedInt = leftLimit + (int)
                                (random.nextFloat() * (rightLimit - leftLimit + 1));
                        buffer.append((char) randomLimitedInt);
                    }
                    String generatedString = buffer.toString();
                    File f = new File("./unsus.json");
                    if (!f.exists()) {
                        try {
                            f.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        FileWriter fw = new FileWriter(f);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write("{\"id\": \"" + args[1] + "\", \"email\": \"" + args[2] + "\", \"response\": \"" + generatedString + "\", \"user\": \"" + user.getId() + "\", \"type\": \"dm\"}");
                        bw.close();
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        ProcessBuilder processBuilder = new ProcessBuilder("./unsus.bat");
                        processBuilder.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    new Thread(() -> {
                        for (int a = 0; a < 1800; a++) {
                            try {
                                File file = new File("./" + generatedString + ".json");
                                if (file.exists()) {
                                    embedBuilder.setColor(Color.GREEN)
                                            .setTitle("申請成功しました！")
                                            .appendDescription("ID: " + args[1]);
                                    m.editMessage(embedBuilder.build()).queue();
                                    file.delete();
                                    break;
                                }
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        }
        else {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setColor(Color.RED)
                    .setTitle("あなたはDMで申請が有効になっていません！");
            event.getChannel().sendMessage(embedBuilder.build()).queue();
        }
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        Message message = event.getMessage();
        User user = event.getAuthor();
        String content = message.getContentDisplay();

        if (event.getChannel().getType() == ChannelType.PRIVATE) {
            return;
        }
        if (!user.isBot()) {
            if (content.contains("/unsus")) {
                String[] args = content.split(" ");
                if (args.length == 2) {
                    EmbedBuilder embedBuilder = new EmbedBuilder();
                    embedBuilder.setColor(Color.BLUE)
                            .setTitle("申請します！");
                    Message m = event.getTextChannel().sendMessage(embedBuilder.build()).complete();
                    int leftLimit = 97; // letter 'a'
                    int rightLimit = 122; // letter 'z'
                    int targetStringLength = 10;
                    Random random = new Random();
                    StringBuilder buffer = new StringBuilder(targetStringLength);
                    for (int i = 0; i < targetStringLength; i++) {
                        int randomLimitedInt = leftLimit + (int)
                                (random.nextFloat() * (rightLimit - leftLimit + 1));
                        buffer.append((char) randomLimitedInt);
                    }
                    String generatedString = buffer.toString();
                    File f = new File("./unsus.json");
                    if (!f.exists()) {
                        try {
                            f.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        FileWriter fw = new FileWriter(f);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write("{\"id\": \"" + args[1] + "\", \"email\": \"" + "smsfollowerjp+" + new Random().nextInt(1000) + "@gmail.com" + "\", \"response\": \"" + generatedString + "\", \"user\": \"" + user.getId() + "\", \"type\": \"server\"}");
                        bw.close();
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        ProcessBuilder processBuilder = new ProcessBuilder("./unsus.bat");
                        processBuilder.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    new Thread(() -> {
                        for (int a = 0; a < 1800; a++) {
                            try {
                                File file = new File("./" + generatedString + ".json");
                                if (file.exists()) {
                                    embedBuilder.setColor(Color.GREEN)
                                            .setTitle("申請成功しました！")
                                            .appendDescription("ID: " + args[1]);
                                    m.editMessage(embedBuilder.build()).queue();
                                    file.delete();
                                    break;
                                }
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
                if (args.length == 3) {
                    EmbedBuilder embedBuilder = new EmbedBuilder();
                    embedBuilder.setColor(Color.BLUE)
                            .setTitle("申請します！");
                    Message m = event.getTextChannel().sendMessage(embedBuilder.build()).complete();
                    int leftLimit = 97; // letter 'a'
                    int rightLimit = 122; // letter 'z'
                    int targetStringLength = 10;
                    Random random = new Random();
                    StringBuilder buffer = new StringBuilder(targetStringLength);
                    for (int i = 0; i < targetStringLength; i++) {
                        int randomLimitedInt = leftLimit + (int)
                                (random.nextFloat() * (rightLimit - leftLimit + 1));
                        buffer.append((char) randomLimitedInt);
                    }
                    String generatedString = buffer.toString();
                    File f = new File("./unsus.json");
                    if (!f.exists()) {
                        try {
                            f.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        FileWriter fw = new FileWriter(f);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write("{\"id\": \"" + args[1] + "\", \"email\": \"" + args[2] + "\", \"response\": \"" + generatedString + "\", \"user\": \"" + user.getId() + "\", \"type\": \"server\"}");
                        bw.close();
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        ProcessBuilder processBuilder = new ProcessBuilder("./unsus.bat");
                        processBuilder.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    new Thread(() -> {
                        for (int a = 0; a < 1800; a++) {
                            try {
                                File file = new File("./" + generatedString + ".json");
                                if (file.exists()) {
                                    embedBuilder.setColor(Color.GREEN)
                                            .setTitle("申請成功しました！")
                                            .appendDescription("ID: " + args[1]);
                                    m.editMessage(embedBuilder.build()).queue();
                                    file.delete();
                                    break;
                                }
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        }
    }
}

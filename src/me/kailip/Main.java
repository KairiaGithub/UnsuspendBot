package me.kailip;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) {
        try {
            JDABuilder.createDefault("").addEventListeners(new Listener()).build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}

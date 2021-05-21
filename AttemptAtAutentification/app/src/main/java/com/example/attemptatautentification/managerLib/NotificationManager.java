package com.example.attemptatautentification.managerLib;

import com.example.attemptatautentification.possumLib.Plan;

public class NotificationManager {
    public NotificationManager(){
        messageService = new MyFirebaseMessagingService();
    }

    public void editNotification(String token, Plan plan){

    }

    private MyFirebaseMessagingService messageService;
}

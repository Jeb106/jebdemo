package com.example.demojdk8.threads;

import java.util.concurrent.TimeUnit;

class TaskExcute{
        
        public static void execute(){
            System.out.println("taskexecute");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }
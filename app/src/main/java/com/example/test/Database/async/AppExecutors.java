package com.example.test.Database.async;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

//Class for instantiate object to execute asynchronous operations
public class AppExecutors {

    //Singleton instantiation
    private static final Object LOCK = new Object();
    private static AppExecutors instance;
    private final Executor diskIO;

    //Constructor
    private AppExecutors(Executor diskIO){
        this.diskIO = diskIO;
    }


    public static AppExecutors getInstance(){
        if (instance == null){
            synchronized (LOCK){
                instance = new AppExecutors(Executors.newSingleThreadExecutor());
            }
        }
        return instance;
    }

    public Executor getDiskIO() {
        return diskIO;
    }
}

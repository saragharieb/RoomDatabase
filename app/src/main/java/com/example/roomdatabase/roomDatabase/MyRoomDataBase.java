package com.example.roomdatabase.roomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Employee.class,Salery.class},version = 1,exportSchema = false)
public abstract class MyRoomDataBase extends RoomDatabase {
    public EmployeeDAO employeeDAO;
    public SaleryDAO saleryDAO;

    private static volatile MyRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseWriteExecutor=
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MyRoomDataBase getDataBase(final Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (MyRoomDataBase.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext()
                    ,MyRoomDataBase.class,"employees_db")
                            .addCallback(sRoomDatabaseCallback)
                            .allowMainThreadQueries()
                            .build();
                }
            }

        }
        return INSTANCE;
    }


    private static RoomDatabase.Callback sRoomDatabaseCallback =new
            RoomDatabase.Callback()
            {
                public void onCreate(SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    databaseWriteExecutor.execute(()->{

                    });
                }
            };
}

package com.cuonghuynh.mvvmapp.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Category.class,Course.class}, version = 1)
public abstract class CourseDatabase  extends RoomDatabase {

    public abstract CategoryDAO categoryDAO();
    public abstract CourseDAO courseDAO();

    //Singleton Pattern

    private static CourseDatabase instance;

    public static synchronized CourseDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CourseDatabase.class,"courses_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            InitializeData();
        }
    };


    private static void InitializeData(){
        CourseDAO courseDAO = instance.courseDAO();;
        CategoryDAO categoryDAO = instance.categoryDAO();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                //Category
                Category category1 = new Category();
                category1.setCategoriesName("Front End");
                category1.setCategoriesDescription("Web dev Interface");

                Category category2 = new Category();
                category2.setCategoriesName("Back End");
                category2.setCategoriesDescription("Android App");

                categoryDAO.insert(category1);
                categoryDAO.insert(category2);

                Course course1 = new Course();
                course1.setCourseName("HTML");
                course1.setUnitPrice("100$");
                course1.setCategoryId(1);

                Course course2 = new Course();
                course2.setCourseName("CSS3");
                course2.setUnitPrice("200$");
                course2.setCategoryId(2);

                Course course3 = new Course();
                course2.setCourseName("JS");
                course2.setUnitPrice("300$");
                course2.setCategoryId(3);

                Course course4 = new Course();
                course2.setCourseName("JAVA");
                course2.setUnitPrice("2000$");
                course2.setCategoryId(4);

                courseDAO.insert(course1);
                courseDAO.insert(course2);
                courseDAO.insert(course3);
                courseDAO.insert(course4);

            }
        });
    }


    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}

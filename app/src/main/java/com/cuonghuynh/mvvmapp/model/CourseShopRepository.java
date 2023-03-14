package com.cuonghuynh.mvvmapp.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CourseShopRepository {

    private CategoryDAO categoryDAO;
    private CourseDAO courseDAO;

    private LiveData<List<Category>> categories;
    private LiveData<List<Course>> courses;

    public CourseShopRepository(Application application) {
        CourseDatabase courseDatabase = CourseDatabase.getInstance(application);
        categoryDAO = courseDatabase.categoryDAO();
        courseDAO = courseDatabase.courseDAO();
    }

    public LiveData<List<Category>> getCategories() {
        return categoryDAO.getAllCategories();
    }

    public LiveData<List<Course>> getCourses(int categoryId) {
        return courseDAO.getCourse(categoryId);
    }

    private void insertCategory(Category category){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        //Handler handler  = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                categoryDAO.insert(category);
            }
            
        });
    }

    private void deleteCategory(Category category){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        //Handler handler  = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                categoryDAO.delete(category);
            }
        });
    }
    private void updateCategory(Category category){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        //Handler handler  = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                categoryDAO.update(category);
            }
        });
    }

    private void insertCourse(Course course){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        //Handler handler  = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                courseDAO.insert(course);
            }
        });
    }

    private void deleteCourse(Course course){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        //Handler handler  = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                courseDAO.delete(course);
            }
        });
    }
    private void updateCourse(Course course){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        //Handler handler  = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                courseDAO.update(course);
            }
        });
    }

}

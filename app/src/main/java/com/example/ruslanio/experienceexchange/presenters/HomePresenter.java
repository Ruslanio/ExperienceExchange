package com.example.ruslanio.experienceexchange.presenters;

import android.os.Bundle;

import com.example.ruslanio.experienceexchange.database.DataBaseManager;
import com.example.ruslanio.experienceexchange.database.model.Course;
import com.example.ruslanio.experienceexchange.database.model.Interest;
import com.example.ruslanio.experienceexchange.interfaces.presenter.HomePresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.HomeViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruslanio on 29.11.2017.
 */

public class HomePresenter extends BasePresenter<HomeViewInterface> implements HomePresenterInterface {

    private DataBaseManager mDataBaseManager;
    private static final double MIN_POPULAR_PERCENTAGE = 0.0;

    public HomePresenter(HomeViewInterface view) {
        super(view);
    }

    @Override
    public void onInit(Bundle saveInstanceState) {
        super.onInit(saveInstanceState);

        mDataBaseManager = DataBaseManager.getInstance(mView.getContext());

        mView.setThemes(getPopularInterests());


        List<String> authors = new ArrayList<>();
        authors.add("John Kennedy");
        authors.add("Victor Reznov");
        authors.add("Guy Richy");

        List<Course> courses = new ArrayList<>();

        Course course1 = new Course();
        course1.setCourseName("How to learn russian in 4 days");
        course1.setAuthor("Barrack Ivanovich Obama");
        course1.setLiked(true);
        course1.setLikesNumber(12);
        course1.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in");

        Course course2 = new Course();
        course2.setCourseName("How to learn russian in 4 days");
        course2.setAuthor("Barrack Ivanovich Obama");
        course2.setLiked(false);
        course2.setLikesNumber(4);
        course2.setDescription("Just some random short description to test this shit, mothafucka!!!");

        courses.add(course1);
        courses.add(course2);

        mView.setAuthors(authors);
        mView.setCourses(courses);

    }



    private List<Interest> getPopularInterests() {
        List<Interest> interests = mDataBaseManager.getAllInterests();
        for (Interest interest : interests) {
            if (interest.getPercentage() < MIN_POPULAR_PERCENTAGE)
                interests.remove(interest);
        }
        return interests;
    }
}

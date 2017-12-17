package com.example.ruslanio.experienceexchange.interfaces.view;

import com.example.ruslanio.experienceexchange.database.model.Lesson;

import java.util.List;

/**
 * Created by Ruslanio on 16.12.2017.
 */

public interface CourseCreatingViewInterface extends BaseViewInterface{
    void showOverView(List<Lesson> lessons);

    void showLesson();
}

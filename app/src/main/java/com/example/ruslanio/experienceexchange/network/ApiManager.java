package com.example.ruslanio.experienceexchange.network;

import com.example.ruslanio.experienceexchange.network.body.CourseBody;
import com.example.ruslanio.experienceexchange.network.body.LoginBody;
import com.example.ruslanio.experienceexchange.network.body.RegistrationBody;
import com.example.ruslanio.experienceexchange.network.body.interest.InterestBody;
import com.example.ruslanio.experienceexchange.network.body.lesson.LessonBody;
import com.example.ruslanio.experienceexchange.network.pojo.course.added.CourseAddedResponce;
import com.example.ruslanio.experienceexchange.network.pojo.course.news.CoursesNewsResponce;
import com.example.ruslanio.experienceexchange.network.pojo.image.ImageResponce;
import com.example.ruslanio.experienceexchange.network.pojo.interest.InterestResponse;
import com.example.ruslanio.experienceexchange.network.pojo.interest.send.InterestSendResponce;
import com.example.ruslanio.experienceexchange.network.pojo.lesson.LessonAddedResponce;
import com.example.ruslanio.experienceexchange.network.pojo.login.LoginResponce;
import com.example.ruslanio.experienceexchange.network.pojo.registration.RegistrationResponce;
import com.example.ruslanio.experienceexchange.network.pojo.user.UserProfileResponce;
import com.example.ruslanio.experienceexchange.network.retrofit.GetRequest;
import com.example.ruslanio.experienceexchange.network.retrofit.PostRequest;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ruslanio on 06.11.2017.
 */

public class ApiManager {
    private static ApiManager mApiManager;
    private final static String BASE_URL = "http://18.221.121.255:8080/exex/";

    private final PostRequest mPostRequest;
    private final GetRequest mGetRequest;

    public static ApiManager getInstance() {
        if (mApiManager == null) {
            mApiManager = new ApiManager();
        }
        return mApiManager;
    }

    private ApiManager() {
        mGetRequest = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .baseUrl(BASE_URL)
                .build()
                .create(GetRequest.class);
        mPostRequest = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .baseUrl(BASE_URL)
                .build()
                .create(PostRequest.class);
    }

    public Observable<LoginResponce> login(LoginBody loginBody) {
        return mPostRequest.login(loginBody);
    }

    public Observable<RegistrationResponce> register(RegistrationBody registrationBody) {
        return mPostRequest.register(registrationBody);
    }

    public Observable<InterestResponse> getAllInterests(String token){
        return mGetRequest.getAllInterests(token);
    }

    public Observable<ImageResponce> uploadImage(File image){
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),image);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file",image.getName(),requestBody);
        return mPostRequest.loadImage(part);
    }

    public Call<ImageResponce> uploadImageLikeDCP(File image){
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),image);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file",image.getName(),requestBody);
        return mPostRequest.loadImageLikeDCP(part);
    }

    public Observable<UserProfileResponce> getProfileData(String token, int id){
        return mGetRequest.getUserProfileInfo(token, id);
    }

    public Observable<CourseAddedResponce> uploadCourse(String token, int id, CourseBody body){
        return mPostRequest.addCourse(token,id,body);
    }

    public Call<LessonAddedResponce> uploadLesson(String token, int userId,int courseId,LessonBody body){
        return mPostRequest.addLesson(token,userId,courseId,body);
    }

    public Observable<InterestSendResponce> attachInterests(String token, int id, InterestBody body){
        return mPostRequest.addInterest(token,id,body);
    }

    public Observable<CoursesNewsResponce> getNews(String token, int id){
        return mGetRequest.getNews(token,id);
    }
}

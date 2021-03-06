package com.example.ruslanio.experienceexchange.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.ruslanio.experienceexchange.interfaces.presenter.BasePresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.BaseViewInterface;
import com.example.ruslanio.experienceexchange.utils.managers.DialogManager;

import butterknife.ButterKnife;


/**
 * Created by Ruslanio on 06.11.2017.
 */

public abstract class BaseActivity<T extends BasePresenterInterface> extends AppCompatActivity implements BaseViewInterface, BaseConstants{

    protected static final String KEY_INTERESTS_ID_LIST = "interest_bundle";



    protected T mPresenter;

    private DialogManager mDialogManager;

    protected abstract T getPresenter();

    @Override
    public DialogFragment addDialog(DialogFragment dialogFragment, String tag) {
        return mDialogManager.addDialog(dialogFragment, tag);
    }

    @Override
    public void dismissDialog(String tag) {
        mDialogManager.dismissDialog(tag);
    }

    @Override
    public void showDialog(String tag) {
        mDialogManager.showDialog(tag);
    }

    @Override
    public void hideDialog(String tag) {
        mDialogManager.hideDialog(tag);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        if (mPresenter == null)
            mPresenter = getPresenter();
        mPresenter.onCreate(savedInstanceState);
        mDialogManager = new DialogManager(this);

        onInit();
        mPresenter.onInit(savedInstanceState);

    }


    protected abstract void onInit();

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @LayoutRes
    protected abstract int getLayout();

    public void showToast(@StringRes int text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    public void showSnackbar(@StringRes int text) {
        Snackbar.make(getWindow().getDecorView(), text, BaseTransientBottomBar.LENGTH_LONG).show();
    }

    public void showSnackbar(String text) {
        Snackbar.make(getWindow().getDecorView(), text, BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public Context getContext() {
        return this;
    }
}

package com.fuicui.gitdroid.gitdroid.gank.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 123 on 2016/9/1.
 */
public class GankResult {

    private List<String> category;

    private boolean error;

    @SerializedName("results")
    private Result result;

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static class Result{

        @SerializedName("Android")
        private List<GankItem> androidItems;

        public List<GankItem> getAndroidItems() {
            return androidItems;
        }
    }
}

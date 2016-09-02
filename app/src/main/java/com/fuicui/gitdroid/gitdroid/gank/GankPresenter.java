package com.fuicui.gitdroid.gitdroid.gank;

import com.fuicui.gitdroid.gitdroid.commons.LogUtils;
import com.fuicui.gitdroid.gitdroid.gank.model.GankItem;
import com.fuicui.gitdroid.gitdroid.gank.model.GankResult;
import com.fuicui.gitdroid.gitdroid.gank.network.GankClient;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 123 on 2016/9/2.
 */
public class GankPresenter {

    private Call<GankResult> call;
    private GankView gankView;

    public GankPresenter(GankView gankView) {
        this.gankView = gankView;
    }

    public void getGanks(Date date) {
        //去做获取干货数据的操作
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        call = GankClient.getInstance().getDailyData(year, month, day);
        call.enqueue(callback);
    }

    private Callback<GankResult> callback = new Callback<GankResult>() {
        @Override
        public void onResponse(Call<GankResult> call, Response<GankResult> response) {
            //请求结果
            GankResult gankResult = response.body();
            if (gankResult == null) {
                gankView.showMessage("数据是空的！");
                return;
            }
            if (gankResult.isError()
                    || gankResult.getResult() == null
                    || gankResult.getResult().getAndroidItems() == null
                    || gankResult.getResult().getAndroidItems().isEmpty()) {
                gankView.showEmptyView();
                return;
            }
            List<GankItem> androidItems = gankResult.getResult().getAndroidItems();
            //获取到的数据交给视图，让视图展示和改变
            gankView.hideEmptyView();
            gankView.setData(androidItems);
        }

        @Override
        public void onFailure(Call<GankResult> call, Throwable t) {
            //请求失败
            gankView.showMessage("Error："+t.getMessage());
        }
    };

    public interface GankView {
/**
 *1. 设置数据
 * 2. 显示空视图
 * 3. 显示信息
 * 4. 隐藏空视图
 */
        void setData(List<GankItem> list);

        void showEmptyView();

        void showMessage(String msg);

        void hideEmptyView();
    }
}

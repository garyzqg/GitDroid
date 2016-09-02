package com.fuicui.gitdroid.gitdroid.favorite.model;

/**
 * Created by 123 on 2016/9/1.
 */

import android.support.annotation.NonNull;

import com.fuicui.gitdroid.gitdroid.github.hotrepo.repolist.model.Repo;

import java.util.ArrayList;
import java.util.List;

/**
 * 将Repo转换为LocalRepo
 * 方法中传入Repo，返回的结果是LocalRepo
 */
public class RepoConverter {

    /**
     * 将一个仓库数据转换为本地仓库数据
     * @param repo
     * @return
     */
    public static @NonNull LocalRepo convert(@NonNull Repo repo){
        LocalRepo localRepo = new LocalRepo();
        /**
         "id": 22374063,
         "name": "android-best-practices",
         "full_name": "futurice/android-best-practices",
         "avatar_url": "https://avatars.githubusercontent.com/u/852157?v=3",
         "description": "Do's and Don'ts for Android development, by Futurice developers",
         "stargazers_count": 10469,
         "forks_count": 1974
         */
        localRepo.setId(repo.getId());
        localRepo.setName(repo.getName());
        localRepo.setFullName(repo.getFullName());
        localRepo.setAvatarUrl(repo.getOwner().getAvatar());
        localRepo.setDescription(repo.getDescription());
        localRepo.setStargazersCount(repo.getStarCount());
        localRepo.setForksCount(repo.getForksCount());
        localRepo.setRepoGroup(null);
        return localRepo;
    }

    //集合转换
    public static @NonNull List<LocalRepo> convertAll(@NonNull List<Repo> repos){
        ArrayList<LocalRepo> localRepos = new ArrayList<>();
        for (Repo repo:repos) {
            localRepos.add(convert(repo));
        }
        return localRepos;
    }
}

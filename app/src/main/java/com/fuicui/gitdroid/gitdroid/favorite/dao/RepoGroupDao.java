package com.fuicui.gitdroid.gitdroid.favorite.dao;

/**
 * Created by 123 on 2016/8/31.
 */

import com.fuicui.gitdroid.gitdroid.favorite.model.RepoGroup;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * 完成对RepoGroup表进行操作
 */
public class RepoGroupDao {


    private Dao<RepoGroup,Long> dao;

    public RepoGroupDao(DBHelp dbHelp) {
        try {
            dao = dbHelp.getDao(RepoGroup.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对于仓库类别表进行添加和更新
     * @param repoGroup
     */
    public void createOrUpdate(RepoGroup repoGroup){
        try {
            dao.createOrUpdate(repoGroup);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对于仓库类别表进行添加和更新
     */
    public void createOrUpdate(List<RepoGroup> list){
        for (RepoGroup repo:list) {
            createOrUpdate(repo);
        }
    }

    /**
     * 查询所有的
     * @return select * from RepoGroup
     */
    public List<RepoGroup> queryForAll(){
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据id去进行查询
     * @param id
     * @return
     */
    public RepoGroup queryForId(long id){
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

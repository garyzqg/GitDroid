package com.fuicui.gitdroid.gitdroid.github.repoinfo;

/**
 * 作者：yuanchao on 2016/8/29 0029 15:16
 * 邮箱：yuanchao@feicuiedu.com
 */
public class RepoContentResult {
    /**
     * {
     "encoding": "base64",
     "content": "encoded content ..."
     }
     */

    private String encoding;
    private String content;

    public String getEncoding() {
        return encoding;
    }

    public String getContent() {
        return content;
    }
}

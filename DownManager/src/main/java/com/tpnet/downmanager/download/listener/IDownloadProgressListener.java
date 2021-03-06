package com.tpnet.downmanager.download.listener;

/**
 * 下载进度接口
 * Created by litp on 2017/4/10.
 */

public interface IDownloadProgressListener {

    /**
     * 下载进度
     * @param down 下载的数量
     * @param total 文件的总长度
     * @param finish 是否下载完成
     */
    void update(long down, long total, boolean finish);

    /**
     * 更新总长度
     * @param totalLength 文件总长度
     */
    void updateTotalLength(long totalLength);


    /**
     * 更新下载状态
     */
    void updateDowning();
}

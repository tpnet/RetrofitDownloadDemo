package com.tpnet.downmanager.download;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.squareup.sqldelight.RowMapper;
import com.tpnet.downmanager.DownInfoModel;


/**
 * 下载的信息
 * Created by litp on 2017/4/10.
 */

@AutoValue
public abstract class DownInfo implements Parcelable,DownInfoModel{
    
    //下面6种下载状态
    public final static int DOWN_START = 0x0;    //开始
    public final static int DOWN_PAUSE = 0x1;    //暂停
    public final static int DOWN_STOP = 0x2;     //停止
    public final static int DOWN_ERROR = 0x3;     //错误
    public final static int DOWN_ING = 0x4;         //下载中
    public final static int DOWN_FINISH = 0x5;      //完成
    
    
    public static final Factory<DownInfo> FACTORY = new Factory<>(new DownInfoModel.Creator<DownInfo>() {
        @Override
        public DownInfo create(@NonNull long _id, @NonNull String downUrl, String downName, String downType, @NonNull String savePath, long totalLength, long downLength, int downState, long startTime, long finishTime) {
            return new AutoValue_DownInfo(_id, downUrl, downName, downType, savePath, totalLength, downLength, downState, startTime, finishTime);
        }
    });
    
    
    public static final RowMapper<DownInfo> LIST_ROW_MAPPER = FACTORY.selectAllMapper();
    
    
    public static final RowMapper<String> DOWN_EXIST_MAPPER = FACTORY.selectDowninfoSavePathMapper();
    
    
    public static final RowMapper<Long> TOTALLENGTH_MAPPER = FACTORY.selectTotalLengthMapper();


    public static DownInfo create(String downUrl, String downName, String downType, String savePath, long totalLength, long downLength, int downState, long startTime, long finishTime, long pauseTime, long allTime) {
        return builder()
                ._id(0)  //id自动增长的
                .downUrl(downUrl)
                .downName(downName)
                .downType(downType)
                .savePath(savePath)
                .totalLength(totalLength)
                .downLength(downLength)
                .downState(downState)
                .startTime(startTime)
                .finishTime(finishTime)
                .build();
    }


    public static Builder create(DownInfo downInfo) {
        return builder()
                ._id(0)
                .downUrl(downInfo.downUrl())
                .downName(downInfo.downName())
                .downType(downInfo.downType())
                .savePath(downInfo.savePath())
                .totalLength(downInfo.totalLength())
                .downLength(downInfo.downLength())
                .downState(downInfo.downState())
                .startTime(downInfo.startTime())
                .finishTime(downInfo.finishTime());
    }


    

    public static Builder builder() {
        return new AutoValue_DownInfo.Builder();
    }


    @AutoValue.Builder
    public abstract static class Builder {
      
        public abstract Builder _id(long id);

        public abstract Builder downUrl(String downUrl)
                ;

        public abstract Builder downName(String downName);

        public abstract Builder downType(String downType);
        
        public abstract Builder savePath(String savePath);

        public abstract Builder totalLength(long totalLength);

        public abstract Builder downLength(long downLength);

        public abstract Builder downState(@DownState int downState);


        public abstract Builder startTime(long startTime);

        public abstract Builder finishTime(long finishTime);
        
        
        public abstract DownInfo build();

        //创建基本任务 
        public DownInfo create(String savePath, String downUrl, String downName) {
            
            _id(0);

            downUrl(downUrl);

            savePath(savePath);

            downName(downName);

            downType("");
            
            startTime(0);
            
            finishTime(0);
            
            totalLength(0);
            
            downLength(0);
            
            downState(DOWN_START);
            
            return build();
        }
        
    }
}

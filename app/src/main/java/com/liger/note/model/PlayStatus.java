package com.liger.note.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.liger.note.model.PlayStatus.COMPLETED;
import static com.liger.note.model.PlayStatus.END;
import static com.liger.note.model.PlayStatus.ERROR;
import static com.liger.note.model.PlayStatus.IDLE;
import static com.liger.note.model.PlayStatus.INITIALIZED;
import static com.liger.note.model.PlayStatus.PAUSE;
import static com.liger.note.model.PlayStatus.PREPARED;
import static com.liger.note.model.PlayStatus.PREPARING;
import static com.liger.note.model.PlayStatus.STARTED;
import static com.liger.note.model.PlayStatus.STOP;

//import static com.liger.note.model.PlayStatus.INIT;
//import static com.liger.note.model.PlayStatus.BUFFERING;
//import static com.liger.note.model.PlayStatus.PLAYING;
//import static com.liger.note.model.PlayStatus.PAUSE;
//import static com.liger.note.model.PlayStatus.STOP;
//import static com.liger.note.model.PlayStatus.ERROR;

/**
 * @author Liger
 * @date 2018/12/25 02:17
 */
@Retention(RetentionPolicy.SOURCE)
//@IntDef({INIT, BUFFERING, PLAYING, PAUSE, STOP, ERROR})
@IntDef({IDLE, INITIALIZED, PREPARING, PREPARED, STARTED, PAUSE, STOP, COMPLETED, END, ERROR})
public @interface PlayStatus {

    int IDLE = 0;
    int INITIALIZED = 1;
    int PREPARING = 2;
    int PREPARED = 3;
    int STARTED = 4;
    int PAUSE = 5;
    int STOP = 6;
    int COMPLETED = 7;
    int END = 8;
    int ERROR = 9;


//    /**
//     * 初始状态
//     */
//    int INIT = 0;
//    /**
//     * 缓冲中
//     */
//    int BUFFERING = 1;
//    /**
//     * 正在播放
//     */
//    int PLAYING = 2;
//    /**
//     * 暂停
//     */
//    int PAUSE = 3;
//    /**
//     * 停止播放
//     */
//    int STOP = 4;
//    /**
//     * 播放出错
//     */
//    int ERROR = 5;
}

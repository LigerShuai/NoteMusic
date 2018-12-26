package com.liger.note.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.liger.note.model.Music;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zs
 * @date 2018/10/25 0025.
 */
public class ScanUtil {

    public static boolean isSdCardExist() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static List<Music> scanFiles(Context context) {
        List<Music> musicList = new ArrayList<>();
        Music music;
        //查询媒体数据库
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Audio.Media
                .DEFAULT_SORT_ORDER);
        if (cursor == null) {
            return null;
        }
        //遍历媒体数据库
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                //标题
                String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                //显示的名称
                String displayName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                //专辑
                int albumId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
                String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
                //歌手
                String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                //文件路径
                String url = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                //时长
                long duration = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                //文件大小
                long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
                String bitmapPath = getAlbumBitmapPath(context, albumId);

                String suffix = displayName.substring(displayName.length() - 4, displayName.length());
                //如果后缀为mp3，将其存入到map集合中
                if (suffix.endsWith(".mp3") || suffix.endsWith(".MP3")) {
                    music = new Music();
//                    Music.setId(id);
                    music.setName(title);
                    music.setDisplayName(displayName);
                    music.setAlbum(album);
                    music.setSinger(artist);
//                    music.setDuration(duration);
                    music.setPath(url);
                    music.setSize(size);
                    musicList.add(music);
                    music.setImgUrl(bitmapPath);
                }
                cursor.moveToNext();
            }
        }
        cursor.close();
        //返回存储数据的集合
        return musicList;
    }

    private static String getAlbumBitmapPath(Context context, int albumId) {
        String mUriAlbums = "content://media/external/audio/albums";
        String[] projection = new String[]{"album_art"};
        Cursor cursor = context.getContentResolver()
                .query(Uri.parse(mUriAlbums + File.separator + albumId), projection, null, null, null);
        if (cursor == null) {
            return null;
        }
        String bitmapPath = null;
        if (cursor.getCount() > 0 && cursor.getColumnCount() > 0) {
            cursor.moveToNext();
            bitmapPath = cursor.getString(0);
        }
        cursor.close();
        return bitmapPath;
    }

}

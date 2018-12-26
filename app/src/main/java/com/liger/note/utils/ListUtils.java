package com.liger.note.utils;

import java.util.Collection;

/**
 * @author Liger
 * @date 2018/12/25 01:31
 */
public class ListUtils {

    public static boolean isEmpty(Collection collection) {
        return (collection == null || collection.isEmpty());
    }
}

package com.github.kyo7701.collection;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Author:Mr.Cris
 * Date:2020-06-12 12:56
 *
 * @description
 */
public class CollectionTest {

    public static void main(String[] args) {
        WeakHashMap weakHashMap = new WeakHashMap();
        weakHashMap.put("1","2");
        HashMap map = new HashMap();
        map.put("a","q123");
        WeakReference<HashMap> weakReference = new WeakReference<>(map);
        System.out.println(weakReference.get());
        map = null;
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(weakReference.get());

    }

}

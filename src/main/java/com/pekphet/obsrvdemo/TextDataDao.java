package com.pekphet.obsrvdemo;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/31.
 */
public class TextDataDao {

    private final TextData data = new TextData();
    private List<Obsrv> obs = new ArrayList();

    private static TextDataDao instance = null;


    public static TextDataDao getInstance() {
        if (instance == null) {
            instance = new TextDataDao();
        }
        return instance;
    }

    private TextDataDao() {
        super();
    }

    public void update (TextData data) {
        instance.data.setShowText(data.getShowText());
        for (Obsrv ob : obs) {
            ob.onChanged();
        }
    }

    public TextData get () {
        return data;
    }

    public void registerOb (Obsrv ob) {
        obs.add(ob);
    }

    public void deregisterOb (Obsrv ob) {
        obs.remove(ob);
    }


    public void changeTextData (final String str) {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    instance.update(new TextData(str));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

}

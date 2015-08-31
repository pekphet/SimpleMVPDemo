package com.pekphet.obsrvdemo;

import android.widget.TextView;


/**
 * Created by Administrator on 2015/8/31.
 */
public class Presenter {

    private TextDataDao dao = TextDataDao.getInstance();

    public Presenter() {
        initData();
    }

    private void initData() {
    }

    private void registView(Obsrv ob) {
        dao.registerOb(ob);
    }

    /**
     * 这个方法是需要绑定事件的
     * @param tv
     */
    public void bindShowText(final TextView tv) {
        //这个地方可以赋初始值 但是 如果不是和数据层连接紧密的话建议在Activity里面做初始化
        Obsrv ob = new Obsrv() {
            @Override
            public void onChanged() {
                tv.post(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(dao.get().getShowText());
                    }
                });
            }
        };
        registView(ob);
    }

}

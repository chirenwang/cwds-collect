package com.wcc.wds.web.task;

import com.wcc.wds.web.model.CollectTask;

import java.util.concurrent.Callable;

/**
 * 实例线程
 */
public class CollectTaskCallable implements Callable<String> {

    private CollectTask collectTask;

    public CollectTaskCallable(CollectTask collectTask) {
        this.collectTask = collectTask;
    }


    @Override
    public String call() throws Exception {
        return "null";
    }
}

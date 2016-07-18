package com.example.anull.excitingnews.util;

import rx.Observable;
import rx.subjects.Subject;

/**
 * Created by null on 2016/7/18.
 */
public class RxBus {
    Subject<Object, Object> subject;


    public void post(Object o){
        subject.onNext(o);
    }

    public <T>Observable<T> toObservable(Class<T> eventType){
        return subject.ofType(eventType);
    }

    private RxBus() {
    }

    public static RxBus getSingle(){
        return RxBusHolder.rxBus;
    }

    static class RxBusHolder{
        public static RxBus rxBus = new RxBus();
    }
}

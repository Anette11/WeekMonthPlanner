package com.example.weekmonthplanner.utils;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class RxFragmentNotifier {

    private final PublishSubject<Object> publishSubject = PublishSubject.create();

    public void onNext() {
        publishSubject.onNext(new Object());
    }

    public Observable<Object> getPublishSubject() {
        return publishSubject;
    }
}
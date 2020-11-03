package com.naofi.model;

import java.util.HashSet;
import java.util.Set;

public class ModelFacade {
    protected Set<Subscriber> subscribers = new HashSet<>();
    protected int algorithmNumber = 1;

    public void setAlgorithmNumber(int number) {
        algorithmNumber = number;
        notifySubscribers();
    }

    public int getAlgorithmNumber() {
        return algorithmNumber;
    }

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    protected void notifySubscribers() {
        subscribers.forEach(s -> s.update(algorithmNumber));
    }
}

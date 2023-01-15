package ru.job4j.ood.isp.prime2;

public class Camera implements IPhone {
    @Override
    public void call() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void takePhoto() {

    }

    @Override
    public void makeVideo() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void browseInternet() {
        throw new UnsupportedOperationException();
    }
}

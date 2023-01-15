package ru.job4j.ood.isp.prime2;

public class Photograph {
    public void takePhoto(IPhone photoMaker) {
        photoMaker.takePhoto();
    }

    public static void main(String[] args) {
        Photograph photograph = new Photograph();
        IPhone camera = new Camera();
        photograph.takePhoto(camera);
    }
}

public enum Transport {
    CAR(65), TRUCK(55), AIRPLANE(600), TRAIN(70), BOAT(22);

    private int speed;

    Transport(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}


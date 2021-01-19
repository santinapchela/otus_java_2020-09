package ru.express.bank;

public enum Banknote{
    NOTE_100(100), NOTE_500(500), NOTE_1000(1000), NOTE_5000(5000);
    private final int value;

    Banknote(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}

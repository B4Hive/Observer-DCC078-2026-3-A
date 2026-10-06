package b4hive;

import java.util.Observable;
@SuppressWarnings ("deprecation")
public class Attendant extends Observable {

    private int lastCalled;
    private int nextId;
    private Client client;

    private Attendant() {
        lastCalled = 0;
        nextId = 0;
        client = null;
    }
    private static Attendant instance;
    public static Attendant getInstance() {
        if (instance == null) {
            instance = new Attendant();
        }
        return instance;
    }

    public void setClient(Client client) { this.client = client; }
    public Client getClient() { return client; }
    public int getLastCalled() { return lastCalled; }
    public int getNextId() {
        nextId++;
        return nextId;
    }

    public void callClient() {
        setClient(null);
        lastCalled++;
        setChanged();
        notifyObservers(lastCalled);
        // clearChanged(); Aparentemente Observable dá clear automaticamente
    }

}
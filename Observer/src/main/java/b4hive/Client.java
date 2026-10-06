package b4hive;

import java.util.Observable;
import java.util.Observer;
@SuppressWarnings ("deprecation")
public class Client implements Observer{

    private int id;
    private int lastCalled;
    public Client() {
        this.lastCalled = 0;
        this.id = Attendant.getInstance().getNextId() + 1;
        Attendant.getInstance().addObserver(this);
    }
    public int getId() { return id; }
    public int getLastCalled() { return lastCalled; }

    @Override
    public void update(Observable o, Object arg) {
        try{
            lastCalled = (Integer) arg;
        } catch (ClassCastException e) {
            throw new RuntimeException("O argumento passado para o método update não é do tipo Integer.");
        }
        if (lastCalled == id) {
            try{
                ((Attendant)o).setClient(this);
            } catch (ClassCastException e) {
                throw new RuntimeException("O objeto Observable passado para o método update não é do tipo Attendant.");
            }
        }
    }

}
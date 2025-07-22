import java.util.Vector;

public class Vestuario {
    private Vector<Item> itens;
    public Vestuario() {
        this.itens = new Vector<>();
    }

    public void addItem(Item item) {
        itens.add(item);
    }

    public Vector<Item> getItens() {
        return itens;
    }

    public void removerItem(String item) {
        itens.remove(item);
    }
}

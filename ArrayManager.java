import java.util.ArrayList;
import java.util.List;

public class ArrayManager {

  private int[] data;
  private final List<ArrayChangeListener> listeners = new ArrayList<>();

  public ArrayManager(int[] initialData) {
    setData(initialData);
  }

  // Methode zum Registrieren eines Listeners
  public void addArrayChangeListener(ArrayChangeListener listener) {
    listeners.add(listener);
  }

  // Methode zum Entfernen eines Listeners
  public void removeArrayChangeListener(ArrayChangeListener listener) {
    listeners.remove(listener);
  }

  // Methode zum Setzen eines neuen Arrays
  public void setData(int[] newData) {
    this.data = newData;
    notifyListeners();
  }

  public void setIndex(int index, int value) {
    data[index] = value;
    notifyListeners(index);
    try {
      Thread.sleep(0, 0); // delay in milliseconds
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  public int[] getData() {
    return data;
  }

  // Benachrichtige alle Listener
  private void notifyListeners() {
    for (ArrayChangeListener listener : listeners) {
      listener.onArrayChanged(data);
    }
  }

  // Benachrichtige alle Listener mit optionalem Index
  private void notifyListeners(int index) {
    for (ArrayChangeListener listener : listeners) {
      listener.onArrayChanged(data, index);
    }
  }
}

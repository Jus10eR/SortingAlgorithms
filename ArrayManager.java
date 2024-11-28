import java.util.ArrayList;
import java.util.List;

public class ArrayManager {

  private int[] data;
  private final List<ArrayChangeListener> listeners = new ArrayList<>();

  public ArrayManager(int[] initialData) {
    setData(initialData);
  }

  // Register a listener
  public void addArrayChangeListener(ArrayChangeListener listener) {
    listeners.add(listener);
  }

  // Remove a listener
  public void removeArrayChangeListener(ArrayChangeListener listener) {
    listeners.remove(listener);
  }

  // Set a new array and notify listeners
  public void setData(int[] newData) {
    this.data = newData;
    notifyListeners();
  }

  // Update an element at a specific index and notify listeners
  public void setIndex(int index, int value) {
    data[index] = value;
    notifyListeners(index);
    try {
      Thread.sleep(0, 0); // delay in nanoseconds
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  public int[] getData() {
    return data;
  }

  // Notify all listeners of a change in the array
  private void notifyListeners() {
    for (ArrayChangeListener listener : listeners) {
      listener.onArrayChanged(data);
    }
  }

  // Notify all listeners of a change at a specific index
  private void notifyListeners(int index) {
    for (ArrayChangeListener listener : listeners) {
      listener.onArrayChanged(data, index);
    }
  }
}

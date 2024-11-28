public class QuickSort {

  private final ArrayManager arrayManager;

  public QuickSort(ArrayManager arrayManager) {
    this.arrayManager = arrayManager;
  }

  public int[] sort() {
    int[] array = arrayManager.getData();
    quickSort(array, 0, array.length - 1);
    return array;
  }

  private void quickSort(int[] array, int low, int high) {
    if (low < high) {
      int pi = partition(array, low, high);
      quickSort(array, low, pi - 1);
      quickSort(array, pi + 1, high);
    }
  }

  private int partition(int[] array, int low, int high) {
    int pivot = array[high];
    int i = (low - 1);
    for (int j = low; j < high; j++) {
      if (array[j] < pivot) {
        i++;
        // Swap array[i] and array[j]
        int temp = array[i];
        arrayManager.setIndex(i, array[j]);
        arrayManager.setIndex(j, temp);
      }
    }
    // Swap array[i + 1] and array[high] (or pivot)
    int temp = array[i + 1];
    arrayManager.setIndex(i + 1, array[high]);
    arrayManager.setIndex(high, temp);
    return i + 1;
  }
}

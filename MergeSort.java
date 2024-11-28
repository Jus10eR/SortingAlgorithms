public class MergeSort {

  private final ArrayManager arrayManager;

  public MergeSort(ArrayManager arrayManager) {
    this.arrayManager = arrayManager;
  }

  public int[] sort() {
    int[] array = arrayManager.getData();
    mergeSort(array, 0, array.length - 1);
    return array;
  }

  private void mergeSort(int[] array, int left, int right) {
    if (left < right) {
      int mid = (left + right) / 2;
      mergeSort(array, left, mid);
      mergeSort(array, mid + 1, right);
      merge(array, left, mid, right);
    }
  }

  private void merge(int[] array, int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;

    int[] L = new int[n1];
    int[] R = new int[n2];

    for (int i = 0; i < n1; ++i) {
      L[i] = array[left + i];
    }
    for (int j = 0; j < n2; ++j) {
      R[j] = array[mid + 1 + j];
    }

    int i = 0, j = 0;
    int k = left;
    while (i < n1 && j < n2) {
      if (L[i] <= R[j]) {
        arrayManager.setIndex(k, L[i]);
        i++;
      } else {
        arrayManager.setIndex(k, R[j]);
        j++;
      }
      k++;
    }

    while (i < n1) {
      arrayManager.setIndex(k, L[i]);
      i++;
      k++;
    }

    while (j < n2) {
      arrayManager.setIndex(k, R[j]);
      j++;
      k++;
    }
  }
}

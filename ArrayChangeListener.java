public interface ArrayChangeListener {
  void onArrayChanged(int[] newData);

  void onArrayChanged(int[] newData, int index);
}

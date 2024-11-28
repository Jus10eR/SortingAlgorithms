import java.awt.*;
import javax.swing.*;

public class SortingControlPanel extends JPanel {

  private final ArrayManager arrayManager;

  public SortingControlPanel(ArrayManager arrayManager) {
    this.arrayManager = arrayManager;

    setLayout(new GridBagLayout());
    setBackground(new Color(30, 30, 30));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 20, 10);
    gbc.anchor = GridBagConstraints.CENTER;

    String[] algorithms = { "Bubble Sort", "Quick Sort", "Merge Sort" };
    JComboBox<String> algorithmComboBox = new JComboBox<>(algorithms);
    algorithmComboBox.setBackground(new Color(30, 30, 30));
    algorithmComboBox.setForeground(Color.WHITE);
    algorithmComboBox.setBorder(BorderFactory.createEmptyBorder());

    gbc.gridx = 0;
    gbc.gridy = 0;
    add(algorithmComboBox, gbc);

    JButton startSortingButton = createButton("Start Sorting");
    startSortingButton.addActionListener(e -> {
      startSorting(
        algorithmComboBox.getSelectedItem().toString(),
        startSortingButton,
        algorithmComboBox
      );
    });

    gbc.gridx = 1;
    add(startSortingButton, gbc);
  }

  private JButton createButton(String text) {
    JButton button = new JButton(text);
    button.setBackground(Color.WHITE);
    button.setForeground(Color.BLACK);
    button.setFocusPainted(false);
    button.setBorderPainted(false);
    button.setPreferredSize(new Dimension(180, 40));
    button.setFont(new Font("Arial", Font.PLAIN, 14));
    button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    button.setOpaque(true);
    button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    return button;
  }

  private void startSorting(
    String algorithm,
    JButton startSortingButton,
    JComboBox<String> algorithmComboBox
  ) {
    startSortingButton.setEnabled(false);
    algorithmComboBox.setEnabled(false);

    Runnable sortingTask = () -> {
      if ("Bubble Sort".equals(algorithm)) {
        new BubbleSort(arrayManager).sort();
      } else if ("Quick Sort".equals(algorithm)) {
        new QuickSort(arrayManager).sort();
      } else if ("Merge Sort".equals(algorithm)) {
        new MergeSort(arrayManager).sort();
      }
    };

    Thread sortingThread = new Thread(sortingTask);
    sortingThread.start();

    ArrayChangeAdapter myArrayChangeAdapter = new ArrayChangeAdapter() {
      public void onArrayChanged(int[] newData) {
        if (!sortingThread.isAlive()) {
          SwingUtilities.invokeLater(() -> {
            startSortingButton.setEnabled(true);
            algorithmComboBox.setEnabled(true);
            arrayManager.removeArrayChangeListener(this);
          });
        }
      }
    };
    arrayManager.addArrayChangeListener(myArrayChangeAdapter);
  }
}

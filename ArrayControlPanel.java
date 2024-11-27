import java.awt.*;
import javax.swing.*;

public class ArrayControlPanel extends JPanel {

  private final ArrayManager arrayManager;
  private JSlider sizeSlider;
  private final int sizeSliderMaxValue = 1000;

  public ArrayControlPanel(ArrayManager arrayManager) {
    this.arrayManager = arrayManager;

    setLayout(new GridBagLayout());
    setBackground(new Color(30, 30, 30)); // Match GraphPanel background color
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(20, 10, 10, 10);

    // Panel for the first row
    JPanel firstRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    firstRowPanel.setBackground(new Color(30, 30, 30)); // Match background

    // Slider for array size
    sizeSlider = new JSlider(2, this.sizeSliderMaxValue, 10);
    sizeSlider.setBackground(new Color(30, 30, 30)); // Match background
    sizeSlider.setMajorTickSpacing(10);
    sizeSlider.setMinorTickSpacing(1);
    sizeSlider.setPaintTicks(true);
    sizeSlider.setPaintLabels(true);

    // Number input for array size
    JTextField arraySizeField = new JTextField("10");
    arraySizeField.setColumns(5);

    sizeSlider.addChangeListener(e -> {
      if (!sizeSlider.getValueIsAdjusting()) {
        return;
      }
      int size = sizeSlider.getValue();
      arraySizeField.setText(String.valueOf(size));
      updateArraySize(size);
    });

    arraySizeField.addActionListener(e -> {
      int size = Integer.parseInt(arraySizeField.getText());
      sizeSlider.setValue(size);
      updateArraySize(size);
    });

    // Generate Random Array Button
    JButton generateButton = createButton("Generate Random Array");
    generateButton.addActionListener(e -> generateRandomArray());

    firstRowPanel.add(sizeSlider);
    firstRowPanel.add(arraySizeField);
    firstRowPanel.add(generateButton);

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    add(firstRowPanel, gbc);
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

  private void generateRandomArray() {
    int size = sizeSlider.getValue();
    int[] data = new int[size];
    for (int i = 0; i < data.length; i++) {
      data[i] = (int) (Math.random() * 1000 + 1);
    }
    arrayManager.setData(data);
  }

  private void updateArraySize(int size) {
    int[] data = new int[size];
    for (int i = 0; i < data.length; i++) {
      data[i] = (int) (Math.random() * 100 + 1);
    }
    arrayManager.setData(data);
  }
}

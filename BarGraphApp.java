import java.awt.*;
import javax.swing.*;

public class BarGraphApp {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      JFrame frame = new MainFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
    });
  }
}

class MainFrame extends JFrame {

  private final GraphPanel graphPanel;
  private final ArrayControlPanel arrayControlPanel;
  private final SortingControlPanel sortingControlPanel;
  private final ArrayManager arrayManager;

  public MainFrame() {
    setTitle("Dynamic Bar Graph");
    setSize(800, 600);
    setLayout(new BorderLayout());

    // Initial data
    int[] initialData = new int[] { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 };
    arrayManager = new ArrayManager(initialData);

    // Create GraphPanel and ArrayControlPanel
    graphPanel = new GraphPanel(arrayManager);
    sortingControlPanel = new SortingControlPanel(arrayManager);
    arrayControlPanel =
      new ArrayControlPanel(arrayManager, sortingControlPanel);

    add(graphPanel, BorderLayout.CENTER);
    add(arrayControlPanel, BorderLayout.NORTH);
    add(sortingControlPanel, BorderLayout.SOUTH);
  }
}

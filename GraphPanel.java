import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class GraphPanel extends JPanel {

  private int[] data;
  private int maxDataValue;
  private Integer changedIndex;
  private Rectangle2D.Double[] bars;

  // Styling
  private final int gridPadding = 50;
  private final int barPadding = 2;
  private final Color backgroundColor = new Color(30, 30, 30); // Very dark gray
  private final Color gridColor = new Color(100, 100, 100); // Medium gray
  private final Color barColor = Color.WHITE; // White bars

  public GraphPanel(ArrayManager arrayManager) {
    setDoubleBuffered(true); // Enables double buffering
    // Listen for changes in the data array
    arrayManager.addArrayChangeListener(
      new ArrayChangeAdapter() {
        @Override
        public void onArrayChanged(int[] newData) {
          data = newData;
          maxDataValue = getMaxValue(data);
          changedIndex = null;
          bars = new Rectangle2D.Double[data.length];
          SwingUtilities.invokeLater(() -> repaint());
        }

        public void onArrayChanged(int[] newData, int index) {
          data = newData;
          changedIndex = index;
          SwingUtilities.invokeLater(() ->
            repaint(
              //   (int) bars[index].getX(),
              //   (int) bars[index].getY(),
              //   (int) bars[index].getWidth(),
              //   (int) bars[index].getHeight()
            )
          );
        }
      }
    );
    data = arrayManager.getData();
    maxDataValue = getMaxValue(data);
    bars = new Rectangle2D.Double[data.length];

    // Repaint when resized
    addComponentListener(
      new ComponentAdapter() {
        @Override
        public void componentResized(ComponentEvent e) {
          repaint();
        }
      }
    );
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(
      RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON
    );

    if (this.changedIndex != null && false) {
      // draw only the changed bar
      // Clear the background of the changed bar area
      g2d.setColor(backgroundColor);
      double barWidth = (double) (
        getWidth() - 2 * gridPadding - barPadding * 2
      ) /
      data.length;
      double barX = gridPadding + changedIndex * barWidth + barPadding;
      g2d.fillRect(
        (int) barX,
        gridPadding,
        (int) barWidth,
        getHeight() - 2 * gridPadding
      );

      // Redraw the changed bar
      double barHeight = (double) data[changedIndex] /
      maxDataValue *
      (getHeight() - 2 * gridPadding);
      double barY = getHeight() - gridPadding - barHeight;

      Rectangle2D.Double bar = new Rectangle2D.Double(
        barX,
        barY,
        barWidth,
        barHeight
      );
      bars[changedIndex] =
        new Rectangle2D.Double(barX, barY, barWidth, barHeight);
      g2d.setColor(barColor);
      g2d.fill(bar);
      if (barWidth >= 3) {
        g2d.setColor(gridColor);
        g2d.draw(bar);
      }
    } else {
      // Clear background
      g2d.setColor(backgroundColor);
      g2d.fillRect(0, 0, getWidth(), getHeight());

      // Define padding and grid area
      int gridX = gridPadding;
      int gridY = gridPadding;
      int gridWidth = getWidth() - 2 * gridPadding;
      int gridHeight = getHeight() - 2 * gridPadding;

      // Draw bars
      if (data != null && data.length > 0) {
        int maxDataValue = this.maxDataValue;
        double barWidth = (double) (gridWidth - barPadding * 2) / data.length;
        barWidth = barWidth < 0.003 ? 0.003 : barWidth;

        int gap = (int) (gridWidth - barWidth * data.length); // Gap between bars
        gridWidth -= gap - barPadding * 2; // Adjust grid width to fit bars
        gridX += gap / 2 - barPadding; // Adjust grid x to center bars

        // Draw coordinate system
        g2d.setColor(gridColor);
        g2d.drawRect(gridX, gridY, gridWidth, gridHeight);

        // Calculate and Draw Bars
        for (int i = 0; i < data.length; i++) {
          double barHeight = (int) (
            (double) data[i] / maxDataValue * gridHeight
          ) -
          gridHeight /
          20;
          double barX = gridX + i * barWidth + barPadding;
          double barY = gridY + gridHeight - barHeight;

          Rectangle2D.Double bar = new Rectangle2D.Double(
            barX,
            barY,
            barWidth,
            barHeight
          );
          bars[i] = new Rectangle2D.Double(barX, barY, barWidth, barHeight);
          g2d.setColor(barColor);
          g2d.fill(bar);
          if (barWidth >= 3) {
            g2d.setColor(gridColor);
            g2d.draw(bar);
          }
        }
      }
    }
  }

  private int getMaxValue(int[] array) {
    int max = Integer.MIN_VALUE;
    for (int value : array) {
      max = Math.max(max, value);
    }
    return max;
  }
}

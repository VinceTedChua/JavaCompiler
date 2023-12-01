import javax.swing.border.AbstractBorder;
import java.awt.*;

public class BorderExtension {
    public static class BottomBorder extends AbstractBorder {
        protected int thickness;

        protected Color lineColor;

        protected int gap;

        public BottomBorder(Color color) {
            this(color, 1, 1);
        }

        public BottomBorder(Color color, int thickness) {
            this(color, thickness, thickness);
        }

        public BottomBorder(Color color, int thickness, int gap) {
            lineColor = color;
            this.thickness = thickness;
            this.gap = gap;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Color oldColor = g.getColor();
            int i;

            g.setColor(lineColor);
            for (i = 0; i < thickness; i++) {
                g.drawLine(x, y + height - i - 1, x + width, y + height - i - 1);
            }
            g.setColor(oldColor);
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(0, 0, gap, 0);
        }

        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = 0;
            insets.top = 0;
            insets.right = 0;
            insets.bottom = gap;
            return insets;
        }
        public Color getLineColor() {
            return lineColor;
        }
        public int getThickness() {
            return thickness;
        }
        public boolean isBorderOpaque() {
            return false;
        }

        public int getGap() {
            return gap;
        }

    }
}

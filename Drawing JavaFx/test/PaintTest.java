import drawing.Circle;
import drawing.Paint;
import drawing.Rectangle;
import javafx.geometry.Point2D;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.Iterator;

import static org.junit.Assert.*;

public class PaintTest extends ApplicationTest {

    Paint app;

    @Override
    public void start(Stage stage) {
        try {
            app = new Paint();
            app.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void should_draw_circle_programmatically() {
        interact(() -> {
                    app.getDrawing().addShape(new Circle(new Point2D(20, 20), 30));
                });
        Iterator it = app.getDrawing().iterator();
        assertTrue(it.next() instanceof Circle);
        assertFalse(it.hasNext());
    }

    @Test
    public void should_draw_circle() {
        // given:
        clickOn("Cercle");
        moveBy(60,60);

        // when:
        drag().dropBy(30,30);
        //press(MouseButton.PRIMARY); moveBy(30,30); release(MouseButton.PRIMARY);

        // then:
        Iterator it = app.getDrawing().iterator();
        assertTrue(it.next() instanceof Circle);
        assertFalse(it.hasNext());
    }

    @Test
    public void should_draw_rectangle() {
        // given:
        clickOn("Rectangle");
        moveBy(0,60);

        // when:
        drag().dropBy(70,40);

        // then:
        Iterator it = app.getDrawing().iterator();
        assertTrue(it.next() instanceof Rectangle);
        assertFalse(it.hasNext());
    }

    @Test
    public void should_clear() {
        // given:
        clickOn("Rectangle");
        moveBy(30,60).drag().dropBy(70,40);
        clickOn("Cercle");
        moveBy(-30,160).drag().dropBy(70,40);

        // when:
        clickOn("Clear");

        // then:
        assertFalse(app.getDrawing().iterator().hasNext());
    }

}
package spark.embeddedserver.jetty.websocket;

import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.junit.Test;

import spark.embeddedserver.jetty.websocket.WebSocketCreatorFactory.SparkWebSocketCreator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class WebSocketCreatorFactoryTest_OE25Dev {

    @Test
    public void testCreateWebSocketHandler() {
        WebSocketCreator annotated =
                WebSocketCreatorFactory.create(new WebSocketHandlerClassWrapper(AnnotatedHandler.class));
        assertTrue(annotated instanceof SparkWebSocketCreator);
        assertTrue(SparkWebSocketCreator.class.cast(annotated).getHandler() instanceof AnnotatedHandler);

        WebSocketCreator listener =
                WebSocketCreatorFactory.create(new WebSocketHandlerClassWrapper(ListenerHandler.class));
        assertTrue(listener instanceof SparkWebSocketCreator);
        assertTrue(SparkWebSocketCreator.class.cast(listener).getHandler() instanceof ListenerHandler);
    }

    @Test
    public void testCannotCreateInvalidHandlers() {
        try {
            WebSocketCreatorFactory.create(new WebSocketHandlerClassWrapper(InvalidHandler.class));
            fail("Handler creation should have thrown an IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertEquals("WebSocket handler must implement 'WebSocketListener' or be annotated as '@WebSocket'",ex.getMessage());
        }
    }

    @Test
    public void testCreate_whenInstantiationException() throws Exception {
        try {
            WebSocketCreatorFactory.create(new WebSocketHandlerClassWrapper(FailingHandler.class));
            fail("Handler creation should have thrown a RunTimeException");
        } catch(RuntimeException ex) {
            assertEquals("Could not instantiate websocket handler", ex.getMessage());
        }

    }

    @WebSocket
    class FailingHandler {

    }

    @WebSocket
    static class AnnotatedHandler {

    }

    static class ListenerHandler extends WebSocketAdapter {

    }

    static class InvalidHandler {

    }

    @Test
    public void testCreateWebSocketHandler_1_oe() {
        WebSocketCreator annotated =
                WebSocketCreatorFactory.create(new WebSocketHandlerClassWrapper(AnnotatedHandler.class));
        assertTrue(annotated instanceof SparkWebSocketCreator);
    }

    @Test
    public void testCreateWebSocketHandler_2_oe() {
        WebSocketCreator annotated =
                WebSocketCreatorFactory.create(new WebSocketHandlerClassWrapper(AnnotatedHandler.class));
        assertTrue(SparkWebSocketCreator.class.cast(annotated).getHandler() instanceof AnnotatedHandler);
    }

    @Test
    public void testCreateWebSocketHandler_3_oe() {
        WebSocketCreator annotated =
                WebSocketCreatorFactory.create(new WebSocketHandlerClassWrapper(AnnotatedHandler.class));

        WebSocketCreator listener =
                WebSocketCreatorFactory.create(new WebSocketHandlerClassWrapper(ListenerHandler.class));
        assertTrue(listener instanceof SparkWebSocketCreator);
    }

    @Test
    public void testCreateWebSocketHandler_4_oe() {
        WebSocketCreator annotated =
                WebSocketCreatorFactory.create(new WebSocketHandlerClassWrapper(AnnotatedHandler.class));

        WebSocketCreator listener =
                WebSocketCreatorFactory.create(new WebSocketHandlerClassWrapper(ListenerHandler.class));
        assertTrue(SparkWebSocketCreator.class.cast(listener).getHandler() instanceof ListenerHandler);
    }

    @Test
    public void testCannotCreateInvalidHandlers_2_oe() {
        try {
            WebSocketCreatorFactory.create(new WebSocketHandlerClassWrapper(InvalidHandler.class));
            fail("Handler creation should have thrown an IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertEquals("WebSocket handler must implement 'WebSocketListener' or be annotated as '@WebSocket'",ex.getMessage());
    }
    }

    @Test
    public void testCreate_whenInstantiationException_2_oe() throws Exception {
        try {
            WebSocketCreatorFactory.create(new WebSocketHandlerClassWrapper(FailingHandler.class));
            fail("Handler creation should have thrown a RunTimeException");
        } catch(RuntimeException ex) {
            assertEquals("Could not instantiate websocket handler", ex.getMessage());
    }
    }

@Test
    public void testCannotCreateInvalidHandlers_oe_101_oe() {
        try {
            WebSocketCreatorFactory.create(new WebSocketHandlerClassWrapper(InvalidHandler.class));
            fail("Handler creation should have thrown an IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
        }
    }

@Test
    public void testCreate_whenInstantiationException_oe_101_oe() throws Exception {
        try {
            WebSocketCreatorFactory.create(new WebSocketHandlerClassWrapper(FailingHandler.class));
            fail("Handler creation should have thrown a RunTimeException");
        } catch(RuntimeException ex) {
        }
    }

}

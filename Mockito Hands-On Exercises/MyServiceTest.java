package com.example.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


public class MyServiceTest {


    @Test
    public void testExternalApi() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("Mock Data");

        MyService service = new MyService(mockApi);
        String result = service.fetchData();

        assertEquals("Mock Data", result);
    }

    @Test
    public void testVerifyInteraction() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.fetchData();

        verify(mockApi).getData();
    }

    @Test
    public void testArgumentMatching() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.fetchDataWithArg("hello");

        
        verify(mockApi).performAction("hello");

       
        verify(mockApi).performAction(anyString());

        verify(mockApi).performAction(argThat(arg -> arg.startsWith("hel")));
    }

  
    @Test
    public void testVoidMethod() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Stub void method to do nothing (default), or explicitly:
        doNothing().when(mockApi).performAction(anyString());

        MyService service = new MyService(mockApi);
        service.fetchDataWithArg("world");

        verify(mockApi, times(1)).performAction("world");
    }

    @Test
    public void testMultipleReturns() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getData())
                .thenReturn("First Call")
                .thenReturn("Second Call");

        MyService service = new MyService(mockApi);
        String result = service.fetchTwice();

        assertEquals("First Call|Second Call", result);
    }

 
    @Test
    public void testInteractionOrder() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.doOrderedWork();

        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).performAction("first");
        inOrder.verify(mockApi).performAction("second");
    }

    @Test
    public void testVoidMethodThrowsException() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        doThrow(new RuntimeException("API failure"))
                .when(mockApi).riskyAction();

        MyService service = new MyService(mockApi);
        String result = service.safeRiskyCall();

        assertEquals("handled:API failure", result);
        verify(mockApi).riskyAction();
    }
}

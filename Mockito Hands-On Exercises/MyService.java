package com.example.mockito;


public class MyService {

    private final ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        return externalApi.getData();
    }

    public String fetchDataWithArg(String arg) {
        externalApi.performAction(arg);
        return "processed:" + arg;
    }

    public String fetchTwice() {
        String first = externalApi.getData();
        String second = externalApi.getData();
        return first + "|" + second;
    }

    public void doOrderedWork() {
        externalApi.performAction("first");
        externalApi.performAction("second");
    }

    public String safeRiskyCall() {
        try {
            externalApi.riskyAction();
            return "ok";
        } catch (RuntimeException e) {
            return "handled:" + e.getMessage();
        }
    }
}

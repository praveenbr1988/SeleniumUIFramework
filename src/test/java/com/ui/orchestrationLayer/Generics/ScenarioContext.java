package com.ui.orchestrationLayer.Generics;


import java.util.HashMap;

public class ScenarioContext {

    public static ThreadLocal<HashMap<String, Object>> scenarioContext = new ThreadLocal<HashMap<String, Object>>() {
        protected HashMap<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    public ScenarioContext() {
    }

    private final static ScenarioContext instance = new ScenarioContext();

    public static ScenarioContext getInstance() {
        return ScenarioContext.instance;
    }

    public void setScenarioContext(String key, Object value) {
        scenarioContext.get().put(key, value);
    }

    public  Object getScenarioContext(String key) {
        return scenarioContext.get().get(key);
    }

    public void clearScenarioContext(){
        scenarioContext.get().clear();
    }

}

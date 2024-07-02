package com.ui.coreLayer.FrameworkConfigs;

import com.ui.businessLayer.pageObjects.crewA.GooglePage;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class EnvironmentConfiguration {
    private static final Logger logger = LoggerUtil.getLogger(GooglePage.class);
    private Map<String, String> environments;
    private Map<String,Map<String,String>> databaseConfigs;
    private static final String ENVIRONMENT_KEY = "environments";
    private static final String DATABASE_KEY = "databses";
    private static final String CONNECTION_KEY = "connection";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String HOST_NAME = "hostName";
    private static final String PORT = "port";

    public EnvironmentConfiguration() throws Exception {
        this(new File("").getAbsolutePath() + "/src/test/resources/yamlFiles/environments1.yaml");

    }

    public EnvironmentConfiguration(Map<String, Map<String, Map<String, String>>> values) throws Exception {
        this.setupConfig(values);

    }

    public EnvironmentConfiguration(String fileName) throws Exception {
        Map<String, Map<String, Map<String, String>>> values = readYamlFile(fileName);
        this.setupConfig(values);
    }

    private void setupConfig(Map<String, Map<String, Map<String, String>>> values) throws Exception {

        validateConfiguration(values);
        Map<String, Map<String, String>> tmpEnvironments = values.get("environments");
        Map<String, Map<String, String>> tmpDatabases = values.get("databases");
        this.environments = parseEnvironments(tmpEnvironments);
        this.databaseConfigs = parseDatabases(tmpDatabases);

    }

    public String getEnvironment(String name) throws Exception {
        try{
            return (String) this.environments.get(name);
        } catch(NullPointerException var3){
            logger.info("No Environment Configuration for "+name+" found. Check your environments.yaml file: "+var3.getMessage());
            throw new Exception("No App Environment Configuration for "+name+" found. Check your environments.yaml file: "+var3.getMessage());
        }
    }

    public Map<String, String> getDatabseConfigs(String name) throws Exception {
        try{
            return (Map) this.databaseConfigs.get(name);
        } catch(NullPointerException var3){
            logger.info("No Environment Configuration for "+name+" found. Check your environments.yaml file: "+var3.getMessage());
            throw new Exception("No DB Environment Configuration for "+name+" found. Check your environments.yaml file: "+var3.getMessage());
        }
    }

    public static Map<String, String> parseEnvironments(Map<String, Map<String, String>> envs){
        Map<String, String> rtn = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        Iterator var2 = envs.entrySet().iterator();
        while(var2.hasNext()){
            Map.Entry<String, Map<String, String>> env = (Map.Entry) var2.next();
            String keyName = (String)env.getKey();
            String url = String.valueOf(env.getValue());
            rtn.put(keyName,url);
        }
        return rtn;

    }

    private static Map<String, Map<String,String>> parseDatabases(Map<String, Map<String, String>> db){
        Map<String, Map<String, String>> rtnMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        try{
            Iterator var2 = db.entrySet().iterator();

            while(true){
                while(var2.hasNext()){
                    Map.Entry<String, Map<String, String>> dbName = (Map.Entry) var2.next();
                    Map<String, String> connectionCfg = new TreeMap(String.CASE_INSENSITIVE_ORDER);
                    String keyName = (String)dbName.getKey();
                    connectionCfg.put("connection", (String) ((Map)dbName.getValue()).get("connection"));

                    if(((Map)dbName.getValue()).get("port")==null) {
                        connectionCfg.put("user", (String) ((Map)dbName.getValue()).get("user"));
                        connectionCfg.put("password", (String) ((Map)dbName.getValue()).get("password"));
                        rtnMap.put(keyName,connectionCfg);
                    } else if(((Map)dbName.getValue()).get("port")!=null && ((Map)dbName.getValue()).get("user")!=null ){
                        connectionCfg.put("hostName", (String) ((Map)dbName.getValue()).get("hostName"));
                        connectionCfg.put("port", (String) ((Map)dbName.getValue()).get("port"));
                        connectionCfg.put("user", (String) ((Map)dbName.getValue()).get("user"));
                        connectionCfg.put("password", (String) ((Map)dbName.getValue()).get("password"));
                        rtnMap.put(keyName,connectionCfg);
                    } else if(((Map)dbName.getValue()).get("port")!=null && ((Map)dbName.getValue()).get("user")==null ){
                        connectionCfg.put("hostName", (String) ((Map)dbName.getValue()).get("hostName"));
                        connectionCfg.put("port", (String) ((Map)dbName.getValue()).get("port"));
                        rtnMap.put(keyName,connectionCfg);
                    }
                }
                return rtnMap;
            }

        } catch(NullPointerException var6){
            logger.info("No Database Configurations detected");
            return rtnMap;
        }


    }

    private static Map<String, Map<String, Map<String, String>>> readYamlFile(String filename) throws FileNotFoundException {
        Yaml yaml = new Yaml();
        return yaml.load(new FileInputStream(new File(filename)));
    }


    public static void validateConfiguration(Map<String, Map<String, Map<String, String>>> config) throws Exception {
        try{
            assert config.get("environments") != null;
            assert !config.get("environments").isEmpty();

            Iterator var1 = ((Map)config.get("environments")).entrySet().iterator();
            Map.Entry entry;
            while(var1.hasNext()){
                entry= (Map.Entry) var1.next();
                assert entry.getValue() != null;
                assert !String.valueOf(entry.getValue()).isEmpty();
            }
            if(config.get("databases")!=null){
                assert !config.get("databases").keySet().isEmpty();
                var1 = ((Map)config.get("databases")).entrySet().iterator();
                while(var1.hasNext()){
                    entry= (Map.Entry) var1.next();
                    Map<String, String> dbValues = (Map)entry.getValue();
                    assert dbValues.containsKey("connection");
                    assert dbValues.get("connection") != null;
                }
            }


        } catch(AssertionError var4){
            throw new Exception("Assertion Errors: "+var4.getMessage());
        }
    }

}

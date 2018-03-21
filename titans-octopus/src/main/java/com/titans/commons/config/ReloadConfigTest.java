package com.titans.commons.config;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.ConfigurationBuilderEvent;
import org.apache.commons.configuration2.builder.ReloadingFileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.FileBasedBuilderParameters;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.event.EventListener;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.reloading.PeriodicReloadingTrigger;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 *
 * https://stackoverflow.com/questions/36730860/commons-configuration2-reloadingfilebasedconfiguration
 * http://blog.csdn.net/wanghantong/article/details/79072474
 * reload config
 *
 * @author fangwenhui
 * @date 2018-03-21 10:51
 **/
public class ReloadConfigTest {

    public static void main(String[] args) throws ConfigurationException {
        Parameters parameters = new Parameters();


        String resource = "";
        String path = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();
        System.out.println(path);
        File configFile = new File(path);
        System.out.println(configFile.exists());

        FileBasedBuilderParameters param = parameters.fileBased().setFile(configFile)
                .setEncoding("UTF-8")
                .setListDelimiterHandler(new DefaultListDelimiterHandler(','))
                .setThrowExceptionOnMissing(true);

        ReloadingFileBasedConfigurationBuilder<PropertiesConfiguration> builder =
                new ReloadingFileBasedConfigurationBuilder<PropertiesConfiguration>(PropertiesConfiguration.class)
                .configure(param);

//        builder.configure(param);

        builder.addEventListener(ConfigurationBuilderEvent.RESET, new EventListener<ConfigurationBuilderEvent>() {
            @Override
            public void onEvent(ConfigurationBuilderEvent event) {
                System.out.println(event.getEventType());
                //builder.getReloadingController().checkForReloading(null);
            }
        });
        PeriodicReloadingTrigger trigger = new PeriodicReloadingTrigger(builder.getReloadingController(), null, 10, TimeUnit.SECONDS);

        trigger.start();

        PropertiesConfiguration config = builder.getConfiguration();


        /**
         * One important point to keep in mind when using this approach to reloading is that reloads are only functional if the builder is used as central component for accessing configuration data.
         * The configuration instance obtained from the builder will not change automagically!
         * So if an application fetches a configuration object from the builder at startup and then uses it throughout its life time, changes on the external configuration file become never visible.
         * The correct approach is to keep a reference to the builder centrally and obtain the configuration from there every time configuration data is needed.
         */

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    //error!
//                    boolean aBoolean = config.getBoolean("sync.status");
                    boolean status = builder.getConfiguration().getBoolean("sync.status");
                    System.out.println("sync.status:"+status);

                    String string = builder.getConfiguration().getString("switch.status");
                    System.out.println("switchstatus:"+string);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task,0,11000);

    }
}

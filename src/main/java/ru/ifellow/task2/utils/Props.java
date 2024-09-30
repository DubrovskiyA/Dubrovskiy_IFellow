package ru.ifellow.task2.utils;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"file:src/test/resources/test.properties"})

public interface Props extends Config {

    Props props = ConfigFactory.create(Props.class);

    @Key("test.task2.base.url")
    String baseUrl();

    @Key("test.task2.create.user.path")
    String createUserPath();
}

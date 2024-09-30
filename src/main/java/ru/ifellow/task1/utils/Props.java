package ru.ifellow.task1.utils;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"file:src/test/resources/test.properties"})

public interface Props extends Config {

    Props props = ConfigFactory.create(Props.class);

    @Key("test.task1.base.url")
    String baseUrl();

    @Key("test.task1.character.path")
    String characterPath();

    @Key("test.task1.episode.path")
    String episodePath();
}

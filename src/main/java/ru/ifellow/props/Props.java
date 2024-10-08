package ru.ifellow.props;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"file:src/test/resources/test.properties"})

public interface Props extends Config {

    Props props = ConfigFactory.create(Props.class);

    @Key("test.url")
    String baseUrl();

    @Key("test.user")
    String userName();

    @Key("test.pass")
    String pass();
}

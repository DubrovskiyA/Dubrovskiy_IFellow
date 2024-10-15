package ru.ifellow.utils;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"file:src/test/resources/test.properties"})

public interface Props extends Config {

    Props props = ConfigFactory.create(Props.class);

    @Key("test.rickandmorty.base.url")
    String rickAndMortyBaseUrl();

    @Key("test.rickandmorty.character.path")
    String rickAndMortyCharacterPath();

    @Key("test.rickandmorty.episode.path")
    String rickAndMortyEpisodePath();

    @Key("test.reqres.base.url")
    String reqResBaseUrl();

    @Key("test.reqres.create.user.path")
    String reqResCreateUserPath();
}

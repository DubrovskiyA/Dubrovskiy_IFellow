package ru.ifellow.task2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    private String name;
    private String job;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO that)) return false;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getJob(), that.getJob());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getJob());
    }

    @Override
    public String toString() {
        return "UserToCreateDTO{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}

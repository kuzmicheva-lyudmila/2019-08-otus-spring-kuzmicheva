package ru.otus.homework.dao;

import ru.otus.homework.domain.Test;

public class TestDaoImpl implements TestDao{
    private static String url;
    private static Test test;

    public TestDaoImpl(String url) {
        this.url = url;
    }

    @Override
    public Test getTest() {
        return null;
    }
}

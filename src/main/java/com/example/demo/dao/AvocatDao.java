package com.example.demo.dao;

import com.example.demo.entities.Avocat;

import java.util.List;

public interface AvocatDao {
    void insert(Avocat avocat);
    void update(Avocat avocat);
    void deleteById(Integer id);
    Avocat findById(Integer id);
    List<Avocat> findAll();
}


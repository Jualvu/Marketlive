package com.cci.MarketLive.service;

import java.sql.SQLException;
import java.util.List;

public interface ICrud<T> {

    boolean create(T objeto) throws SQLException;

    T read(int id) throws SQLException;
    
    boolean update(T objeto) throws SQLException;

    boolean delete(int id) throws SQLException;

    List<T> readAll() throws SQLException;
}

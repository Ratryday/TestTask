package dao;

import java.util.ArrayList;

public interface NEXEntityDAOInterface<Bean> {
    void delete(int id);

    ArrayList<Bean> getAll();

    Bean getById(int id);

    void update(Bean object);

    void add(Bean object);

    @Deprecated
    void clear();
}

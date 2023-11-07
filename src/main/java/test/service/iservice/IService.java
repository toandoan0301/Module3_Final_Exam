package test.service.iservice;

import java.util.List;

public interface IService<E> {
    void add(E e);
    void delete(int id);
    void edit(E e);
    List<E> findAll();
    List<E> findByName(String name);
    E findById(int id);
}

package com.nsmjsf.web.datalayer;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;

public interface IDataSource<T> {

	public T create(T record, Session session);

	public T create(T record);

	public void createAll(List<T> records);

	public List<T> getAll();

	public T get(int id);

	public T get(int id, Session session);

	public List<T> getWhere(HashMap<String, Object> clauses);

	public List<T> getWhere(HashMap<String, Object> clauses, Session session);

	public void delete(int id);

	public void delete(int id, Session session);

	public void delete(T record);

	public void delete(T record, Session session);

	public T update(T record);

	public T update(T record, Session session);

	public T getByPost(int postId);

	public List<T> getByUser(int userId);

	public T saveOrUpdate(T record);

}

package com.nsmjsf.web.datalayer;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.faces.bean.ManagedBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean
public class TDataSource<T> implements IDataSource<T>, Closeable {
	private static final Log log = LogFactory.getLog(TDataSource.class);
	private static int counter;
	private String tableName;
	private String primaryKey;
	private String postField;
	private String userField;
	private Session session;

	public TDataSource() {
		this.session = DbSessionManager.getUserDbsession().getSession();
		counter++;
		log.info(counter + "dataSource created");

	}

	public Session getSession() {
		if (this.session == null || (!this.session.isOpen())) {
			log.warn("null session occurred");
			session = DbSessionManager.getUserDbsession().getSession();
		}

		return session;

	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getUserField() {
		return userField;
	}

	public void setUserField(String userField) {
		this.userField = userField;
	}

	public String getPostField() {
		return postField;
	}

	public void setPostField(String postField) {
		this.postField = postField;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	@Override
	public T create(T record, Session session) {
		try {
			session.save(record);
			session.flush();
			session.refresh(record);
		} catch (HibernateException ex) {
			log.info(ex.getMessage());
		}

		return record;

	}

	@Override
	public List<T> getAll() {
		@SuppressWarnings("unchecked")
		List<T> list = getSession().createQuery("from " + getTableName())
				.list();

		return list;
	}

	@Override
	public T get(int id) {
		@SuppressWarnings("unchecked")
		Session sess = getSession();

		List<T> list = sess
				.createQuery(
						"from " + getTableName() + " where " + getPrimaryKey()
								+ "=?")

				.setParameter(0, id).list();

		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<T> getWhere(HashMap<String, Object> clauses) {
		Session s = getSession();
		StringBuilder sb = new StringBuilder();
		sb.append("FROM " + getTableName());
		String hql = null;
		List<T> list = null;
		Iterator<Entry<String, Object>> it = clauses.entrySet().iterator();
		boolean needsAnd = false;
		if (clauses.isEmpty()) {
			hql = sb.toString();
			list = s.createQuery(hql).list();

		} else {
			sb.append(" WHERE ");
			while (it.hasNext()) {

				if (needsAnd) {
					sb.append(" AND ");
				}
				Entry<String, Object> entry = it.next();
				sb.append(entry.getKey() + "=:" + entry.getKey());
				needsAnd = true;

			}
			hql = sb.toString();
			// Utils.printDebug("hql", hql);
			Query q = s.createQuery(hql);
			Iterator<Entry<String, Object>> it2 = clauses.entrySet().iterator();
			while (it2.hasNext()) {
				Entry<String, Object> entry = it2.next();
				q.setParameter(entry.getKey(), entry.getValue());
			}
			list = q.list();
		}

		return list;
	}

	@Override
	public void delete(int id) {
		this.delete(get(id));

	}

	@Override
	public void delete(T record) {
		try {
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			session.delete(record);
			tx.commit();

			getSession().flush();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	@Override
	public T update(T record) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(record);
			tx.commit();
			session.flush();
			session.refresh(record);
		} catch (HibernateException ex) {
			log.info(ex.getMessage());
		}

		return record;

	}

	@Override
	public T create(T record) {
		Session session = getSession();
		try {
			Transaction tx = session.beginTransaction();
			session.save(record);
			tx.commit();
			// session.flush();

			session.refresh(record);
		} catch (HibernateException ex) {
			log.info(ex.getMessage());
		}
		return record;
	}

	@Override
	public T get(int id, Session session) {

		List<T> list = session
				.createQuery(
						"from " + getTableName() + " where " + getPrimaryKey()
								+ "=?")

				.setParameter(0, id).list();

		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}

	}

	@Override
	public List<T> getWhere(HashMap<String, Object> clauses, Session session) {
		StringBuilder sb = new StringBuilder();
		sb.append("FROM " + getTableName());
		String hql = null;
		List<T> list = null;
		Iterator<Entry<String, Object>> it = clauses.entrySet().iterator();
		boolean needsAnd = false;
		if (clauses.isEmpty()) {
			hql = sb.toString();
			list = session.createQuery(hql).list();

		} else {
			sb.append(" WHERE ");
			while (it.hasNext()) {

				if (needsAnd) {
					sb.append(" AND ");
				}
				Entry<String, Object> entry = it.next();
				sb.append(entry.getKey() + "=:" + entry.getKey());
				needsAnd = true;

			}
			hql = sb.toString();
			// Utils.printDebug("hql", hql);
			Query q = session.createQuery(hql);
			Iterator<Entry<String, Object>> it2 = clauses.entrySet().iterator();
			while (it2.hasNext()) {
				Entry<String, Object> entry = it2.next();
				q.setParameter(entry.getKey(), entry.getValue());
			}
			list = q.list();
		}

		return list;
	}

	@Override
	public void delete(int id, Session session) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(T record, Session session) {
		session.delete(record);
		// session.flush();

	}

	@Override
	public T update(T record, Session session) {
		try {
			session.update(record);
			// session.flush();
			// session.refresh(record);
		} catch (HibernateException ex) {
			log.info(ex.getMessage());
		}
		return record;
	}

	@Override
	public List<T> getByUser(int userId) {
		Session session = getSession();

		String hql = "FROM " + this.getTableName()
				+ " E WHERE E.post.user.userId =" + userId;
		Query query = session.createQuery(hql);
		List<T> results = query.list();
		return results;
	}

	@Override
	public void close() throws IOException {
		try {
			log.info(counter + "closing datasource");
			counter--;
			this.session.flush();
			this.session.close();
		} catch (HibernateException ex) {
			log.info(ex.getMessage());
		}

	}

	public void refreshDataSource() {

	}

	@Override
	public T getByPost(int postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createAll(List<T> records) {
		for (T record : records) {
			this.create(record);
		}

	}

	@Override
	public T saveOrUpdate(T record) {
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(record);
			tx.commit();
		} catch (Exception ex) {
			log.equals(ex.getMessage());
		}
		return record;
	}

}

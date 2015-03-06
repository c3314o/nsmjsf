package com.nsmjsf.web.datasources;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import com.nsmjsf.web.datalayer.TDataSource;
import com.nsmjsf.web.datamodels.MarketIndex;

public class MarketIndexDataSource extends TDataSource<MarketIndex> {

	public MarketIndexDataSource() {
		super();
		this.setTableName("MarketIndex");
		this.setPrimaryKey("market_index_id");
	}

	@Override
	public List<MarketIndex> getByUser(int userId) {
		// TODO Auto-generated method stub
		return super.getByUser(userId);
	}

	@Override
	public Session getSession() {
		// TODO Auto-generated method stub
		return super.getSession();
	}

	@Override
	public void setSession(Session session) {
		// TODO Auto-generated method stub
		super.setSession(session);
	}

	@Override
	public String getUserField() {
		// TODO Auto-generated method stub
		return super.getUserField();
	}

	@Override
	public void setUserField(String userField) {
		// TODO Auto-generated method stub
		super.setUserField(userField);
	}

	@Override
	public String getPostField() {
		// TODO Auto-generated method stub
		return super.getPostField();
	}

	@Override
	public void setPostField(String postField) {
		// TODO Auto-generated method stub
		super.setPostField(postField);
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return super.getTableName();
	}

	@Override
	public void setTableName(String tableName) {
		// TODO Auto-generated method stub
		super.setTableName(tableName);
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return super.getPrimaryKey();
	}

	@Override
	public void setPrimaryKey(String primaryKey) {
		// TODO Auto-generated method stub
		super.setPrimaryKey(primaryKey);
	}

	@Override
	public MarketIndex create(MarketIndex record, Session session) {
		// TODO Auto-generated method stub
		return super.create(record, session);
	}

	@Override
	public List<MarketIndex> getAll() {
		// TODO Auto-generated method stub
		return super.getAll();
	}

	@Override
	public MarketIndex get(int id) {
		// TODO Auto-generated method stub
		return super.get(id);
	}

	@Override
	public List<MarketIndex> getWhere(HashMap<String, Object> clauses) {
		// TODO Auto-generated method stub
		return super.getWhere(clauses);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		super.delete(id);
	}

	@Override
	public void delete(MarketIndex record) {
		// TODO Auto-generated method stub
		super.delete(record);
	}

	@Override
	public MarketIndex update(MarketIndex record) {
		// TODO Auto-generated method stub
		return super.update(record);
	}

	@Override
	public MarketIndex create(MarketIndex record) {
		// TODO Auto-generated method stub
		return super.create(record);
	}

	@Override
	public MarketIndex get(int id, Session session) {
		// TODO Auto-generated method stub
		return super.get(id, session);
	}

	@Override
	public List<MarketIndex> getWhere(HashMap<String, Object> clauses,
			Session session) {
		// TODO Auto-generated method stub
		return super.getWhere(clauses, session);
	}

	@Override
	public void delete(int id, Session session) {
		// TODO Auto-generated method stub
		super.delete(id, session);
	}

	@Override
	public void delete(MarketIndex record, Session session) {
		// TODO Auto-generated method stub
		super.delete(record, session);
	}

	@Override
	public MarketIndex update(MarketIndex record, Session session) {
		// TODO Auto-generated method stub
		return super.update(record, session);
	}

	@Override
	public MarketIndex getByPost(int postId) {
		// TODO Auto-generated method stub
		return super.getByPost(postId);
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		super.close();
	}

	@Override
	public void refreshDataSource() {
		// TODO Auto-generated method stub
		super.refreshDataSource();
	}

}

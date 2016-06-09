/**
 * 
 */
package com.cer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cer.dao.GMDao;
import com.cer.persistent.Seller;
import com.cer.util.PropertyConfigurer;
import com.vividsolutions.jts.geom.Polygon;

/**
 * @author PRaveen Kumar
 *
 */
@Repository
public class GMDaoImpl implements GMDao {

	@Resource(name = "dataSourceBean")
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private PropertyConfigurer propertyConfigurer;

	protected final Logger logger = LoggerFactory.getLogger(GMDaoImpl.class);

	/* @Transactional(propagation=Propagation.MANDATORY) */
	public void save(Object entity) throws IllegalArgumentException {
		logger.info("Save Start ");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(entity);

			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();

		} finally {
			session.close();
			logger.info("Save End ");
		}
	}

	/* @Transactional(propagation=Propagation.MANDATORY) */
	public void update(Object entity) throws IllegalArgumentException {
		logger.info("update Start ");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(entity);

			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();

		} finally {
			logger.info("update end ");
			session.close();
		}
	}

	/* @Transactional(propagation=Propagation.MANDATORY) */
	public void delete(Object entity) throws IllegalArgumentException {
		logger.info("delete Start ");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		try {
			session.delete(entity);

			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();

		} finally {
			logger.info("delete End ");
			session.close();
		}

	}

	/* @Transactional(propagation=Propagation.MANDATORY) */
	public List find(String queryString) throws IllegalArgumentException {
		logger.info("find(String queryString) Start ");
		return find(queryString, null);
	}

	/* @Transactional(propagation=Propagation.MANDATORY) */
	public List find(String queryString, Object value) throws IllegalArgumentException {
		logger.info("find(String queryString, Object value) Start ");
		return find(queryString, new Object[] { value });
	}

	/* @Transactional(propagation=Propagation.MANDATORY) */
	public List find(String queryString, Object[] values) throws IllegalArgumentException {
		logger.info("find(String queryString, Object[] values) Start ");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(queryString);
			if (values != null) {
				for (int i = 1; i <= values.length; i++) {
					query.setParameter(i - 1, values[i - 1]);
				}
			}
			return query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();

		} finally {

			session.close();
		}
		logger.info("find(String queryString, Object[] values)  End ");
		return null;
	}

	/*
	 * public Object get(Class entityClass, Object id) throws
	 * IllegalArgumentException { return
	 * sessionFactory.getCurrentSession().get(entityClass.getClass(), id);
	 * 
	 * }
	 */

	public List executeDataBaseQuery(String sql, String[] values) throws SQLException {
		List<String> list = new ArrayList<String>();
		/*
		 * Connection conn = null; try { conn = dataSource.getConnection();
		 * PreparedStatement ps = conn.prepareStatement(sql); if (values !=
		 * null) { int idx = 1; for (String value : values) { ps.setString(idx,
		 * (String) value); idx++; } } ResultSet rs = ps.executeQuery(); while
		 * (rs.next()) { // list.add(rs.getString("entity_id"));
		 * list.add(rs.getString(1)); } rs.close(); ps.close(); } catch
		 * (SQLException e) { throw e; } finally { if (conn != null) { try {
		 * conn.close(); } catch (SQLException e) { } } }
		 */
		return list;
	}

	public Object get(Class entityClass, Object id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Seller> nearestWarehouse(Polygon polygon) throws SQLException {
		List<Seller> result = null;
		logger.info("nearestWarehouse Start ");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		try {
			String sqlQuery = propertyConfigurer.getProperty("GET_NEAREST_WAREHOUSE");

			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();

		} finally {
			logger.info("delete End ");
			session.close();
		}

		return result;
	}

	public List<Seller> nearestWarehouse(String sqlQuery) throws SQLException {
		logger.info("nearestWarehouse start ");
		List<Seller> result = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Seller ware = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(sqlQuery);
			rs = ps.executeQuery();
			result = new ArrayList<Seller>();
			while (rs.next()) {
				ware = new Seller();
				ware.setId(rs.getLong("ID"));
				// ID,name, address, ST_AsGeoJSON(servingArea) as servingArea,
				// ST_AsGeoJSON(location) as location, formatted_address
				ware.setAddress(rs.getString("address"));
				ware.setName(rs.getString("name"));
				ware.setServingAreaJson(rs.getString("servingArea"));
				ware.setLocationJson(rs.getString("location"));
				ware.setFormatted_address(rs.getString("formatted_address"));
				result.add(ware);

			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {

			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		logger.info("nearestWarehouse end ");
		return result;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public PropertyConfigurer getPropertyConfigurer() {
		return propertyConfigurer;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void setPropertyConfigurer(PropertyConfigurer propertyConfigurer) {
		this.propertyConfigurer = propertyConfigurer;
	}

}

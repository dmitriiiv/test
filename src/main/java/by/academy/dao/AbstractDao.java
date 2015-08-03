package by.academy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import by.academy.pool.ConnectionPool;
import static by.academy.util.Constants.*;
import by.academy.util.DBUnits;

public abstract class AbstractDao<T> implements GenericDao<T> {

	@Override
	public void create(T object) {
		Connection connection = null;
		PreparedStatement statement = null;
		try{
			connection = ConnectionPool.getInstance().getConnection();
			statement = connection.prepareStatement(getSql(METHOD_NAME_CREATE));
			setParameters(METHOD_NAME_CREATE, statement, object);
			statement.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			DBUnits.close(connection, statement);
		}
	}

	@Override
	public T read(T object) {
		T result = null;
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
        	connection = ConnectionPool.getInstance().getConnection();
			statement = connection.prepareStatement(getSql(METHOD_NAME_READ));
			setParameters(METHOD_NAME_READ, statement, object);
			resultSet = statement.executeQuery();
			result = create(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUnits.close(connection, statement, resultSet);
        }
		return result;
	}

	@Override
	public List<T> readAll() {
		List<T> result = null;
		Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
        	connection = ConnectionPool.getInstance().getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(getSql(METHOD_NAME_READ_ALL));
			result = createList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUnits.close(connection, statement, resultSet);
        }
        return result;
	}
	
	protected abstract void setParameters(String methodName, Statement statement, T object) throws SQLException;

	protected abstract String getSql(String methodName);
	
	protected abstract T create(ResultSet resultSet) throws SQLException;
	
	protected abstract List<T> createList(ResultSet resultSet) throws SQLException;
	
}

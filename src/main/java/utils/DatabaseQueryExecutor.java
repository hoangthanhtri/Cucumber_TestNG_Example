package utils;

import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryExecutor {
    private Connection connection;
    private Statement statement;

    public DatabaseQueryExecutor(String url, String username, String password) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
            this.statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public ResultSet executeQuery(String query) {
        try {
            return statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public void executeUpdate(String query) {
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void close() {

        try {
            if (statement != null && !statement.isClosed())
                statement.close();
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getFirstRecordResult(String query) {
        try {
            ResultSet resultSet = executeQuery(query);
            resultSet.next();
            return resultSet.getString(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public <T> T getFirstRecordResult(String query, String... columnNames) {
        try {
            ResultSet resultSet = executeQuery(query);
            resultSet.next();

            if (columnNames.length == 1) {
                return (T) resultSet.getString(columnNames[0]);
            } else {
                JSONObject jsonObject = new JSONObject();
                for (String columnName : columnNames) {
                    jsonObject.put(columnName, resultSet.getString(columnName));
                }
                return (T) jsonObject;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }


    public String getFirstRecordResult(String query, int columnIndex) {
        try {
            ResultSet resultSet = executeQuery(query);
            resultSet.next();
            return resultSet.getString(columnIndex);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String getFirstValueFirstRecord(String query) {
        try {
            ResultSet resultSet = executeQuery(query);
            resultSet.next();
            return resultSet.getString(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<String> getColumnRecords(String query) {
        try {
            ResultSet resultSet = executeQuery(query);
            List<String> columnValues = new ArrayList<>();
            while (resultSet.next()) {
                columnValues.add(resultSet.getString(1));
            }
            return columnValues;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<String> getColumnRecords(String query, String columnName) {
        try {
            ResultSet resultSet = executeQuery(query);
            List<String> columnValues = new ArrayList<>();
            while (resultSet.next()) {
                columnValues.add(resultSet.getString(columnName));
            }
            return columnValues;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<String> getColumnRecords(String query, int columnIndex) {
        try {
            ResultSet resultSet = executeQuery(query);
            List<String> columnValues = new ArrayList<>();
            while (resultSet.next()) {
                columnValues.add(resultSet.getString(columnIndex));
            }
            return columnValues;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public JSONObject getAllRecordsToJsonObject(String query, String primaryColumn, String... columnNames) {
        try {
            ResultSet resultSets = executeQuery(query);
            JSONObject jsonObject = new JSONObject();
            while (resultSets.next()) {
                JSONObject jsonChildObject = new JSONObject();
                for (String columnName : columnNames) {
                    jsonChildObject.put(columnName, resultSets.getString(columnName));
                }
                jsonObject.put(resultSets.getString(primaryColumn), jsonChildObject);
            }
            return jsonObject;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
}

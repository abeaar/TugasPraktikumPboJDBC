package DAOdataMovie;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import connection.Connector;
import DAOImplement.DataMovieImplement;
import model.DataMovie;
import javax.swing.JOptionPane;

public class DataMovieDAO implements DataMovieImplement {

    Connection connection;
    final String select = "SELECT * FROM movie";
    final String insert = "INSERT INTO movie (judul, alur, penokohan, akting, nilai) VALUES (?, ?, ?, ?, ?);";
    final String update = "UPDATE movie SET alur = ?, penokohan = ?, akting = ?, nilai = ? WHERE judul = ?;";
    final String delete = "DELETE FROM movie WHERE judul = ?";

    public DataMovieDAO() {
        connection = Connector.connection();
    }

    public void insert (DataMovie dm){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert, statement.RETURN_GENERATED_KEYS);
            statement.setString(1, dm.getJudul());
            statement.setDouble(2, dm.getAlur());
            statement.setDouble(3, dm.getPenokohan());
            statement.setDouble(4, dm.getAkting());
            statement.setDouble(5, dm.getNilai());
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "Data gagal ditambahkan: Data dengan primary key tersebut sudah ada.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data gagal ditambahkan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void update (DataMovie dm){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setDouble(1, dm.getAlur());
            statement.setDouble(2, dm.getPenokohan());
            statement.setDouble(3, dm.getAkting());
            statement.setDouble(4, dm.getNilai());
            statement.setString(5, dm.getJudul());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    public void delete (String judul){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setString(1, judul);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void select (DataMovie dm){}
    public List<DataMovie> getAll(){
        List<DataMovie> dm = null;
        try {
            dm = new ArrayList<DataMovie>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            while (resultSet.next()) {
                DataMovie movie = new DataMovie();
                movie.setJudul(resultSet.getString("judul"));
                movie.setAlur(resultSet.getDouble("alur"));
                movie.setPenokohan(resultSet.getDouble("penokohan"));
                movie.setAkting(resultSet.getDouble("akting"));
                movie.setNilai(resultSet.getDouble("nilai"));
                dm.add(movie);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataMovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return dm;
    }
}
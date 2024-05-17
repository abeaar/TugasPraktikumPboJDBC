package controller;

import java.util.List;
import DAOdataMovie.DataMovieDAO;
import DAOImplement.DataMovieImplement;
import model.*;
import view.MainView;
import javax.swing.JOptionPane;

public class DataMovieController {
    MainView frame;
    DataMovieImplement implDataMovie;
    List<DataMovie> dm;
    
    private String deleteValue;
    private Double tmpAlur;
    private Double tmpPenokohan;
    private Double tmpAkting;
    private Double tmpRateResult;
    private boolean insertError = false;
    private boolean updateError = false;
    private boolean deleteError = false;
    
    private boolean isFormValid() {
        return !frame.getJTxtjudul().getText().isEmpty()
            && !frame.getJTxtalur().getText().isEmpty()
            && !frame.getJTxtpenokohan().getText().isEmpty()
            && !frame.getJTxtakting().getText().isEmpty();
    }
    
    public DataMovieController (MainView frame){
    this.frame = frame; 
    implDataMovie = new DataMovieDAO();
    dm = implDataMovie.getAll();
    }
    public void isitabel () {
       dm = implDataMovie.getAll();
       ModelTabelDataMovie mm = new ModelTabelDataMovie(dm);
       frame.getTableDataMovie().setModel(mm);  
    }
    
    public void insert() {
        if (isFormValid()) {
            try {
                DataMovie movie = new DataMovie();
                movie.setJudul(frame.getJTxtjudul().getText());
                movie.setAlur(Double.parseDouble(frame.getJTxtalur().getText()));
                movie.setPenokohan(Double.parseDouble(frame.getJTxtpenokohan().getText()));
                movie.setAkting(Double.parseDouble(frame.getJTxtakting().getText()));
                tmpAlur = Double.parseDouble(frame.getJTxtalur().getText());
                tmpPenokohan = Double.parseDouble(frame.getJTxtpenokohan().getText());
                tmpAkting = Double.parseDouble(frame.getJTxtakting().getText());
                tmpRateResult = (tmpAlur + tmpPenokohan + tmpAkting) / 3;
                movie.setNilai(tmpRateResult);
                implDataMovie.insert(movie);
                insertError = false;
            } catch (NumberFormatException ex) {
                // Tampilkan pesan bahwa ada input kosong
                JOptionPane.showMessageDialog(null, "Invalid input!", "Warning", JOptionPane.WARNING_MESSAGE);
                ex.printStackTrace();
                insertError = true;
            }
        } else {
            // Tampilkan pesan peringatan bahwa data belum diisi
            JOptionPane.showMessageDialog(null, "Please fill the form!", "Warning", JOptionPane.WARNING_MESSAGE);
            insertError = true;
        }
    }
      public void update() {
        if (isFormValid()) {
            try {
                DataMovie movie = new DataMovie();
                movie.setJudul(frame.getJTxtjudul().getText());
                movie.setAlur(Double.parseDouble(frame.getJTxtalur().getText()));
                movie.setPenokohan(Double.parseDouble(frame.getJTxtpenokohan().getText()));
                movie.setAkting(Double.parseDouble(frame.getJTxtakting().getText()));
                tmpAlur = Double.parseDouble(frame.getJTxtalur().getText());
                tmpPenokohan = Double.parseDouble(frame.getJTxtpenokohan().getText());
                tmpAkting = Double.parseDouble(frame.getJTxtakting().getText());
                tmpRateResult = (tmpAlur + tmpPenokohan + tmpAkting) / 3;
                movie.setNilai(tmpRateResult);
                implDataMovie.update(movie);
                updateError = false;
                JOptionPane.showMessageDialog(null, "Data Updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                // Tampilkan pesan bahwa ada input kosong
                JOptionPane.showMessageDialog(null, "Invalid Input!", "Warning", JOptionPane.WARNING_MESSAGE);
                ex.printStackTrace();
                updateError = true;
            }
        } else {
            // Tampilkan pesan peringatan bahwa data belum diisi
            JOptionPane.showMessageDialog(null, "Please Fill the form!", "Warning", JOptionPane.WARNING_MESSAGE);
            updateError = true;
        }
      }
    public void delete() {
        if (isFormValid()) {
            try {
                DataMovie movie = new DataMovie();
                deleteValue = frame.getJTxtjudul().getText();
                implDataMovie.delete(deleteValue);
                deleteError = false;
                JOptionPane.showMessageDialog(null, "Data Deleted!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                // Tampilkan pesan bahwa ada input kosong
                JOptionPane.showMessageDialog(null, "Invalid Input!", "Warning", JOptionPane.WARNING_MESSAGE);
                ex.printStackTrace();
                deleteError = true;
            }
        } else {
            // Tampilkan pesan peringatan bahwa data belum diisi
            JOptionPane.showMessageDialog(null, "Please fill the form!", "Warning", JOptionPane.WARNING_MESSAGE);
            deleteError = true;
        }
    }
    public boolean isInsertError() {
        return insertError;
    }

    public boolean isUpdateError() {
        return updateError;
    }

    public boolean isDeleteError() {
        return deleteError;
    }

}





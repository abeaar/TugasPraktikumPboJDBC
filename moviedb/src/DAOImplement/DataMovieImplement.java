package DAOImplement;

import java.util.List;

import model.DataMovie;

public interface DataMovieImplement {
    public void insert(DataMovie dm);
    public void update(DataMovie dm);
    public void delete(String judul);
    public List<DataMovie>getAll();


}



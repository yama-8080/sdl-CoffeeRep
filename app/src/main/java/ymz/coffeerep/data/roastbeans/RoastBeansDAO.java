package ymz.coffeerep.data.roastbeans;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ymz.coffeerep.data.roastbeans.RoastBeans;

@Dao
public interface RoastBeansDAO {

    @Query("SELECT roastbeans_id FROM roastbeans WHERE roastbeans_name IN (:name)")
    int getRoastBeansId(String name);

    @Query("SELECT * FROM roastbeans WHERE roastbeans_id = :id")
    LiveData<RoastBeans> getRoastBeans(int id);

    @Query("SELECT * FROM roastbeans ORDER BY roastbeans_name ASC")
    LiveData<List<RoastBeans>> getAlphabetizedRoastBeans();

    @Query("SELECT * FROM roastbeans ORDER BY registered_time DESC")
    LiveData<List<RoastBeans>> getAllRoastBeans();
    //getAllRoastBeansSortedByTime() is more suitable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertRoastBeans(RoastBeans roastbeans);

    @Update
    void updateRoastBeans(RoastBeans roastbeans);

    @Delete
    void deleteRoastBeans(RoastBeans roastbeans);

    @Query("DELETE FROM roastbeans")
    void deleteAllRoastBeans();
}

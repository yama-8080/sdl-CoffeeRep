package ymz.coffeerep.data.rawbeans;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RawBeansDAO {

    @Query("SELECT rawbeans_id FROM rawbeans WHERE rawbeans_name IN (:name)")
    int getRawBeansId(String name);

    @Query("SELECT * FROM rawbeans WHERE rawbeans_id = :id")
    LiveData<RawBeans> getRawBeans(int id);

    @Query("SELECT * FROM rawbeans ORDER BY rawbeans_name ASC")
    LiveData<List<RawBeans>> getAlphabetizedRawBeans();

    @Query("SELECT * FROM rawbeans ORDER BY registered_time DESC")
    LiveData<List<RawBeans>> getAllRawBeans();
    //getAllRawBeansSortedByTime() is more suitable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertRawBeans(RawBeans rawbeans);

    @Update
    void updateRawBeans(RawBeans rawbeans);

    @Delete
    void deleteRawBeans(RawBeans rawbeans);

    @Query("DELETE FROM rawbeans")
    void deleteAllRawBeans();
}

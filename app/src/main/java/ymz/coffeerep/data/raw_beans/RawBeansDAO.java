package ymz.coffeerep.data.raw_beans;

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

    @Query("SELECT * FROM rawbeans WHERE rawbeans_name IN (:name) AND registered_time IN(:time)")
    LiveData getRawBeans(String name, long time);

    @Query("SELECT rawbeans_id FROM rawbeans WHERE rawbeans_name IN (:name)")
    int getRawBeansId(String name);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertRawBeans(RawBeans rawbeans);

    @Update
    void updateRawBeans(RawBeans rawbeans);

    @Delete
    void deleteRawBeans(RawBeans rawbeans);
}

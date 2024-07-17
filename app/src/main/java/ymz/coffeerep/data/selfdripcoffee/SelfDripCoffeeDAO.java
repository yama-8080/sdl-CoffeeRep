package ymz.coffeerep.data.selfdripcoffee;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ymz.coffeerep.data.selfdripcoffee.SelfDripCoffee;

@Dao
public interface SelfDripCoffeeDAO {

    @Query("SELECT selfdripcoffee_id FROM selfdripcoffee WHERE selfdripcoffee_name IN (:name)")
    int getSelfDripCoffeeId(String name);

    @Query("SELECT * FROM selfdripcoffee WHERE selfdripcoffee_id = :id")
    LiveData<SelfDripCoffee> getSelfDripCoffee(int id);

    @Query("SELECT * FROM selfdripcoffee ORDER BY selfdripcoffee_name ASC")
    LiveData<List<SelfDripCoffee>> getAlphabetizedSelfDripCoffee();

    @Query("SELECT * FROM selfdripcoffee ORDER BY registered_time DESC")
    LiveData<List<SelfDripCoffee>> getAllSelfDripCoffee();
    //getAllSelfDripCoffeeSortedByTime() is more suitable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSelfDripCoffee(SelfDripCoffee selfdripcoffee);

    @Update
    void updateSelfDripCoffee(SelfDripCoffee selfdripcoffee);

    @Delete
    void deleteSelfDripCoffee(SelfDripCoffee selfdripcoffee);

    @Query("DELETE FROM selfdripcoffee")
    void deleteAllSelfDripCoffee();
}

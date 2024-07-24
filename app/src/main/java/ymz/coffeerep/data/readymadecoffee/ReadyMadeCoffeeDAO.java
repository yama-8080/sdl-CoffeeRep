package ymz.coffeerep.data.readymadecoffee;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ymz.coffeerep.data.readymadecoffee.ReadyMadeCoffee;

@Dao
public interface ReadyMadeCoffeeDAO {

    @Query("SELECT readymadecoffee_id FROM readymadecoffee WHERE readymadecoffee_name IN (:name)")
    int getReadyMadeCoffeeId(String name);

    @Query("SELECT * FROM readymadecoffee WHERE readymadecoffee_id = :id")
    LiveData<ReadyMadeCoffee> getReadyMadeCoffee(int id);

    @Query("SELECT * FROM readymadecoffee ORDER BY readymadecoffee_name ASC")
    LiveData<List<ReadyMadeCoffee>> getAlphabetizedReadyMadeCoffee();

    @Query("SELECT * FROM readymadecoffee ORDER BY registered_time DESC")
    LiveData<List<ReadyMadeCoffee>> getAllReadyMadeCoffee();
    //getAllReadyMadeCoffeeSortedByTime() is more suitable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertReadyMadeCoffee(ReadyMadeCoffee readymadecoffee);

    @Update
    void updateReadyMadeCoffee(ReadyMadeCoffee readymadecoffee);

    @Delete
    void deleteReadyMadeCoffee(ReadyMadeCoffee readymadecoffee);

    @Query("DELETE FROM readymadecoffee")
    void deleteAllReadyMadeCoffee();
}

package ymz.coffeerep.data.readymadecoffee;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ymz.coffeerep.data.readymadecoffee.ReadyMadeCoffee;
import ymz.coffeerep.data.readymadecoffee.ReadyMadeCoffeeDAO;
import ymz.coffeerep.data.readymadecoffee.ReadyMadeCoffeeDatabase;

@Database(entities = {ReadyMadeCoffee.class}, version = 1, exportSchema = false)
public abstract class ReadyMadeCoffeeDatabase extends RoomDatabase {

    public abstract ReadyMadeCoffeeDAO readymadecoffeeDao();

    private static volatile ReadyMadeCoffeeDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ReadyMadeCoffeeDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ReadyMadeCoffeeDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ReadyMadeCoffeeDatabase.class, "readymadecoffee_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onCreate method to populate the database.
     * For this sample, we clear the database every time it is created.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                ReadyMadeCoffeeDAO dao = INSTANCE.readymadecoffeeDao();
                dao.deleteAllReadyMadeCoffee();
            });
        }
    };
}

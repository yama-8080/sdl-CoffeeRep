package ymz.coffeerep.data.roastbeans;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ymz.coffeerep.data.roastbeans.RoastBeansDAO;
import ymz.coffeerep.data.roastbeans.RoastBeans;

@Database(entities = {RoastBeans.class}, version = 1, exportSchema = false)
abstract public class RoastBeansDatabase extends RoomDatabase {
    public abstract RoastBeansDAO roastbeansDao();

    private static volatile RoastBeansDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static RoastBeansDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoastBeansDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    RoastBeansDatabase.class, "roastbeans_db")
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
                RoastBeansDAO dao = INSTANCE.roastbeansDao();
                dao.deleteAllRoastBeans();
            });
        }
    };
}

package ymz.coffeerep.data.raw_beans;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {RawBeans.class}, version = 1, exportSchema = false)
public abstract class RawBeansDatabaseOld extends RoomDatabase {
    /*
    public abstract RawBeansDAO rawbeansDao();

    private static volatile RawBeansDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RawBeansDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    RawBeansDatabase.class, "rawbeans_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    */
}

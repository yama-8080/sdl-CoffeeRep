package ymz.coffeerep.data.raw_beans;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {RawBeans.class}, version = 1, exportSchema = false)
public abstract class RawBeansDatabaseOld extends RoomDatabase {

    public abstract RawBeansDAO rawbeansDao();

    private static volatile RawBeansDatabaseOld INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static RawBeansDatabaseOld getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RawBeansDatabaseOld.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    RawBeansDatabaseOld.class, "rawbeans_db")
                                    .build();
                }
            }
        }
        return INSTANCE;
    }

}

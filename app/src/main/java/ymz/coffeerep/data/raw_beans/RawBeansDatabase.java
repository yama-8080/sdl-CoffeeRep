package ymz.coffeerep.data.raw_beans;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {RawBeans.class}, version = 1)
public abstract class RawBeansDatabase extends RoomDatabase {
    public abstract RawBeansDAO rawbeansDao();
}

package ymz.coffeerep.repository.raw_beans;

public class RawBeansRepositoryOld {
    /*
    private RawBeansDAO mRawbeansDao;
    private LiveData<RawBeans> mRawbeans;
    private int flag = 0;

    public RawBeansRepositoryOld(Application application) {
        RawBeansDatabase db = RawBeansDatabase.getDatabase(application);
        mRawbeansDao = db.rawbeansDao();
    }

    public LiveData<RawBeans> getRawBeans(String name, long time){
        mRawbeans = mRawbeansDao.getRawBeans(name, time);
        return mRawbeans;
    }

    public void insert(RawBeans rawbeans){
        RawBeansDatabase.databaseWriteExecutor.execute(() -> {
            mRawbeansDao.insertRawBeans(rawbeans);
        });
    }
    */
}

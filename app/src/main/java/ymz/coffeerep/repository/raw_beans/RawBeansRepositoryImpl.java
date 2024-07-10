package ymz.coffeerep.repository.raw_beans;

import ymz.coffeerep.data.raw_beans.RawBeans;
import ymz.coffeerep.data.raw_beans.RawBeansDAO;

public class RawBeansRepositoryImpl implements RawBeansRepository{

    private RawBeansDAO rawbeansDao;

    public RawBeansRepositoryImpl(RawBeansDAO rawbeansDao){
        this.rawbeansDao = rawbeansDao;
    }

    @Override
    public void insert(String name, String country){
        long time = System.currentTimeMillis();
        RawBeans rawbeans = new RawBeans(name, country, time);
        //withContext(Dispatchers.IO){
        rawbeansDao.insertRawBeans(rawbeans);
        //}
    }
}

package ymz.coffeerep.repository.raw_beans;

import ymz.coffeerep.data.raw_beans.RawBeans;
import ymz.coffeerep.data.raw_beans.RawBeansDAO;
/*
public class RawBeansRepositoryOldImpl implements RawBeansRepositoryOld {
    private RawBeansDAO rawbeansDao;

    public RawBeansRepositoryOldImpl(RawBeansDAO rawbeansDao){
        this.rawbeansDao = rawbeansDao;
    }

    @Override
    public void insert(String name, String country){
        long time = System.currentTimeMillis();
        RawBeans rawbeans = new RawBeans(name, country, time);
        withContext(Dispatchers.IO){ //no idea to implement for Java...
        rawbeansDao.insertRawBeans(rawbeans);
        }
    }
}
*/

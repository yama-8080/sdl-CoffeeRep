package ymz.coffeerep.repository.rawbeans;

import ymz.coffeerep.data.rawbeans.RawBeans;
import ymz.coffeerep.data.rawbeans.RawBeansDAO;

public class RawBeansRepositoryOldImpl implements RawBeansRepositoryOld {

    private RawBeansDAO rawbeansDao;

    public RawBeansRepositoryOldImpl(RawBeansDAO rawbeansDao){
        this.rawbeansDao = rawbeansDao;
    }

    @Override
    public void insert(String name, String country){
        long time = System.currentTimeMillis();
        RawBeans rawbeans = new RawBeans(name, country, time);
        //withContext(Dispatchers.IO){
        rawbeansDao.insertRawBeans(rawbeans);
        //}
        //no idea to implement for Java...
    }
}

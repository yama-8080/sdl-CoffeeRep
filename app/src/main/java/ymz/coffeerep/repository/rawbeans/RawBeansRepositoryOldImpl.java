package ymz.coffeerep.repository.rawbeans;

import ymz.coffeerep.data.rawbeans.RawBeans;
import ymz.coffeerep.data.rawbeans.RawBeansDAO;

public class RawBeansRepositoryOldImpl implements RawBeansRepositoryOld {

    private RawBeansDAO _rawbeansDao;

    public RawBeansRepositoryOldImpl(RawBeansDAO rawbeansDao){
        this._rawbeansDao = rawbeansDao;
    }

    @Override
    public void insert(String name, String country){
        long time = System.currentTimeMillis();
        RawBeans rawbeans = new RawBeans(name, country, time);
        //withContext(Dispatchers.IO){
        _rawbeansDao.insertRawBeans(rawbeans);
        //}
        //no idea to implement for Java...
    }
}

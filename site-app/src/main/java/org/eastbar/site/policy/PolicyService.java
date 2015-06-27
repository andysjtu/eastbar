package org.eastbar.site.policy;

import org.eastbar.site.policy.dao.*;
import org.eastbar.site.policy.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by andysjtu on 2015/5/11.
 */
@Service
@Transactional
public class PolicyService {

    @Autowired
    private BanUrlDao banUrlDao;
    @Autowired
    private KeyWordDao keyWordDao;
    @Autowired
    private BanProgDao banProgDao;
    @Autowired
    private SpecialPersonDao specialPersonDao;
    @Autowired
    private PolicyVersionDao policyVersionDao;


    public void updateUrlPolicy(int urlNum, List<BanUrl> addList, List<BanUrl> updateList, List<BanUrl> deleteList) {
        SitePolicyVersion version = policyVersionDao.findOne(1);
        if (version == null) {
            version = new SitePolicyVersion();
            version.setId(1);
        }
        version.setUrlVersion(urlNum);
        policyVersionDao.save(version);

        if (addList != null && addList.size() > 0)
            banUrlDao.save(addList);
        if (updateList != null && updateList.size() > 0)
            banUrlDao.save(updateList);
        if (deleteList != null && deleteList.size() > 0)
            banUrlDao.delete(deleteList);
    }

    public void updateKwPolicy(int urlNum, List<KeyWord> addList, List<KeyWord> updateList, List<KeyWord> deleteList) {
        SitePolicyVersion version = policyVersionDao.findOne(1);
        if (version == null) {
            version = new SitePolicyVersion();
            version.setId(1);
        }
        version.setKwVersion(urlNum);
        policyVersionDao.save(version);

        if (addList != null && addList.size() > 0)
            keyWordDao.save(addList);
        if (updateList != null && updateList.size() > 0)
            keyWordDao.save(updateList);
        if (deleteList != null && deleteList.size() > 0)
            keyWordDao.delete(deleteList);
    }

    public void updateProgPolicy(int urlNum, List<BanProg> addList, List<BanProg> updateList, List<BanProg> deleteList) {
        SitePolicyVersion version = policyVersionDao.findOne(1);
        if (version == null) {
            version = new SitePolicyVersion();
            version.setId(1);
        }
        version.setPrgVersion(urlNum);
        policyVersionDao.save(version);

        if (addList != null && addList.size() > 0)
            banProgDao.save(addList);
        if (updateList != null && updateList.size() > 0)
            banProgDao.save(updateList);
        if (deleteList != null && deleteList.size() > 0)
            banProgDao.delete(deleteList);
    }

    public void updateSpPolicy(int urlNum, List<SpecialPerson> addList, List<SpecialPerson> updateList, List<SpecialPerson> deleteList) {
        SitePolicyVersion version = policyVersionDao.findOne(1);
        if (version == null) {
            version = new SitePolicyVersion();
            version.setId(1);
        }
        version.setSmVersion(urlNum);
        policyVersionDao.save(version);

        if (addList != null && addList.size() > 0)
            specialPersonDao.save(addList);
        if (updateList != null && updateList.size() > 0)
            specialPersonDao.save(updateList);
        if (deleteList != null && deleteList.size() > 0)
            specialPersonDao.delete(deleteList);
    }


}

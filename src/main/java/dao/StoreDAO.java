package dao;

import entity.Store;

public class StoreDAO extends AbstractHibernateDao<Store> {
    public StoreDAO() {
        super(Store.class);
    }

}

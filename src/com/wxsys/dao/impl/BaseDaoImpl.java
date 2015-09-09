package com.wxsys.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.wxsys.dao.BaseDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;



/**
 * Dao实现类 - Dao实现类基类
 */
public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {

    private Class<T> entityClass;
    protected SessionFactory sessionFactory;
    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseDaoImpl() {
        this.entityClass = null;
        Class c = getClass();
        Type type = c.getGenericSuperclass(); 
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            this.entityClass = (Class<T>) parameterizedType[0];
        }
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
	public T get(PK id) {
    	return (T) getSession().get(entityClass, id);
    }

	@SuppressWarnings("unchecked")
	public List<T> get(PK[] ids) {
        String hql = "from " + entityClass.getName() + " as model where model.id in(:ids)";
        return getSession().createQuery(hql).setParameterList("ids", ids).list();
    }

    @SuppressWarnings("unchecked")
	public T get(String propertyName, Object value) {
        String hql = "from " + entityClass.getSimpleName() + " as model where model." + propertyName + " = :propertyValue";
        return (T) getSession().createQuery(hql).setParameter("propertyValue", value).uniqueResult();
    }

    @SuppressWarnings("unchecked")
	public List<T> getList(String propertyName, Object value) {
        String hql = "from " + entityClass.getName() + " as model where model." + propertyName + " = :propertyValue";
        return getSession().createQuery(hql).setParameter("propertyValue", value).list();
    }

    public boolean isExist(String propertyName, Object value) {
        T object = get(propertyName, value);
        return (object != null);
    }

    public PK save(T entity) {
        getSession().save(entity);
        getSession().flush();
        return null;
    }

    public void update(T entity) {
        getSession().update(entity);
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    public void delete(PK id) {
        T entity = load(id);
        getSession().delete(entity);
    }

    public void delete(PK[] ids) {
        for (PK id : ids) {
            T entity = load(id);
            getSession().delete(entity);
        }
    }

	@SuppressWarnings("unchecked")
	public T load(PK id) {
		return (T) getSession().load(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> getList() {
        String hql = "from " + entityClass.getName() + " where 1=1";
        return getSession().createQuery(hql).list();
    }
	
	@Override
	public Map<String, Object> getList(Map<String, Object> map, Integer begin, Integer max,Boolean getCount,String ...option) {
		if(map==null||map.size()==0)return null;
		StringBuffer hql = new StringBuffer("from "+entityClass.getSimpleName()+" where ");
		Set<String> keySet=map.keySet();
		for(String key : keySet){
			hql.append(key+"=:"+key+" and ");
		}
		hql.append(" 1=1 ");
		if(option!=null&&option.length==2){
			hql.append(" order by "+option[0]+" "+option[1]);
		}
		Query query =getSession().createQuery(hql.toString());
		for(String key : keySet){
			query.setParameter(key,map.get(key));
		}
		
		Integer pageCount=null;
		if (getCount) {
			int size = query.list().size();
			if(size/(max+0.0)==size/max){
				pageCount = size/max;
			}else{
				pageCount = ((Long)Math.round(size/(max+0.0)+0.5)).intValue();
			}
		}
			
		if(begin!=null&&max!=null){
			query.setFirstResult(begin);
			query.setMaxResults(max);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("entityList",query.list());
		result.put("pageCount", pageCount);
		return result;
	}

	@Override
	public String[] getValueList(Map<String, Object> map,String propertyName,Integer begin,Integer max) {
		StringBuffer hql = new StringBuffer("select "+propertyName+" from "+entityClass.getSimpleName()+" where ");
		Set<String> keySet=map.keySet();
		for(String key : keySet){
			hql.append(key+"=:"+key+" and ");
		}
		hql.append(" 1=1 ");
		Query query =getSession().createQuery(hql.toString());
		for(String key : keySet){
			query.setParameter(key,map.get(key));
		}
		if(begin!=null&&max!=null){
			query.setFirstResult(begin);
			query.setMaxResults(max);
		}
		@SuppressWarnings("unchecked")
		List<Object> list = query.list();
		if(list==null||list.size()==0)return null;
		String[] s = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			s[i]=list.get(i).toString();
		}
		return s;
	}
    

}